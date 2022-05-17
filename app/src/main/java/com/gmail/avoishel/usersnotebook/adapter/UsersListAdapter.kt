package com.gmail.avoishel.usersnotebook.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gmail.avoishel.usersnotebook.R
import com.gmail.avoishel.usersnotebook.models.UserModel
import com.squareup.picasso.Picasso

class UsersListAdapter(): RecyclerView.Adapter<UsersListAdapter.UserItemViewHolder>() {

    private var userList: List<UserModel>? = null
    private var onItemClickListener: ((UserModel) -> Unit)? = null

    fun setUserList(userList: List<UserModel>?){
        this.userList = userList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.UserItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return UserItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.UserItemViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.apply {
            firstNameView.text = user.first_name
            lastNameView.text = user.last_name

            Picasso.get()
                .load(user.avatar)
                //.placeholder(R.drawable.ic_placeholder_foreground)
                //.error(R.drawable.ic_placeholder_error_foreground)
                .into(imgView)

            itemView.setOnClickListener {
            //setOnItemClickListener {
                onItemClickListener?.let{ it(user) }
            }

        }
    }

    override fun getItemCount(): Int {
//        if (userList == null) return 0
//        else return userList?.size!!
        return differ.currentList.size
    }

    fun setOnItemClickListener (listener: (UserModel) -> Unit){
        onItemClickListener = listener
    }



    inner class UserItemViewHolder(view : View): RecyclerView.ViewHolder(view){
        val firstNameView = view.findViewById<TextView>(R.id.firstName)
        val lastNameView = view.findViewById<TextView>(R.id.lastName)
        val imgView = view.findViewById<ImageView>(R.id.userImage)

//        init {
//            view.setOnClickListener {
//                Log.i("TAG", "_----_---> CLICK!!!")
//                //onItemClickListener?.invoke(userList[adapterPosition])
//            }
//        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)



//    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
//        val firstNameView = view.findViewById<TextView>(R.id.firstName)
//        val lastNameView = view.findViewById<TextView>(R.id.lastName)
//        val imgView = view.findViewById<ImageView>(R.id.userImage)
//
//
//        fun bind(data: UserModel,  activity: Activity){
//            firstNameView.text = data.first_name
//            lastNameView.text = data.last_name
//
//            Picasso.get()
//                .load(data.avatar)
//                //.placeholder(R.drawable.ic_placeholder_foreground)
//                //.error(R.drawable.ic_placeholder_error_foreground)
//                .into(imgView)
//        }
//    }
}