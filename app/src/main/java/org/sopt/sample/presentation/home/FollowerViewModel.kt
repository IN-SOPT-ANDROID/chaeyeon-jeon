package org.sopt.sample.presentation.home

import androidx.lifecycle.ViewModel
import org.sopt.sample.data.remote.ResponseGetFollowerListDto

class FollowerViewModel : ViewModel() {
    val followerList = mutableListOf<ResponseGetFollowerListDto.Follower>()
}
