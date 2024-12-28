package com.school_of_company.main.util

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

fun <T> ImmutableList<T>.updateAtIndex(index: Int, update: (T) -> T): ImmutableList<T> {
    return if (index in indices) {
        toMutableList().apply { this[index] = update(this[index]) }.toImmutableList()
    } else {
        this
    }
}