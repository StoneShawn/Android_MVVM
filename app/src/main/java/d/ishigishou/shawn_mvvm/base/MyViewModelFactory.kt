package d.ishigishou.shawn_mvvm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import d.ishigishou.shawn_mvvm.ui.main.MainRepository

class MyViewModelFactory<T : ViewModel?>(private val listener: ()->T?= { null }): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return listener() as? T ?: modelClass.newInstance()
    }
}