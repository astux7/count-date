package com.astux7.counter.screen

import android.graphics.Paint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
        Text(stringResource(id = R.string.count_text))
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(10.dp))
        if (vm.timeAdded.value.isNullOrBlank()) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),
                onClick = {
                    navController.navigate("${Directions.enterDate.name}")
                }) {
                Text(text = stringResource(id = R.string.leave_label))
            }

        } else {
            Text(stringResource(id = R.string.time_remain))
            Text(vm.leftTime.value ?: "" , textAlign = TextAlign.Center)
        }
    }

}

