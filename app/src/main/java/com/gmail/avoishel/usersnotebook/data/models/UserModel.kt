package com.gmail.avoishel.usersnotebook.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "users"
)
data class UserModel(
    @PrimaryKey( autoGenerate = true )
    var dbKey: Int? = null,

    val id: Int?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?
) : Serializable
