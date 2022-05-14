package com.gmail.avoishel.usersnotebook.adapter

import android.app.Activity
import android.app.TabActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.data.UserModel
import com.squareup.picasso.Picasso

class UsersListAdapter(val activity: Activity): RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {

    private var userList: List<UserModel>? = null

    fun setUserList(userList: List<UserModel>?){
        this.userList = userList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.MyViewHolder, position: Int) {
        holder.bind(userList?.get(position)!!, activity)
    }

    override fun getItemCount(): Int {
        if (userList == null) return 0
        else return userList?.size!!
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        val firstNameView = view.findViewById<TextView>(R.id.firstName)
        val lastNameView = view.findViewById<TextView>(R.id.lastName)
        val imgView = view.findViewById<ImageView>(R.id.userImage)


        fun bind(data: UserModel,  activity: Activity){
            firstNameView.text = data.first_name
            lastNameView.text = data.last_name

            Picasso.get()
                .load(data.avatar)
                //.placeholder(R.drawable.ic_placeholder_foreground)
                //.error(R.drawable.ic_placeholder_error_foreground)
                .into(imgView)
        }
    }
}