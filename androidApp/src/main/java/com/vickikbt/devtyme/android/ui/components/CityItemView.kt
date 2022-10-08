package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.vickikbt.devtyme.android.R

@Composable
fun CityItemView(modifier: Modifier, title: String, image: String, selected: Boolean) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = 0.dp,
        shape = RoundedCornerShape(6.dp),
        backgroundColor = colorResource(id = if (selected) R.color.cardBackgroundSelected else R.color.cardBackground)
    ) {

        Row(
            modifier = Modifier
                .padding(vertical = 6.dp, horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                fontSize = 16.sp,
                style = MaterialTheme.typography.h5,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start
            )

            //region Profile Picture
            val profileImagePainter = rememberImagePainter(data = image) {
                placeholder(R.drawable.ic_launcher_foreground)
                crossfade(true)
            }

            ItemCircleImage(
                modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp),
                image = profileImagePainter,
                contentDescription = stringResource(R.string.profile_image),
            )
            //endregion
        }
    }
}
