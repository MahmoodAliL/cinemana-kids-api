package com.teaml.utils

import org.jetbrains.exposed.sql.Database

object DatabaseUtils  {

    private val database by lazy {
        Database.connect(
            "jdbc:mysql://localhost:3306/cinemana_kids_new", driver = "com.mysql.jdbc.Driver",
            user = "root", password = "root"
        )
    }

    fun connect() {
        database
    }

}