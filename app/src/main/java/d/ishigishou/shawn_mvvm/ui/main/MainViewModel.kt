package d.ishigishou.shawn_mvvm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import d.ishigishou.shawn_mvvm.base.BaseViewModel
import d.ishigishou.shawn_mvvm.models.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(mainRepository: MainRepository) : BaseViewModel() {

    private val repos = mainRepository

    val userList = MutableLiveData<ArrayList<String>>()


    fun getUsers(since: Int, per_page: Int): LiveData<Pair<ArrayList<String>?,ErrorResponse?>>{
        return liveData(Dispatchers.Main){
            val response = repos.getUsers(since,per_page)
            emit(response)
        }
    }
//    fun getUsers(since: Int, per_page: Int){
//        job = CoroutineScope(Dispatchers.IO).launch {
//            loading.postValue(true)
//            val response = mainRepository.getUsers(since,per_page)
//            withContext(Dispatchers.Main){
//                if (response.isSuccessful){
//                    userList.postValue(response.body())
//                    loading.value = false
//                }else{
//                    onError("Error : ${response.message()} ")
//                }
//
//            }
//        }
//    }



}