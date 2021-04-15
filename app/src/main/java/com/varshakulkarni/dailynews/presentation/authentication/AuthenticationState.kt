package com.varshakulkarni.dailynews.presentation.authentication

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.PersistState

enum class UserState{
    AUTHENTICATED, UNAUTHENTICATED
}

data class AuthenticationState(@PersistState val userState: UserState): MavericksState