package com.beran.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.beran.models.Dessert
import com.beran.models.DessertInput
import com.beran.services.DessertService

fun SchemaBuilder.dessertSchema(dessertService: DessertService) {

    inputType<DessertInput> {
        description = "The input of Dessert without identifier"
    }

    type<Dessert> {
        description = "Dessert object with attribute name, description, imageUrl"
    }

    /**
     * query untuk mengambil sebuah dessert
     */
    query("dessert") {
        resolver { dessertId: String ->
            try {
                dessertService.getDessert(dessertId)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * query untuk mengambil kumpulan dessert
     */
    query("desserts") {
        resolver { page: Int?, size: Int? ->
            try {
                dessertService.getDessertsPage(size ?: 10, page ?: 0)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * menambahkan sebuah dessert
     */
    mutation("createDessert") {
        description = "Create a new Dessert"
        resolver { dessertInput: DessertInput ->
            try {
                val userId = "abc"
                dessertService.createDessert(dessertInput, userId)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * updateDessert
     */
    mutation("updateDessert") {
        description = "Update a exists dessert"
        resolver { dessertId: String, dessertInput: DessertInput ->
            try {
                val userId = "abc"
                dessertService.updateDessert(userId, dessertId, dessertInput)
            } catch (e: Exception) {
                null
            }
        }
    }

    /**
     * deleteDessert
     */
    mutation("deleteDessert") {
        resolver { dessertId: String ->
            try {
                val userId = "abc"
                dessertService.deleteDessert(userId, dessertId)
            } catch (e: Exception) {
                null
            }
        }
    }
}