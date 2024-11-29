package com.example.thucdon.navigation

sealed class Screen (val route: String){
    object ThucDon: Screen("ThucDonScreen")
    object ThucDonDetail: Screen("ThucDonDetailScreen")
}