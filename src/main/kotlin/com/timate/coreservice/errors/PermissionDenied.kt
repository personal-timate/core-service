package com.timate.coreservice.errors

/**
 * @author Nicholas Dietz @ Timate
 **/
class PermissionDenied(message: String = "No permission to execute operation.", ex: Throwable? = null) : RuntimeException(message, ex)
