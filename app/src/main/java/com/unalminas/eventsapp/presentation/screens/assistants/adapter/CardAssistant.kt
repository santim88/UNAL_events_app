package com.unalminas.eventsapp.presentation.screens.assistants.adapter

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.unalminas.eventsapp.domain.Assistant
import com.unalminas.eventsapp.presentation.ui.theme.NunitoFont
import com.unalminas.eventsapp.presentation.ui.theme.OxfordBlue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardAssistant(
    modifier: Modifier = Modifier,
    index: Int = 0,
    item: Assistant
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = index.toString(),
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier
                    .padding(start = 16.dp, end = 4.dp)
                    .weight(1f)
                    .basicMarquee(),
                text = item.name,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
                    .basicMarquee(),
                text = item.identification,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f)
                    .basicMarquee(),
                text = item.email,
                fontFamily = NunitoFont,
                fontWeight = FontWeight.Bold,
                color = OxfordBlue
            )
        }
    }
}
