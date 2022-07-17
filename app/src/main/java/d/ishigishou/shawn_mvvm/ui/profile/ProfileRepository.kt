package d.ishigishou.shawn_mvvm.ui.profile

import com.haroldadmin.cnradapter.NetworkResponse
import d.ishigishou.shawn_mvvm.api.ApiService
import d.ishigishou.shawn_mvvm.models.ErrorResponse
import d.ishigishou.shawn_mvvm.models.UserModel

class ProfileRepository(private val api: ApiService) {

    suspend fun getUser(name: String): Pair<UserModel?,ErrorResponse?>{
        return when(val response = api.getUser(name)){
            is NetworkResponse.Success ->{
                Pair(response.body,null)
            }
            is NetworkResponse.ServerError ->{
                Pair(null,ErrorResponse(400,"Server Error"))
            }
            is NetworkResponse.NetworkError ->{
                Pair(null,ErrorResponse(400,"Network Error"))
            }
            is NetworkResponse.UnknownError ->{
                Pair(null,ErrorResponse(400,"Unknown Error"))
            }
        }
    }
}