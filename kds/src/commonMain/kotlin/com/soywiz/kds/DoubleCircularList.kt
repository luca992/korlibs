package com.soywiz.kds

import kotlin.collections.set

// Accessing by index: 1
// Inserting at first or last: 1
// Getting first or last: 1
// Deleting first or last: 1
// Inserting/Removing an arbitrary index: 1 .. N/2
// Locating an index: 1 .. N
class DoubleCircularList() : MutableCollection<Double> {
	private var _start: Int = 0
	private var _size: Int = 0
	private var data: DoubleArray = DoubleArray(16)
	private val capacity: Int get() = data.size

	override val size: Int get() = _size

	fun isNotEmpty(): Boolean = size != 0
	override fun isEmpty(): Boolean = size == 0

	private fun resizeIfRequiredFor(count: Int) {
		if (size + count > capacity) {
			val i = this.data
			val istart = this._start
			val o = DoubleArray(this.data.size * 2)
			copyCyclic(i, istart, o, this._size)
			this.data = o
			this._start = 0
		}
	}

	private fun copyCyclic(i: DoubleArray, istart: Int, o: DoubleArray, count: Int) {
		val size1 = kotlin.math.min(i.size - istart, count)
		val size2 = count - size1
		MemTools.arraycopy(i, istart, o, 0, size1)
		if (size2 > 0) MemTools.arraycopy(i, 0, o, size1, size2)
	}

	fun addAll(items: Iterable<Double>) = run {
		resizeIfRequiredFor(items.count())
		for (i in items) addLast(i)
	}

	fun addFirst(item: Double) {
		resizeIfRequiredFor(1)
		KdsExt { _start = (_start - 1) umod capacity }
		_size++
		data[_start] = item
	}

	fun addLast(item: Double) {
		resizeIfRequiredFor(1)
		KdsExt { data[(_start + size) umod capacity] = item }
		_size++
	}

	fun removeFirst(): Double {
		if (_size <= 0) throw IndexOutOfBoundsException()
		return first.apply { KdsExt { _start = (_start + 1) umod capacity; _size-- } }
	}

	fun removeLast(): Double {
		if (_size <= 0) throw IndexOutOfBoundsException()
		return last.apply { _size-- }
	}

	// @TODO: This is slow. But we can improve it using two arraycopy. Also we can reduce from left or from right.
	fun removeAt(index: Int): Double {
		if (index < 0 || index >= size) throw IndexOutOfBoundsException()
		if (index == 0) return removeFirst()
		if (index == size - 1) return removeLast()

		// if (index < size / 2) // @TODO: reduce from left
		val old = this[index]
		for (n in index until size - 1) this[n] = this[n + 1]
		_size--
		return old
	}

	override fun add(element: Double): Boolean = true.apply { addLast(element) }
	override fun addAll(elements: Collection<Double>): Boolean = true.apply { addAll(elements as Iterable<Double>) }
	override fun clear() = run { _size = 0 }
	override fun remove(element: Double): Boolean {
		val index = indexOf(element)
		if (index >= 0) removeAt(index)
		return (index >= 0)
	}

	override fun removeAll(elements: Collection<Double>): Boolean = _removeRetainAll(elements, retain = false)
	override fun retainAll(elements: Collection<Double>): Boolean = _removeRetainAll(elements, retain = true)

	private fun _removeRetainAll(elements: Collection<Double>, retain: Boolean): Boolean {
		val eset = elements.toSet()
		val temp = this.data.copyOf()
		var tsize = 0
		val osize = size
		for (n in 0 until size) {
			val c = this[n]
			if ((c in eset) == retain) {
				temp[tsize++] = c
			}
		}
		this.data = temp
		this._start = 0
		this._size = tsize
		return tsize != osize
	}

	val first: Double get() = data[_start]
	val last: Double get() = data[internalIndex(size - 1)]

	private fun internalIndex(index: Int) = KdsExt { (_start + index) umod capacity }

	operator fun set(index: Int, value: Double): Unit = run { data[internalIndex(index)] = value }
	operator fun get(index: Int): Double = data[internalIndex(index)]

	override fun contains(element: Double): Boolean = (0 until size).any { this[it] == element }

	fun indexOf(element: Double): Int {
		for (n in 0 until size) if (this[n] == element) return n
		return -1
	}

	override fun containsAll(elements: Collection<Double>): Boolean {
		val emap = elements.map { it to 0 }.toLinkedMap()
		for (it in 0 until size) {
			val e = this[it]
			if (e in emap) emap[e] = 1
		}
		return emap.values.all { it == 1 }
	}

	override fun iterator(): MutableIterator<Double> {
		return object : MutableIterator<Double> {
			var index = 0
			override fun next(): Double = this@DoubleCircularList[index++]
			override fun hasNext(): Boolean = index < size
			override fun remove() = TODO()
		}
	}
}
