package com.intern.evtutors.ui.personal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.databinding.FragmentPersonalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalFragment:BaseFragment() {
    private lateinit var dataBinding:FragmentPersonalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
        dataBinding = FragmentPersonalBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner
        return dataBinding.root
    }
}