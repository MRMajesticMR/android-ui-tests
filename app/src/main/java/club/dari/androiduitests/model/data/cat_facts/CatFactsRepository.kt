package club.dari.androiduitests.model.data.cat_facts

import io.reactivex.Single

interface CatFactsRepository {

    /**
     * Load cat facts from cloud
     */
    fun loadCatFactsFromCloud(): Single<List<CatFact>>

}

class CatFactsRepositoryImpl : CatFactsRepository {

    //todo:
    override fun loadCatFactsFromCloud(): Single<List<CatFact>> =
        Single.just(emptyList())

}