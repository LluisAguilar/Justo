package com.android.code.challenge.justo.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.android.code.challenge.justo.R
import com.android.code.challenge.justo.data.retrofit.response.Result
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class UserProfileRecyclerAdapter(var usersList: ArrayList<Result>, val supportMapFragment: FragmentManager) : RecyclerView.Adapter<UserProfileRecyclerAdapter.UserListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_information_item, parent, false)
        return UserListViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(usersList.get(position), supportMapFragment)
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    fun updateUserData(userList: ArrayList<Result>){
        usersList = userList
        notifyDataSetChanged()
    }


    class UserListViewHolder(view: View) : RecyclerView.ViewHolder(view), OnMapReadyCallback {
        private var mLatitude: Double = 0.0
        private var mLongitude: Double = 0.0
        private val mPhone: TextView = view.findViewById(R.id.phone_number_tv)

        fun bind(user:Result, supportMapFragment: FragmentManager){
            mPhone.text = user.phone
            mLatitude = user.location.coordinates.latitude.toDouble()
            mLongitude = user.location.coordinates.longitude.toDouble()

            val mapFragment = (supportMapFragment.findFragmentById(R.id.location_map_fg) as SupportMapFragment?)!!
            mapFragment.getMapAsync(this)
        }

        override fun onMapReady(googleMap: GoogleMap?) {
            val marker = LatLng(mLatitude, mLongitude)
            googleMap!!.addMarker(MarkerOptions().position(marker).title("Here"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15f))
        }

    }

}