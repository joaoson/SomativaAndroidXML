package com.example.somativaandroid.nasaimages

data class NasaResponse(val collection: Collection)

data class Collection(
    val items: List<Item>
)

data class Item(
    val data: List<Data>,
    val href: String
)

data class Data(
    val title: String
)