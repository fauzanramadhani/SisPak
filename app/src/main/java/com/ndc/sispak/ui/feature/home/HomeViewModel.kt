package com.ndc.sispak.ui.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ndc.sispak.common.BaseViewModel
import com.ndc.sispak.common.ErrorMessageHandler
import com.ndc.sispak.common.UiStatus
import com.ndc.sispak.domain.GetAllSystemUseCase
import com.ndc.sispak.domain.GetUserInfoUseCase
import com.ndc.sispak.domain.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val errorMessageHandler: ErrorMessageHandler,
    private val getAllSystemUseCase: GetAllSystemUseCase,
) : BaseViewModel<HomeState, HomeAction, HomeEffect>(
    HomeState()
) {
    init {
        getUserInfo()
        getAllSystem()
    }

    override fun onAction() {
        on(HomeAction.OnSelectedScreenChange::class.java) {
            updateState { copy(screen = this@on.screen) }
        }
        // Main Screen
        on(HomeAction.OnGetAllSystem::class.java) {
            getAllSystem()
        }
        // Setting Screen
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
                        loadingSwipeProfile = true
                    )
                }
            }
            .onEach {
                when (it) {
                    is UiStatus.Error -> {
                        Log.e(HomeViewModel::class.simpleName, it.message)
                        showToast(errorMessageHandler.fromCode(it.code))
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
                updateState { copy(loadingSwipeProfile = false) }
            }
            .collect()
    }

    private fun getAllSystem() = viewModelScope.launch {
        getAllSystemUseCase.invoke()
            .onStart {
                updateState {
                    copy(
                        loadingGetAllSystem = true,
                        loadingSwipeGetAllSystem = true
                    )
                }
            }.onEach { response ->
                when (response) {
                    is UiStatus.Error -> {
                        Log.e(HomeViewModel::class.simpleName, response.message)
                        // showToast(errorMessageHandler.fromCode(response.code))
                        throw Exception()
                    }

                    is UiStatus.Success -> updateState {
                        val data = (response.data ?: listOf()).sortedByDescending { it.createdAt }
                        copy(myExpertSystem = data)
                    }
                }
            }
            .retry {
                delay(1000)
                true
            }
            .onCompletion {
                updateState {
                    copy(
                        loadingGetAllSystem = false,
                        loadingSwipeGetAllSystem = false
                    )
                }
            }
            .collect()
    }

    private fun showToast(message: String) = sendEffect(HomeEffect.OnShowToast(message))
}