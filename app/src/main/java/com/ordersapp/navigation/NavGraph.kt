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

@Composable
fun NavGraph(navHostController: NavHostController, productViewModel: ProductViewModel, productState : ProductState, tableState: TableState) {

    NavHost(navController = navHostController, startDestination = Routes.RegisterFoodScreen.route) {

        composable(Routes.TableOrderScreen.route) {
            TableOrderScreen(
                productViewModel = productViewModel,
                productState = productState,
                navHostController = navHostController
            )
        }
        composable(Routes.CategoriesScreen.route) {
            CategoriesScreen(
                navHostController = navHostController,
                productViewModel = productViewModel,
                productState = productState
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
            val categoryId = backStackEntry.arguments?.getString("categoryId")?.toIntOrNull() ?: 1
            ListOfProducts(
                navHostController = navHostController,
                viewModel = productViewModel,
                state = productState,
                categoryId = categoryId
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
                productState = productState,
                tableState = tableState,
                navHostController = navHostController
            )
        }


    }
}