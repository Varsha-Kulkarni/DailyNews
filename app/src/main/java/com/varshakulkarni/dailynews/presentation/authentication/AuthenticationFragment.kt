package com.varshakulkarni.dailynews.presentation.authentication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.firebase.ui.auth.AuthUI
import com.varshakulkarni.dailynews.databinding.FragmentAuthenticationBinding
import com.varshakulkarni.dailynews.presentation.news.NewsActivity

class AuthenticationFragment : Fragment(), MavericksView {
    private var _binding: FragmentAuthenticationBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val authViewModel: AuthenticationViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)

        _binding = FragmentAuthenticationBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    }

    private fun launchSignInFlow() {

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(), AuthUI.IdpConfig.GoogleBuilder().build()
        )
        val startForResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                when (result.resultCode) {
                    AppCompatActivity.RESULT_OK -> {
                        startActivity(Intent(activity, NewsActivity::class.java))
                    }
                    AppCompatActivity.RESULT_CANCELED -> {

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
                startActivity(Intent(activity, NewsActivity::class.java))
            }
            UserState.UNAUTHENTICATED -> {
                binding.buttonLogin.setOnClickListener {
                    launchSignInFlow()
                }
            }
        }
    }
}