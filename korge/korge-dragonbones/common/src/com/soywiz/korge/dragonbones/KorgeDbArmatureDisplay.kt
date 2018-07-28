/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2012-2018 DragonBones team and other contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.soywiz.korge.dragonbones

import com.dragonbones.animation.*
import com.dragonbones.armature.*
import com.dragonbones.core.*
import com.dragonbones.event.*
import com.dragonbones.model.*
import com.dragonbones.util.*
import com.soywiz.korge.view.*
import com.soywiz.korim.bitmap.*
import com.soywiz.korim.color.*

/**
 * @inheritDoc
 */
class KorgeDbArmatureDisplay : Image(Bitmaps.transparent), IArmatureProxy {
	/**
	 * @private
	 */
	var debugDraw: Boolean = false
	private var _debugDraw: Boolean = false
	// private var _disposeProxy: boolean = false;
	//private var _armature: Armature = null as any
	private var _armature: Armature? = null
	private var _debugDrawer: Container? = null
	/**
	 * @inheritDoc
	 */
	override fun dbInit(armature: Armature) {
		this._armature = armature
	}

	// Do not use the time from DragonBones, but the UpdateComponent
	init {
		addUpdatable {
			_armature?.advanceTime(it.toDouble() / 1000.0)
			if (_armature != null) {
				dbUpdate()
			}
		}
	}

	/**
	 * @inheritDoc
	 */
	override fun dbClear() {
		if (this._debugDrawer !== null) {
			//this._debugDrawer.destroy(true) // Note: Korge doesn't require this
		}

		this._armature = null
		this._debugDrawer = null

		//super.destroy() // Note: Korge doesn't require this
	}

	/**
	 * @inheritDoc
	 */
	override fun dbUpdate() {
		val armature = this._armature ?: return
		val drawed = DragonBones.debugDraw || this.debugDraw
		if (drawed || this._debugDraw) {
			this._debugDraw = drawed
			if (this._debugDraw) {
				if (this._debugDrawer === null) {
					this._debugDrawer = Image(Bitmaps.transparent)
					val boneDrawer = Graphics()
					this._debugDrawer?.addChild(boneDrawer)
				}

				this.addChild(this._debugDrawer!!)
				val boneDrawer = this._debugDrawer?.getChildAt(0) as Graphics
				boneDrawer.clear()

				val bones = armature.getBones()
				//for (let i = 0, l = bones.length; i < l; ++i) {
				for (i in 0 until bones.length) {
					val bone = bones[i]
					val boneLength = bone.boneData.length
					val startX = bone.globalTransformMatrix.tx
					val startY = bone.globalTransformMatrix.ty
					val endX = startX + bone.globalTransformMatrix.a * boneLength
					val endY = startY + bone.globalTransformMatrix.b * boneLength

					boneDrawer.lineStyle(2.0, Colors.PURPLE, 0.7)
					boneDrawer.moveTo(startX, startY)
					boneDrawer.lineTo(endX, endY)
					boneDrawer.lineStyle(0.0, Colors.BLACK, 0.0)
					boneDrawer.beginFill(Colors.PURPLE, 0.7)
					boneDrawer.drawCircle(startX, startY, 3.0)
					boneDrawer.endFill()
				}

				val slots = armature.getSlots()
				//for (let i = 0, l = slots.length; i < l; ++i) {
				for (i in 0 until slots.length) {
					val slot = slots[i]
					val boundingBoxData = slot.boundingBoxData

					if (boundingBoxData != null) {
						var child = this._debugDrawer?.getChildByName(slot.name) as? Graphics?
						if (child == null) {
							child = Graphics()
							child.name = slot.name
							this._debugDrawer?.addChild(child)
						}

						child.clear()
						child.lineStyle(2.0, Colors.RED, 0.7)

						when (boundingBoxData.type) {
							BoundingBoxType.Rectangle -> {
								child.drawRect(
									-boundingBoxData.width * 0.5,
									-boundingBoxData.height * 0.5,
									boundingBoxData.width,
									boundingBoxData.height
								)
							}

							BoundingBoxType.Ellipse -> {
								child.drawEllipse(
									-boundingBoxData.width * 0.5,
									-boundingBoxData.height * 0.5,
									boundingBoxData.width,
									boundingBoxData.height
								)
							}

							BoundingBoxType.Polygon -> {
								val vertices = (boundingBoxData as PolygonBoundingBoxData).vertices
								//for (let i = 0, l = vertices.length; i < l; i += 2) {
								for (i in 0 until vertices.length step 2) {
									val x = vertices[i]
									val y = vertices[i + 1]

									if (i == 0) {
										child.moveTo(x, y)
									} else {
										child.lineTo(x, y)
									}
								}

								child.lineTo(vertices[0], vertices[1])
							}

							else -> {

							}
						}

						child.endFill()
						slot.updateTransformAndMatrix()
						slot.updateGlobalTransform()

						val transform = slot.global
						//println("SET TRANSFORM: $transform")
						child.setTransform(
							transform.x, transform.y,
							transform.scaleX, transform.scaleY,
							transform.rotation,
							transform.skew, 0.0,
							slot._pivotX, slot._pivotY
						)
					} else {
						val child = this._debugDrawer?.getChildByName(slot.name)
						if (child != null) {
							this._debugDrawer?.removeChild(child)
						}
					}
				}
			} else if (this._debugDrawer !== null && this._debugDrawer?.parent === this) {
				this.removeChild(this._debugDrawer)
			}
		}
	}

	/**
	 * @inheritDoc
	 */
	override fun dispose(disposeProxy: Boolean) {
		if (this._armature != null) {
			this._armature?.dispose()
			this._armature = null
		}
	}

	/**
	 * @inheritDoc
	 */
	fun destroy() {
		//this.dispose() // Note: Korge doesn't require this!
	}

	/**
	 * @private
	 */
	override fun dispatchDBEvent(type: EventStringType, eventObject: EventObject) {
		println("TODO: dispatchDBEvent: $type, $eventObject")
		//this.emit(type, eventObject)
	}

	/**
	 * @inheritDoc
	 */
	override fun hasDBEventListener(type: EventStringType): Boolean {
		//return this.listeners(type, true) as boolean // .d.ts bug
		return false
	}

	/**
	 * @inheritDoc
	 */
	override fun addDBEventListener(type: EventStringType, listener: (event: EventObject) -> Unit, thisObject: Any) {
		println("TODO: addDBEventListener: $type")
		//this.addListener(type as any, listener as any, thisObject)
	}

	/**
	 * @inheritDoc
	 */
	override fun removeDBEventListener(type: EventStringType, listener: (event: EventObject) -> Unit, thisObject: Any) {
		println("TODO: removeDBEventListener: $type")
		//this.removeListener(type as any, listener as any, thisObject)
	}

	/**
	 * @inheritDoc
	 */
	override val armature: Armature get() = this._armature!!
	/**
	 * @inheritDoc
	 */
	override val animation: Animation get() = this._armature!!.animation
}