package com.utils.extensionFunctions

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.util.Log
import android.view.WindowInsets
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.File
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun Fragment.showToast(msg: String) {
    Toast.makeText(requireActivity(), msg, Toast.LENGTH_SHORT).show()
}

fun showDatePicker(callBack: (Long) -> Unit): MaterialDatePicker<Long> {
    val picker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds()).build()


    picker.addOnPositiveButtonClickListener {
        callBack.invoke(it)
    }
    return picker
}

fun Fragment.goBack() {
    findNavController().navigateUp()
}

fun Fragment.createImageUri(id: String): Uri {
    requireActivity().apply {
        val image = File(applicationContext.filesDir, "$id-${System.currentTimeMillis()}.png")
        return FileProvider.getUriForFile(
            applicationContext, "com.udhaarwala.utils.fileProvider", image
        )
    }

}









fun date(): String {
    val currentDate = getCurrentDate()
    val formattedDate = formatDate(currentDate)
    Log.d("TAG Date", "date: $formattedDate")
    return formattedDate
}

private fun getCurrentDate(): Date {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        LocalDate.now().toDate()
    } else {
        Date()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun LocalDate.toDate(): Date {
    return Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())
}

private fun formatDate(date: Date): String {
    val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return dateFormat.format(date)
}

fun convertTimestampToTime(timestamp: Long): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = timestamp
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val time = String.format("%02d:%02d", hour, minute)
    Log.d("TAG", "convertTimestampToTime: $time")
    return time
}




fun getHoursFromTimestamp(timestamp: Long): Int {
    val calendar = Calendar.getInstance()
    calendar.time = Timestamp(timestamp)
    return calendar.get(Calendar.HOUR_OF_DAY)
}

@SuppressLint("WrongConstant")
fun Fragment.updateStatusBarColor(color: Int) {
    // Set status bar color
    requireActivity().apply {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.setSystemBarsAppearance(
                0, WindowInsets.Type.statusBars()
            )
            window.statusBarColor = getColor(color)
        } else {
            window.statusBarColor = getColor(color)
        }
    }

}

inline fun <reified T> JSONArray.jsonArrayToModelList(): List<T> {
    val gson = Gson()
    val list = mutableListOf<T>()

    for (i in 0 until this.length()) {
        val jsonObject = this.getJSONObject(i)
        val person = gson.fromJson<T>(jsonObject.toString())
        list.add(person)
    }

    return list
}


inline fun <reified T> Gson.fromJson(json: String): T {
    return fromJson(json, object : TypeToken<T>() {}.type)
}

fun getHrMinSec(seconds: Long): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60

    println("$seconds seconds is equal to $hours hours and $minutes minutes.")
    return when {
        minutes > 0 && hours <= 0 -> {
            val min = if (minutes in 1..9) "0$minutes" else "$minutes"

            "00:${min}:${seconds % 60}"
        }

        minutes > 0 && hours > 0 -> {
            val hr = if (hours in 1..9) "0$hours" else "$hours"
            "$hr:$minutes:${seconds % 360}"
        }

        else -> {
            val sec = if (seconds in 1..9) "0$seconds" else "$seconds"
            "00:00:$sec"
        }

    }


}

fun Fragment.updateTitle(title: String) {
    (activity as AppCompatActivity?)!!.supportActionBar!!.title = title
}



