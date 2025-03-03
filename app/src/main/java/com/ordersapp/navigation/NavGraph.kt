package com.ordersapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ordersapp.presentation.ProductState
import com.ordersapp.presentation.TableState
import com.ordersapp.screens.AddProductScreen
import com.ordersapp.screens.CategoriesScreen
import com.ordersapp.screens.EditProductScreen
import com.ordersapp.screens.ListOfProducts
import com.ordersapp.screens.RegisterfoodScreen
import com.ordersapp.screens.SignInScreen
import com.ordersapp.screens.TableOrderScreen
import com.ordersapp.viewModel.ProductViewModel
import com.ordersapp.viewModel.TableProductsCrossRefViewModel
import com.ordersapp.viewModel.TableViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    productViewModel: ProductViewModel,
    productState: ProductState,
    tableViewModel: TableViewModel,
    tableState: TableState,
    tableProductsCrossRefViewModel: TableProductsCrossRefViewModel
) {

    NavHost(navController = navHostController, startDestination = Routes.RegisterFoodScreen.route) {
        composable(Routes.TableOrderScreen.route) { backStackEntry ->
            val tableId = backStackEntry.arguments?.getString("tableId")?.toIntOrNull() ?: 0
            TableOrderScreen(
                navHostController = navHostController,
                tableViewModel = tableViewModel,
                productViewModel = productViewModel,
                tableInt = tableId,
                tableProductsCrossRefViewModel = tableProductsCrossRefViewModel
            )
        }
        composable(Routes.CategoriesScreen.route) { backStackEntry ->
            val tableId = backStackEntry.arguments?.getString("tableId")?.toIntOrNull() ?: 0
            CategoriesScreen(
                productViewModel = productViewModel,
                productState = productState,
                navHostController = navHostController,
                tableId = tableId
            )
        }
        composable(Routes.AddProductScreen.route) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 1
            AddProductScreen(
                navHostController = navHostController,
                productViewModel = productViewModel,
                productState = productState,
                categoryId = categoryId
            )
        }
        composable(Routes.ListOfProducts.route) { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 0
            val tableId = backStackEntry.arguments?.getString("tableId")?.toIntOrNull() ?: 0
            ListOfProducts(
                navHostController = navHostController,
                productViewModel = productViewModel,
                tableViewModel = tableViewModel,
                productState = productState,
                categoryId = categoryId,
                tableId = tableId
            )
        }
        composable(Routes.EditProductScreen.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 1
            EditProductScreen(
                navHostController = navHostController,
                productViewModel = productViewModel,
                productState = productState,
                productId = productId,
            )
        }
        composable(Routes.SignInScreen.route) {
            SignInScreen()
        }
        composable(Routes.RegisterFoodScreen.route) {
            RegisterfoodScreen(
                tableViewModel = tableViewModel,
                navHostController = navHostController
            )
        }


    }
}