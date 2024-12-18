package com.ndc.sispak.ui.feature.create_system

import com.ndc.sispak.R
import com.ndc.sispak.ui.feature.home.ExpertSystemDetail

data class CreateSystemState(
    val screen: Int = 0,
    val searchValue: String = "",
    val expertSystem: List<ExpertSystemDetail> = listOf(
        ExpertSystemDetail(
            id = 1,
            title = R.string.forward_chaining_title,
            description = R.string.forward_chaining_desc
        ),
        ExpertSystemDetail(
            id = 2,
            title = R.string.fuzzy_title,
            description = R.string.fuzzy_desc
        ),
        ExpertSystemDetail(
            id = 3,
            title = R.string.backward_chaining_title,
            description = R.string.backward_chaining_desc
        ),
        ExpertSystemDetail(
            id = 4,
            title = R.string.bayesian_title,
            description = R.string.bayesian_desc
        ),
        ExpertSystemDetail(
            id = 5,
            title = R.string.certainty_title,
            description = R.string.certainty_desc
        )
    ),
    val expertSystemVisible: List<ExpertSystemDetail> = listOf(
        ExpertSystemDetail(
            id = 1,
            title = R.string.forward_chaining_title,
            description = R.string.forward_chaining_desc
        ),
        ExpertSystemDetail(
            id = 2,
            title = R.string.fuzzy_title,
            description = R.string.fuzzy_desc
        ),
        ExpertSystemDetail(
            id = 3,
            title = R.string.backward_chaining_title,
            description = R.string.backward_chaining_desc
        ),
        ExpertSystemDetail(
            id = 4,
            title = R.string.bayesian_title,
            description = R.string.bayesian_desc
        ),
        ExpertSystemDetail(
            id = 5,
            title = R.string.certainty_title,
            description = R.string.certainty_desc
        )
    )
)