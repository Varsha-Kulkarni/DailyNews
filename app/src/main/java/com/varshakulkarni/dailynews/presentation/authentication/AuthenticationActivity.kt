package com.varshakulkarni.dailynews.presentation.authentication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ActivityAuthenticationBinding
import com.varshakulkarni.dailynews.presentation.news.NewsActivity

const val SIGN_IN_CODE = 123
const val TAG = "AuthenticationActivity"

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)

        setupUI()
    }

    private fun setupUI() {
        binding.buttonLogin.setOnClickListener() {
            launchSignInFlow()
        }
    }

    private fun launchSignInFlow() {

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers)
                .build(), SIGN_IN_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SIGN_IN_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                startActivity(Intent(this, NewsActivity::class.java))
            } else {
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
                return
            }
        }
    }
}
