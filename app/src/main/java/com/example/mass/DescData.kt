package com.example.mass

data class DescData(var link : String, var name : String, var desc : String, var price : String, val rs : Int){
    constructor():this("", "", "", "",  0)
}
