package com.touchin.domain.repository.common

interface PreferenceRepository {

    fun getEmail(): String

    fun getSubject(): String

    fun getBody(): String

    fun setEmail(): String

    fun setSubject(): String

    fun setBody(): String
}
