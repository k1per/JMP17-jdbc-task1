package com.epam.learnign.aykorenev.jdbc.task1.configuration


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import java.io.File
import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.DriverManager
import java.sql.ResultSet

/**
 * Created by Anton_Korenev on 4/2/2017.
 */
@SpringBootApplication
open class Application {

    @Autowired
    lateinit var env: Environment

    @Bean
    open fun init() = CommandLineRunner {
        val rowsOrder = env.getProperty("rows.order") ?: "asc"

        val connection = DriverManager.getConnection(
                env.getProperty("jdbc.url"),
                env.getProperty("jdbc.name"),
                env.getProperty("jdbc.password"))

        val metaData = connection.metaData
        printDbMetadata(metaData)
        val tables = getTables(metaData)
        saveTableNamesToFile(metaData, tables)
        saveRowsDataTofFiles(connection, tables, rowsOrder)
    }

    private fun getTables(metadata: DatabaseMetaData): List<String> {
        val resultSetTables = metadata.getTables(null, null, null, arrayOf("TABLE"))
        val tables = arrayListOf<String>()
        while (resultSetTables.next()) {
            tables.add(resultSetTables.getString(3))
        }
        return tables
    }

    private fun saveRowsDataTofFiles(connection: Connection,
                                     tables: List<String>,
                                     rowsOrder: String) {
        tables.forEach {
            File("$it.txt").printWriter().use { out ->
                val query = "SELECT * FROM $it"
                val statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
                val resultSet = statement.executeQuery(query)

                val columnCount = resultSet.metaData.columnCount
                if (rowsOrder == "desc") {
                    resultSet.afterLast()
                    while (resultSet.previous()) {
                        for (i in 1..columnCount) {
                            out.print(resultSet.getString(i) + " ")
                        }
                        out.println()
                    }
                } else {
                    while (resultSet.next()) {
                        for (i in 1..columnCount) {
                            out.print(resultSet.getString(i) + " ")
                        }
                        out.println()
                    }
                }
            }
        }
    }

    private fun printDbMetadata(metadata: DatabaseMetaData) {
        println("------Print database metadata------")
        println("Database product name ${metadata.databaseProductName}")
        println("Product version ${metadata.databaseProductVersion}")
        println("Default transaction ${metadata.defaultTransactionIsolation}")
        println("Driver version: ${metadata.driverVersion}")
        println("Max connections: ${metadata.maxConnections}")
        println("---------------END----------------")
    }

    private fun saveTableNamesToFile(metadata: DatabaseMetaData, tables: List<String>) {
        println("Saving tables name to file")
        File("tables.txt").printWriter().use { out ->
            tables.forEach {
                out.println(it)
            }
        }
    }

}