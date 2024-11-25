package com.itworxedu.core.ui

sealed class UIComponent{

    data class Dialog(
        val title:String,
        val description: String
    ): UIComponent()

    data class Toast(
        val message:String
    ): UIComponent()

}
