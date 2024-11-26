package com.ndc.sispak.ui.feature.home

import android.content.Context
import com.ndc.sispak.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    HomeState()
) {
    override fun onAction() {
        on(HomeAction.OnSearchValueChange::class.java) {
            updateState { copy(searchValue = value) }
            findExpertSystem(value)
        }
    }

    private fun findExpertSystem(keyword: String) {
        if (keyword.isEmpty()) {
            updateState { copy(expertSystemVisible = expertSystem) }
        } else {
            val newExpertSystemVisible = uiState.value.expertSystem
                .toMutableList()
                .filter { item ->
                    val title = context.getString(item.title)
                    title.contains(keyword, true)
                }
            updateState { copy(expertSystemVisible = newExpertSystemVisible) }
        }
    }
}