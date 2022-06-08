package com.gmail.avoishel.usersnotebook.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "users"
)
data class UserModel(
//    @PrimaryKey( autoGenerate = true )
//    var dbKey: Int? = null,

    @PrimaryKey
    val id: Int?,
    val email: String?,
    val first_name: String?,
    val last_name: String?,
    val avatar: String?,

    var favorite: Boolean
) : Serializable
//todo а почему не parcelable // ---> Согласен, parcelable быстрее, но было проще реализовать )
