package com.example.shoppinglistexample.domain

import androidx.lifecycle.LiveData

// Получает весь список элементов
class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}