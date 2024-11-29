package com.example.thucdon.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.thucdon.models.ThucDon
import com.example.thucdon.viewmodels.ThucDonDetailViewModel
import com.example.thucdon.viewmodels.ThucDonViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThucDonDetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    navController: NavHostController,
    viewModel: ThucDonDetailViewModel
) {
    var thucDonItem by remember{
        mutableStateOf(viewModel.getThucDonDetail(id))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Đặt Món")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),

            )
        },
        content = { innerpadding ->
            Box(
                modifier = Modifier.padding(innerpadding).fillMaxSize()
            ) {
                val localConfig = LocalConfiguration.current
                Box(
                    modifier = Modifier.padding(16.dp)
                        .height((localConfig.screenHeightDp *3 /10).dp)
                        .fillMaxSize().background(Color.White)
                ) {
                    AsyncImage(
                        model = thucDonItem!!.picture,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier.padding(top =(localConfig.screenHeightDp*3/10).dp)
                        .fillMaxSize()
                        .clip(
                            shape = RoundedCornerShape(
                                topStartPercent = 8,
                                topEndPercent = 8
                            )
                        )
                        .background(Color(0xFFadd8e6))
                ){
                    Column(
                        modifier = Modifier.height((LocalConfiguration.current.screenHeightDp * 8 /10).dp)
                            .padding(10.dp).verticalScroll(ScrollState(0) ),
                        verticalArrangement = Arrangement.spacedBy(10.dp)

                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                            text = thucDonItem!!.name,
                            color = Color.White,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                            text = thucDonItem!!.price.toString()+" VNĐ",
                            color = Color(0xFFff8c00),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = thucDonItem!!.description,
                            softWrap = true,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color(0xFFFFFAF0),
                        )
                    }

                }


            }

        }
    )

}