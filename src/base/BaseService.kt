package com.teaml.base

import com.teaml.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BaseService {


    private fun pageOffset(page: Int): Int {
        return (page - 1) * Constant.ITEM_IN_PAGE
    }


}