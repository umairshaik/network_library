package com.example.network_library.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class Series(

    @SerializedName("resources") var resources: ArrayList<Resources> = arrayListOf(),
    @SerializedName("provider") var provider: String? = null,
    @SerializedName("id") var id: String? = null

)

@Serializable
data class Resources(

    @SerializedName("displayMore") var displayMore: Boolean? = null,
    @SerializedName("displayType") var displayType: String? = null,
    @SerializedName("i18nKey") var i18nKey: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("orientation") var orientation: String? = null,
    @SerializedName("pageStructureId") var pageStructureId: String? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("type") var type: Int? = null

)


data class Movies(

    @SerializedName("id") var id: String? = null,
    @SerializedName("isTrailer") var isTrailer: Boolean? = null,
    @SerializedName("translations") var translations: Translations? = Translations(),
    @SerializedName("casts") var casts: ArrayList<String> = arrayListOf(),
    @SerializedName("categoryIds") var categoryIds: ArrayList<String> = arrayListOf(),
    @SerializedName("categoryNames") var categoryNames: ArrayList<String> = arrayListOf(),
    @SerializedName("contentType") var contentType: String? = null,
    @SerializedName("dateReleased") var dateReleased: Int? = null,
    @SerializedName("directors") var directors: ArrayList<String> = arrayListOf(),
    @SerializedName("genreIds") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("genreNames") var genreNames: ArrayList<String> = arrayListOf(),
    @SerializedName("languages") var languages: ArrayList<String> = arrayListOf(),
    @SerializedName("providerId") var providerId: String? = null,
    @SerializedName("providerContentId") var providerContentId: String? = null,
    @SerializedName("seriesNo") var seriesNo: Int? = null,
    @SerializedName("episodeNum") var episodeNum: Int? = null,
    @SerializedName("nSecStatus") var nSecStatus: String? = null,
    @SerializedName("tNetStatus") var tNetStatus: String? = null,
    @SerializedName("subtitleLanguage") var subtitleLanguage: String? = null,
    @SerializedName("rating") var rating: String? = null,
    @SerializedName("runTime") var runTime: Int? = null,
    @SerializedName("trailers") var trailers: ArrayList<String> = arrayListOf()
)

data class En(

    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null

)

data class Android(

    @SerializedName("key") var key: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("embedded") var embedded: String? = null,
    @SerializedName("catchUp") var catchUp: String? = null

)


data class Id(

    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null

)

data class Translations(

    @SerializedName("en") var en: En? = En(), @SerializedName("id") var id: Id? = Id()

)