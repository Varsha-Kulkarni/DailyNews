package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksViewModel
import com.google.firebase.auth.FirebaseAuth


class AuthenticationViewModel(initialState: AuthenticationState) :
    MavericksViewModel<AuthenticationState>(initialState) {
    init {
        setState {
            copy(userState =  if (FirebaseAuth.getInstance().currentUser != null) {
                UserState.AUTHENTICATED
            } else
                UserState.UNAUTHENTICATED)
        }
    }
}