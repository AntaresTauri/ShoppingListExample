package com.example.shoppinglistexample.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglistexample.R
import com.example.shoppinglistexample.domain.ShopItem

// Одно из преимуществ ListAdapter в том, что он скрывает в себе всю логику работы со списком.
// Нам не нужно хранить ссылку на этот список самостоятельно.
// Так как вся работа со списком скрыта внутри ListAdapter, то метод getItemCount()
// можно вообще не переопределять.
class ShopListAdapter : ListAdapter<ShopItem,
        ShopItemViewHolder>(ShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        // В зависимости от типа возвращаем разные макеты
        val layout = when (viewType) {
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        // Получаем элемент по его позиции
        val shopItem = getItem(position)

        // Долгое нажатие на кнопку
        holder.view.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopItem)
            true
        }

        // обычное нажатие на кнопку
        holder.view.setOnClickListener {
            onShopItemClickListener?.invoke(shopItem)
        }
        holder.tvName.text = shopItem.name
        holder.tvCount.text = shopItem.count.toString()
    }

    // Метод getItemViewType()и отвечает за определение типа представления
    override fun getItemViewType(position: Int): Int {

        // Получаем элемент по позиции
        val item = getItem(position)

        // Определяем и сразу же возвращаем соответствующее значение
        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {

        // Константа для item_shop_enabled
        const val VIEW_TYPE_ENABLED = 0

        // Константа для item_shop_disabled
        const val VIEW_TYPE_DISABLED = 1

        // Константа для хранения количества ViewHolders в пуле
        const val MAX_POOL_SIZE = 30
    }
}

