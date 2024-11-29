package com.example.thucdon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.thucdon.viewmodels.ThucDonDetailViewModel
import com.example.thucdon.viewmodels.ThucDonViewModel
import com.example.thucdon.views.ThucDonDetailScreen
import com.example.thucdon.views.ThucDonScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: ThucDonViewModel,
    detailViewModel: ThucDonDetailViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ThucDon.route
    ) {
        composable(Screen.ThucDon.route) {
            ThucDonScreen(navController=navController,viewModel = viewModel)
        }
        composable(Screen.ThucDonDetail.route +"?id={id}",
            arguments = listOf(navArgument("id") {
                nullable=true
            })
            ) {
            var id = it.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                ThucDonDetailScreen(navController = navController, id = id,viewModel = detailViewModel)
            }else{
                ThucDonScreen(navController=navController,viewModel = viewModel)
            }
        }
    }

}