package com.example.compose_start.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TimeSelectionComposableView(
    startingValue: Int,
    onSelectedTime: (selectedTime: Int) -> Unit,
    desc: String,
    isClearSelection: Boolean,
    onCleared: () -> Unit
) {
    val stateHolder = mutableListOf<MutableState<Boolean>>()
    Column {
        Row {
            Text(text = desc, fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (index in 0 until 4) {
                val isSelected = remember {
                    mutableStateOf(false)
                }
                stateHolder.add(isSelected)
                RadioButton(selected = isSelected.value, onClick = {
                    isSelected.value = !isSelected.value
                    stateHolder.forEachIndexed { stateHolderIndex, mutableState ->
                        if (stateHolderIndex != index) {
                            mutableState.value = false
                        }
                    }
                    onSelectedTime.invoke(startingValue + 5 * index)
                })
                Text(text = (startingValue + 5 * index).toString(), fontSize = 16.sp)
            }
        }
    }

    if (isClearSelection) {
        stateHolder.forEachIndexed { stateHolderIndex, mutableState ->
            mutableState.value = false
        }
        onCleared.invoke()
    }
}

@Preview
@Composable
fun DefaultTimeSelectionComposableView() {
    TimeSelectionComposableView(
        15,
        onSelectedTime = {},
        desc = "Select to cook time...",
        false,
        onCleared = {})
}