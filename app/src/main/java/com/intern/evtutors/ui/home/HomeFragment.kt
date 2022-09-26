package com.intern.evtutors.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.intern.evtutors.base.fragment.BaseFragment
import com.intern.evtutors.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var dataBinding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentHomeBinding.inflate(inflater)
        dataBinding.lifecycleOwner = viewLifecycleOwner

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.lesson.observe(viewLifecycleOwner) {
            listLesson ->
            if(listLesson.isNotEmpty()) {
                dataBinding.loadingStatement.isVisible = false
                dataBinding.classList.adapter = HomeAdapter(activity, context, listLesson)
            }
            else {
                dataBinding.loadingStatement.text = "Chưa đăng ký" //Handle situation that do not have any lesson
            }
        }
    }
}