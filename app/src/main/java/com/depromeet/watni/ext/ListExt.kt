package com.depromeet.watni.ext

import kotlin.random.Random

/*
 * Created by yunji on 14/04/2020
 */
fun <T> List<T>.random(): T = get(Random.nextInt() % size)

fun <T> Array<T>.random(): T = get(Random.nextInt() % size)