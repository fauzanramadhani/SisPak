package com.ndc.sispak.ui.feature.home

import com.ndc.sispak.R
import com.ndc.sispak.data.remote.response.SystemResponse
import com.ndc.sispak.data.remote.response.UserInfoResponse
import com.ndc.sispak.ui.component.app_bar.BottomNavigationItem

data class HomeState(
    val bottomNavigationItems: List<BottomNavigationItem> = listOf(
        BottomNavigationItem(
            label = R.string.main,
            unselectedIcon = R.drawable.ic_home,
            selectedIcon = R.drawable.ic_home_fill
        ),
        BottomNavigationItem(
            label = R.string.contribution,
            unselectedIcon = R.drawable.ic_contribute,
            selectedIcon = R.drawable.ic_contribute_fill
        ),
        BottomNavigationItem(
            label = R.string.setting,
            unselectedIcon = R.drawable.ic_setting,
            selectedIcon = R.drawable.ic_setting_fill
        ),
    ),
    val screen: Int = 0,
    // Main Screen
    val myExpertSystem: List<SystemResponse> = listOf(),
    val loadingGetAllSystem: Boolean = true,
    val loadingSwipeGetAllSystem: Boolean = false,
    // Profile Screen
    val loadingSwipeProfile: Boolean = false,
    val loadingGetUserInfo: Boolean = true,
    val userInfo: UserInfoResponse? = null
)