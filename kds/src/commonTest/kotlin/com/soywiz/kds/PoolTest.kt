package com.soywiz.kds

import kotlin.test.*

class PoolTest {
	class Demo {
		var x: Int = 0
		var y: Int = 0
	}

	@kotlin.test.Test
	fun name() {
		val pool = Pool { Demo() }
		val a = pool.alloc()
		val b = pool.alloc()
		val c = pool.alloc()
		assertEquals(0, pool.itemsInPool)
		pool.free(c)
		assertEquals(1, pool.itemsInPool)
		pool.free(b)
		assertEquals(2, pool.itemsInPool)
		pool.free(a)
		assertEquals(3, pool.itemsInPool)
		val d = pool.alloc()
		assertEquals(2, pool.itemsInPool)
	}
}