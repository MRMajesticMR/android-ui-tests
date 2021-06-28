package club.dari.androiduitests.model.data.cat_facts.remote

import club.dari.androiduitests.api.cats.CatsApi
import club.dari.androiduitests.model.data.cat_facts.CatFact
import io.reactivex.Single

interface CatsFactsCloudDataSource {

    /**
     * Get cats facts
     */
    fun getCatFacts(): Single<List<CatFact>>

}

class CatsFactsCloudDataSourceImpl(
    private val catsApi: CatsApi,
    private val catsFactFromCatsApiObjectMapper: CatsFactFromCatsApiObjectMapper
) : CatsFactsCloudDataSource {


    override fun getCatFacts(): Single<List<CatFact>> =
        catsApi.getFacts()
            .map {
                it.map(catsFactFromCatsApiObjectMapper::map)
            }


}