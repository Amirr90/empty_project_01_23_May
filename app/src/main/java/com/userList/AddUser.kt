package com.userList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.emptyprojectt1.R
import com.emptyprojectt1.databinding.ActivityAddUserBinding
import com.emptyprojectt1.databinding.ActivityUserListScreenBinding
import com.login.loginevent.LoginEvent
import com.utils.db.room.RoomViewModel
import com.utils.db.room.UserState
import com.utils.db.room.roomEvent.AddUserEvent
import com.utils.flowCollector.collectFlow
import com.utils.network.ApiEvents
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "AddUser"

@AndroidEntryPoint
class AddUser : AppCompatActivity() {
    private lateinit var userListViewModel: RoomViewModel
    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        userListViewModel = ViewModelProvider(this)[RoomViewModel::class.java]
        setContentView(binding.root)

    }


    override fun onStart() {
        super.onStart()
        setListerens()


        collectFlow(userListViewModel.roomEvent) {
            binding.userError = it
            Log.d(TAG, "roomEvent: $it")
            setError(it)
        }

        collectFlow(userListViewModel.roomChannel) {
            Log.d(TAG, "roomChannel: $it")
            when (it) {
                is ApiEvents.Failure<*> ->
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_SHORT).show()

                is ApiEvents.Loading -> binding.progressBar2.isVisible = it.loading

                is ApiEvents.Success<*> -> {
                    Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    private fun setError(it: UserState) {
        binding.apply {
            textInputLayoutFname.error = it.fNameError
            textInputLayoutLname.error = it.lNameError
            age.error = it.ageError
            address.error = it.addressError
            city.error = it.cityError
        }
    }

    private fun setListerens() {
        binding.apply {

            fName.addTextChangedListener { value ->
                value?.apply {
                    val event = AddUserEvent.FNameChanged(this.toString())
                    userListViewModel.onEvent(event)
                }
            }

            lName.addTextChangedListener { value ->
                value?.apply {
                    val event = AddUserEvent.LNameChanged(this.toString())
                    userListViewModel.onEvent(event)
                }
            }

            age.addTextChangedListener { value ->
                value?.apply {
                    val event = AddUserEvent.AgeChanged(this.toString())
                    userListViewModel.onEvent(event)
                }
            }
            address.addTextChangedListener { value ->
                value?.apply {
                    val event = AddUserEvent.AddressChanged(this.toString())
                    userListViewModel.onEvent(event)
                }
            }
            city.addTextChangedListener { value ->
                value?.apply {
                    val event = AddUserEvent.CityChanged(this.toString())
                    userListViewModel.onEvent(event)
                }
            }


            btnAddNewUser.setOnClickListener {
                val event = AddUserEvent.SaveUser
                userListViewModel.onEvent(event)
            }

        }
    }
}