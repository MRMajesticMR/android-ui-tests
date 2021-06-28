package club.dari.androiduitests.model.data.cat_facts.local

import club.dari.androiduitests.model.data.cat_facts.CatFact

interface CatsFactsLocalDataSource {

    /**
     * Save cat facts in local storage
     */
    fun saveFacts(facts: List<CatFact>)

    /**
     * Get cat facts from local storage
     */
    fun getFacts(): List<CatFact>

    /**
     * Delete all facts from local storage
     */
    fun clearAll()

}

class CatsFactsLocalDataSourceImpl: CatsFactsLocalDataSource {

    private val cachedFacts = mutableListOf<CatFact>()

    override fun saveFacts(facts: List<CatFact>) {
        cachedFacts.addAll(facts)
    }

    override fun getFacts(): List<CatFact> =
        cachedFacts.toList()

    override fun clearAll() {
        cachedFacts.clear()
    }

}