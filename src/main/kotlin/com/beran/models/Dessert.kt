package com.beran.models

/**
 * id meng-override interface model
 *
 */
data class Dessert(override val id: String, var userId: String, var name: String, var description: String, var imageUrl: String) : Model

data class DessertInput(val name: String, val description: String, val imageUrl: String)

data class PagingInfo(var count: Int, var pages: Int, var next: Int?, var prev: Int?)

data class DessertPages(val result: List<Dessert>, val info: PagingInfo)
