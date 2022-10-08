package com.vickikbt.devtyme.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickikbt.devtyme.android.R

@Composable
fun WeatherInfoView(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        //region Title
        Text(
            text = "$title: ",
            color = MaterialTheme.colors.onSurface,
            fontSize = 15.sp,
            style = MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
        //endregion

        Spacer(modifier = Modifier.width(20.dp))

        //region SubTitle
        Text(
            text = subTitle,
            color = colorResource(id = R.color.red),
            fontSize = 14.sp,
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )
        //endregion
    }
}

@Preview
@Composable
fun WeatherPreview() {
    WeatherInfoView(title = "Hello", subTitle = "Victor")
}
