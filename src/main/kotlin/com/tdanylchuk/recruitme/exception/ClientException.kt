package com.tdanylchuk.recruitme.exception

class ClientException : RuntimeException {

    constructor(message: String) : super(message)

    constructor(message: String, cause: Throwable) : super(message, cause)
}
