package club.dari.androiduitests.model.services

interface TestableService {

    /**
     * Addition int b to int b
     */
    fun additionMethod(a: Int, b: Int): Int

}

class TestableServiceImpl: TestableService {

    override fun additionMethod(a: Int, b: Int): Int = a + b

}