package com.elemental.firebasechat.model

import java.lang.reflect.Constructor

data class Chat(
    val sender:String,
    val receiver:String,
    val message:String
){
    constructor():this("","","")
}