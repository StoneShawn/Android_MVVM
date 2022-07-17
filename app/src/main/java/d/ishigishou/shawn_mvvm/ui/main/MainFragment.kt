package d.ishigishou.shawn_mvvm.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import d.ishigishou.shawn_mvvm.R
import d.ishigishou.shawn_mvvm.base.MyViewModelFactory
import d.ishigishou.shawn_mvvm.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        initView()
    }

    private fun initView() {

        viewModel.getUsers(0,20).observe(viewLifecycleOwner) {
            if (it.first !=null){

            }

        }

    }

}