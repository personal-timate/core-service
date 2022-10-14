package com.timate.coreservice.errors

/**
 * @author Nicholas Dietz @ USU GmbH
 **/
class PermissionDenied(message: String = "No permission to execute operation.", ex: Throwable? = null) : RuntimeException(message, ex)
