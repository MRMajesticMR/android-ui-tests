package club.dari.androiduitests.model.data.cat_facts.cloud

import club.dari.androiduitests.api.cats.CatsApi
import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import io.kotest.core.spec.style.StringSpec
import io.reactivex.Single
import org.mockito.internal.verification.Times
import org.mockito.kotlin.*

class CatsFactsCloudDataSourceImplTest : StringSpec() {

    private val mockFacts = listOf(
        CatFactCatsApiObject(
            status = CatFactCatsApiObject.Status(
                verified = true,
                sentCount = 1,
                feedback = ""
            ),
            type = "cat",
            deleted = false,
            text = "Cats make about 100 different sounds. Dogs make only about 10.",
            createdAt = "2020-09-03T16:39:39.578Z",
            updatedAt = "2018-01-15T21:20:00.003Z",
            used = true
        )
    )

    private val mockApiAnswer = Single.just(mockFacts)

    private val mockCatApi = mock<CatsApi>() {
        on { getFacts() } doReturn mockApiAnswer
    }

    private val catsFactFromCatsApiObjectMapper = mock<CatsFactFromCatsApiObjectMapper>()

    init {

        "getCatFacts" {
            //Init
            val catsFactsCloudDataSourceImpl = CatsFactsCloudDataSourceImpl(
                catsApi = mockCatApi,
                catsFactFromCatsApiObjectMapper = catsFactFromCatsApiObjectMapper
            )


            //Action
            catsFactsCloudDataSourceImpl.getCatFacts()
                .test()
                .assertNoErrors()
                .assertValueCount(mockFacts.size)
                .dispose()

            //Result
            verify(mockCatApi, Times(1)).getFacts()
            verify(catsFactFromCatsApiObjectMapper, Times(mockFacts.size)).map(any())

            verifyNoMoreInteractions(mockCatApi)
            verifyNoMoreInteractions(catsFactFromCatsApiObjectMapper)
        }

    }

}
