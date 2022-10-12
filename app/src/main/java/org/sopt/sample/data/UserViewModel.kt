package org.sopt.sample.data

import androidx.lifecycle.ViewModel
import org.sopt.sample.R

class UserViewModel: ViewModel() {
    val mockUserList = listOf<User>(
        User(
            img = R.drawable.ic_profile,
            id = "Wonyong",
            pwd = "password",
            mbti = "ISTJ",
        ),
        User(
            img = R.drawable.ic_profile,
            id = "Yubin",
            pwd = "password",
            mbti = "ISFJ",
        ),
        User(
            img = R.drawable.ic_profile,
            id = "Sumin",
            pwd = "password",
            mbti = "ISTP",
        ),
        User(
            img = R.drawable.ic_profile,
            id = "Chaeyeon",
            pwd = "password",
            mbti = "INFJ",
        ),
        User(
            img = R.drawable.ic_profile,
            id = "Anonymous",
            pwd = "password",
            mbti = "ENFP",
        ),
    )
}