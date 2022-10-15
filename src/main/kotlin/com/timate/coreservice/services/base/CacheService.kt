package com.timate.coreservice.services.base

import com.timate.coreservice.errors.CacheClearingFailed
import org.slf4j.Logger
import org.springframework.cache.CacheManager
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CacheService(
    val cacheManager: CacheManager,
    val logger: Logger
) {

    @kotlin.jvm.Throws(CacheClearingFailed::class)
    fun clearAllCaches(): ResponseEntity<String> {
        try {
            cacheManager.cacheNames.forEach { cacheName ->
                try {
                    cacheManager.getCache(cacheName)!!.clear()
                } catch (ex: Exception) {
                    throw CacheClearingFailed("Failed to clear cache '$cacheName'", ex = ex)
                }
            }
            return ResponseEntity.ok("Successfully cleared cache(s).")
        } catch (ex: Exception) {
            throw CacheClearingFailed(ex = ex)
        }
    }

}