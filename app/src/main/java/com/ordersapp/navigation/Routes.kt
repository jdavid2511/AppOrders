package com.ordersapp.navigation


sealed class Routes(var route: String) {

    object SignInScreen : Routes("sign_in_screen")
    object RegisterFoodScreen : Routes("register_food_screen")
    object TableOrderScreen : Routes("table_order_screen")
    object CategoriesScreen : Routes("categories_screen")
    object AddProductScreen : Routes("add_product_screen/{categoryId}") {
        fun createRoute(categoryId: Int) = "add_product_screen/$categoryId"
    }
    object ListOfProducts : Routes("list_of_products_screen/{categoryId}") {
        fun createRoute(categoryId: Int) = "list_of_products_screen/$categoryId"
    }

    object EditProductScreen : Routes("edit_product_screen/{productId}") {
        fun createRoute(productId: Int) = "edit_product_screen/$productId"
    }
}