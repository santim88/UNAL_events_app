package com.example.pokedex.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokedex.R

@Preview
@Composable
fun ButtonFullWidth(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
){
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
    ) {
        Text(text = "TOMAR ASISTENCIA", fontSize = 8.sp)
    }
}

@Preview
@Composable
fun EditButtons(
    modifier: Modifier = Modifier,
    onEditClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
){
    Row(
        modifier = modifier
    ) {
        IconButton(modifier = Modifier, onClick = onEditClick) {
            Image(
                modifier = Modifier.size(26.dp),
                painter = painterResource(R.drawable.baseline_edit_24),
                contentDescription = "edit"
            )
        }
        IconButton(modifier = Modifier, onClick = onDeleteClick) {
            Image(
                modifier = Modifier.size(26.dp),
                painter = painterResource(R.drawable.baseline_delete_24),
                contentDescription = "delete"
            )
        }
    }
}