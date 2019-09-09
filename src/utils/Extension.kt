package com.teaml.utils

import com.teaml.utils.Constant.ITEM_IN_PAGE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.math.roundToInt

suspend fun <T> dbQuery(blocK: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { blocK() }
    }

fun Table.getTotalPage(where: (SqlExpressionBuilder.() -> Op<Boolean>)? = null): Int {
    val exp = where?.let { Op.build { where.invoke(this) } }
    val totalItems = Query(selectAll().set, exp).count()
    return (totalItems / ITEM_IN_PAGE.toDouble()).roundToInt()
}

fun FieldSet.selectPage(page: Int): Query {
    val pageOffset = (page - 1) * ITEM_IN_PAGE
    return selectAll().limit(ITEM_IN_PAGE, pageOffset)
}

public fun <T> List<T>.firstOrElse(defaultValue: () -> T): T {
    return if (isEmpty()) defaultValue() else this[0]
}




