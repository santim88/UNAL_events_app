package com.example.pokedex

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// 1. Entities/Domain

data class Event(
    val id: Int,
    val name: String,
    val description: String,
    val place: String,
    val date: String,
    val hour: String
)

interface GetEventDataSource {
    suspend fun getEventList(): List<Event>
}

interface SaveEventDataSource {
    fun saveEvent(event: Event)
}

interface EventDataSource : GetEventDataSource, SaveEventDataSource

//////////////////////////////Datos de prueba//////////////////////////////////////
class GetEventMockDataSourceImpl : GetEventDataSource {

    private val eventMockList = listOf(
        Event(1, "Pikachu", "ti","minas", "16/09/09/09", "2:00"),
        Event(2, "Charmander", "ti","minas_2", "17/09/09/09", "2:00"),
        Event(3, "Squirtle", "ti","minas_3", "18/09/09/09", "2:00"),
        Event(4, "Bulbasaur", "ti","minas_4", "19/09/09/09", "2:00"),
    )

    override suspend fun getEventList(): List<Event> = eventMockList

}
//Data Source
class GetEventDataSourceImpl(private val mockDataSource: GetEventMockDataSourceImpl) : GetEventDataSource {

    override suspend fun getEventList(): List<Event> = mockDataSource.getEventList()
}

////////////////////////////// ROOM //////////////////////////////
@Entity(tableName = "events")
data class EventEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "place") val place: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "hour") val hour: String?,
)

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAll(): List<EventEntity>

    @Query("SELECT * FROM events WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg users: EventEntity)

    @Delete
    fun delete(user: EventEntity)
}

@Database(entities = [EventEntity::class], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun EventDao(): EventDao
}

class EventDataSourceImpl(
    private val applicationContext: Context
) : EventDataSource {

    private val db = Room.databaseBuilder(
        applicationContext,
        EventDatabase::class.java, "events_db"
    ).build()

    private val eventDao = db.EventDao()
    override suspend fun getEventList(): List<Event> = eventDao.getAll().map {
        it.toEvent()
    }

    override fun saveEvent(event: Event) {
        eventDao.insertAll(
            event.toEventEntity()
        )
    }

}

fun EventEntity.toEvent() = Event(
    id = id ?: 0,
    name = name ?: "",
    description = description,
    place= place ?: "",
    date= date ?:"",
    hour= hour ?: ""
)

fun Event.toEventEntity() = EventEntity(
    id = id,
    name = name,
    description = description,
    place= place,
    date= date ,
    hour= hour
)
/////////////////////////////////End ROOM/////////////////////////////////



/////////////////////////////////////Reposittory/////////////////////////////////////
interface EventRepository {
    suspend fun getEventList(): List<Event>
}

class EventRepositoryImpl(
    private val getEventDataSource: GetEventDataSourceImpl = GetEventDataSourceImpl(GetEventMockDataSourceImpl()) // TODO: FIX THIS WHIT HILT
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return getEventDataSource.getEventList()
    }
}
/////////////////////////////////////END Reposittory/////////////////////////////////////

// 4. Presenter/ViewModel
class EventViewModel : ViewModel() {

    // TODO: FIX THIS WITH HILT
    private val eventRepository: EventRepository = EventRepositoryImpl()

    private val _pokemonListState = MutableStateFlow(listOf<Event>())
    val pokemonListState = _pokemonListState.asStateFlow()

    private val _pokemonFavoriteListState = MutableStateFlow(listOf<Event>())
    val pokemonFavoriteListState = _pokemonFavoriteListState.asStateFlow()

    fun getEventList() {
        viewModelScope.launch(Dispatchers.IO) {
            _pokemonListState.value = eventRepository.getEventList()
            //_pokemonFavoriteListState.value =
              //  pokemonFavoritesRepository?.getPokemonList() ?: emptyList()
        }
    }
}
/////////////////////////////////////Reposittory/////////////////////////////////////



// 4. Presenter/UI
class MainActivity : ComponentActivity() {

    private val EvenViewModel by viewModels<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      /*  EvenViewModel.initDependencies(this)*/
        setContent {
         LaunchedEffect(Unit) {
                EvenViewModel.getEventList()
            }
            val pokemonListState by EvenViewModel.pokemonListState.collectAsState()
            MainActivityContent(pokemonListState)
        }
    }
}

//////////////////////////////PersonalCode///////////////////////////////////////////////////////

@Preview(showBackground = true)
@Composable
fun MyScreenPreview() {
    val sampleEvents = listOf(
        Event(id = 1, name = "Sample Event 1", description = "Description 1", place = "Place 1", date = "Date 1", hour = "Hour 1"),
        Event(id = 2, name = "Sample Event 2", description = "Description 2", place = "Place 2", date = "Date 2", hour = "Hour 2")
    )
    MainActivityContent(sampleEvents)
}

@Composable
fun MainActivityContent(data: List<Event>) {
    /*    val eventRepository = EventRepository()*/
    /*    val getAllData = eventRepository.getAllData()*/
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(items = data) { event ->
            CardEvent(event = event)
        }
    }
}

@Preview
@Composable
fun CardEvent(
    modifier: Modifier = Modifier,
    event: Event = Event(
        id = 100,
        name = "Event 1",
        description = "Event description",
        place = "M2",
        date = "03/02/2023",
        hour = "12:40"
    ),
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)) {
        Column(Modifier.fillMaxWidth()) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    text = "${event.name} | ${event.place}",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
                    /*  fontWeight = FontWeight.Bold,*/
                    /*   fontSize = MaterialTheme.typography.headlineMedium*/
                )
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd),
//                horizontalArrangement = Arrangement.End,
                ) {
                    IconButton(modifier = Modifier, onClick = {}) {
                        Image(
                            painterResource(R.drawable.baseline_edit_24),
                            contentDescription = "like",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    IconButton(modifier = Modifier, onClick = { }) {
                        Image(
                            painterResource(R.drawable.baseline_delete_24),
                            contentDescription = "like",
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
            Column(Modifier.padding(horizontal = 10.dp)) {
                Text(
                    text = event.date,
                    color = Color.Black,
                    /*       fontWeight = FontWeight.Normal,*/
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = event.hour,
                    color = Color.Black,
                    fontWeight = FontWeight.Normal,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Button(
                onClick = onClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            ) {
                Text(text = "TOMAR ASISTENCIA", fontSize = 8.sp)
            }
        }
    }
}

/*
@Preview
@Composable
fun PokemonFavorite(
    pokemon: Pokemon = Pokemon(0, "Pikachu")
) {
    Text(
        modifier = Modifier.padding(8.dp),
        text = pokemon.name
    )
}


@Preview
@Composable
fun PokemonRow(
    pokemon: Pokemon = Pokemon(0, "Pikachu"),
    isLiked: Boolean = false,
    onLikeClick: (Pokemon) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterStart),
            text = pokemon.name
        )
        IconButton(
            modifier = Modifier.align(Alignment.CenterEnd),
            onClick = { onLikeClick(pokemon) }
        ) {
            Icon(
                modifier = Modifier,
                imageVector = Icons.Sharp.Favorite,
                contentDescription = "like",
                tint = if (isLiked) Color.Red else Color.Gray
            )
        }
    }
}*/
