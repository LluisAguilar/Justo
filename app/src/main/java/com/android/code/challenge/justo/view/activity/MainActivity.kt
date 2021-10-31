package com.android.code.challenge.justo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.justo.R
import com.android.code.challenge.justo.domain.model.Result
import com.android.code.challenge.justo.view.viewmodel.UserProfileViewModel
import com.android.code.challenge.justo.view.adapter.UserProfileRecyclerAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserProfileViewModel: UserProfileViewModel
    private lateinit var mUserAdapter: UserProfileRecyclerAdapter
    private var reload = false
    private var hasRequestPending = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserProfileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        next_fab.setOnClickListener(this)
        close_iv.setOnClickListener(this)
        setUserProfileResultObserver()
        requestUserProfile()


    }

    private fun setUserProfileResultObserver() {
        mUserProfileViewModel.userProfileResult.observe(this, { result ->
            if (!reload) {
                setDataToViews(result)
            } else {
                reloadUser(result)
                reload = false
            }
            hasRequestPending = false
        })

        mUserProfileViewModel.userProfileError.observe(this, { error ->

            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()

        })
    }

    private fun requestUserProfile() {
        mUserProfileViewModel.getUserProfileResponse()
        hasRequestPending = true
    }

    private fun setDataToViews(results: Result) {
        setHeaderData(results)
        mUserAdapter = UserProfileRecyclerAdapter(this, results, supportFragmentManager)
        user_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        user_recycler_view.adapter = mUserAdapter
    }

    private fun reloadUser(results: Result){
        setHeaderData(results)
        mUserAdapter.updateUserData(results)
    }

    private fun setHeaderData(results: Result){
        Picasso.get().load(results.picture.large).into(user_image_iv)
        user_name_tv.setText(String.format(results.name.title + " " + results.name.first + " " + results.name.last))
        user_gender_tv.setText(String.format(getString(R.string.gender) + " " + results.gender))
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {

            R.id.next_fab -> {
                if (!hasRequestPending){
                    reload = true
                    requestUserProfile()
                }
            }
            R.id.close_iv -> {
                finish()
            }

        }
    }

}