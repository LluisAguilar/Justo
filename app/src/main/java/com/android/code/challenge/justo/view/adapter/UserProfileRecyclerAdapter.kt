package com.android.code.challenge.justo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.justo.R
import com.android.code.challenge.justo.domain.model.Result
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserProfileRecyclerAdapter(val context:Context, var user: Result, val supportMapFragment: FragmentManager) : RecyclerView.Adapter<UserProfileRecyclerAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_information_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(context, user, supportMapFragment)
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateUserData(user: Result){
        this.user = user
        notifyDataSetChanged()
    }


    class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view), OnMapReadyCallback {
        private var mLatitude: Double = 0.0
        private var mLongitude: Double = 0.0
        private lateinit var mContext: Context

        private val mPhone: TextView = view.findViewById(R.id.phone_number_tv)
        private val mMobile: TextView = view.findViewById(R.id.mobile_phone_number_tv)
        private val mEmail: TextView = view.findViewById(R.id.user_email_tv)
        private val mUser: TextView = view.findViewById(R.id.user_name_tv)
        private val mPassword: TextView = view.findViewById(R.id.user_password_tv)
        private val mStreet: TextView = view.findViewById(R.id.street_tv)
        private val mCity: TextView = view.findViewById(R.id.city_tv)
        private val mState: TextView = view.findViewById(R.id.state_tv)
        private val mCountry: TextView = view.findViewById(R.id.country_tv)

        fun bind(context:Context, user:Result, supportMapFragment: FragmentManager){
            mContext = context
            mPhone.text = user.phone
            mMobile.text = user.cell
            mEmail.text = user.email
            mUser.text = user.login.username
            mPassword.text = user.login.password
            mStreet.text = user.location.street.name
            mCity.text = user.location.city
            mState.text = user.location.state
            mCountry.text = user.location.country

            mLatitude = user.location.coordinates.latitude.toDouble()
            mLongitude = user.location.coordinates.longitude.toDouble()

            val mapFragment = (supportMapFragment.findFragmentById(R.id.location_map_fg) as SupportMapFragment?)!!
            mapFragment.getMapAsync(this)
        }

        override fun onMapReady(googleMap: GoogleMap?) {
            val marker = LatLng(mLatitude, mLongitude)
            googleMap!!.addMarker(MarkerOptions().position(marker).title(mContext.getString(R.string.here)))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))
        }

    }

}