package com.harukadev.pascalina.calculator.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harukadev.pascalina.R

@Composable
fun ButtonKey(
    modifier: Modifier = Modifier,
    key: String,
    color: Color = Color(0xFF202521),
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(CircleShape)
            .clickable(onClick=onClick)
            .background(color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = key,
            fontSize = 30.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonKey(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    color: Color = Color(0xFF202521),
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .background(color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(43.dp),
            imageVector = ImageVector.vectorResource(id = icon),
            tint = Color.Gray,
            contentDescription = "" // TODO: Set accessibility
        )
    }
}

@Preview
@Composable
fun ButtonKeyPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(105.dp)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ButtonKey(modifier = Modifier.weight(1f), key = "AC", color = Color(0xff2d4b58))
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.backspace,
                    color = Color(0xff2d4b58)
                )
                ButtonKey(modifier = Modifier.weight(1f), key = "%", color = Color(0xff3b4a40))
                ButtonKey(modifier = Modifier.weight(1f), key = "/", color = Color(0xff3b4a40))
            }
        }
    }
}