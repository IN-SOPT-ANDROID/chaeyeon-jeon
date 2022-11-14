package org.sopt.sample.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ResponseGetFollowerListDTO

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseGetFollowerListDTO.Follower>()
}