package com.intern.evtutors.ui.customer.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intern.evtutors.base.activities.BaseActivity
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.databinding.FragmentRegisterBinding
import com.intern.evtutors.ui.customer.login.RegisterFragmentDirections
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RegisterFragment: BaseFragment() {
    private val viewModel by viewModels<RegisterViewModel>()

    private lateinit var dataBingding: FragmentRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel.fetchData()
    }


    override fun onCreateView(

        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBingding= FragmentRegisterBinding.inflate(inflater)
        dataBingding.lifecycleOwner= viewLifecycleOwner
        return dataBingding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        dataBingding.createAccount.setOnClickListener {
            if(dataBingding.RegisterTeacher.isChecked){
                viewModel.fetchRegisterTeacher(
                    dataBingding.email.text.toString(),
                    dataBingding.pass.text.toString())
            }
            if (dataBingding.Registerstudent.isChecked){
                viewModel.fetchRegisterStudent(
                    dataBingding.email.text.toString(),
                    dataBingding.pass.text.toString())
            }
        }


        registerAllExceptionEvent(viewModel,viewLifecycleOwner)
        registerObserverLoadingEvent(viewModel,viewLifecycleOwner)
        viewModel.listPots.observe(viewLifecycleOwner){
                posts ->
            if(posts !=null){
                val titles = posts.name.toString()
                Toast.makeText(context,"Wellcome "+titles, Toast.LENGTH_SHORT).show()
                val action = com.intern.evtutors.ui.customer.register.RegisterFragmentDirections.actionRegisterFragment2ToRegisterFragment()
                findNavController().navigate(action)
               // dataBingding.text.text = titles
            }
            else{
                Toast.makeText(context,"Fail", Toast.LENGTH_SHORT).show()
            }
        }
    }
}