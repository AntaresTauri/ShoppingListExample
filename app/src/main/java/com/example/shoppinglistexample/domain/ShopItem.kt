package com.example.shoppinglistexample.domain

// Класс модели
data class ShopItem(
    val name: String,
    val count: Int,
    val enabled: Boolean,

    // id не определен
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
