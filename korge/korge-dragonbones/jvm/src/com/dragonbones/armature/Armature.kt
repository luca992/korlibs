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
package com.dragonbones.armature

import com.dragonbones.animation.*
import com.dragonbones.core.*
import com.dragonbones.event.*
import com.dragonbones.geom.*
import com.dragonbones.model.*

/**
 * - Armature is the core of the skeleton animation system.
 * @see dragonBones.ArmatureData
 * @see dragonBones.Bone
 * @see dragonBones.Slot
 * @see dragonBones.Animation
 * @version DragonBones 3.0
 * @language en_US
 */
/**
 * - 骨架是骨骼动画系统的核心。
 * @see dragonBones.ArmatureData
 * @see dragonBones.Bone
 * @see dragonBones.Slot
 * @see dragonBones.Animation
 * @version DragonBones 3.0
 * @language zh_CN
 */
class Armature  : BaseObject(), IAnimatable {
	public override fun toString(): String {
		return "[class dragonBones.Armature]"
	}
	companion object {
		private fun _onSortSlots(a: Slot, b: Slot): Double {
			return a._zIndex * 1000 + a._zOrder > b._zIndex * 1000 + b._zOrder ? 1 : -1
		}
	}
	/**
	 * - Whether to inherit the animation control of the parent armature.
	 * True to try to have the child armature play an animation with the same name when the parent armature play the animation.
	 * @default true
	 * @version DragonBones 4.5
	 * @language en_US
	 */
	/**
	 * - 是否继承父骨架的动画控制。
	 * 如果该值为 true，当父骨架播放动画时，会尝试让子骨架播放同名动画。
	 * @default true
	 * @version DragonBones 4.5
	 * @language zh_CN
	 */
	public var inheritAnimation: Boolean
	/**
	 * @private
	 */
	public var userData: Any
	/**
	 * @internal
	 */
	public var _lockUpdate: Boolean
	private var _slotsDirty: Boolean
	private var _zOrderDirty: Boolean
	/**
	 * @internal
	 */
	public var _zIndexDirty: Boolean
	/**
	 * @internal
	 */
	public var _alphaDirty: Boolean
	private var _flipX: Boolean
	private var _flipY: Boolean
	/**
	 * @internal
	 */
	public var _cacheFrameIndex: Double
	private var _alpha: Double
	/**
	 * @internal
	 */
	public var _globalAlpha: Double
	private val _bones: Array<Bone> = []
	private val _slots: Array<Slot> = []
	/**
	 * @internal
	 */
	public val _constraints: Array<Constraint> = []
	private val _actions: Array<EventObject> = []
	/**
	 * @internal
	 */
	public var _armatureData: ArmatureData
	private var _animation: Animation = null as any // Initial value.
	private var _proxy: IArmatureProxy = null as any // Initial value.
	private var _display: Any
	/**
	 * @internal
	 */
	public var _replaceTextureAtlasData: TextureAtlasData? = null // Initial value.
	private var _replacedTexture: Any
	/**
	 * @internal
	 */
	public var _dragonBones: DragonBones
	private var _clock: WorldClock? = null // Initial value.
	/**
	 * @internal
	 */
	public var _parent: Slot?

	protected fun _onClear(): Unit {
		if (this._clock !== null) { // Remove clock first.
			this._clock.remove(this)
		}

		for (bone in this._bones) {
			bone.returnToPool()
		}

		for (slot in this._slots) {
			slot.returnToPool()
		}

		for (constraint in this._constraints) {
			constraint.returnToPool()
		}

		for (action in this._actions) {
			action.returnToPool()
		}

		if (this._animation !== null) {
			this._animation.returnToPool()
		}

		if (this._proxy !== null) {
			this._proxy.dbClear()
		}

		if (this._replaceTextureAtlasData !== null) {
			this._replaceTextureAtlasData.returnToPool()
		}

		this.inheritAnimation = true
		this.userData = null

		this._lockUpdate = false
		this._slotsDirty = true
		this._zOrderDirty = false
		this._zIndexDirty = false
		this._alphaDirty = true
		this._flipX = false
		this._flipY = false
		this._cacheFrameIndex = -1
		this._alpha = 1.0
		this._globalAlpha = 1.0
		this._bones.length = 0
		this._slots.length = 0
		this._constraints.length = 0
		this._actions.length = 0
		this._armatureData = null as any //
		this._animation = null as any //
		this._proxy = null as any //
		this._display = null
		this._replaceTextureAtlasData = null
		this._replacedTexture = null
		this._dragonBones = null as any //
		this._clock = null
		this._parent = null
	}
	/**
	 * @internal
	 */
	public fun _sortZOrder(slotIndices:  DoubleArray | Int16Array?, offset: Double): Unit {
		val slotDatas = this._armatureData.sortedSlots
		val isOriginal = slotIndices === null

		if (this._zOrderDirty || !isOriginal) {
			for (let i = 0, l = slotDatas.length; i < l; ++i) {
				val slotIndex = isOriginal ? i : (slotIndices as  DoubleArray)[offset + i]
				if (slotIndex < 0 || slotIndex >= l) {
					continue
				}

				val slotData = slotDatas[slotIndex]
				val slot = this.getSlot(slotData.name)

				if (slot !== null) {
					slot._setZOrder(i)
				}
			}

			this._slotsDirty = true
			this._zOrderDirty = !isOriginal
		}
	}
	/**
	 * @internal
	 */
	public fun _addBone(value: Bone): Unit {
		if (this._bones.indexOf(value) < 0) {
			this._bones.push(value)
		}
	}
	/**
	 * @internal
	 */
	public fun _addSlot(value: Slot): Unit {
		if (this._slots.indexOf(value) < 0) {
			this._slots.push(value)
		}
	}
	/**
	 * @internal
	 */
	public fun _addConstraint(value: Constraint): Unit {
		if (this._constraints.indexOf(value) < 0) {
			this._constraints.push(value)
		}
	}
	/**
	 * @internal
	 */
	public fun _bufferAction(action: EventObject, append: Boolean): Unit {
		if (this._actions.indexOf(action) < 0) {
			if (append) {
				this._actions.push(action)
			}
			else {
				this._actions.unshift(action)
			}
		}
	}
	/**
	 * - Dispose the armature. (Return to the object pool)
	 * @example
	 * <pre>
	 *     removeChild(armature.display);
	 *     armature.dispose();
	 * </pre>
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 释放骨架。 （回收到对象池）
	 * @example
	 * <pre>
	 *     removeChild(armature.display);
	 *     armature.dispose();
	 * </pre>
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun dispose(): Unit {
		if (this._armatureData !== null) {
			this._lockUpdate = true
			this._dragonBones.bufferObject(this)
		}
	}
	/**
	 * @internal
	 */
	public fun init(
		armatureData: ArmatureData,
		proxy: IArmatureProxy, display: Any, dragonBones: DragonBones
	): Unit {
		if (this._armatureData !== null) {
			return
		}

		this._armatureData = armatureData
		this._animation = BaseObject.borrowObject(Animation)
		this._proxy = proxy
		this._display = display
		this._dragonBones = dragonBones

		this._proxy.dbInit(this)
		this._animation.init(this)
		this._animation.animations = this._armatureData.animations
	}
	/**
	 * @inheritDoc
	 */
	public fun advanceTime(passedTime: Double): Unit {
		if (this._lockUpdate) {
			return
		}

		this._lockUpdate = true

		if (this._armatureData === null) {
			console.warn("The armature has been disposed.")
			return
		}
		else if (this._armatureData.parent === null) {
			console.warn("The armature data has been disposed.\nPlease make sure dispose armature before call factory.clear().")
			return
		}

		const prevCacheFrameIndex = this._cacheFrameIndex
		// Update animation.
		this._animation.advanceTime(passedTime)
		// Sort slots.
		if (this._slotsDirty || this._zIndexDirty) {
			this._slots.sort(Armature._onSortSlots)

			if (this._zIndexDirty) {
				for (let i = 0, l = this._slots.length; i < l; ++i) {
					this._slots[i]._setZOrder(i) //
				}
			}

			this._slotsDirty = false
			this._zIndexDirty = false
		}
		// Update alpha.
		if (this._alphaDirty) {
			this._alphaDirty = false
			this._globalAlpha = this._alpha * (this._parent !== null ? this._parent._globalAlpha : 1.0)

			for (bone in this._bones) {
				bone._updateAlpha()
			}

			for (slot in this._slots) {
				slot._updateAlpha()
			}
		}
		// Update bones and slots.
		if (this._cacheFrameIndex < 0 || this._cacheFrameIndex !== prevCacheFrameIndex) {
			let i = 0, l = 0
			for (i = 0, l = this._bones.length; i < l; ++i) {
				this._bones[i].update(this._cacheFrameIndex)
			}

			for (i = 0, l = this._slots.length; i < l; ++i) {
				this._slots[i].update(this._cacheFrameIndex)
			}
		}
		// Do actions.
		if (this._actions.length > 0) {
			for (action in  this._actions) {
				const actionData = action.actionData
				if (actionData !== null) {
					if (actionData.type === ActionType.Play) {
						if (action.slot !== null) {
							const childArmature = action.slot.childArmature
							if (childArmature !== null) {
								childArmature.animation.fadeIn(actionData.name)
							}
						}
						else if (action.bone !== null) {
							for (slot in this.getSlots()) {
								if (slot.parent === action.bone) {
									const childArmature = slot.childArmature
									if (childArmature !== null) {
										childArmature.animation.fadeIn(actionData.name)
									}
								}
							}
						}
						else {
							this._animation.fadeIn(actionData.name)
						}
					}
				}

				action.returnToPool()
			}

			this._actions.length = 0
		}

		this._lockUpdate = false
		this._proxy.dbUpdate()
	}
	/**
	 * - Forces a specific bone or its owning slot to update the transform or display property in the next frame.
	 * @param boneName - The bone name. (If not set, all bones will be update)
	 * @param updateSlot - Whether to update the bone's slots. (Default: false)
	 * @see dragonBones.Bone#invalidUpdate()
	 * @see dragonBones.Slot#invalidUpdate()
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 强制特定骨骼或其拥有的插槽在下一帧更新变换或显示属性。
	 * @param boneName - 骨骼名称。 （如果未设置，将更新所有骨骼）
	 * @param updateSlot - 是否更新骨骼的插槽。 （默认: false）
	 * @see dragonBones.Bone#invalidUpdate()
	 * @see dragonBones.Slot#invalidUpdate()
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun invalidUpdate(boneName: String? = null, updateSlot: Boolean = false): Unit {
		if (boneName !== null && boneName.length > 0) {
			val bone = this.getBone(boneName)
			if (bone !== null) {
				bone.invalidUpdate()

				if (updateSlot) {
					for (slot in this._slots) {
						if (slot.parent === bone) {
							slot.invalidUpdate()
						}
					}
				}
			}
		}
		else {
			for (bone in  this._bones) {
				bone.invalidUpdate()
			}

			if (updateSlot) {
				for (slot in this._slots) {
					slot.invalidUpdate()
				}
			}
		}
	}
	/**
	 * - Check whether a specific point is inside a custom bounding box in a slot.
	 * The coordinate system of the point is the inner coordinate system of the armature.
	 * Custom bounding boxes need to be customized in Dragonbones Pro.
	 * @param x - The horizontal coordinate of the point.
	 * @param y - The vertical coordinate of the point.
	 * @version DragonBones 5.0
	 * @language en_US
	 */
	/**
	 * - 检查特定点是否在某个插槽的自定义边界框内。
	 * 点的坐标系为骨架内坐标系。
	 * 自定义边界框需要在 DragonBones Pro 中自定义。
	 * @param x - 点的水平坐标。
	 * @param y - 点的垂直坐标。
	 * @version DragonBones 5.0
	 * @language zh_CN
	 */
	public fun containsPoint(x: Double, y: Double): Slot? {
		for (slot in this._slots) {
			if (slot.containsPoint(x, y)) {
				return slot
			}
		}

		return null
	}
	/**
	 * - Check whether a specific segment intersects a custom bounding box for a slot in the armature.
	 * The coordinate system of the segment and intersection is the inner coordinate system of the armature.
	 * Custom bounding boxes need to be customized in Dragonbones Pro.
	 * @param xA - The horizontal coordinate of the beginning of the segment.
	 * @param yA - The vertical coordinate of the beginning of the segment.
	 * @param xB - The horizontal coordinate of the end point of the segment.
	 * @param yB - The vertical coordinate of the end point of the segment.
	 * @param intersectionPointA - The first intersection at which a line segment intersects the bounding box from the beginning to the end. (If not set, the intersection point will not calculated)
	 * @param intersectionPointB - The first intersection at which a line segment intersects the bounding box from the end to the beginning. (If not set, the intersection point will not calculated)
	 * @param normalRadians - The normal radians of the tangent of the intersection boundary box. [x: Normal radian of the first intersection tangent, y: Normal radian of the second intersection tangent] (If not set, the normal will not calculated)
	 * @returns The slot of the first custom bounding box where the segment intersects from the start point to the end point.
	 * @version DragonBones 5.0
	 * @language en_US
	 */
	/**
	 * - 检查特定线段是否与骨架的某个插槽的自定义边界框相交。
	 * 线段和交点的坐标系均为骨架内坐标系。
	 * 自定义边界框需要在 DragonBones Pro 中自定义。
	 * @param xA - 线段起点的水平坐标。
	 * @param yA - 线段起点的垂直坐标。
	 * @param xB - 线段终点的水平坐标。
	 * @param yB - 线段终点的垂直坐标。
	 * @param intersectionPointA - 线段从起点到终点与边界框相交的第一个交点。 （如果未设置，则不计算交点）
	 * @param intersectionPointB - 线段从终点到起点与边界框相交的第一个交点。 （如果未设置，则不计算交点）
	 * @param normalRadians - 交点边界框切线的法线弧度。 [x: 第一个交点切线的法线弧度, y: 第二个交点切线的法线弧度] （如果未设置，则不计算法线）
	 * @returns 线段从起点到终点相交的第一个自定义边界框的插槽。
	 * @version DragonBones 5.0
	 * @language zh_CN
	 */
	public fun intersectsSegment(
		xA: Double, yA: Double, xB: Double, yB: Double,
		intersectionPointA: Point? = null,
		intersectionPointB: Point? = null,
		normalRadians: Point? = null
	): Slot? {
		val isV = xA === xB
		var dMin = 0.0
		var dMax = 0.0
		var intXA = 0.0
		var intYA = 0.0
		var intXB = 0.0
		var intYB = 0.0
		var intAN = 0.0
		var intBN = 0.0
		var intSlotA: Slot? = null
		var intSlotB: Slot? = null

		for (slot in this._slots) {
			val intersectionCount = slot.intersectsSegment(xA, yA, xB, yB, intersectionPointA, intersectionPointB, normalRadians)
			if (intersectionCount > 0) {
				if (intersectionPointA !== null || intersectionPointB !== null) {
					if (intersectionPointA !== null) {
						var d = if (isV) intersectionPointA.y - yA else intersectionPointA.x - xA
						if (d < 0.0) {
							d = -d
						}

						if (intSlotA === null || d < dMin) {
							dMin = d
							intXA = intersectionPointA.x
							intYA = intersectionPointA.y
							intSlotA = slot

							if (normalRadians != 0.0) {
								intAN = normalRadians.x
							}
						}
					}

					if (intersectionPointB !== null) {
						var d = intersectionPointB.x - xA
						if (d < 0.0) {
							d = -d
						}

						if (intSlotB === null || d > dMax) {
							dMax = d
							intXB = intersectionPointB.x
							intYB = intersectionPointB.y
							intSlotB = slot

							if (normalRadians !== null) {
								intBN = normalRadians.y
							}
						}
					}
				}
				else {
					intSlotA = slot
					break
				}
			}
		}

		if (intSlotA !== null && intersectionPointA !== null) {
			intersectionPointA.x = intXA
			intersectionPointA.y = intYA

			if (normalRadians !== null) {
				normalRadians.x = intAN
			}
		}

		if (intSlotB !== null && intersectionPointB !== null) {
			intersectionPointB.x = intXB
			intersectionPointB.y = intYB

			if (normalRadians !== null) {
				normalRadians.y = intBN
			}
		}

		return intSlotA
	}
	/**
	 * - Get a specific bone.
	 * @param name - The bone name.
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 获取特定的骨骼。
	 * @param name - 骨骼名称。
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getBone(name: String): Bone? {
		for (bone in this._bones) {
			if (bone.name === name) {
				return bone
			}
		}

		return null
	}
	/**
	 * - Get a specific bone by the display.
	 * @param display - The display object.
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 通过显示对象获取特定的骨骼。
	 * @param display - 显示对象。
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getBoneByDisplay(display: Any): Bone? {
		val slot = this.getSlotByDisplay(display)
		return slot !== null ? slot.parent : null
	}
	/**
	 * - Get a specific slot.
	 * @param name - The slot name.
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 获取特定的插槽。
	 * @param name - 插槽名称。
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getSlot(name: String): Slot? {
		for (slot in this._slots) {
			if (slot.name === name) {
				return slot
			}
		}

		return null
	}
	/**
	 * - Get a specific slot by the display.
	 * @param display - The display object.
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 通过显示对象获取特定的插槽。
	 * @param display - 显示对象。
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getSlotByDisplay(display: Any): Slot? {
		if (display !== null) {
			for (slot in this._slots) {
				if (slot.display === display) {
					return slot
				}
			}
		}

		return null
	}
	/**
	 * - Get all bones.
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 获取所有的骨骼。
	 * @see dragonBones.Bone
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getBones(): Array<Bone> {
		return this._bones
	}
	/**
	 * - Get all slots.
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 获取所有的插槽。
	 * @see dragonBones.Slot
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public fun getSlots(): Array<Slot> {
		return this._slots
	}
	/**
	 * - Whether to flip the armature horizontally.
	 * @version DragonBones 5.5
	 * @language en_US
	 */
	/**
	 * - 是否将骨架水平翻转。
	 * @version DragonBones 5.5
	 * @language zh_CN
	 */
	public var flipX: Boolean get() {
		return this._flipX
	}
	set(value: Boolean) {
		if (this._flipX === value) {
			return
		}

		this._flipX = value
		this.invalidUpdate()
	}
	/**
	 * - Whether to flip the armature vertically.
	 * @version DragonBones 5.5
	 * @language en_US
	 */
	/**
	 * - 是否将骨架垂直翻转。
	 * @version DragonBones 5.5
	 * @language zh_CN
	 */
	public var flipY: Boolean get() {
		return this._flipY
	}
	set(value: Boolean) {
		if (this._flipY === value) {
			return
		}

		this._flipY = value
		this.invalidUpdate()
	}
	/**
	 * - The animation cache frame rate, which turns on the animation cache when the set value is greater than 0.
	 * There is a certain amount of memory overhead to improve performance by caching animation data in memory.
	 * The frame rate should not be set too high, usually with the frame rate of the animation is similar and lower than the program running frame rate.
	 * When the animation cache is turned on, some features will fail, such as the offset property of bone.
	 * @example
	 * <pre>
	 *     armature.cacheFrameRate = 24;
	 * </pre>
	 * @see dragonBones.DragonBonesData#frameRate
	 * @see dragonBones.ArmatureData#frameRate
	 * @version DragonBones 4.5
	 * @language en_US
	 */
	/**
	 * - 动画缓存帧率，当设置的值大于 0 的时，将会开启动画缓存。
	 * 通过将动画数据缓存在内存中来提高运行性能，会有一定的内存开销。
	 * 帧率不宜设置的过高，通常跟动画的帧率相当且低于程序运行的帧率。
	 * 开启动画缓存后，某些功能将会失效，比如骨骼的 offset 属性等。
	 * @example
	 * <pre>
	 *     armature.cacheFrameRate = 24;
	 * </pre>
	 * @see dragonBones.DragonBonesData#frameRate
	 * @see dragonBones.ArmatureData#frameRate
	 * @version DragonBones 4.5
	 * @language zh_CN
	 */
	public var cacheFrameRate: Double get() {
		return this._armatureData.cacheFrameRate
	}
	set(value: Double) {
		if (this._armatureData.cacheFrameRate !== value) {
			this._armatureData.cacheFrames(value)

			// Set child armature frameRate.
			for (slot in this._slots) {
				const childArmature = slot.childArmature
				if (childArmature !== null) {
					childArmature.cacheFrameRate = value
				}
			}
		}
	}
	/**
	 * - The armature name.
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 骨架名称。
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public val name: String get() {
		return this._armatureData.name
	}
	/**
	 * - The armature data.
	 * @see dragonBones.ArmatureData
	 * @version DragonBones 4.5
	 * @language en_US
	 */
	/**
	 * - 骨架数据。
	 * @see dragonBones.ArmatureData
	 * @version DragonBones 4.5
	 * @language zh_CN
	 */
	public val armatureData: ArmatureData get() {
		return this._armatureData
	}
	/**
	 * - The animation player.
	 * @see dragonBones.Animation
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 动画播放器。
	 * @see dragonBones.Animation
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public val animation: Animation get() {
		return this._animation
	}
	/**
	 * @pivate
	 */
	public val proxy: IArmatureProxy get() {
		return this._proxy
	}
	/**
	 * - The EventDispatcher instance of the armature.
	 * @version DragonBones 4.5
	 * @language en_US
	 */
	/**
	 * - 该骨架的 EventDispatcher 实例。
	 * @version DragonBones 4.5
	 * @language zh_CN
	 */
	public val eventDispatcher: IEventDispatcher get() {
		return this._proxy
	}
	/**
	 * - The display container.
	 * The display of the slot is displayed as the parent.
	 * Depending on the rendering engine, the type will be different, usually the DisplayObjectContainer type.
	 * @version DragonBones 3.0
	 * @language en_US
	 */
	/**
	 * - 显示容器实例。
	 * 插槽的显示对象都会以此显示容器为父级。
	 * 根据渲染引擎的不同，类型会不同，通常是 DisplayObjectContainer 类型。
	 * @version DragonBones 3.0
	 * @language zh_CN
	 */
	public val display: Any get() {
		return this._display
	}
	/**
	 * @private
	 */
	public var replacedTexture: Any get() {
		return this._replacedTexture
	}
	set (value: Any) {
		if (this._replacedTexture === value) {
			return
		}

		if (this._replaceTextureAtlasData !== null) {
			this._replaceTextureAtlasData.returnToPool()
			this._replaceTextureAtlasData = null
		}

		this._replacedTexture = value

		for (slot in this._slots) {
			slot.invalidUpdate()
			slot.update(-1)
		}
	}
	/**
	 * @inheritDoc
	 */
	public var clock: WorldClock? get() {
		return this._clock
	}
	set(value: WorldClock?) {
		if (this._clock === value) {
			return
		}

		if (this._clock !== null) {
			this._clock.remove(this)
		}

		this._clock = value

		if (this._clock) {
			this._clock.add(this)
		}

		// Update childArmature clock.
		for (slot in this._slots) {
			val childArmature = slot.childArmature
			if (childArmature !== null) {
				childArmature.clock = this._clock
			}
		}
	}
	/**
	 * - Get the parent slot which the armature belongs to.
	 * @see dragonBones.Slot
	 * @version DragonBones 4.5
	 * @language en_US
	 */
	/**
	 * - 该骨架所属的父插槽。
	 * @see dragonBones.Slot
	 * @version DragonBones 4.5
	 * @language zh_CN
	 */
	public val parent: Slot? get() {
		return this._parent
	}
	/**
	 * - Deprecated, please refer to {@link #display}.
	 * @deprecated
	 * @language en_US
	 */
	/**
	 * - 已废弃，请参考 {@link #display}。
	 * @deprecated
	 * @language zh_CN
	 */
	public fun getDisplay(): Any {
		return this._display
	}
}