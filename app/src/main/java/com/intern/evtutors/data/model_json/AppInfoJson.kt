package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.AppInfo

class AppInfoJson (
    val id:Int,
    val appID:String,
    val appCertificate:String
) {
    fun toAppInfo():AppInfo {
        return AppInfo(
            id=id,
            appID=appID,
            appCertificate=appCertificate
        )
    }
}