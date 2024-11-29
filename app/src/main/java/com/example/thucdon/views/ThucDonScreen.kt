package com.example.thucdon.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thucdon.models.ThucDon
import com.example.thucdon.navigation.Screen
import com.example.thucdon.viewmodels.ThucDonViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThucDonScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ThucDonViewModel
) {
    val activity = (LocalContext.current as? Activity)
    var thucDonList by remember {
       mutableStateOf(viewModel.getThucDon())
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Thực đơn") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        activity?.finishAndRemoveTask()
                    }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
            ) {
                items(thucDonList) {
                    ThucDonCard(thucDon = it,
                        onClick = {
                            navController.navigate(Screen.ThucDonDetail.route + "?id=${it.id}")
                        }
                    )
                }
            }
        }
    )

}

@Composable
fun ThucDonCard(thucDon: ThucDon, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        onClick = onClick,
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(2.dp),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                   model= thucDon.picture,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(80.dp),
                    contentScale = ContentScale.Fit
                )
                Column {
                    Text(
                        thucDon.name.uppercase(Locale.getDefault()),
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(thucDon.price.toString(), color = Color.LightGray, fontSize = 12.sp)
                }
            }
        },
    )

}