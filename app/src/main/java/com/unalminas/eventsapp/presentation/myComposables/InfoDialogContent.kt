package com.unalminas.eventsapp.presentation.myComposables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.presentation.ui.theme.DesertSand
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.Melon
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.PrussianBlue
import com.unalminas.eventsapp.presentation.ui.theme.Snow

@Composable
fun InfoDialogContent(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20),
        colors = CardDefaults.cardColors(
            containerColor = Snow,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(id = title),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Button(
                    onClick = onCancel,
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Snow,
                        containerColor = PrussianBlue
                    )
                ) {
                    Text(text = stringResource(id = R.string.cancel), fontFamily = LatoFont)
                }
                Button(
                    onClick = onDeleteClick,
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Snow,
                        containerColor = PrussianBlue
                    )
                ) {
                    Text(text = stringResource(id = R.string.delete), fontFamily = LatoFont)
                }
            }
        }
    }
}