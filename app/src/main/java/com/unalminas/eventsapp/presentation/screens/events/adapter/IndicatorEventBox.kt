package com.unalminas.eventsapp.presentation.screens.events.adapter


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.unalminas.eventsapp.R
import com.unalminas.eventsapp.domain.Event
import com.unalminas.eventsapp.presentation.Screen
import com.unalminas.eventsapp.presentation.ui.theme.DesertSand
import com.unalminas.eventsapp.presentation.ui.theme.LatoFont
import com.unalminas.eventsapp.presentation.ui.theme.LavenderBlush
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue
import com.unalminas.eventsapp.presentation.ui.theme.PrussianBlue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IndicatorEventBox(
    navController: NavController,
    event: Event,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20))
            .background(DesertSand)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 2.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .basicMarquee(),
                text = stringResource(id = R.string.event_name_format, event.name),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 8.dp),
                text = stringResource(id = R.string.event_date_format, event.date),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue,
                style = TextStyle(fontSize = 16.sp, color = Color.Gray),
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                text = stringResource(id = R.string.event_place_format, event.place),
                fontFamily = LatoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue,
                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
            )
        }
        Column(
            modifier = Modifier.padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            IconButton(modifier = Modifier
                .padding(5.dp)
                .size(80.dp),
                onClick = {
                    val screen = Screen.CreateAssistantCameraScreen(event.id.toString())
                    navController.navigate(screen.createRoute())
                }
            )
            {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Filled.AddPhotoAlternate,
                    contentDescription = "add photo",
                    tint = PrussianBlue
                )
            }
        }
    }
}
