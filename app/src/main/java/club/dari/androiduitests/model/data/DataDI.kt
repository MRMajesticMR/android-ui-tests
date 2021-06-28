package club.dari.androiduitests.model.data

import club.dari.androiduitests.api.ApisProvider
import club.dari.androiduitests.model.data.cat_facts.remote.CatsFactFromCatsApiObjectMapper
import club.dari.androiduitests.model.data.cat_facts.remote.CatsFactsCloudDataSource
import club.dari.androiduitests.model.data.cat_facts.remote.CatsFactsCloudDataSourceImpl
import org.koin.dsl.module

val dataModule = module {

    factory { CatsFactFromCatsApiObjectMapper() }

    factory<CatsFactsCloudDataSource> {
        CatsFactsCloudDataSourceImpl(
            catsApi = get<ApisProvider>().catsApi,
            catsFactFromCatsApiObjectMapper = get()
        )
    }

}