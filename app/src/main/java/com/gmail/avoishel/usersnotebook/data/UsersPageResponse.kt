package com.gmail.avoishel.usersnotebook.data

import android.icu.text.IDNA

class UsersPageRespose(
val info: IDNA.Info = IDNA.Info(),
val result: List<GetCharacterByIdResponse> = emptyList()
) {
    data class Info(
        val page: Int = 0,
        val per_page: Int = 0,
        val total: String? = null,
        val prev: String? = null
    ){
    }
}