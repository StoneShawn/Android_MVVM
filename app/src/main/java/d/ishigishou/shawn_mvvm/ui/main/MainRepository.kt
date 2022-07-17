package d.ishigishou.shawn_mvvm.ui.main

import com.haroldadmin.cnradapter.NetworkResponse
import d.ishigishou.shawn_mvvm.api.ApiService
import d.ishigishou.shawn_mvvm.models.ErrorResponse
import d.ishigishou.shawn_mvvm.network.RetrofitManager

class MainRepository(private val api: ApiService) {

    suspend fun getUsers(since: Int, per_page: Int): Pair<ArrayList<String>?, ErrorResponse?> {
        return when (val response = api.getUsers(since, per_page)) {
            is NetworkResponse.Success -> {
                Pair(response.body, null)
            }
            is NetworkResponse.ServerError -> {
                Pair(null, ErrorResponse(400, "Server Error"))
            }
            is NetworkResponse.NetworkError -> {
                Pair(null, ErrorResponse(400, "NetWork Error"))
            }
            is NetworkResponse.UnknownError -> {
                Pair(null, ErrorResponse(400, "UnKnown Error"))
            }
        }
    }
}