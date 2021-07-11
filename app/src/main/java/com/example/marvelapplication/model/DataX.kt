package com.example.marvelapplication.model

data class DataX(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<ResultX>,
    val total: Int
)