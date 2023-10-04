package com.example.mass

sealed class ListItem{
    data class Commodity(val link : String = "", val name : String = "", val price : String = "", val rs: Int = 0, var item : Int = 0) : ListItem()
    data class Price(val text : String):ListItem()
}
