package club.dari.androiduitests.model.data.cat_facts.remote

import club.dari.androiduitests.api.ApisProviderImpl
import club.dari.androiduitests.api.cats.CatsApi
import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import club.dari.androiduitests.model.utils.MockResponseFileReader
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.reactivex.Single
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.mockito.internal.verification.Times
import org.mockito.kotlin.*
import retrofit2.HttpException
import java.net.HttpURLConnection

class CatsFactsRemoteDataSourceImplTest : StringSpec() {

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

    private lateinit var mockCatApi: CatsApi

    private lateinit var mockWebServer: MockWebServer
    private lateinit var catsApi: CatsApi

    private lateinit var catsFactFromCatsApiObjectMapper: CatsFactFromCatsApiObjectMapper

    private lateinit var catsFactsCloudDataSourceImpl: CatsFactsCloudDataSourceImpl

    override fun beforeTest(testCase: TestCase) {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        mockCatApi = mock {
            on { getFacts() } doReturn mockApiAnswer
        }
        catsApi = ApisProviderImpl(
            baseUrl = mockWebServer.url("").toString()
        ).catsApi

        catsFactFromCatsApiObjectMapper = mock()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        mockWebServer.shutdown()
    }

    init {
        "getCatFacts actions" {
            //Init
            catsFactsCloudDataSourceImpl = CatsFactsCloudDataSourceImpl(
                catsApi = mockCatApi,
                catsFactFromCatsApiObjectMapper = catsFactFromCatsApiObjectMapper
            )


            //Action
            catsFactsCloudDataSourceImpl.getCatFacts()
                .test()
                .dispose()

            //Result
            verify(mockCatApi, Times(1)).getFacts()
            verify(catsFactFromCatsApiObjectMapper, Times(mockFacts.size)).map(any())

            verifyNoMoreInteractions(mockCatApi)
            verifyNoMoreInteractions(catsFactFromCatsApiObjectMapper)
        }

        "getCatFacts with success response" {
            //Init
            val reader = MockResponseFileReader("cats-api-responses/get-facts(success).json")

            println("Mock response:")
            println(reader.content)

            val response = MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(reader.content)

            mockWebServer.enqueue(response)

            catsFactsCloudDataSourceImpl = CatsFactsCloudDataSourceImpl(
                catsApi = catsApi,
                catsFactFromCatsApiObjectMapper = catsFactFromCatsApiObjectMapper
            )

            //Action
            val testObserver = catsFactsCloudDataSourceImpl.getCatFacts()
                .test()

            testObserver.awaitTerminalEvent()

            testObserver
                .assertNoErrors()
                .dispose()

            //Result
            verify(catsFactFromCatsApiObjectMapper, Times(6)).map(any())

            verifyNoMoreInteractions(catsFactFromCatsApiObjectMapper)
        }

        "getCatFacts with error response" {
            //Init
            mockWebServer.enqueue(
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            )

            catsFactsCloudDataSourceImpl = CatsFactsCloudDataSourceImpl(
                catsApi = catsApi,
                catsFactFromCatsApiObjectMapper = catsFactFromCatsApiObjectMapper
            )

            //Action
            val testObserver = catsFactsCloudDataSourceImpl.getCatFacts()
                .test()

            testObserver.awaitTerminalEvent()

            testObserver
                .assertError { it is HttpException }
                .dispose()

            //Result
            verifyZeroInteractions(catsFactFromCatsApiObjectMapper)
        }

    }

}
