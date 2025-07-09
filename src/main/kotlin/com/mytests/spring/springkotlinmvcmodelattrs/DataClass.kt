package com.mytests.spring.springkotlinmvcmodelattrs

class DataClass(name: String, size: Int) {

    var name: String
        internal set
    var size: Int = 0
        internal set

    init {
        this.name = name
        this.size = size
    }
}
