package com.utils.db.room.roomEvent

sealed class AddUserEvent {
    data class FNameChanged(val fname: String) : AddUserEvent()
    data class LNameChanged(val lName: String) : AddUserEvent()
    data class AddressChanged(val address: String) : AddUserEvent()
    data class AgeChanged(val age: String) : AddUserEvent()
    data class CityChanged(val city: String) : AddUserEvent()
    object SaveUser : AddUserEvent()
}
