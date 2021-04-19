package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksState

enum class UserState{
    AUTHENTICATED, UNAUTHENTICATED
}

data class AuthenticationState(val userState: UserState = UserState.UNAUTHENTICATED): MavericksState