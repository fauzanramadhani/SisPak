package com.ndc.sispak.ui.component.textfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.ndc.sispak.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {},
) {
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    BaseOutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholder = "Cari Metode",
        leadingIcon = {
            Icon(
                painter = painterResource(
                    id = R.drawable.ic_search
                ),
                contentDescription = "",
                tint = color.secondary
            )
        },
        trailingIcon = if (value.isNotEmpty()) {    
            {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_x_outlined
                    ),
                    contentDescription = "",
                    tint = color.secondary,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            onValueChange("")
                        }
                )
            }
        } else null
    )
    
}