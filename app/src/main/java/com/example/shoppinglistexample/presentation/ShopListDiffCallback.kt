package com.example.shoppinglistexample.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglistexample.domain.ShopItem

// Класс сравнивает новый список и старый
class ShopListDiffCallback(
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>
) : DiffUtil.Callback() {

    // Возвращает количество элементов старого списка
    override fun getOldListSize(): Int {
        return oldList.size
    }

    // Возвращает количество элементов нового списка
    override fun getNewListSize(): Int {
        return newList.size
    }

    // Сравнивает id старого и нового элементов, чтобы знать что мы работаем
    // с одним и тем же объектом
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id
    }

    // Метод проверяет содержимое объектов на изменение полей этого объекта.
    // Если какое-нибудь поле изменилось, то объект нужно будет перерисовать,
    // иначе все остается как есть. Метод возвращает true только в том случае,
    // если все поля объекта остались прежними
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }
}