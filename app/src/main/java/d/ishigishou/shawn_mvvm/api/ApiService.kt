package d.ishigishou.shawn_mvvm.api

import com.haroldadmin.cnradapter.NetworkResponse
import d.ishigishou.shawn_mvvm.models.ErrorResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun getUsers(@Query("since") since: Int,@Query("per_page") par_page: Int): NetworkResponse<ArrayList<String>,ErrorResponse>
}