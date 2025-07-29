package com.harukadev.pascalina.calculator.presentation.components

import androidx.annotation.DrawableRes
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
fun ButtonKeyShortcuts(
    modifier: Modifier = Modifier,
    key: Char,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clip(CircleShape)
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = key.toString(),
            fontSize = 30.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ButtonKeyShortcuts(modifier: Modifier = Modifier, @DrawableRes icon: Int) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .clickable(onClick = {}),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = "", // TODO: add description
            tint = Color(0xffB2C2B4),
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview
@Composable
fun ButtonKeyShortcutsPreview() {
    MaterialTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '√')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = 'π')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '^')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '!')
            ButtonKeyShortcuts(
                modifier = Modifier.weight(1f),
                icon = R.drawable.keyboard_arrow_down
            )
        }
    }
}