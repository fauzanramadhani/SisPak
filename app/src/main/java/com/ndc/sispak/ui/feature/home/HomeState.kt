package com.ndc.sispak.ui.feature.home

import com.ndc.sispak.R
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
    val myExpertSystem: List<ExpertSystemDetail> = listOf(
        // TODO
    ),
    val loadingSwipe: Boolean = false,
    val loadingGetUserInfo: Boolean = true,
    val userInfo: UserInfoResponse? = null
)