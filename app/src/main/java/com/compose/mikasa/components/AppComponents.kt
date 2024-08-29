package com.compose.mikasa.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.mikasa.R
import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.model.Result
import com.compose.mikasa.ui.theme.Purple40

/**
 * This fun works as a loader for the informacion of the API
 */
@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp),
            color = Purple40,
            //strokeWidth = 10.dp
        )
    }

}

/**
 * this fun unique fun for the list of characters but it is not used
 */
@Composable
fun MiKasaList(characters: CharactersModel) {
    LazyColumn {
        items(characters.results) { character ->
            NormalTextComponent(textValue = character.name ?: "NA")
        }
    }
}

/**
 * this fun works as a text component, with a Normalized style
 */
@Composable
fun NormalTextComponent(textValue: String) {
    Text(
        modifier = Modifier
            //.fillMaxSize()
            .wrapContentHeight()
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace
        )
    )
}

/**
 * this fun works as a text component, with a Heading style
 */
@Composable
fun HeadingTextComponent(textValue: String) {
    Text(
        modifier = Modifier
            .padding(8.dp),
        text = textValue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

/**
 * this card is another way for use card in compose
 */
@Composable
fun MyCard(page: Int, character: Result) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .width(300.dp)
                .height(300.dp),//.padding(15.dp,15.dp,15.dp,15.dp),
            //,
            // shape = CutCornerShape(20.dp)
            elevation = CardDefaults.cardElevation(10.dp),
            //border = BorderStroke(3.dp,Color.Gray)
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(
                    modifier = Modifier
                        .height(240.dp),
                    model = character.img,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.eren_image)
                )
                Text(
                    text = "Name: ${character.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                )
                //HeadingTextComponent(textValue = "Name: ${character.name}")

            }

        }
    }
}

/**
 * this fun works as a column component for the list of characters
 */
@Composable
fun MiKasaRowComponent(page: Int, character: Result) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .height(240.dp),
            model = character.img,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            error = painterResource(id = R.drawable.eren_image)
        )
        HeadingTextComponent(textValue = "Name: ${character.name}")
        Spacer(modifier = Modifier.height(5.dp))
        character.alias!!.forEach { alias ->
            NormalTextComponent(textValue = "Alias: $alias")
        }
        character.species!!.forEach { specie ->
            NormalTextComponent(textValue = "Specie: $specie")
        }
        character.groups!!.forEach { groups ->
            groups.sub_groups.forEach { subGroups ->
                GroupComponent(groups.name, subGroups)
            }

        }

        character.relatives!!.forEach { relative ->
            relative.members.forEach { members ->
                RelativeComponent(relative.family, members)
            }

        }


    }
}

/**
 * this fun works as a card component for the list of characters, it's the main using for this example
 */
@Composable
fun CardLikeMovies(itemIndex: Int, character: List<Result>) {
    Card(
        Modifier
            .wrapContentSize()
            .padding(10.dp)
            .clickable {
            },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            AsyncImage(
                model = character[itemIndex].img,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
                error = painterResource(id = R.drawable.eren_image)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.LightGray.copy(.7f))
                    .padding(6.dp)
            ) {
                Text(
                    text = "Name: ${character[itemIndex].name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    maxLines = 1,
                    style = TextStyle(
                        shadow = Shadow(
                            Color(0xFFFC6603),
                            offset = Offset(1f, 1f),
                            3f
                        )
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.align(Alignment.End)) {
                        Icon(imageVector = Icons.Rounded.Star, contentDescription = "")
                    Text(
                        text = "Occupation: ${character[itemIndex].occupation}",
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 8.dp),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

/**
 * this fun take a group textViews, and show the data of the group
 */
@Composable
fun GroupComponent(name: String?, sub_groups: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {
        name.also {
            Text(text = "Relative: $it")
        }
        Spacer(modifier = Modifier.height(5.dp))
        sub_groups.also {
            Text(text = it!!)
        }
    }
}

/**
 * this fun make a relative textViews, and show the data of the relative
 */
@Composable
fun RelativeComponent(name: String?, sub_groups: String?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {

        name.also {
            Text(text = "Relative: $it")
        }
        Spacer(modifier = Modifier.height(5.dp))
        sub_groups.also {
            Text(text = it!!)
        }
    }
}

@Composable
fun MiKasaRowComponentPreview() {
    val character = com.compose.mikasa.model.Result(
        1,
        name = "Miksa San",
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
    MiKasaRowComponent(0, character)
}

/**
 * this fun show a empty state when the list is empty
 */
@Composable
fun EmptyStateComponent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = stringResource(
                R.string.no_data_available
            )
        )
    }
}