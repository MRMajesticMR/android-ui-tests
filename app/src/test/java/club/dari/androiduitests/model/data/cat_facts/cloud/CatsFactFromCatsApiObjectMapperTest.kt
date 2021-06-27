package club.dari.androiduitests.model.data.cat_facts.cloud

import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CatsFactFromCatsApiObjectMapperTest : StringSpec({

    "map" {
        //Init
        val mapper = CatsFactFromCatsApiObjectMapper()

        val apiObject = CatFactCatsApiObject(
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

        println("CatFactCatsApiObject:")
        println(apiObject.toString())

        //Action
        val result = mapper.map(apiObject)

        println("CatFact:")
        println(result.toString())

        //Result
        result.status.verified shouldBe true
        result.status.sentCount shouldBe 1
        result.status.feedback shouldBe ""

        result.type shouldBe "cat"
        result.deleted shouldBe false
        result.text shouldBe "Cats make about 100 different sounds. Dogs make only about 10."
        result.createdAt shouldBe "2020-09-03T16:39:39.578Z"
        result.updatedAt shouldBe "2018-01-15T21:20:00.003Z"
        result.used shouldBe true
    }
})
