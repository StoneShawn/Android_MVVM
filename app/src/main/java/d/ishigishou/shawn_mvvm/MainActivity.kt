package d.ishigishou.shawn_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import d.ishigishou.shawn_mvvm.databinding.ActivityMainBinding
import d.ishigishou.shawn_mvvm.ui.main.MainFragment
import d.ishigishou.shawn_mvvm.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

//    private fun setupNavController(){
//        val navControl = findNavController(this@MainActivity,R.id.container_fragment)
//
//        binding.boot
//    }
}