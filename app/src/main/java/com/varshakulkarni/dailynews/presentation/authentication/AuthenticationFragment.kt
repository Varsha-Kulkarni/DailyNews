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

/**
 *  Fragment to handle SignIn methods and authentication
 *
 *  Implements MavericksView method invalidate() to check the authentication state of the user
 *
 *  If user is already authenticated, launch NewsActivity
 *  If not authenticated, request for Google SignIn or Email
 */

class AuthenticationFragment : Fragment(), MavericksView {
    private var _binding: FragmentAuthenticationBinding? = null
    private val binding get() = _binding ?: error("null Binding")

    private val authViewModel: AuthenticationViewModel by activityViewModel()

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                launchNewsActivity()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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

        AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build()
            .apply {
                startForResult.launch(this)
            }
    }

    override fun invalidate() = withState(authViewModel) { state ->
        when (state.userState) {
            UserState.AUTHENTICATED -> {
                launchNewsActivity()
            }
            UserState.UNAUTHENTICATED -> {
                binding.buttonLogin.setOnClickListener {
                    launchSignInFlow()
                }
            }
        }
    }

    private fun launchNewsActivity() {
        startActivity(Intent(activity, NewsActivity::class.java))
        activity?.finish()
    }
}