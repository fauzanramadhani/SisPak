package com.ndc.sispak.ui.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.GetUserInfoUseCase
import com.ndc.sispak.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val errorMessageHandler: ErrorMessageHandler,
) : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    HomeState()
) {
    init {
        getUserInfo()
    }

    override fun onAction() {
        on(HomeAction.OnSelectedScreenChange::class.java) {
            updateState { copy(screen = this@on.screen) }
        }
        on(HomeAction.OnGetUserInfo::class.java) {
            getUserInfo()
        }
        on(HomeAction.OnLogout::class.java) {
            logoutUseCase.invoke()
            send(HomeEffect.OnLogout)
        }
    }

    private fun getUserInfo() = viewModelScope.launch {
        getUserInfoUseCase.invoke()
            .onStart {
                updateState {
                    copy(
                        loadingGetUserInfo = true,
                        loadingSwipe = true
                    )
                }
            }
            .onEach {
                when (it) {
                    is UiStatus.Error -> {
                        Log.e(HomeViewModel::class.simpleName, it.message)
                        send(HomeEffect.OnShowToast(errorMessageHandler.fromCode(it.code)))
                    }

                    is UiStatus.Success -> updateState {
                        copy(
                            userInfo = it.data,
                            loadingGetUserInfo = false
                        )
                    }
                }
            }
            .onCompletion {
                updateState { copy(loadingSwipe = false) }
            }
            .collect()
    }
}