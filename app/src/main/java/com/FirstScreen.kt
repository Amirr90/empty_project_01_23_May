package com

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.bluetooth.le.ScanCallback
import android.content.Context
import android.content.Intent
import android.net.wifi.ScanResult
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emptyprojectt1.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FirstScreen : AppCompatActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter

    private var bluetoothGatt: BluetoothGatt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)


        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Log.d(TAG, "bluetoothAdapter: device not found")
        }

        // Check if Bluetooth is enabled
        if (!bluetoothAdapter.isEnabled) {
            // Request to enable Bluetooth
            Log.d(TAG, "bluetoothAdapter: device not found")

        }

    }


    @SuppressLint("MissingPermission")
    private fun startScan() {
        val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner

        val scanCallback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: android.bluetooth.le.ScanResult?) {
                super.onScanResult(callbackType, result)
                val device = result?.device
                // Do something with the device (e.g., display it in a list)
            }

            override fun onBatchScanResults(results: MutableList<android.bluetooth.le.ScanResult>?) {
                super.onBatchScanResults(results)
                for (result in results!!) {
                    val device = result.device.address

                    // Do something with the device (e.g., display it in a list)
                }
            }

            override fun onScanFailed(errorCode: Int) {
                super.onScanFailed(errorCode)
                // Handle the error
            }
        }

        bluetoothLeScanner.startScan(scanCallback)
    }

    @SuppressLint("MissingPermission")
    private fun connectToDevice(device: BluetoothDevice) {
        bluetoothGatt = device.connectGatt(this, false, object : BluetoothGattCallback() {
            override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
                super.onConnectionStateChange(gatt, status, newState)
                if (newState == BluetoothProfile.STATE_CONNECTED) {
                    // Connected to the device, discover services
                    gatt.discoverServices()
                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                    // Disconnected from the device
                }
            }

            override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
                super.onServicesDiscovered(gatt, status)
                if (status == BluetoothGatt.GATT_SUCCESS) {
                    // Services discovered, you can interact with them
                }
            }

            // Add other callbacks as needed (e.g., for reading/writing characteristics)
        })

    }

    @SuppressLint("MissingPermission")
    private fun stopScan() {
        val bluetoothLeScanner = bluetoothAdapter.bluetoothLeScanner
        bluetoothLeScanner.stopScan(scanCallback)
    }

    @SuppressLint("MissingPermission")
    private fun disconnect() {
        bluetoothGatt?.disconnect()
        bluetoothGatt?.close()
        bluetoothGatt = null
    }

    private val scanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: android.bluetooth.le.ScanResult?) {
            super.onScanResult(callbackType, result)
            val device = result!!.device

            // Do something with the device (e.g., display it in a list)
            // Handle the single scan result (e.g., display it in a list)

        }

        override fun onBatchScanResults(results: MutableList<android.bluetooth.le.ScanResult>?) {
            super.onBatchScanResults(results)
            for (result in results!!) {
                val device = result.device
                Log.d(TAG, "onBatchScanResults: " + device.address)
                // Do something with the device (e.g., display it in a list)
                // Handle each scan result in the batch (e.g., display them in a list)


            }
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            // Handle the error
        }
    }
}

