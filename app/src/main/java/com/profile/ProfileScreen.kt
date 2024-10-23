package com.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emptyprojectt1.databinding.ProfileFragmentBinding
import com.profile.adapters.ProfileAdapter
import com.profile.adapters.ProfileOption

class ProfileScreen : Fragment() {
    private lateinit var binding: ProfileFragmentBinding
    private val profileOptions = mutableListOf<ProfileOption>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = ProfileFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpProfileData()
        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            btnLogout.setOnClickListener {

            }
        }
    }

    private fun setUpProfileData() {

        profileOptions.apply {
            add(ProfileOption("My orders", "Already have 12 orders"))
            add(ProfileOption("Shipping addresses", "3 addresses"))
            add(ProfileOption("Payment methods", "Visa **34"))
            add(ProfileOption("PromoCodes", "You have special promoCodes"))
            add(ProfileOption("My reviews", "Reviews for 4 items"))
            add(ProfileOption("Settings", "Notifications, password"))
        }

        val adapter = ProfileAdapter(requireActivity(), profileOptions)
        binding.userDataRec.adapter = adapter
    }
}