package com.varshakulkarni.dailynews.presentation.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.withState
import com.airbnb.mvrx.activityViewModel
import com.firebase.ui.auth.AuthUI
import com.varshakulkarni.dailynews.R
import com.varshakulkarni.dailynews.databinding.ActivityAuthenticationBinding
import com.varshakulkarni.dailynews.presentation.news.NewsActivity

const val TAG = "AuthenticationActivity"

class AuthenticationActivity : AppCompatActivity(), MavericksView {
    private lateinit var binding: ActivityAuthenticationBinding

    //throwing error Unresolved Reference for activityViewModel:
    // None of the following candidates is applicable because of receiver type mismatch..
    private val authViewModel: AuthenticationViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setupUI()
    }

    private fun setupUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
        binding.buttonLogin.setOnClickListener() {
            launchSignInFlow()
        }
    }

    private fun launchSignInFlow() {

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                when (result.resultCode) {
                    RESULT_OK -> {
                        startActivity(Intent(this, NewsActivity::class.java))
                    }
                    RESULT_CANCELED -> {

                    }
                    else -> {
                    }
                }
            }
        AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build()
            .apply {
                startForResult.launch(this)
            }

    }

    override fun invalidate() = withState(authViewModel) { state ->
        when (state.userState) {
            UserState.AUTHENTICATED -> {
                startActivity(Intent(this, NewsActivity::class.java))
                finish()
            }
            UserState.UNAUTHENTICATED -> {
                binding = DataBindingUtil.setContentView(this, R.layout.activity_authentication)
                binding.buttonLogin.setOnClickListener {
                    launchSignInFlow()
                }
            }
        }
    }
}
