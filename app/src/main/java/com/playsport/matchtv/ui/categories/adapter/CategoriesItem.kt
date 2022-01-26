package com.playsport.matchtv.ui.categories.adapter

import com.playsport.matchtv.ui.categories.products.adapter.ProductsItem

data class CategoriesItem(
    val name: String,
    val image: Int,
    val product: ArrayList<ProductsItem>,
    val id: Int
)
