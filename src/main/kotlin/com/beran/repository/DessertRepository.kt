package com.beran.repository

import com.beran.models.Dessert
import com.beran.models.DessertPages
import com.beran.models.PagingInfo
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.getCollection

/**
 * class ini akan mengimplementasikan semua model class dari interface
 */
class DessertRepository(client: MongoClient) : RepositoryInterface<Dessert> {
    override lateinit var col: MongoCollection<Dessert>

    init {
        val database = client.getDatabase("test")
        col = database.getCollection<Dessert>("Dessert")
    }

    fun getDessertPage(page: Int, size: Int): DessertPages {
        return try {
            val skips = page * size
            val res = col.find().skip(skips).limit(size)
            val result = res.asIterable().map { it }
            // count, next, prev, pages
            val totalDessert = col.estimatedDocumentCount()
            val totalPages = (totalDessert / size) + 1
            val next = if (result.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(totalDessert.toInt(), totalPages.toInt(), next, prev)
            DessertPages(
                result,
                info
            )

        } catch (t: Throwable) {
            throw Exception("Cannot get desserts page")
        }
    }

}