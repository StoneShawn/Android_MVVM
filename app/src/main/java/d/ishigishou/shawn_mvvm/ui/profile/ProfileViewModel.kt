package d.ishigishou.shawn_mvvm.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import d.ishigishou.shawn_mvvm.models.ErrorResponse
import d.ishigishou.shawn_mvvm.models.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(profileRepository: ProfileRepository): ViewModel() {
    private val repos = profileRepository
    val userDataResponse: MutableLiveData<Pair<UserModel?, ErrorResponse?>> = MutableLiveData()



//    fun getUser(name: String){
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = repos.getUser(name)
//            withContext(Dispatchers.Main){
//                userDataResponse.value = response
//            }
//        }
//    }
//
    fun getUser(name: String): LiveData<Pair<UserModel?,ErrorResponse?>>{
        return liveData(Dispatchers.Main){
            val response = repos.getUser(name)
            emit(response)
        }

    }
}