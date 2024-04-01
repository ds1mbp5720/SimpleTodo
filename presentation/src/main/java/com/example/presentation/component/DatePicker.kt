package com.example.presentation.component

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
import com.example.presentation.theme.Highlight
import com.example.presentation.utils.millToDate
import com.example.presentation.utils.timeString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDatePickerButton(
    modifier: Modifier,
    hint: String,
    useTime: Boolean = false,
    updateDate: (String) -> Unit
) {
    //todo edit일때  Button text?
    
    var yearText by remember { mutableStateOf(hint) }
    var monthText by remember { mutableStateOf("") }
    var dayText by remember { mutableStateOf("") }
    var timeText by remember { mutableStateOf("") }
    val dateState = rememberDatePickerState(
        yearRange = 2023..2024,
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = System.currentTimeMillis()
    )
    val timeState = rememberTimePickerState()
    val openDateDialog = remember { mutableStateOf(false) }
    val openTimeDialog = remember { mutableStateOf(false) }

    BasicRowButton(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Highlight,
                shape = RoundedCornerShape(30.dp)
            ),
        textStart = yearText,
        textCenter = if (monthText.isEmpty() && dayText.isEmpty()) {
            ""
        } else {
            "$monthText/$dayText"
        },
        textEnd = timeText,
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
                        yearText = millToDate(dateState.selectedDateMillis ?: 0).substring(0, 4)
                        monthText = millToDate(dateState.selectedDateMillis ?: 0).substring(5, 7)
                        if (monthText[0] == '0') {
                            monthText = monthText.replace("0", "")
                        }
                        dayText = millToDate(dateState.selectedDateMillis ?: 0).substring(8, 10)
                        if (dayText[0] == '0') {
                            dayText = dayText.replace("0", "")
                        }
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
                        timeText = timeState.timeString()
                        updateDate("${yearText.substring(2, 4)}' $monthText/$dayText ${timeState.timeString()}")
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