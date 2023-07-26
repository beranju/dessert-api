package com.beran.services

import com.beran.models.Dessert
import com.beran.models.DessertInput
import com.beran.models.DessertPages
import com.beran.repository.DessertRepository
import com.mongodb.client.MongoClient
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*


class DessertService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo: DessertRepository = DessertRepository(client)

    fun getDessertsPage(size: Int, page: Int): DessertPages {
        return repo.getDessertPage(page, size)
    }

    fun getDessert(id: String): Dessert {
        return repo.getById(id)
    }

    fun createDessert(dessertInput: DessertInput, userId: String): Dessert {
        val uid = UUID.randomUUID().toString()
        val dessert = Dessert(
            id = uid,
            userId = userId,
            name = dessertInput.name,
            description = dessertInput.description,
            imageUrl = dessertInput.imageUrl
        )
        return repo.add(dessert)
    }

    fun updateDessert(userId: String, dessertId: String, dessertInput: DessertInput): Dessert {
        val dessert = repo.getById(dessertId)
        if (dessert.userId == userId) {
            val updates = Dessert(
                id = dessertId,
                userId = userId,
                name = dessertInput.name,
                description = dessertInput.description,
                imageUrl = dessertInput.imageUrl
            )
            return repo.update(updates)
        }
        error("Cannot update dessert")
    }

    fun deleteDessert(userId: String, dessertId: String): Boolean {
        val dessert = repo.getById(dessertId)
        if (dessert.userId == userId) {
            return repo.delete(dessertId)
        }
        error("Cannot delete dessert")
    }
}