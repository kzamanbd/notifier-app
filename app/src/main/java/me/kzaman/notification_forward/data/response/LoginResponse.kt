package me.kzaman.notification_forward.data.response

import me.kzaman.notification_forward.data.model.TokenDataModel
import me.kzaman.notification_forward.data.model.UserDataModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response_code") var responseCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") var data: DataModel,
)

data class DataModel(
    @SerializedName("token") val token: TokenDataModel,
    @SerializedName("user") val user: UserDataModel? = null,
)