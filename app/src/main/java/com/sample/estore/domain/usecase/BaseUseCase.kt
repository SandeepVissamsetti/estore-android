package com.sample.estore.domain.usecase

interface BaseUseCase< in Params, out Result> {
    fun execute(params: Params): Result
}

object Nothing