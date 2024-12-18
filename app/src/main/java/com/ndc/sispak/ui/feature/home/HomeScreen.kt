package com.ndc.sispak.ui.feature.home

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ndc.sispak.R
import com.ndc.sispak.common.Either
import com.ndc.sispak.ui.component.app_bar.PrimaryAppBar
import com.ndc.sispak.ui.component.card.MyExpertSystemCard
import com.ndc.sispak.ui.component.empty.EmptyItem
import com.ndc.sispak.ui.component.textfield.SearchTextField
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    stateFlow: Flow<HomeState>,
    effectFlow: Flow<Either<HomeEffect>>,
    action: (HomeAction) -> Unit
) {
    val ctx = LocalContext.current
    val typography = MaterialTheme.typography
    val color = MaterialTheme.colorScheme

    val state by stateFlow.collectAsState(initial = HomeState())
    val effect by effectFlow.collectAsState(initial = Either.left())

    BackHandler {
        (ctx as Activity).finish()
    }

    Scaffold(
        modifier = modifier
            .navigationBarsPadding(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = color.primaryContainer,
                onClick = {

                }
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(start = 12.dp, end = 24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(id = R.string.cd_ic_pencil),
                        tint = color.onPrimaryContainer,
                        modifier = modifier
                            .size(24.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.create),
                        style = typography.labelLarge,
                        color = color.onPrimaryContainer,
                    )
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item {
                Column {
                    PrimaryAppBar(
                        modifier = modifier
                            .padding(paddingValues.calculateTopPadding())
                    )
                    Column(
                        modifier = modifier.padding(horizontal = 12.dp, vertical = 24.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.my_expert_system),
                            style = typography.titleSmall,
                            color = color.onBackground
                        )
                        Spacer(modifier = Modifier.padding(bottom = 4.dp))
                        Text(
                            text = stringResource(id = R.string.my_expert_system_description),
                            style = typography.labelSmall,
                            fontWeight = FontWeight.Normal,
                            color = color.onBackground
                        )
                        Spacer(modifier = Modifier.padding(bottom = 12.dp))
                        SearchTextField(modifier = Modifier.fillMaxWidth(),
                            value = state.searchValue,
                            onValueChange = {
                                action(HomeAction.OnSearchValueChange(it))
                            }
                        )
                    }
                }
            }
            if (state.expertSystemVisible.isNotEmpty()) {
                items(state.expertSystemVisible) { item ->
                    MyExpertSystemCard(
                        modifier = Modifier
                            .padding(horizontal = 12.dp),
                        title = stringResource(id = item.title),
                        description = stringResource(id = item.description),
                        methodName = "Forward Chaining"
                    ) {

                    }
                }
            } else {
                item {
                    EmptyItem(message = "Ayo buat sistem pakar pertama anda!")
                }
            }
        }
    }
}