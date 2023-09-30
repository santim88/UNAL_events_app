package com.example.pokedex

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
<<<<<<< Updated upstream:app/src/main/java/com/example/pokedex/MainActivity.kt
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
import com.example.pokedex.data.Event
=======
import com.example.pokedex.data.EventDataSource
import com.example.pokedex.data.EventDataSourceImpl
import com.example.pokedex.domain.Event
import com.example.pokedex.presentation.ui.CardEvent
>>>>>>> Stashed changes:app/src/main/java/com/example/pokedex/presentation/MainActivity.kt


//////////////////////////////use navigating//////////////////////////


///////////////////////////////end navigating/////////////////////////////////
// 1. Entities/Domain

interface GetEventDataSource {
    suspend fun getEventList(): List<Event>
}

interface SaveEventDataSource {
    fun saveEvent(event: Event)
}

interface EventDataSource : GetEventDataSource, SaveEventDataSource

//////////////////////////////Datos de prueba//////////////////////////////////////
class EventMockDataSourceImpl : EventDataSource {

    private val eventMockList = listOf(
        Event(1, "Pikachu", "ti", "minas", "16/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(2, "Charmander", "ti", "minas_2", "17/09/09/09", "2:00"),
        Event(3, "Squirtle", "ti", "minas_3", "18/09/09/09", "2:00"),
        Event(4, "Bulbasaur", "ti", "minas_4", "19/09/09/09", "2:00"),
    )

    override suspend fun getEventList(): List<Event> = eventMockList

    override fun saveEvent(event: Event) {

    }

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

//Data Source
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
    place = place ?: "",
    date = date ?: "",
    hour = hour ?: ""
)

fun Event.toEventEntity() = EventEntity(
    id = id,
    name = name,
    description = description,
    place = place,
    date = date,
    hour = hour
)
/////////////////////////////////End ROOM/////////////////////////////////


/////////////////////////////////////Reposittory/////////////////////////////////////
interface EventRepository {
    suspend fun getEventList(): List<Event>
}

class EventRepositoryImpl(
    private val context: Context,
    private val eventDataSource: EventDataSource = EventMockDataSourceImpl() //EventDataSourceImpl(context) // TODO: FIX THIS WHIT HILT
) : EventRepository {

    override suspend fun getEventList(): List<Event> {
        return eventDataSource.getEventList()
    }
}
/////////////////////////////////////END Reposittory/////////////////////////////////////

/////////////////////////////////////Reposittory/////////////////////////////////////


// 4. Presenter/UI
class MainActivity : ComponentActivity() {

    private val evenViewModel by viewModels<EventViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        evenViewModel.initDependencies(this)
        setContent {
            LaunchedEffect(Unit) {
                evenViewModel.getEventList()
            }

            val eventDataSource = EventDataSourceImpl(applicationContext)
            val eventListState by evenViewModel.eventListState.collectAsState()
            val navController = rememberNavController()
            Nav(eventListState, eventDataSource, navController)
        }
    }
}

@Composable
fun Nav(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "A") {
        composable("A") {
            ScreenMain(data, eventDataSource, navController)
        }
        composable("B") {
            ScreenDetail(navController)
        }
    }
}

@Composable
fun ScreenDetail(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Screen B click to pass C", fontSize = 64.sp)
        Spacer(modifier = Modifier.height(45.dp))
        Button(onClick = {
            navController.navigate("A") {
                popUpTo("A") { inclusive = true }
            }
        }) {
            Text(text = "Go to screen C", fontSize = 40.sp)
        }

    }
}

@Composable
fun ScreenMain(
    data: List<Event>,
    eventDataSource: EventDataSource,
    navController: NavHostController
) {
    /*    val eventRepository = EventRepository()*/
    /*    val getAllData = eventRepository.getAllData()*/
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(items = data) { event ->
                CardEvent(event = event) {
                    navController.navigate("B")
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = {
                navController.navigate("B") {
                    popUpTo("B") { inclusive = true }
                }
            },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Composable
fun SaveEventButton(eventDataSource: EventDataSource) {
    Button(onClick = {
        val event = Event(
            id = 100,
            name = "Event 1",
            description = "Event description",
            place = "M2",
            date = "03/02/2023",
            hour = "12:40"
        )
        eventDataSource.saveEvent(event)
    }) {
        Text("Save Event")
    }
}

