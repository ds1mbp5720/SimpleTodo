package com.example.simpletodoapp.component

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.simpletodoapp.ui.theme.Highlight
import com.example.simpletodoapp.utils.millToDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDatePickerButton(
    modifier: Modifier,
    hint: String,
    useTime: Boolean = false,
    updateDate: (String) -> Unit
) {
    var buttonText by remember { mutableStateOf(hint) }
    val dateState = rememberDatePickerState(
        yearRange = 2023..2024,
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    val timeState = rememberTimePickerState()
    val openDateDialog = remember { mutableStateOf(false) }
    val openTimeDialog = remember { mutableStateOf(false) }

    BasicButton(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Highlight,
                shape = RoundedCornerShape(30.dp)
            ),
        text = buttonText,
        buttonColor = Color.White,
        textColor = Color.Gray,
        textAlign = TextAlign.Start
    ) {
        openDateDialog.value = true
    }
    if (openDateDialog.value) {
        DatePickerDialog(
            onDismissRequest = {
                openDateDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        buttonText = millToDate(dateState.selectedDateMillis ?: 0)
                        openDateDialog.value = false
                        if (useTime) {
                            openTimeDialog.value = true
                        }
                    }
                ) {
                    Text("Next")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDateDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        ) {
            DatePicker(
                state = dateState
            )
        }
    }
    if (openTimeDialog.value) {
        TimePickerDialog(
            onDismissRequest = {
                openTimeDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        Log.e("", "날짜 시간 선택 상태 ${timeState.hour} / ${timeState.minute} / ${timeState}")
                        openTimeDialog.value = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openTimeDialog.value = false
                    }
                ) {
                    Text("CANCEL")
                }
            }
        ) {
            TimePicker(
                state = timeState
            )
        }
    }
}