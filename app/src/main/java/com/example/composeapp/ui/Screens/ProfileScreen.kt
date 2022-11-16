package com.example.composeapp.ui.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeapp.R
import com.example.composeapp.model.ImageWithText

@Composable
@Preview
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            ProfileImage(80.dp, 0.dp, "Aakash Soni", painterResource(id = R.drawable.profile))
            ProfileInfoItem("12", "Posts")
            ProfileInfoItem("120", "Followers")
            ProfileInfoItem("456", "Following")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.White)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            ProfileButton("Following")
            ProfileButton("Message")
            ProfileButton("Add")
        }

        StoriesSection()

    }

}

@Composable
@Preview
fun ProfileInfoItem(title: String = "", desc: String = "") {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = desc,
            fontSize = 12.sp
        )
    }
}

@Composable
@Preview
fun ProfileButton(text: String = "") {
    Button(
        onClick = { },
        modifier = Modifier
            .wrapContentWidth()
            .height(35.dp),
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Color.White
        ),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Text(text = text, color = Color.Black)
    }
}

@Composable
fun ProfileImage(size: Dp, padding: Dp, title: String, image: Painter) {
    Column(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(vertical = 20.dp, horizontal = padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(size),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = image,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun StoriesSection() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        items(
            listOf(
                ImageWithText("Apple", R.drawable.apple),
                ImageWithText("Netflix", R.drawable.netfkix),
                ImageWithText("Amazon", R.drawable.amazon),
                ImageWithText("Spotify", R.drawable.spotify),
                ImageWithText("Twitter", R.drawable.twitter),
                ImageWithText("Youtube", R.drawable.youtube)
            )
        ) {
            ProfileImage(
                size = 60.dp,
                padding = 12.dp,
                title = it.text,
                painterResource(id = it.image)
            )
        }
    }
}