package com.timate.coreservice.errors

/**
 * @author Nicholas Dietz @ Timate
 **/
class CacheClearingFailed(message: String = "Failed to clear cache.", ex: Throwable? = null) : RuntimeException(message, ex)
