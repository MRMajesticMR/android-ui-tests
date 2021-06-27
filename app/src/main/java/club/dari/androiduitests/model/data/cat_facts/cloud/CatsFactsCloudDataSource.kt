package club.dari.androiduitests.model.data.cat_facts.cloud

import club.dari.androiduitests.api.ApisProvider
import club.dari.androiduitests.api.cats.CatsApi
import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import club.dari.androiduitests.model.data.cat_facts.CatFact
import io.reactivex.Single
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject

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