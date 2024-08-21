package com.emptyprojectt1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.UserListScreen
import com.emptyprojectt1.databinding.FragmentFirstBinding
import com.login.loginevent.LoginEvent
import com.login.viewModel.LoginViewModel
import com.login2.viewModel.Login2ViewModel
import com.utils.extensionFunctions.showToast
import com.utils.flowCollector.collectFlow
import com.utils.sharedPrefs.AppPrefs
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginScreen : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: LoginViewModel
    private lateinit var viewModel2: Login2ViewModel

    @Inject
    lateinit var appPrefs: AppPrefs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        viewModel2 = ViewModelProvider(this)[Login2ViewModel::class.java]
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (appPrefs.getValue("token") != "") {
            navigateToNextScreen()
        }

        setListeners()
        viewModel2.login()

        collectFlow(viewModel.loginUI) {
            print("viewModel.loginUI $it")
        }

        viewModel.login.observe(viewLifecycleOwner) {
            print("viewModel.loginUI $it")
            binding.progressBar.isVisible = it.isLoading
            if (it.success) {
                navigateToNextScreen()
            }
        }

        viewModel.alertMsg.observe(viewLifecycleOwner) {
            showToast(it)
        }
    }

    private fun navigateToNextScreen() {
        startActivity(Intent(requireActivity(), UserListScreen::class.java))
        requireActivity().finish()
    }

    private fun setListeners() {
        binding.apply {
            email.addTextChangedListener { email ->
                email?.apply {
                    val event = LoginEvent.onEmailEvent(this.toString())
                    viewModel.onEvent(event)
                }
            }
            password.addTextChangedListener { password ->
                password?.apply {
                    val event = LoginEvent.onPasswordEvent(this.toString())
                    viewModel.onEvent(event)
                }
            }

            btnLogin.setOnClickListener {
                val event = LoginEvent.onLoginButtonClicked
                viewModel.onEvent(event)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}