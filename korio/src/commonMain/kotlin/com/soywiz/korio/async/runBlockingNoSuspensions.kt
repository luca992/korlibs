package com.soywiz.korio.async

import com.soywiz.korio.error.*
import com.soywiz.korio.lang.*
import com.soywiz.std.coroutine.*
import kotlinx.coroutines.*
import kotlinx.coroutines.intrinsics.*
import kotlinx.coroutines.timeunit.*
import kotlin.coroutines.*

// Fails on JS:     InvalidOperationException: ioSync completed=true, result=null, resultEx=null, suspendCount=3015
///**
// * Allows to execute a suspendable block as long as you can ensure no suspending will happen at all..
// */
//fun <T : Any> runBlockingNoSuspensions(callback: suspend () -> T): T {
//	var completed = false
//	var result: T? = null
//	var resultEx: Throwable? = null
//	var suspendCount = 0
//
//	callback.startCoroutineUndispatched(object : Continuation<T> {
//		override val context: CoroutineContext = object : ContinuationInterceptor {
//			override val key: CoroutineContext.Key<*> = ContinuationInterceptor.Key
//
//			override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
//				suspendCount++
//				return continuation
//			}
//		}
//		override fun resume(value: T) {
//			result = value
//			completed = true
//			println("COMPLETED WITH RESULT: result=$result")
//		}
//		override fun resumeWithException(exception: Throwable) {
//			resultEx = exception
//			completed = true
//			println("COMPLETED WITH EXCEPTION: exception=$exception")
//			exception.printStackTrace()
//		}
//	})
//	if (!completed) invalidOp("ioSync was not completed synchronously! suspendCount=$suspendCount")
//	if (resultEx != null) throw resultEx!!
//	if (result != null) return result!!
//	invalidOp("ioSync completed=$completed, result=$result, resultEx=$resultEx, suspendCount=$suspendCount")
//}

/**
 * Allows to execute a suspendable block as long as you can ensure no suspending will happen at all..
 */
fun <T : Any> runBlockingNoSuspensions(callback: suspend () -> T): T {
	//TODO("runBlockingNoSuspensions not supported yet!")
	var completed = false
	lateinit var result: T
	var resultEx: Throwable? = null
	var suspendCount = 0

	callback.startCoroutineUndispatched(object : OldContinuationAdaptor<T?>() {
		override val context: CoroutineContext = object :
				AbstractCoroutineContextElement(ContinuationInterceptor), ContinuationInterceptor, Delay {
			override val key: CoroutineContext.Key<*> = ContinuationInterceptor.Key

			override fun <T> interceptContinuation(continuation: Continuation<T>): Continuation<T> {
				suspendCount++
				return continuation
			}

			fun dispatch(context: CoroutineContext, block: Runnable) {
				block.run()
			}

			override fun scheduleResumeAfterDelay(timeMillis: Long,
												  continuation: CancellableContinuation<Unit>): Unit {
				continuation.resume(Unit)
			}
		}
		override fun resume(value: T?) {
			val rvalue = value ?: (Unit as T) // @TODO: Kotlin-js BUG reteurns undefined instead of Unit! In runBlockingNoSuspensions { uncompress(i.toAsyncInputStream(), o.toAsyncOutputStream()) }
			//if (rvalue == null) error("ERROR: unexpected completed value=$value, rvalue=$rvalue, suspendCount=$suspendCount")
			result = rvalue
			completed = true
			//println("COMPLETED WITH RESULT: result=$result, value=$rvalue")
		}
		override fun resumeWithException(exception: Throwable) {
			resultEx = exception
			completed = true
			//println("COMPLETED WITH EXCEPTION: exception=$exception")
			exception.printStackTrace()
		}
	})
	if (!completed) invalidOp("ioSync was not completed synchronously! suspendCount=$suspendCount")
	if (resultEx != null) throw resultEx!!
	return result
}
