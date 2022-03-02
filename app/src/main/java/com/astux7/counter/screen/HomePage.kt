package com.astux7.counter.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.astux7.counter.R
import com.astux7.counter.navigation.Directions
import com.astux7.counter.components.ContentWrapper
import com.astux7.counter.viewmodel.DateCounterViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomePage(navController: NavController = NavController(LocalContext.current)) {
    val vm: DateCounterViewModel = getViewModel()

    ContentWrapper {
        if (vm.titleText.isNullOrBlank()) {
            Text(stringResource(id = R.string.welcome_text_label), color = Color.White)
        } else {
            Text(stringResource(id = R.string.welcome_text_string_label, vm.titleText.orEmpty()), color = Color.White)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))

        if (vm.timeAdded.isNullOrBlank()) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
                onClick = { navController.navigate(Directions.enterDate.name) }) {
                Text(text = stringResource(id = R.string.start_action))
            }
        } else {

            Text(stringResource(id = R.string.time_remain_label), color = Color.White)

            Text(vm.leftTime.value.orEmpty(), textAlign = TextAlign.Center, color = Color.White)
        }
    }
}

