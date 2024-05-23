package com

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ActivityMainBinding
import com.emptyprojectt1.databinding.ActivityUserListScreenBinding
import com.userList.AddUser
import com.userList.controller.UserListController
import com.utils.db.room.RoomViewModel
import com.utils.flowCollector.collectFlow
import com.utils.sharedPrefs.AppPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val TAG = "UserListScreen"

@AndroidEntryPoint
class UserListScreen : AppCompatActivity() {
    private lateinit var binding: ActivityUserListScreenBinding
    private lateinit var controller: UserListController
    private lateinit var userListViewModel: RoomViewModel


    @Inject
    lateinit var allPrefs: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListScreenBinding.inflate(layoutInflater)
        userListViewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        bindController()
        collectFlow(userListViewModel.users) {
            Log.d(TAG, "userListViewModel.users: $it")
            controller.setData(it)
        }

        binding.buttonAddNewUser.setOnClickListener {
            startActivity(Intent(this, AddUser::class.java))
        }


        binding.buttonLogout.setOnClickListener {
            allPrefs.saveValue("token", "")
            startActivity(Intent(this, FirstScreen::class.java))
            finish()
        }
    }


    private fun bindController() {
        controller = UserListController()
        binding.epoxyRec.setController(controller)
    }
}