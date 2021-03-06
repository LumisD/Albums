package com.vladus177.albums.data.remote.model

import com.google.gson.annotations.SerializedName
import com.vladus177.albums.data.Constants.USER_CITY
import com.vladus177.albums.data.Constants.USER_LOCATION
import com.vladus177.albums.data.Constants.USER_STREET
import com.vladus177.albums.data.Constants.USER_SUITE
import com.vladus177.albums.data.Constants.USER_ZIPCODE


data class AddressEntry(
    @SerializedName(USER_STREET) val street: String?,
    @SerializedName(USER_SUITE) val suite: String?,
    @SerializedName(USER_CITY) val city: String?,
    @SerializedName(USER_ZIPCODE) val zipcode: String?,
    @SerializedName(USER_LOCATION) val location: LocationEntry?
)