package com.gmail.avoishel.usersnotebook.models

import java.io.Serializable

data class UserModel(
    val id: Int?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?
) : Serializable
//todo а почему не parcelable
