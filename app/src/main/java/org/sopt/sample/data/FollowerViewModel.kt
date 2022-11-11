package org.sopt.sample.data

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ResponseGetFollowerListDTO

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseGetFollowerListDTO.Follower>()

    companion object {
        const val PREF_FILE_NAME = "IN_SOPT"
        const val PREF_USER_ID = "USER_ID"
        const val PREF_USER_PWD = "USER_PWD"
    }
}