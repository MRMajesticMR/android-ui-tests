package club.dari.androiduitests.model.services

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class TestableServiceImplTest : StringSpec() {

    private lateinit var testableServiceImpl: TestableServiceImpl

    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)

        testableServiceImpl = TestableServiceImpl()
    }

    init {
        "additionMethod" {
            testableServiceImpl.additionMethod(1, 2) shouldBe 3
        }

        "additionMethod with table" {
            table(
                headers("a", "b", "result"),
                row(2, 3, 5),
                row(0, 0, 0),
                row(4, -1, 3),
                row(-2, -1, -3)
            ).forAll { a, b, result ->
                println("Testing a(${a}) b(${b}) and result(${result})")

                testableServiceImpl.additionMethod(a, b) shouldBe result
            }
        }

        "additionMethod with forall" {
            forAll(
                row(2, 3, 5),
                row(0, 0, 0),
                row(4, -1, 3),
                row(-2, -1, -3)
            ) { a, b, result ->
                println("Testing a(${a}) b(${b}) and result(${result})")

                testableServiceImpl.additionMethod(a, b) shouldBe result
            }
        }
    }

}