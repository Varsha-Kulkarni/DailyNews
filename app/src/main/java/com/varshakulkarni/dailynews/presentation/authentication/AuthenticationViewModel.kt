package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksViewModel
import com.google.firebase.auth.FirebaseAuth

/**
 *   This ViewModel class updates the Authentication state,
 *   In the init block, state changes are subscribed.
 */
class AuthenticationViewModel(initialState: AuthenticationState) :
    MavericksViewModel<AuthenticationState>(initialState) {
    init {
        setState {
            copy(
                userState = if (FirebaseAuth.getInstance().currentUser != null) {
                    UserState.AUTHENTICATED
                } else
                    UserState.UNAUTHENTICATED
            )
        }
    }
}