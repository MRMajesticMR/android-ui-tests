package club.dari.androiduitests.model.data.cat_facts.local

import club.dari.androiduitests.model.data.cat_facts.CatFact
import io.kotest.core.spec.style.StringSpec
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.matchers.shouldBe

class CatsFactsLocalDataSourceImplTest : StringSpec() {

    private val mockFacts = listOf(
        CatFact(
            status = CatFact.Status(
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
        ),
        CatFact(
            status = CatFact.Status(
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

    private lateinit var catsFactsLocalDataSourceImpl: CatsFactsLocalDataSourceImpl

    override fun beforeTest(testCase: TestCase) {
        catsFactsLocalDataSourceImpl = CatsFactsLocalDataSourceImpl()
    }

    override fun afterTest(testCase: TestCase, result: TestResult) {
        catsFactsLocalDataSourceImpl.clearAll()
    }


    init {

        "saveFacts" {
            getCachedCatFacts().size shouldBe 0

            catsFactsLocalDataSourceImpl.saveFacts(mockFacts)

            val cachedFacts = getCachedCatFacts()

            println("Cached values:")
            cachedFacts.forEach { println(it.toString()) }

            cachedFacts.size shouldBe mockFacts.size

            cachedFacts.forEachIndexed { index, catFact ->
                catFact shouldBe mockFacts[index]
            }
        }

        "saveFacts when double save then all values saved" {
            getCachedCatFacts().size shouldBe 0

            catsFactsLocalDataSourceImpl.saveFacts(mockFacts)
            catsFactsLocalDataSourceImpl.saveFacts(mockFacts)

            val cachedFacts = getCachedCatFacts()

            println("Cached values:")
            cachedFacts.forEach { println(it.toString()) }

            cachedFacts.size shouldBe (mockFacts.size * 2)

            cachedFacts.forEachIndexed { index, catFact ->
                catFact shouldBe mockFacts[index % 2]
            }
        }

        "getFacts" {
            saveCatFactsDirectInCache(mockFacts)

            val cachedFacts = catsFactsLocalDataSourceImpl.getFacts()

            println("Cached values:")
            cachedFacts.forEach { println(it.toString()) }

            cachedFacts.size shouldBe mockFacts.size

            cachedFacts.forEachIndexed { index, catFact ->
                catFact shouldBe mockFacts[index]
            }
        }

        "clearAll" {
            saveCatFactsDirectInCache(mockFacts)

            val cachedFacts = catsFactsLocalDataSourceImpl.getFacts()

            println("Cached values:")
            cachedFacts.forEach { println(it.toString()) }

            cachedFacts.size shouldBe mockFacts.size

            catsFactsLocalDataSourceImpl.clearAll()

            val factsAfterClear = catsFactsLocalDataSourceImpl.getFacts()

            println("Cached values after clear:")
            factsAfterClear.forEach { println(it.toString()) }

            factsAfterClear.size shouldBe 0
        }

    }

    private fun getCachedCatFacts(): List<CatFact> {
        val cachedFactsField =
            CatsFactsLocalDataSourceImpl::class.java.getDeclaredField("cachedFacts")
        cachedFactsField.isAccessible = true

        return cachedFactsField.get(catsFactsLocalDataSourceImpl) as List<CatFact>
    }

    private fun saveCatFactsDirectInCache(facts: List<CatFact>) {
        val cachedFactsField =
            CatsFactsLocalDataSourceImpl::class.java.getDeclaredField("cachedFacts")
        cachedFactsField.isAccessible = true

        (cachedFactsField.get(catsFactsLocalDataSourceImpl) as MutableList<CatFact>).addAll(facts)
    }

}
