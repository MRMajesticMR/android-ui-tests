package club.dari.androiduitests.api.cats

import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import io.reactivex.Single
import retrofit2.http.GET

interface CatsApi {

    /**
     * Get facts about cats
     */
    @GET("facts")
    fun getFacts(): Single<List<CatFactCatsApiObject>>


}
