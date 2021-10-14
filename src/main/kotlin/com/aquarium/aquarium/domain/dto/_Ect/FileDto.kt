package com.aquarium.aquarium.domain.dto._Ect


data class FileDto(

    var filename : String? = null,
    var fileDownloadUri : String? = null,
    var fileType : String? = null,
    var size : Long? = null

)