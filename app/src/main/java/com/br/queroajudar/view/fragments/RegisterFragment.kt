package com.br.queroajudar.view.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.br.queroajudar.R
import com.br.queroajudar.databinding.FragmentRegisterBinding
import com.br.queroajudar.viewmodel.RegisterViewModel

/**
 * A simple [Fragment] subclass.
 */
class RegisterFragment : Fragment() {

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this).get(RegisterViewModel::class.java)
    }

    private lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        binding.viewModel = registerViewModel
        binding.lifecycleOwner = this

        setApiStatusObserver()

        return binding.root
    }

    private fun setApiStatusObserver(){
//        registerViewModel.apiStatus.observe(viewLifecycleOwner, Observer<QueroAjudarApiStatus>{ status ->
//            Timber.i("API status changed")
//
//            if(status == QueroAjudarApiStatus.LOADING){
//                showLoadingOverlay()
//            }
//            else {
//                hideLoadingOverlay()
//
//                if(status == QueroAjudarApiStatus.NETWORK_ERROR){
//                    showNetworkErrorMessage()
//                }
//                else if(status == QueroAjudarApiStatus.DONE){
//                    goToCausesFragment()
//                }
//            }
//        })
    }

    private fun showLoadingOverlay(){
        //binding.registerOverlayLoading.visibility = View.VISIBLE}
    }
    private fun hideLoadingOverlay(){
        //binding.registerOverlayLoading.visibility = View.GONE
    }

    private fun showNetworkErrorMessage(){
        Toast.makeText(context, R.string.error_connection, Toast.LENGTH_LONG).show()
    }

    private fun goToCausesFragment(){
        findNavController().navigate(R.id.action_registerDataFragment_to_homeActivity)
    }
}
