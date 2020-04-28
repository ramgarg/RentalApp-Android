package com.eazyrento.common.model.modelclass

data class ProductDetailsResModel(
    val base_price: Double,
    val id: Int,
    var is_wishlisted: Boolean,
    val name: String,
    val product_description: String,
    val product_details: ProductDetailsInner,
    val product_image_url: String
)