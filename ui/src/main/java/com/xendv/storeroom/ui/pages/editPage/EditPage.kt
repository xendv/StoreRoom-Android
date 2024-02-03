package com.xendv.storeroom.ui.pages.editPage

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.xendv.storeroom.ui.R
import com.xendv.storeroom.ui.pages.simplePage.SimplePage
import com.xendv.storeroom.ui.scanner.ModalScanner
import com.xendv.storeroom.ui.toolbar.Toolbar
import kotlinx.coroutines.launch
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditPage(
    modifier: Modifier = Modifier,
    title: String = "",
    editables: List<Editable>,
    toolbar: @Composable (TopAppBarScrollBehavior) -> Unit = { scrollBehavior ->
        Toolbar(
            title = title,
            scrollBehavior = scrollBehavior,
            navigationIcon = R.drawable.ic_arrow_back,
        )
    },
    onSaveClicked: () -> Unit = {},
) {
    SimplePage(
        modifier = modifier,
        title = title,
        toolbar = toolbar,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(editables) { editable ->
                EditableContainer(
                    modifier = Modifier
                        .padding(horizontal = 26.dp)
                        .fillMaxWidth(),
                    editable = editable,
                )
            }
            item {
                Button(onClick = onSaveClicked) {
                    Text("Save")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class,
    ExperimentalLayoutApi::class
)
@Composable
private fun EditableContainer(
    modifier: Modifier = Modifier,
    editable: Editable,
) {
    val scope = rememberCoroutineScope()

    val calendar = Calendar.getInstance()
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = calendar.timeInMillis
    )

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    selectedDate = datePickerState.selectedDateMillis!!
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDatePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = datePickerState
            )
        }
    }

    var showScanner by remember {
        mutableStateOf(false)
    }

    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    ) { success ->
        if (success) {
            showScanner = true
        }
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    if (showScanner) {
        ModalScanner(
            scanResultCallback = { result ->
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (editable is Editable.ListEditable) {
                        editable.add(result)
                    } else {
                        editable.state.value = TextFieldValue(result.trim())
                    }
                }
            },
            onDismissRequest = {
                showScanner = false
            }
        )
    }
    Column(
        modifier = modifier,
    ) {
        if (editable is Editable.ListEditable) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(vertical = 8.dp),
            ) {
                editable.values.value.forEach { element ->
                    InputChip(
                        modifier = Modifier.padding(
                            vertical = 4.dp,
                        ),
                        selected = false,
                        onClick = {},
                        label = { Text(element) },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    editable.remove(element)
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_close),
                                    contentDescription = "Date"
                                )
                            }
                        },
                        shape = CircleShape,
                    )
                }
            }
        }
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = editable.state.value,
            label = { Text(editable.title) },
            onValueChange = {
                editable.state.value = it
            },
            singleLine = true,
            leadingIcon = when (editable.type) {
                ValueType.BARCODE -> {
                    {
                        IconButton(
                            onClick = {
                                if (cameraPermissionState.status.isGranted) {
                                    showScanner = true
                                } else {
                                    cameraPermissionState.launchPermissionRequest()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_qr_code),
                                contentDescription = "QR-code"
                            )
                        }
                    }
                }
                ValueType.DATE -> {
                    {
                        IconButton(
                            onClick = { showDatePicker = true }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_date),
                                contentDescription = "Date"
                            )
                        }
                    }
                }
                ValueType.INTEGER, ValueType.DOUBLE -> {
                    {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_numbers),
                            contentDescription = "Integer"
                        )
                    }
                }
                else -> null
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = when (editable.type) {
                    ValueType.BARCODE, ValueType.TEXT -> KeyboardType.Text
                    ValueType.INTEGER -> KeyboardType.Number
                    ValueType.DOUBLE, ValueType.DATE -> KeyboardType.Decimal
                }
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (editable is Editable.ListEditable) {
                        editable.commitCurrent()
                    }
                }
            )
        )
    }
}

@Stable
sealed interface Editable {
    val title: String
    val type: ValueType
    val state: MutableState<TextFieldValue>
    val value: Any

    @Stable
    class SingleEditable(
        override val title: String,
        override val type: ValueType,
        value: Any? = null,
        override val state: MutableState<TextFieldValue> = mutableStateOf(
            TextFieldValue(
                text = value?.toString().orEmpty()
            )
        )
    ): Editable {

        override val value
            get() = state.value.text
    }

    @Stable
    class ListEditable(
        override val title: String,
        override val type: ValueType,
        value: List<Any> = emptyList(),
        override val state: MutableState<TextFieldValue> = mutableStateOf(
            TextFieldValue()
        )
    ): Editable {

        internal val values: MutableState<List<String>> = mutableStateOf(
            value
                .map { it.toString() }
                .toMutableList()
        )

        override val value
            get() = values.value.toList()
    }
}

internal fun Editable.ListEditable.commitCurrent() {
    add(state.value.text)
    state.value = TextFieldValue()
}

internal fun Editable.ListEditable.add(element: String) {
    val list = values.value.toMutableList()
    list.add(element.trim())
    values.value = list.toList()
}

internal fun Editable.ListEditable.remove(element: String) {
    val list = values.value.toMutableList()
    list.remove(element)
    values.value = list.toList()
}

enum class ValueType {
    BARCODE,
    TEXT,
    DATE,
    INTEGER,
    DOUBLE,
}