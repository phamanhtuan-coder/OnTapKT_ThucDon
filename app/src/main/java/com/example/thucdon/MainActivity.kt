package com.example.thucdon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thucdon.navigation.NavigationGraph
import com.example.thucdon.ui.theme.ThucDonTheme
import com.example.thucdon.viewmodels.ThucDonDetailViewModel
import com.example.thucdon.viewmodels.ThucDonViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var navController: NavHostController
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ThucDonViewModel(this)
        val detailViewModel = ThucDonDetailViewModel(this)
        setContent {
            ThucDonTheme {
                navController = rememberNavController()
                NavigationGraph(navController = navController, viewModel = viewModel,detailViewModel = detailViewModel)
            }
        }
    }
}