package club.dari.androiduitests.model.data.cat_facts.cloud

import club.dari.androiduitests.api.cats.objects.CatFactCatsApiObject
import club.dari.androiduitests.model.data.cat_facts.CatFact

class CatsFactFromCatsApiObjectMapper {

    fun map(from: CatFactCatsApiObject) =
        CatFact(
            status = mapStatus(from.status),
            type = from.type,
            deleted = from.deleted,
            text = from.text,
            createdAt = from.createdAt,
            updatedAt = from.updatedAt,
            used = from.used
        )

    private fun mapStatus(from: CatFactCatsApiObject.Status) =
        CatFact.Status(
            verified = from.verified,
            sentCount = from.sentCount,
            feedback = from.feedback
        )

}