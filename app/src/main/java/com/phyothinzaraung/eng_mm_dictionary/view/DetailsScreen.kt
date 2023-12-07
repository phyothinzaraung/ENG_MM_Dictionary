package com.phyothinzaraung.eng_mm_dictionary.view

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.phyothinzaraung.eng_mm_dictionary.data.Dictionary
import com.phyothinzaraung.eng_mm_dictionary.data.Favorite
import com.phyothinzaraung.eng_mm_dictionary.viewmodel.DictionaryViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

@Composable
fun DetailsScreen(stripWord: String, viewModel: DictionaryViewModel, navController: NavController) {

    var dictionary by remember { mutableStateOf<Dictionary?>(null) }
    val favorites by viewModel.favorites.collectAsState(initial = emptyList())
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(stripWord) {
        dictionary = viewModel.getDictionaryByStripWord(stripWord).firstOrNull()
        viewModel.getFavorites()
        isFavorite = favorites.any { it.stripword == stripWord }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stripWord,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    ),
                    modifier = Modifier
                        .weight(1f)
                )

                Image(
                    painter = painterResource(
                        if (isFavorite) {
                            android.R.drawable.btn_star_big_on
                        } else {
                            android.R.drawable.btn_star_big_off
                        }
                    ),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .size(36.dp)
                        .clickable(
                            onClick = {
                                isFavorite = !isFavorite
                                toggleFavoriteStatus(isFavorite, dictionary, viewModel)
                            }
                        )
                )
            }

            dictionary?.let { dict ->
                LazyColumn(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        HtmlTextView(htmlContent = dict.definition.orEmpty())
                    }

                    item {
                        if (!dict.synonym.isNullOrBlank()) {
                            Text(
                                text = "Synonyms:",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }

                    item {
                        if (!dict.synonym.isNullOrBlank()) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "${dict.synonym}",
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }
                    }

                    item {
                        if (!dict.keywords.isNullOrBlank()) {
                            Text(
                                text = "Related Keywords:",
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            )
                        }
                    }

                    item {
                        if (!dict.keywords.isNullOrBlank()) {
                            val keywords = dict.keywords!!.split(",").map { it.trim() }

                            val chunkedKeywords =
                                keywords.chunked(5)

                            chunkedKeywords.forEach { rowKeywords ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    rowKeywords.forEach { keyword ->
                                        ClickableText(
                                            text = AnnotatedString(keyword),
                                            onClick = {
                                                navController.navigate("details/$keyword")
                                            },
                                            style = TextStyle(
                                                color = Color.Blue,
                                                textDecoration = TextDecoration.Underline
                                            ),
                                            modifier = Modifier.padding(4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun toggleFavoriteStatus(isFavorite: Boolean, dictionary: Dictionary?, viewModel: DictionaryViewModel) {
    val favorite = dictionary?.let {
        Favorite(
            it.id,
            it.word,
            it.stripWord
        )
    }

    favorite?.let {
        if (isFavorite) {
            viewModel.insertFavorite(favorite)
        } else {
            viewModel.deleteFavorite(favorite)
        }
    }
}