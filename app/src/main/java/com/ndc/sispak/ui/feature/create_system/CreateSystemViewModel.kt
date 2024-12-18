package com.ndc.sispak.ui.feature.create_system

import android.content.Context
import com.ndc.sispak.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CreateSystemViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : BaseViewModel<CreateSystemState, CreateSystemAction, CreateSystemEffect>(
    CreateSystemState()
) {
    override fun onAction() {
        on(CreateSystemAction.OnSearchValueChange::class.java) {
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