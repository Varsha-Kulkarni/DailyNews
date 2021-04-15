package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthenticationViewModel(state: AuthenticationState) :
    MavericksViewModel<AuthenticationState>(state) {
    init {
        val firebaseAuth = FirebaseAuth.getInstance()
        setState {
            copy(userState =  if (firebaseAuth.currentUser != null) {
                UserState.AUTHENTICATED
            } else
                UserState.UNAUTHENTICATED)
        }
    }
}