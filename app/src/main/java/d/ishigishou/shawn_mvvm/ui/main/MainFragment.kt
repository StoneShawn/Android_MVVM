package d.ishigishou.shawn_mvvm.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import d.ishigishou.shawn_mvvm.R
import d.ishigishou.shawn_mvvm.base.MyViewModelFactory
import d.ishigishou.shawn_mvvm.databinding.FragmentMainBinding
import d.ishigishou.shawn_mvvm.models.UserListModel
import d.ishigishou.shawn_mvvm.ui.adapters.MainAdapter
import d.ishigishou.shawn_mvvm.utils.Constants
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main), MainAdapter.Interaction {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var mAdapter: MainAdapter


    /**
     * RecyclerView LoadMore
     */
    private var itemCount: Int = 0
    private var lastPosition: Int = 0
    private var lastItemCount: Int = 0
    lateinit var lastPositionData: UserListModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentMainBinding.bind(view)
        mAdapter = MainAdapter(this)
        initView()
        getData()
    }

    private fun initView() {

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                    itemCount = linearLayoutManager.itemCount
                    lastPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                    if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
                        lastItemCount = itemCount

                        //max size 100
                        if (lastItemCount < 100) {
                            getLoadMoreData()
                        }
                    }

                }
            })
        }


    }

    private fun getData() {
        viewModel.getUsers(0, 20).observe(viewLifecycleOwner) {
            if (it.first != null) {
                val list = it.first
                list?.let { data ->
                    mAdapter.submitList(data)
                    lastPositionData = data[data.size-1]
                }
            }

        }
    }


    override fun onItemSelected(position: Int, item: UserListModel) {
        val bundle = Bundle()
        bundle.putString(Constants.BUNDLE_USER_NAME,item.login)
        findNavController().navigate(R.id.action_mainFragment_to_profileFragment,bundle)
    }

    fun getLoadMoreData() {
        viewModel.getUsers(lastPositionData.id, 20).observe(viewLifecycleOwner) {
            if (it.first != null) {
                val list = it.first
                list?.let { data ->
                    mAdapter.submitList(data)
                }
            }
        }
    }

}