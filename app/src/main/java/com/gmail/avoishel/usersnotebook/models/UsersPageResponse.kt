package com.gmail.avoishel.usersnotebook.models



data class UsersPageResponse(

    val page: Int = 0,
    val per_page: Int = 0,
    val total: Int = 0,
    val total_pages: Int = 0,
    val data: MutableList<UserModel>
)