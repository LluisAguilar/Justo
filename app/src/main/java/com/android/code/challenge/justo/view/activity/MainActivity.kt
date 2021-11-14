package com.android.code.challenge.justo.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.justo.R
import com.android.code.challenge.justo.domain.model.Result
import com.android.code.challenge.justo.view.viewmodel.UserProfileViewModel
import com.android.code.challenge.justo.view.adapter.UserProfileRecyclerAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUserProfileViewModel: UserProfileViewModel
    private lateinit var mUserAdapter: UserProfileRecyclerAdapter
    private var reload = false
    private var hasRequestPending = false
    private lateinit var disposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserProfileViewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)

        disposables = CompositeDisposable()

        next_fab.setOnClickListener(this)
        close_iv.setOnClickListener(this)

        requestUserProfile()
        mUserProfileViewModel.getUserProfileResponseRx()


    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun requestUserProfile() {
        val singleObserverDisposable = mUserProfileViewModel.userProfileResult
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("UserProfile received in activity")
                if (it?.picture?.large?.isNotEmpty() == true){
                    if (!reload) {
                        setDataToViews(it)
                        reload = true
                    } else {
                        reloadUser(it)
                    }
                    hasRequestPending = false
                }
            }, this::onError)

        disposables.add(singleObserverDisposable)
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

    private fun onError(throwable: Throwable) {
        Log.d("onError", "OnError in Observable Time: $throwable")
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
                    requestUserProfile()
                    mUserProfileViewModel.getUserProfileResponseRx()
                }
            }
            R.id.close_iv -> {
                finish()
            }

        }
    }

}