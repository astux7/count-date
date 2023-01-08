package com.basta.counter.screen

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
import com.basta.counter.navigation.Directions
import com.basta.counter.components.ContentWrapper
import com.basta.counter.viewmodel.DateCounterVM
import org.koin.androidx.compose.getViewModel
import com.basta.counter.R

@Composable
fun HomePage(navController: NavController = NavController(LocalContext.current)) {
    val vm: DateCounterVM = getViewModel()

    ContentWrapper {
        if (vm.titleText.isNullOrBlank()) {
            Text(stringResource(id = R.string.welcome_text_label), color = Color.White)
        } else {
            Text(
                stringResource(id = R.string.welcome_text_string_label, vm.titleText.orEmpty()),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (!vm.leftTime.value.isNullOrBlank())
            Text(vm.leftTime.value.orEmpty(), textAlign = TextAlign.Center, color = Color.White)

        if (vm.timeAdded.isNullOrBlank() || vm.timeEnded()) {

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .heightIn(min = 50.dp),
                onClick = { navController.navigate(Directions.enterDate.name) }) {
                Text(text = stringResource(id = R.string.start_action))
            }

        }
    }
}

