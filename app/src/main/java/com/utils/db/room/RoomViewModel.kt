package com.utils.db.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.userList.model.User
import com.utils.db.room.dao.RoomDao
import com.utils.db.room.repo.UserRepo
import com.utils.db.room.roomEvent.AddUserEvent
import com.utils.network.ApiEvents
import com.utils.useCases.validateAddress
import com.utils.useCases.validateAge
import com.utils.useCases.validateCity
import com.utils.useCases.validateFName
import com.utils.useCases.validateLName
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val db: UserRepo,
    dao: RoomDao,
) : ViewModel() {

    private val _roomChannel = Channel<ApiEvents>()
    val roomChannel = _roomChannel.receiveAsFlow()

    private var _roomEvent = MutableStateFlow(UserState())
    val roomEvent = _roomEvent


    fun onEvent(event: AddUserEvent) {
        when (event) {
            is AddUserEvent.AddressChanged -> _roomEvent.update {
                it.copy(
                    address = event.address
                )
            }

            is AddUserEvent.FNameChanged -> _roomEvent.update {
                it.copy(
                    fName = event.fname
                )
            }

            AddUserEvent.SaveUser -> saveCustomer()
            is AddUserEvent.AgeChanged -> _roomEvent.update {
                it.copy(
                    age = event.age
                )
            }

            is AddUserEvent.CityChanged -> _roomEvent.update {
                it.copy(
                    city = event.city
                )
            }

            is AddUserEvent.LNameChanged -> _roomEvent.update {
                it.copy(
                    lName = event.lName
                )
            }

        }
    }


    val users = dao.fetchUser()

    private fun saveCustomer() {
        val fName = _roomEvent.value.fName.validateFName()
        val lastName = _roomEvent.value.lName.validateLName()
        val age = _roomEvent.value.age.validateAge()
        val city = _roomEvent.value.city.validateCity()
        val address = _roomEvent.value.address.validateAddress()

        val hasError = listOf(fName, lastName, age, city, address).any {
            !it.success
        }
        _roomEvent.update {
            it.copy(
                fNameError = if (hasError) fName.error else null,
                lNameError = if (hasError) lastName.error else null,
                ageError = if (hasError) age.error else null,
                cityError = if (hasError) city.error else null,
                addressError = if (hasError) address.error else null,
            )
        }

        if (hasError)
            return

        viewModelScope.launch {
            _roomChannel.send(ApiEvents.Loading(true))
            delay(2000)
            val userSaved = db.saveUser(
                User(
                    fName = _roomEvent.value.fName,
                    lName = _roomEvent.value.lName,
                    age = _roomEvent.value.age,
                    address = _roomEvent.value.address,
                    cityName = _roomEvent.value.city,
                )
            )
            when (userSaved) {
                true -> {
                    _roomChannel.send(ApiEvents.Loading(false))
                    _roomChannel.send(ApiEvents.Success("user save successfully !!"))
                }

                false -> {
                    _roomChannel.send(ApiEvents.Loading(false))
                    _roomChannel.send(ApiEvents.Failure(data = null, "unable to save user!!"))
                }
            }

        }
    }
}

data class UserState(
    val fName: String = "",
    val fNameError: String? = null,
    val lName: String = "",
    val lNameError: String? = null,
    val address: String = "",
    val addressError: String? = null,
    val city: String = "",
    val cityError: String? = null,
    val age: String = "",
    val ageError: String? = null,
)