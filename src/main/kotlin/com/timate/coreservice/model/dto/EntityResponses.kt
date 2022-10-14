package com.timate.coreservice.model.dto

import org.springframework.http.ResponseEntity
import java.util.stream.Collectors
import kotlin.math.ceil


class EntityResponse<T>(
    val data: T
)

class ListResponse<T>(
    val data: List<T> = emptyList()
)

class PagedResponse<T>(
    val data: List<T> = emptyList(),
    val paging: ResponsePagingInformation
)

class ResponsePagingInformation(
    val pageSize: Int,
    val totalObjects: Int,
    val totalPages: Int,
    val page: Int
)

fun <T> Collection<T>.buildResponse(): ResponseEntity<ListResponse<T>> {
    return ResponseEntity.ok(
        ListResponse(data = this.toList())
    )
}

fun <T> Collection<T>.buildPagedResponse(page: Int, sizePerPage: Int, completeDataSize: Int, alreadyPaged: Boolean = false): ResponseEntity<PagedResponse<T>> {
    val map = mapOf(pageList(this.toList(), page, sizePerPage, alreadyPaged) to completeDataSize)
    val pagedList = map.keys.iterator().next()
    val totalPages = ceil(completeDataSize.toDouble() / sizePerPage.toDouble()).toInt()
    return ResponseEntity.ok(
        PagedResponse(
            data = this.toList(),
            paging = ResponsePagingInformation(
                sizePerPage,
                totalObjects = map[pagedList]!!.toInt(),
                totalPages,
                page
            )
        )
    )
}

fun <T: java.io.Serializable> T.buildEntityResponse(): ResponseEntity<EntityResponse<T>> {
    return ResponseEntity.ok(
        EntityResponse(data = this)
    )
}

/**
 * Paging list
 * Removes items from [list] dependent on [page] & [size]
 */
fun <T> pageList(list: List<T>, page: Int, size: Int, alreadyPaged: Boolean?): List<T> {
    return if (alreadyPaged == true) list else list.stream()
        .skip(page.toLong() * size.toLong())
        .limit(size.toLong())
        .collect(Collectors.toList())
}