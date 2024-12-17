package com.ndc.sispak.ui.component.sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ndc.sispak.R
import com.ndc.sispak.ui.component.button.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerSheet(
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
    datePickerState: DatePickerState = rememberDatePickerState(),
    onDismiss: () -> Unit = {},
    onSelect: () -> Unit = {}
) {
    val color = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    ModalBottomSheet(
        modifier = modifier
            .padding(bottom = 49.dp),
        sheetState = sheetState,
        shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
        containerColor = color.background,
        onDismissRequest = onDismiss,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(vertical = 12.dp)
        ) {
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier
                            .padding(12.dp),
                        text = stringResource(id = R.string.select_date),
                        style = typography.titleSmall,
                        color = color.onBackground
                    )
                },
                showModeToggle = false
            )
            PrimaryButton(
                text = stringResource(id = R.string.select),
                onClick = onSelect,
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth()
            )
        }
    }
}