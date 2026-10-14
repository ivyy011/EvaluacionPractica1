
package udg.cuvalles.evaluacionpractica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import udg.cuvalles.evaluacionpractica.ui.theme.EvaluacionPracticaTheme


val RosaPastel = Color(0xFFF8D7E8)
val RosaFuerte = Color(0xFFD65A98)
val RosaClaro = Color(0xFFFFEDF5)
val GrisClaro = Color(0xFFF0F0F0)
val GrisOscuro = Color(0xFF444444)


val personas = listOf(
    Data(R.drawable.theweeknd, "The Weeknd", "Cantante de R&B y pop", 8400),
    Data(R.drawable.theneighbourhood, "The Neighbourhood", "Banda de rock alternativo", 10000),
    Data(R.drawable.thebeatles, "The Beatles", "Banda de rock británica", 9500),
    Data(R.drawable.thejoji, "Joji", "Cantante de R&B alternativo", 8700),
    Data(R.drawable.ivancornejo, "Iván Cornejo", "Cantante de regional mexicano", 9200),
    Data(R.drawable.mon, "Mon Laferte", "Cantautora chileno-mexicana", 7800),
    Data(R.drawable.mon, "José José", "El Príncipe de la Canción", 9900),
    Data(R.drawable.billi, "Billie Eilish", "Cantante de pop alternativo", 9800),
    Data(R.drawable.theweeknd, "Abel Tesfaye", "Artista y compositor canadiense", 8900),
    Data(R.drawable.ivancornejo, "Iván Cornejo - Música", "Artista de música sierreña", 8500)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EvaluacionPracticaTheme() {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = RosaClaro
                ) { innerPadding ->
                    MyColum(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyColum(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RosaClaro)
    ) {
        Text(
            text = "Mis artistas favoritos 💗",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = RosaFuerte,
            modifier = Modifier.padding(
                start = 16.dp,
                top = 12.dp,
                bottom = 4.dp
            )
        )

        TarjetaPersona(personas[0])

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = RosaPastel
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            item {
                Text(
                    text = "Lista Horizontal",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RosaFuerte,
                    modifier = Modifier.padding(16.dp)
                )

                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(personas.take(8)) { persona ->
                        Card(
                            modifier = Modifier.width(160.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = RosaPastel
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = persona.foto),
                                    contentDescription = persona.nombre,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(12.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = persona.nombre,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = GrisOscuro,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = persona.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = GrisOscuro,
                                    textAlign = TextAlign.Center
                                )

                                Text(
                                    text = "Likes: ${persona.likes}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = RosaFuerte
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Lista Vertical",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = RosaFuerte,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(
                items = personas,
                key = { it.nombre }
            ) { persona ->
                TarjetaPersona(persona)
            }
        }
    }
}

@Composable
fun TarjetaPersona(persona: Data) {
    var mostrarTexto by remember(persona.nombre) {
        mutableStateOf(false)
    }

    var seleccionado by remember(persona.nombre) {
        mutableStateOf(false)
    }

    val colorCard by animateColorAsState(
        targetValue = if (seleccionado) {
            RosaPastel
        } else {
            GrisClaro
        },
        animationSpec = tween(durationMillis = 500),
        label = "colorCard"
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                seleccionado = !seleccionado
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorCard
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = persona.foto),
                contentDescription = persona.nombre,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = persona.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = GrisOscuro
                )

                AnimatedVisibility(visible = mostrarTexto) {
                    Column {
                        Text(
                            text = persona.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = GrisOscuro
                        )

                        Text(
                            text = "Likes: ${persona.likes}",
                            style = MaterialTheme.typography.bodySmall,
                            color = RosaFuerte
                        )
                    }
                }

                Button(
                    onClick = {
                        mostrarTexto = !mostrarTexto
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = RosaFuerte,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = if (mostrarTexto) "Ocultar" else "Mostrar"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EvaluacionPracticaTheme() {
        MyColum()
    }
}
