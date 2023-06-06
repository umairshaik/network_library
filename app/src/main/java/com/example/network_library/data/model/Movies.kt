package com.example.network_library.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Series(

    @SerializedName("resources")
    var resources: ArrayList<Resources> = arrayListOf(),
    @SerializedName("provider")
    var provider: String? = null,
    @SerializedName("id")
    var id: String? = null

)

@Serializable
data class Resources(

    @SerializedName("displayMore")
    var displayMore: Boolean? = null,
    @SerializedName("displayType")
    var displayType: String? = null,
    @SerializedName("i18nKey")
    var i18nKey: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("orientation")
    var orientation: String? = null,
    @SerializedName("pageStructureId")
    var pageStructureId: String? = null,
    @SerializedName("order")
    var order: Int? = null,
    @SerializedName("type")
    var type: Int? = null

)
