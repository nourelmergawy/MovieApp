package com.example.movieapp.core.common.data.repository.local

import com.example.movieapp.core.common.domain.repository.local.IStorageKeyEnum


enum class StorageKeyEnum(override val keyValue: String) : IStorageKeyEnum {
    ACCESS_TOKEN("accessToken"),
    USER("user"),
    PREFERRED_LANGUAGE("preferred language"),
    ONBOARDING_COMPLETED("onboarding"),
    COUNTRIES_JSON("countries json"),
    PREFERRED_COUNTRY("preferred country")
}