package com.example.shoppinglistexample.presentation

import androidx.lifecycle.ViewModel
import com.example.shoppinglistexample.data.ShopListRepositoryImpl
import com.example.shoppinglistexample.domain.DeleteShopItemUseCase
import com.example.shoppinglistexample.domain.EditShopItemUseCase
import com.example.shoppinglistexample.domain.GetShopListUseCase
import com.example.shoppinglistexample.domain.ShopItem

class MainViewModel : ViewModel() {

    // В Clean Architecture так делать неправильно, потому что Presentation слой
    // зависит от Data слоя
    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    // Из Activity можно подписаться на эту LiveData и отобразить список
    val shopList = getShopListUseCase.getShopList()

    // Метод удаляет элемент
    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    // Метод меняет состояние
    fun changeEnableState(shopItem: ShopItem) {

        // Создаем копию объекта с противоположным значением enabled
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}