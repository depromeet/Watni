package com.depromeet.watni.ext

import kotlin.random.Random

/*
 * Created by yunji on 14/04/2020
 */
fun <E> List<E>?.isNullOrEmpty(): Boolean = (this == null || isEmpty())

fun <E> List<E>?.isNotNullOrEmpty(): Boolean = !isNullOrEmpty()

fun <T> List<T>.random(): T = get(Random.nextInt(0, size - 1))

fun <T> Array<T>.random(): T = get(Random.nextInt(0, size - 1))

fun <E> List<E?>.filterNotNull(): List<E> = filterNotNullTo(arrayListOf())

fun <E> List<E?>.isValidIndex(index: Int) = index in 0 until size