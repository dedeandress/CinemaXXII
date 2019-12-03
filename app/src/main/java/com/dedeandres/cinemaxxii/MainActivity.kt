package com.dedeandres.cinemaxxii

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dedeandres.cinemaxxii.di.DaggerCoreComponent
import com.dedeandres.cinemaxxii.di.DaggerFeatureComponent
import com.dedeandres.cinemaxxii.di.util.CoreInjectHelper
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {

    companion object{
        val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerFeatureComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .build()
            .inject(this)
//        FirebaseInstanceId.getInstance().instanceId
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    Log.w(TAG, "getInstanceId failed", task.exception)
//                    return@OnCompleteListener
//                }
//
//                // Get new Instance ID token
//                val token = task.result?.token
//                Log.d(TAG, "token : ${token.toString()}")
//                // Log and toast
////                val msg = getString(R.string.msg_token_fmt, token)
////                Log.d(TAG, msg)
////                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
//            })
    }
}
