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
        on(HomeAction.OnSelectedScreenChange::class.java) {
            updateState { copy(screen = this@on.screen) }
        }
    }
}