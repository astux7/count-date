package com.astux7.counter.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astux7.counter.R
import com.astux7.counter.viewmodel.DateCounterViewModel
import org.koin.androidx.compose.getViewModel
import com.astux7.counter.components.ContentWrapper
import com.astux7.counter.navigation.Directions


@Composable
fun EnterDatePage(navController: NavController = NavController(LocalContext.current)) {
    val vm: DateCounterViewModel = getViewModel()
    var dateString by rememberSaveable { mutableStateOf(vm.timeAdded.value ?: "") }

    ContentWrapper {
        Text(stringResource(id = R.string.enter_label))
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
        OutlinedTextField(
            value = dateString,
            onValueChange = {
                dateString = it
            },
            label = { Text(stringResource(id = R.string.date_label)) }
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            onClick = {
                vm.save(dateString)
            }) {
            Text(text = stringResource(id = R.string.leave_label))
        }
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
            onClick = {
                navController.navigate("${Directions.home.name}")
            }) {
            Text(text = stringResource(id = R.string.back_label))
        }
    }
}