package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue

@Preview
@Composable
fun CardAssistant(
    modifier: Modifier = Modifier,
    index: Int = 0,
    item: Assistant = Assistant(
        name = "pepito",
        identification = "2231132",
        email = "dsf,af@gmail.co"
    )
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(11.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = index.toString(),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f),
                text = item.name,
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier.weight(1f),
                text = item.identification,
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier.weight(1f),
                text = item.email,
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
        }
    }
}

//@Composable
//fun Demo_DropDownMenu() {
//    val context = LocalContext.current
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxWidth(0.2f)
//            .wrapContentSize(Alignment.TopEnd)
//    ) {
//        IconButton(onClick = { expanded = !expanded }) {
//            Icon(
//                imageVector = Icons.Default.MoreVert,
//                contentDescription = "More"
//            )
//        }
//
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(
//                text = { Text("Edit") },
//                onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() }
//            )
//            DropdownMenuItem(
//                text = { Text("Delete") },
//                onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() }
//            )
//        }
//    }
//}