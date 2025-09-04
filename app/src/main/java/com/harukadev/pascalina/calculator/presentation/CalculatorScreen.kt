package com.harukadev.pascalina.calculator.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.harukadev.pascalina.R
import com.harukadev.pascalina.calculator.presentation.components.ButtonKey
import com.harukadev.pascalina.calculator.presentation.components.ButtonKeyShortcuts
import com.harukadev.pascalina.ui.theme.PascalinaTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: CalculatorViewModel = viewModel(factory = CalculatorViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(Color(0xff1a1c1a))
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f)
                .clip(
                    RoundedCornerShape(
                        bottomStart = 25.dp,
                        bottomEnd = 25.dp,
                    )
                )
                .background(Color(0xff2b352e))
                .padding(16.dp)
        ) {
            // TODO: make the expression and result selectable for copying
            Text(
                text = state.expression
                    .replace('/', '÷')
                    .replace('*', '×'),
                modifier = Modifier
                    .weight(8f)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color(0xffCACDC9),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.jetbrains_mono))
                ),
                maxLines = 4
            )
            Text(
                text = state.calculationResult,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                style = TextStyle(
                    fontSize = 32.sp,
                    color = Color(0xffCACDC9),
                    textAlign = TextAlign.End,
                    fontFamily = FontFamily(Font(R.font.jetbrains_mono)),
                    lineHeight = 32.sp
                ),
                maxLines = 1
            )
        }

        Spacer(Modifier.size(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '√')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = 'π')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '^')
            ButtonKeyShortcuts(modifier = Modifier.weight(1f), key = '!')
            ButtonKeyShortcuts(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 15.dp)
                    .clip(CircleShape)
                    .background(Color(0xff202521))
                    .weight(1f), icon = R.drawable.keyboard_arrow_down
            )
        }

        Spacer(Modifier.size(10.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(9f)
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
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "AC",
                    color = Color(0xff2d4b58),
                    onClick = { viewModel.onClearKey() }
                )
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "()",
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.onParenthesisKey() })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.percent,
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.appendOperator('%') })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_divide,
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.appendOperator('/') })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "7",
                    onClick = { viewModel.appendNumber(7) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "8",
                    onClick = { viewModel.appendNumber(8) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "9",
                    onClick = { viewModel.appendNumber(9) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.close,
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.appendOperator('*') })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "4",
                    onClick = { viewModel.appendNumber(4) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "5",
                    onClick = { viewModel.appendNumber(5) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "6",
                    onClick = { viewModel.appendNumber(6) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.horizontal,
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.appendOperator('-') })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "1",
                    onClick = { viewModel.appendNumber(1) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "2",
                    onClick = { viewModel.appendNumber(2) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "3",
                    onClick = { viewModel.appendNumber(3) })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.sum,
                    color = Color(0xff3b4a40),
                    onClick = { viewModel.appendOperator('+') })
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = "0",
                    onClick = { viewModel.appendNumber(0) },
                )
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    key = ".",
                    onClick = { viewModel.appendOperator('.') })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.backspace,
                    onClick = { viewModel.onBackspaceKey() })
                ButtonKey(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.equal,
                    color = Color(0xff215135),
                    onClick = {
                        Toast.makeText(
                            context,
                            "This button is just for decoration",
                            Toast.LENGTH_SHORT
                        ).show()
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
    PascalinaTheme {
        MainScreen(modifier)
    }
}