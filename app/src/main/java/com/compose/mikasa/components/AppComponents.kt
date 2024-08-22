package com.compose.mikasa.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.compose.mikasa.R
import com.compose.mikasa.model.CharactersModel
import com.compose.mikasa.model.Result
import com.compose.mikasa.ui.theme.Purple40


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

@Composable
fun MiKasaList(characters: CharactersModel) {
    LazyColumn {
        items(characters.results) { character ->
            NormalTextComponent(textValue = character.name ?: "NA")
        }
    }
}

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

@Composable
fun EmptyStateComponent() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ic_android_black_24dp), contentDescription = stringResource(
            R.string.no_data_available
        )
        )
    }
}