package com.intern.evtutors.data.model_json

import com.intern.evtutors.data.models.Certificates
import com.intern.evtutors.data.models.Teacher

class CertificatesJson (
    val userId:Int,
    val img1:String?,
    val img2:String?,
    val img3:String?,
    val img4:String?,
    val img5:String?
) {
    fun toCertificates(): Certificates {
        return Certificates(
            userId = userId,
            img1 = img1,
            img2 = img2,
            img3 = img3,
            img4 = img4,
            img5 = img5,
        )
    }
}