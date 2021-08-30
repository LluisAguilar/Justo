package com.android.code.challenge.justo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.justo.R
import com.android.code.challenge.justo.data.retrofit.response.Result
import com.android.code.challenge.justo.domain.viewmodel.UserProfileViewModel
import com.android.code.challenge.justo.view.adapter.UserProfileRecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserProfileViewModel: UserProfileViewModel
    private lateinit var mUserAdapter: UserProfileRecyclerAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserProfileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        next_fab.setOnClickListener(this)
        close_iv.setOnClickListener(this)

        requestUserProfile()

    }

    private fun requestUserProfile(reload:Boolean = false) {

        mUserProfileViewModel.getUserProfile().observe(this, {

            if (it.status) {
                it.results?.let {
                    if (!reload) {
                        setDataToViews(it as ArrayList<Result>)
                    } else {
                        reloadUser(it as ArrayList<Result>)
                    }
                }
            }

        })

    }

    private fun setDataToViews(results: ArrayList<Result>) {
        setHeaderData(results)
        mUserAdapter = UserProfileRecyclerAdapter(results, supportFragmentManager)
        user_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        user_recycler_view.adapter = mUserAdapter
    }

    private fun reloadUser(results: ArrayList<Result>){
        setHeaderData(results)
        mUserAdapter.updateUserData(results)
    }

    private fun setHeaderData(results: ArrayList<Result>){
        Picasso.get().load(results.get(0).picture.large).into(user_image_iv)
        user_name_tv.setText(String.format(results.get(0).name.title + " " + results.get(0).name.first + " " + results.get(0).name.last))
        user_gender_tv.setText(String.format(getString(R.string.gender) + " " + results.get(0).gender))
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.next_fab -> {
                requestUserProfile(true)
            }
            R.id.close_iv -> {
                finish()
            }

        }
    }

}