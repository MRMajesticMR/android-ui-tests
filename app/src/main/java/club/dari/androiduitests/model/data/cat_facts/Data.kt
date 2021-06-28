package club.dari.androiduitests.model.data.cat_facts

class CatFact(
    val status: Status,
    val type: String,
    val deleted: Boolean,
    val text: String,
    val createdAt: String,
    val updatedAt: String,
    val used: Boolean
) {

    class Status(
        val verified: Boolean,
        val sentCount: Int,
        val feedback: String?,
    ) {

        override fun toString(): String {
            return "Status(verified=$verified, sentCount=$sentCount, feedback=$feedback)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Status

            if (verified != other.verified) return false
            if (sentCount != other.sentCount) return false
            if (feedback != other.feedback) return false

            return true
        }

        override fun hashCode(): Int {
            var result = verified.hashCode()
            result = 31 * result + sentCount
            result = 31 * result + (feedback?.hashCode() ?: 0)
            return result
        }


    }

    override fun toString(): String {
        return "CatFact(status=$status, type='$type', deleted=$deleted, text='$text', createdAt='$createdAt', updatedAt='$updatedAt', used=$used)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CatFact

        if (status != other.status) return false
        if (type != other.type) return false
        if (deleted != other.deleted) return false
        if (text != other.text) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false
        if (used != other.used) return false

        return true
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + deleted.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        result = 31 * result + used.hashCode()
        return result
    }


}