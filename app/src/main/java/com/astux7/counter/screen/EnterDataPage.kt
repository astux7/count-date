package com.astux7.counter.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.astux7.counter.R
import com.astux7.counter.viewmodel.DateCounterVM
import org.koin.androidx.compose.getViewModel
import com.astux7.counter.components.ContentWrapper
import com.astux7.counter.components.HeadedTextField
import com.astux7.counter.navigation.Directions
import com.astux7.counter.utils.showTimePicker
import com.astux7.counter.utils.toDate

@Composable
fun EnterDatePage(navController: NavController = NavController(LocalContext.current)) {
    val vm: DateCounterVM = getViewModel()
    var dateString by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }

    ContentWrapper {
        Text(
            stringResource(id = R.string.enter_date_label),
            color = MaterialTheme.colors.secondary
        )

        HeadedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { title = it },
            readOnly = false,
            label = { Text(stringResource(id = R.string.title_label), color = MaterialTheme.colors.secondary) }
        )

        Spacer(Modifier.height(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    context.showTimePicker {
                        dateString = it.toDate()
                    }
                },
            readOnly = true,
            enabled = false,
            value = dateString,
            onValueChange = {},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.secondary,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = MaterialTheme.colors.secondary
            ),
            label = { Text(stringResource(id = R.string.date_label), color = MaterialTheme.colors.secondary) },
            placeholder = {
                Text(
                    stringResource(id = R.string.select_date_hint),
                    color = MaterialTheme.colors.secondary
                )
            }
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .heightIn(min = 50.dp),
            enabled = vm.validateDate(dateString),
            colors = ButtonDefaults.buttonColors(disabledBackgroundColor = MaterialTheme.colors.onSecondary),
            onClick = {
                vm.save(dateString, title)
                navController.navigate(Directions.home.name)
            }) {
            Text(text = stringResource(id = R.string.start_action))
        }
    }
}