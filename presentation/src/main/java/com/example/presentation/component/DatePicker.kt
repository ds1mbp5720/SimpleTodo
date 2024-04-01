package com.example.presentation.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.presentation.R
import com.example.presentation.theme.Highlight
import com.example.presentation.utils.millToDate
import com.example.presentation.utils.timeString

/**
 * 날짜선택 Compose함수
 * hint: 최초 text표시
 * useTime: false -> InsertUser  /  true -> Task 에서 사용
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicDatePickerButton(
    modifier: Modifier,
    hint: String,
    useTime: Boolean = false,
    empty: Boolean = false,
    emptyText: String,
    emptyModifier: Modifier,
    updateDate: (String) -> Unit
) {
    var yearText by remember { mutableStateOf(hint) }
    var monthText by remember { mutableStateOf("") }
    var dayText by remember { mutableStateOf("") }
    var timeText by remember { mutableStateOf("") }
    // edit 일때 text 분할
    if(useTime && !hint.contains("Date")){
        val preDate = hint.split(" ")
        val preMonthAndDay = preDate[1].split("/")
        yearText = preDate[0]
        monthText = preMonthAndDay[0]
        dayText = preMonthAndDay[1]
        timeText = preDate[2] + " " + preDate[3]
    }
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
    if (empty) {
        BasicTextBodySmall(
            modifier = emptyModifier,
            text = emptyText,
            color = Color.Red
        )
    } else {
        Spacer(modifier = emptyModifier)
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
                        } else {
                            updateDate("${yearText.substring(0, 4)}.$monthText.$dayText")
                        }
                    }
                ) {
                    if (useTime)
                        Text(stringResource(id = R.string.str_next))
                    else
                        Text(stringResource(id = R.string.str_confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDateDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.str_cancel))
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
                        updateDate(
                            "${
                                yearText.substring(
                                    2,
                                    4
                                )
                            }' $monthText/$dayText ${timeState.timeString()}"
                        )
                        openTimeDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.str_confirm))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openTimeDialog.value = false
                    }
                ) {
                    Text(stringResource(id = R.string.str_cancel))
                }
            }
        ) {
            TimePicker(
                state = timeState
            )
        }
    }
}