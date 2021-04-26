package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksState

enum class UserState {
    AUTHENTICATED, UNAUTHENTICATED
}

/**
 *   This models the Authentication screen as a function of AuthenticationState
 */
data class AuthenticationState(val userState: UserState = UserState.UNAUTHENTICATED) :
    MavericksState