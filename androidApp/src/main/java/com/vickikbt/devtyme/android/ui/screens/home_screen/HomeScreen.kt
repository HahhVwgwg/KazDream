package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vickikbt.devtyme.android.R
import com.vickikbt.devtyme.android.ui.components.CityItemView
import com.vickikbt.devtyme.android.ui.components.DatesTabs
import com.vickikbt.devtyme.android.ui.components.HomeToolbar
import com.vickikbt.devtyme.android.ui.components.WeatherInfo
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = getViewModel()) {

    LaunchedEffect(key1 = true) {
        viewModel.getCurrentUserProfile()
        viewModel.getTimeOfDay()
        viewModel.getCurrentDate()
    }

    val currentUserProfile by viewModel.currentUser.observeAsState()
    val greetingMessage by viewModel.greetingMessage.observeAsState()
    val daysOfWeek = viewModel.daysOfWeek.observeAsState()
    val cities by viewModel.citiesList.observeAsState()
    val weather by viewModel.weather.observeAsState()

    var selectedDate = viewModel.selectedDate

    var selectedCity = viewModel.selectedCity

    Scaffold(
        topBar = {
            HomeToolbar(
                title = "$greetingMessage ${
                    currentUserProfile?.user?.displayName?.substringBefore(" ")
                        ?.trim() ?: currentUserProfile?.user?.username
                }",
                subTitle = currentUserProfile?.user?.bio.orEmpty(),
                profileImageUrl = currentUserProfile?.user?.photo.orEmpty()
            )
        }
    ) {
        Column {

            //region Dates Tabs
            daysOfWeek.value?.let { dates ->
                DatesTabs(
                    modifier = Modifier.fillMaxWidth(),
                    dates = dates,
                    selectedTab = selectedDate,
                    onTabItemClick = {
                        selectedDate = it
                        viewModel.onDateChanged(selectedDate)
                    }
                )
            }
            //endregion

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Spacer(modifier = Modifier.height(20.dp))

                WeatherInfo(
                    modifier = Modifier,
                    title = stringResource(R.string.temperature),
                    subTitle = weather?.main?.temp.toString()
                )

                WeatherInfo(
                    modifier = Modifier,
                    title = stringResource(R.string.feels_like),
                    subTitle = weather?.main?.feelsLike.toString()
                )

                WeatherInfo(
                    modifier = Modifier,
                    title = stringResource(R.string.pressure),
                    subTitle = weather?.main?.pressure.toString()
                )

                WeatherInfo(
                    modifier = Modifier,
                    title = stringResource(R.string.humidity),
                    subTitle = weather?.main?.humidity.toString()
                )

                Text(
                    text = stringResource(R.string.title_cities),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.h5,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )

                cities?.let {
                    LazyColumn(
                        modifier = Modifier,
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        items(items = it, itemContent = { city ->
                            CityItemView(
                                modifier = Modifier.clickable {
                                    selectedCity = city.name
                                    viewModel.onCitySelected(city)
                                },
                                title = city.name,
                                selected = city.name == selectedCity,
                                image = city.image
                            )
                        })
                    }
                }
                //endregion
            }
        }
    }
}
