package com.example.shoppinglistexample.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglistexample.domain.ShopItem

// ItemCallback сравнивает отдельные элементы
class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {

    // Метод areItemsTheSame() принимает сами элементы
    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    // Метод areContentsTheSame() принимает сами элементы
    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {

        // Нужно сравнить все поля данных объектов, но т.к. это data-классы,
        // то можно сравнить сами объекты этих классов
        return oldItem == newItem
    }
}