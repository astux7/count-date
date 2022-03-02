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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.astux7.counter.R
import com.astux7.counter.viewmodel.DateCounterViewModel
import org.koin.androidx.compose.getViewModel
import com.astux7.counter.components.ContentWrapper
import com.astux7.counter.navigation.Directions
import com.astux7.counter.ui.theme.Purple500
import com.astux7.counter.utils.showTimePicker
import com.astux7.counter.utils.toDate
import com.google.android.material.textfield.TextInputEditText

@Composable
fun EnterDatePage(navController: NavController = NavController(LocalContext.current)) {
    val vm: DateCounterViewModel = getViewModel()
    var dateString by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    ContentWrapper {
        Text(stringResource(id = R.string.enter_date_label), color = Color.White)
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        var title by remember { mutableStateOf(TextFieldValue("")) }
        TextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = { Text(text = stringResource(id = R.string.kick_off_label), color = Color.White) },
          //  placeholder = { Text(text = "Add your kick off", color = Color.White) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.White,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = Color.White,
                focusedBorderColor = Purple500
            ),

        )

        Spacer(
            Modifier
                .fillMaxWidth()
                .height(10.dp)
        )
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
                textColor = Color.White,
                backgroundColor = Color.Transparent,
                unfocusedBorderColor = Color.White
            ),
            label = { Text(stringResource(id = R.string.date_label), color = Color.White) },
            placeholder = {
                Text(
                    stringResource(id = R.string.select_date_hint),
                    color = Color.White
                )
            }
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            .heightIn(min = 50.dp),
            enabled = dateString.isNotEmpty(),
            colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.Gray),
            onClick = {
                vm.save(dateString, title.text)
                navController.navigate(Directions.home.name)
            }) {
            Text(text = stringResource(id = R.string.start_action))
        }
    }
}