package com.example.shoppinglistexample.domain

// Класс модели
data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val enabled: Boolean
)
