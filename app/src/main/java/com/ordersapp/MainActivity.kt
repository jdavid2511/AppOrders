package com.ordersapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ordersapp.navigation.NavGraph
import com.ordersapp.viewModel.ProductViewModel
import com.ordersapp.viewModel.TableProductsCrossRefViewModel
import com.ordersapp.viewModel.TableViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val productViewModel = hiltViewModel<ProductViewModel>()
            val productState by productViewModel.state.collectAsState()

            val tableViewModel = hiltViewModel<TableViewModel>()
            val tableState by tableViewModel.state.collectAsState()

            val tableProductsCrossRefViewModel = hiltViewModel<TableProductsCrossRefViewModel>()

            val navHostController = rememberNavController()

            NavGraph(
                navHostController = navHostController,
                productViewModel = productViewModel,
                productState = productState,
                tableViewModel = tableViewModel,
                tableState = tableState,
                tableProductsCrossRefViewModel = tableProductsCrossRefViewModel
            )
        }
    }
}