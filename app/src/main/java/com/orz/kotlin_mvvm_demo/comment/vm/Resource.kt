package com.orz.kotlin_mvvm_demo.comment.vm


/**
 * A generic class that holds a value with its loading status.
 *
 * @param <T>
 */
class Resource<T>(val status: Status,
                  val data: T?,
                  val message: String? = null,
                  val error: Throwable? = null) {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        if (status !== resource?.status) {
            return false
        }
        if (if (error != null) error != resource.error else resource.error != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (error?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource{" +
                "status=" + status +
                ", error='" + error + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }

    companion object {

        fun <T> success(data: T? = null, message: String? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, message)
        }

        fun <T> error(error: Throwable? = null, data: T? = null, message: String? = null): Resource<T> {
            return Resource(Status.ERROR, data, message, error)
        }

        fun <T> loading(data: T? = null, message: String? = null): Resource<T> {
            return Resource(Status.LOADING, data, message)
        }

        fun <T> cancel(data: T? = null, message: String? = null): Resource<T> {
            return Resource(Status.CANCEL, data, message)
        }
    }
}
