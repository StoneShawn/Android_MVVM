package d.ishigishou.shawn_mvvm.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import d.ishigishou.shawn_mvvm.R
import d.ishigishou.shawn_mvvm.databinding.FragmentProfileBinding
import d.ishigishou.shawn_mvvm.models.UserModel
import d.ishigishou.shawn_mvvm.utils.Constants
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    private var userName : String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

        arguments?.let {
            userName = it.getString(Constants.BUNDLE_USER_NAME)!!
        }
        getData(userName)
    }

    private fun getData(name: String){
        viewModel.getUser(name).observe(viewLifecycleOwner){
            if(it.first !=null){
                val list = it.first
                list?.let { data ->
                    setData(data)
                }
            }
        }
    }


    fun setData(model : UserModel){
        binding.tvName.text = model.name
        binding.tvBio.text = model.bio
        binding.tvLogin.text = model.login
        binding.tvLocation.text = model.location
        binding.tvLink.text = model.html_url

        binding.tvBadge.visibility = if(model.site_admin!!) View.VISIBLE else View.GONE

        Glide.with(this)
            .load(model.avatar_url)
            .circleCrop()
            .into(binding.ivAvatar)

        binding.viewClose.setOnClickListener {
            findNavController().popBackStack()
        }

    }

}