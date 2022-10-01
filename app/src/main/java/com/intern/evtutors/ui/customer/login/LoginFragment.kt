package com.intern.evtutors.ui.customer.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intern.evtutors.base.activities.BaseActivity
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.databinding.FragmentJsonLoginBinding
import com.intern.evtutors.databinding.FragmentRegisterBinding
import com.intern.evtutors.ui.customer.register.RegisterFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment: BaseFragment() {
    private val viewModel by viewModels<RegisterViewModel>()

    private lateinit var dataBingding: FragmentJsonLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.fetchDataLogin(dataBingding.UserName.text.toString(),dataBingding.passWord.text.toString())
    }

    override fun showErrorMessage(message: String){
        val activity = requireActivity()
        if (activity is BaseActivity) {
            activity.showErrorDialog("Fail")
        }
    }
    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBingding= FragmentJsonLoginBinding.inflate(inflater)
        dataBingding.lifecycleOwner= viewLifecycleOwner
        return dataBingding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

       dataBingding.LoginUser.setOnClickListener {
           if(dataBingding.UserName.text.toString().isNotEmpty() && dataBingding.passWord.text.toString()
                   .isNotEmpty()
           ) {
               viewModel.fetchDataLogin(
                   dataBingding.UserName.text.toString(),
                   dataBingding.passWord.text.toString()
               )
           }
       }

      dataBingding.createActivity.setOnClickListener {
          val action = RegisterFragmentDirections.actionRegisterFragmentToRegisterFragment2()
          findNavController().navigate(action)
      }

        registerAllExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel,viewLifecycleOwner)
        viewModel.listPots.observe(viewLifecycleOwner){
                posts ->
            if(posts !=null){
                val titles = posts.jwtToken
                Toast.makeText(context,"Welcome", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}