package com.example.shoppinglistexample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglistexample.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Подписываемся на объект shopList
        viewModel.shopList.observe(this) {

            // Чтобы установить новый список в ListAdapter, необходимо вызвать метод
            // submitList() и передать в него список элементов
            shopListAdapter.submitList(it)
        }
    }

    // Метод для настройки RecyclerView
    private fun setupRecyclerView() {
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        with(rvShopList) {
            shopListAdapter = ShopListAdapter()
            adapter = shopListAdapter

            // Устанавливаем размер пула. Метод setMaxRecycledViews() первым
            // параметром принимает ViewType, а второй количество пула. Для
            // каждого типа ViewHolder указываем два пула
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE
            )
        }
        setupLongClickListener()
        setupClickListener()
        setupSwipeListener(rvShopList)
    }

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Срабатывает при свайпе
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                // Получаем позицию элемента.
                // Метод getCurrentList() получает текущий список в адаптере
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]

                // Удаляем полученный элемент
                viewModel.deleteShopItem(item)
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }

    // Обычное нажатие на кнопку
    private fun setupClickListener() {
        shopListAdapter.onShopItemClickListener = {
        }
    }

    // Долгое нажатие на кнопку
    private fun setupLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}
