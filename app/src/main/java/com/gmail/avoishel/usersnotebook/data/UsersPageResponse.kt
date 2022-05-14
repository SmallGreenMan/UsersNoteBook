package com.gmail.avoishel.usersnotebook.data

data class UsersPageResponse(

    val page: Int = 0,
    val per_page: Int = 0,
    val total: Int = 0,
    val total_pages: Int = 0,
    val data: List<UserModel> = emptyList()
)