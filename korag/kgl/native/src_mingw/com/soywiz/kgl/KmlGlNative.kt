// WARNING: File autogenerated DO NOT modify
// https://www.khronos.org/registry/OpenGL/api/GLES2/gl2.h
@file:Suppress("unused", "RedundantUnitReturnType")

package com.soywiz.kgl

import com.soywiz.kmem.*
import kotlinx.cinterop.*
import platform.opengl32.*
import platform.windows.*
import platform.posix.*

class KmlGlNative : KmlGl() {
    override fun activeTexture(texture: Int): Unit = tempBufferAddress { glActiveTexture(texture) }
    override fun attachShader(program: Int, shader: Int): Unit = tempBufferAddress { glAttachShader(program, shader) }
    override fun bindAttribLocation(program: Int, index: Int, name: String): Unit = memScoped { tempBufferAddress { glBindAttribLocation(program, index, (name).cstr.getPointer(this@memScoped)) } }
    override fun bindBuffer(target: Int, buffer: Int): Unit = tempBufferAddress { glBindBuffer(target, buffer) }
    override fun bindFramebuffer(target: Int, framebuffer: Int): Unit = tempBufferAddress { glBindFramebuffer(target, framebuffer) }
    override fun bindRenderbuffer(target: Int, renderbuffer: Int): Unit = tempBufferAddress { glBindRenderbuffer(target, renderbuffer) }
    override fun bindTexture(target: Int, texture: Int): Unit = tempBufferAddress { glBindTexture(target, texture) }
    override fun blendColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = tempBufferAddress { glBlendColor(red, green, blue, alpha) }
    override fun blendEquation(mode: Int): Unit = tempBufferAddress { glBlendEquation(mode) }
    override fun blendEquationSeparate(modeRGB: Int, modeAlpha: Int): Unit = tempBufferAddress { glBlendEquationSeparate(modeRGB, modeAlpha) }
    override fun blendFunc(sfactor: Int, dfactor: Int): Unit = tempBufferAddress { glBlendFunc(sfactor, dfactor) }
    override fun blendFuncSeparate(sfactorRGB: Int, dfactorRGB: Int, sfactorAlpha: Int, dfactorAlpha: Int): Unit = tempBufferAddress { glBlendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha) }
    override fun bufferData(target: Int, size: Int, data: KmlNativeBuffer, usage: Int): Unit = tempBufferAddress { glBufferData(target, size.narrowSize(), data.unsafeAddress().uncheckedCast(), usage) }
    override fun bufferSubData(target: Int, offset: Int, size: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glBufferSubData(target, offset.narrowSize(), size.narrowSize(), data.unsafeAddress().uncheckedCast()) }
    override fun checkFramebufferStatus(target: Int): Int = tempBufferAddress { glCheckFramebufferStatus(target) }
    override fun clear(mask: Int): Unit = tempBufferAddress { glClear(mask) }
    override fun clearColor(red: Float, green: Float, blue: Float, alpha: Float): Unit = tempBufferAddress { glClearColor(red, green, blue, alpha) }
    override fun clearDepthf(d: Float): Unit = tempBufferAddress { glClearDepth(d.narrowFloat()) }
    override fun clearStencil(s: Int): Unit = tempBufferAddress { glClearStencil(s) }
    override fun colorMask(red: Boolean, green: Boolean, blue: Boolean, alpha: Boolean): Unit = tempBufferAddress { glColorMask(red.narrow(), green.narrow(), blue.narrow(), alpha.narrow()) }
    override fun compileShader(shader: Int): Unit = tempBufferAddress { glCompileShader(shader) }
    override fun compressedTexImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, imageSize: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glCompressedTexImage2D(target, level, internalformat, width, height, border, imageSize, data.unsafeAddress().uncheckedCast()) }
    override fun compressedTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, imageSize: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glCompressedTexSubImage2D(target, level, xoffset, yoffset, width, height, format, imageSize, data.unsafeAddress().uncheckedCast()) }
    override fun copyTexImage2D(target: Int, level: Int, internalformat: Int, x: Int, y: Int, width: Int, height: Int, border: Int): Unit = tempBufferAddress { glCopyTexImage2D(target, level, internalformat, x, y, width, height, border) }
    override fun copyTexSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, x: Int, y: Int, width: Int, height: Int): Unit = tempBufferAddress { glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height) }
    override fun createProgram(): Int = tempBufferAddress { glCreateProgram() }
    override fun createShader(type: Int): Int = tempBufferAddress { glCreateShader(type) }
    override fun cullFace(mode: Int): Unit = tempBufferAddress { glCullFace(mode) }
    override fun deleteBuffers(n: Int, items: KmlNativeBuffer): Unit = tempBufferAddress { glDeleteBuffers(n, items.unsafeAddress().uncheckedCast()) }
    override fun deleteFramebuffers(n: Int, items: KmlNativeBuffer): Unit = tempBufferAddress { glDeleteFramebuffers(n, items.unsafeAddress().uncheckedCast()) }
    override fun deleteProgram(program: Int): Unit = tempBufferAddress { glDeleteProgram(program) }
    override fun deleteRenderbuffers(n: Int, items: KmlNativeBuffer): Unit = tempBufferAddress { glDeleteRenderbuffers(n, items.unsafeAddress().uncheckedCast()) }
    override fun deleteShader(shader: Int): Unit = tempBufferAddress { glDeleteShader(shader) }
    override fun deleteTextures(n: Int, items: KmlNativeBuffer): Unit = tempBufferAddress { glDeleteTextures(n, items.unsafeAddress().uncheckedCast()) }
    override fun depthFunc(func: Int): Unit = tempBufferAddress { glDepthFunc(func) }
    override fun depthMask(flag: Boolean): Unit = tempBufferAddress { glDepthMask(flag.narrow()) }
    override fun depthRangef(n: Float, f: Float): Unit = tempBufferAddress { glDepthRange(n.narrowFloat(), f.narrowFloat()) }
    override fun detachShader(program: Int, shader: Int): Unit = tempBufferAddress { glDetachShader(program, shader) }
    override fun disable(cap: Int): Unit = tempBufferAddress { glDisable(cap) }
    override fun disableVertexAttribArray(index: Int): Unit = tempBufferAddress { glDisableVertexAttribArray(index) }
    override fun drawArrays(mode: Int, first: Int, count: Int): Unit = tempBufferAddress { glDrawArrays(mode, first, count) }
    override fun drawElements(mode: Int, count: Int, type: Int, indices: Int): Unit = tempBufferAddress { glDrawElements(mode, count, type, indices.uncheckedCast()) }
    override fun enable(cap: Int): Unit = tempBufferAddress { glEnable(cap) }
    override fun enableVertexAttribArray(index: Int): Unit = tempBufferAddress { glEnableVertexAttribArray(index) }
    override fun finish(): Unit = tempBufferAddress { glFinish() }
    override fun flush(): Unit = tempBufferAddress { glFlush() }
    override fun framebufferRenderbuffer(target: Int, attachment: Int, renderbuffertarget: Int, renderbuffer: Int): Unit = tempBufferAddress { glFramebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer) }
    override fun framebufferTexture2D(target: Int, attachment: Int, textarget: Int, texture: Int, level: Int): Unit = tempBufferAddress { glFramebufferTexture2D(target, attachment, textarget, texture, level) }
    override fun frontFace(mode: Int): Unit = tempBufferAddress { glFrontFace(mode) }
    override fun genBuffers(n: Int, buffers: KmlNativeBuffer): Unit = tempBufferAddress { glGenBuffers(n, buffers.unsafeAddress().uncheckedCast()) }
    override fun generateMipmap(target: Int): Unit = tempBufferAddress { glGenerateMipmap(target) }
    override fun genFramebuffers(n: Int, framebuffers: KmlNativeBuffer): Unit = tempBufferAddress { glGenFramebuffers(n, framebuffers.unsafeAddress().uncheckedCast()) }
    override fun genRenderbuffers(n: Int, renderbuffers: KmlNativeBuffer): Unit = tempBufferAddress { glGenRenderbuffers(n, renderbuffers.unsafeAddress().uncheckedCast()) }
    override fun genTextures(n: Int, textures: KmlNativeBuffer): Unit = tempBufferAddress { glGenTextures(n, textures.unsafeAddress().uncheckedCast()) }
    override fun getActiveAttrib(program: Int, index: Int, bufSize: Int, length: KmlNativeBuffer, size: KmlNativeBuffer, type: KmlNativeBuffer, name: KmlNativeBuffer): Unit = tempBufferAddress { glGetActiveAttrib(program, index, bufSize, length.unsafeAddress().uncheckedCast(), size.unsafeAddress().uncheckedCast(), type.unsafeAddress().uncheckedCast(), name.unsafeAddress().uncheckedCast()) }
    override fun getActiveUniform(program: Int, index: Int, bufSize: Int, length: KmlNativeBuffer, size: KmlNativeBuffer, type: KmlNativeBuffer, name: KmlNativeBuffer): Unit = tempBufferAddress { glGetActiveUniform(program, index, bufSize, length.unsafeAddress().uncheckedCast(), size.unsafeAddress().uncheckedCast(), type.unsafeAddress().uncheckedCast(), name.unsafeAddress().uncheckedCast()) }
    override fun getAttachedShaders(program: Int, maxCount: Int, count: KmlNativeBuffer, shaders: KmlNativeBuffer): Unit = tempBufferAddress { glGetAttachedShaders(program, maxCount, count.unsafeAddress().uncheckedCast(), shaders.unsafeAddress().uncheckedCast()) }
    override fun getAttribLocation(program: Int, name: String): Int = memScoped { tempBufferAddress { glGetAttribLocation(program, (name).cstr.getPointer(this@memScoped)) } }
    override fun getUniformLocation(program: Int, name: String): Int = memScoped { tempBufferAddress { glGetUniformLocation(program, (name).cstr.getPointer(this@memScoped)) } }
    override fun getBooleanv(pname: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glGetBooleanv(pname, data.unsafeAddress().uncheckedCast()) }
    override fun getBufferParameteriv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetBufferParameteriv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getError(): Int = tempBufferAddress { glGetError() }
    override fun getFloatv(pname: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glGetFloatv(pname, data.unsafeAddress().uncheckedCast()) }
    override fun getFramebufferAttachmentParameteriv(target: Int, attachment: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetFramebufferAttachmentParameteriv(target, attachment, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getIntegerv(pname: Int, data: KmlNativeBuffer): Unit = tempBufferAddress { glGetIntegerv(pname, data.unsafeAddress().uncheckedCast()) }
    override fun getProgramInfoLog(program: Int, bufSize: Int, length: KmlNativeBuffer, infoLog: KmlNativeBuffer): Unit = tempBufferAddress { glGetProgramInfoLog(program, bufSize, length.unsafeAddress().uncheckedCast(), infoLog.unsafeAddress().uncheckedCast()) }
    override fun getRenderbufferParameteriv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetRenderbufferParameteriv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getProgramiv(program: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetProgramiv(program, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getShaderiv(shader: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetShaderiv(shader, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getShaderInfoLog(shader: Int, bufSize: Int, length: KmlNativeBuffer, infoLog: KmlNativeBuffer): Unit = tempBufferAddress { glGetShaderInfoLog(shader, bufSize, length.unsafeAddress().uncheckedCast(), infoLog.unsafeAddress().uncheckedCast()) }
    override fun getShaderPrecisionFormat(shadertype: Int, precisiontype: Int, range: KmlNativeBuffer, precision: KmlNativeBuffer): Unit = tempBufferAddress { Unit }
    override fun getShaderSource(shader: Int, bufSize: Int, length: KmlNativeBuffer, source: KmlNativeBuffer): Unit = tempBufferAddress { glGetShaderSource(shader, bufSize, length.unsafeAddress().uncheckedCast(), source.unsafeAddress().uncheckedCast()) }
    override fun getString(name: Int): String = tempBufferAddress { (glGetString(name))?.toKString() ?: "" }
    override fun getTexParameterfv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetTexParameterfv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getTexParameteriv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetTexParameteriv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getUniformfv(program: Int, location: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetUniformfv(program, location, params.unsafeAddress().uncheckedCast()) }
    override fun getUniformiv(program: Int, location: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetUniformiv(program, location, params.unsafeAddress().uncheckedCast()) }
    override fun getVertexAttribfv(index: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetVertexAttribfv(index, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getVertexAttribiv(index: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glGetVertexAttribiv(index, pname, params.unsafeAddress().uncheckedCast()) }
    override fun getVertexAttribPointerv(index: Int, pname: Int, pointer: KmlNativeBuffer): Unit = tempBufferAddress { glGetVertexAttribPointerv(index, pname, pointer.unsafeAddress().uncheckedCast()) }
    override fun hint(target: Int, mode: Int): Unit = tempBufferAddress { glHint(target, mode) }
    override fun isBuffer(buffer: Int): Boolean = tempBufferAddress { glIsBuffer(buffer).toBool() }
    override fun isEnabled(cap: Int): Boolean = tempBufferAddress { glIsEnabled(cap).toBool() }
    override fun isFramebuffer(framebuffer: Int): Boolean = tempBufferAddress { glIsFramebuffer(framebuffer).toBool() }
    override fun isProgram(program: Int): Boolean = tempBufferAddress { glIsProgram(program).toBool() }
    override fun isRenderbuffer(renderbuffer: Int): Boolean = tempBufferAddress { glIsRenderbuffer(renderbuffer).toBool() }
    override fun isShader(shader: Int): Boolean = tempBufferAddress { glIsShader(shader).toBool() }
    override fun isTexture(texture: Int): Boolean = tempBufferAddress { glIsTexture(texture).toBool() }
    override fun lineWidth(width: Float): Unit = tempBufferAddress { glLineWidth(width) }
    override fun linkProgram(program: Int): Unit = tempBufferAddress { glLinkProgram(program) }
    override fun pixelStorei(pname: Int, param: Int): Unit = tempBufferAddress { glPixelStorei(pname, param) }
    override fun polygonOffset(factor: Float, units: Float): Unit = tempBufferAddress { glPolygonOffset(factor, units) }
    override fun readPixels(x: Int, y: Int, width: Int, height: Int, format: Int, type: Int, pixels: KmlNativeBuffer): Unit = tempBufferAddress { glReadPixels(x, y, width, height, format, type, pixels.unsafeAddress().uncheckedCast()) }
    override fun releaseShaderCompiler(): Unit = tempBufferAddress { Unit }
    override fun renderbufferStorage(target: Int, internalformat: Int, width: Int, height: Int): Unit = tempBufferAddress { glRenderbufferStorage(target, internalformat, width, height) }
    override fun sampleCoverage(value: Float, invert: Boolean): Unit = tempBufferAddress { glSampleCoverage(value, invert.narrow()) }
    override fun scissor(x: Int, y: Int, width: Int, height: Int): Unit = tempBufferAddress { glScissor(x, y, width, height) }
    override fun shaderBinary(count: Int, shaders: KmlNativeBuffer, binaryformat: Int, binary: KmlNativeBuffer, length: Int): Unit = tempBufferAddress { throw KmlGlException("shaderBinary not implemented in Native") }
    override fun shaderSource(shader: Int, string: String): Unit = memScoped { tempBufferAddress { run {
                memScoped {
                    val lengths = allocArray<IntVar>(1)
                    val strings = allocArray<CPointerVar<ByteVar>>(1)
                    lengths[0] = strlen(string).narrow()
                    strings[0] = string.cstr.placeTo(this)
                    glShaderSource(shader, 1, strings, lengths)
                }
                } } }
    override fun stencilFunc(func: Int, ref: Int, mask: Int): Unit = tempBufferAddress { glStencilFunc(func, ref, mask) }
    override fun stencilFuncSeparate(face: Int, func: Int, ref: Int, mask: Int): Unit = tempBufferAddress { glStencilFuncSeparate(face, func, ref, mask) }
    override fun stencilMask(mask: Int): Unit = tempBufferAddress { glStencilMask(mask) }
    override fun stencilMaskSeparate(face: Int, mask: Int): Unit = tempBufferAddress { glStencilMaskSeparate(face, mask) }
    override fun stencilOp(fail: Int, zfail: Int, zpass: Int): Unit = tempBufferAddress { glStencilOp(fail, zfail, zpass) }
    override fun stencilOpSeparate(face: Int, sfail: Int, dpfail: Int, dppass: Int): Unit = tempBufferAddress { glStencilOpSeparate(face, sfail, dpfail, dppass) }
    override fun texImage2D(target: Int, level: Int, internalformat: Int, width: Int, height: Int, border: Int, format: Int, type: Int, pixels: KmlNativeBuffer): Unit = tempBufferAddress { glTexImage2D(target, level, internalformat, width, height, border, format, type, pixels.unsafeAddress().uncheckedCast()) }
    override fun texImage2D(target: Int, level: Int, internalformat: Int, format: Int, type: Int, data: KmlNativeImageData): Unit = tempBufferAddress { (data as KmlNativeNativeImageData).data.usePinned { dataPin -> glTexImage2D(target, level, internalformat, data.width, data.height, 0, format, type, dataPin.addressOf(0).uncheckedCast()) } }
    override fun texParameterf(target: Int, pname: Int, param: Float): Unit = tempBufferAddress { glTexParameterf(target, pname, param) }
    override fun texParameterfv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glTexParameterfv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun texParameteri(target: Int, pname: Int, param: Int): Unit = tempBufferAddress { glTexParameteri(target, pname, param) }
    override fun texParameteriv(target: Int, pname: Int, params: KmlNativeBuffer): Unit = tempBufferAddress { glTexParameteriv(target, pname, params.unsafeAddress().uncheckedCast()) }
    override fun texSubImage2D(target: Int, level: Int, xoffset: Int, yoffset: Int, width: Int, height: Int, format: Int, type: Int, pixels: KmlNativeBuffer): Unit = tempBufferAddress { glTexSubImage2D(target, level, xoffset, yoffset, width, height, format, type, pixels.unsafeAddress().uncheckedCast()) }
    override fun uniform1f(location: Int, v0: Float): Unit = tempBufferAddress { glUniform1f(location, v0) }
    override fun uniform1fv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform1fv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform1i(location: Int, v0: Int): Unit = tempBufferAddress { glUniform1i(location, v0) }
    override fun uniform1iv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform1iv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform2f(location: Int, v0: Float, v1: Float): Unit = tempBufferAddress { glUniform2f(location, v0, v1) }
    override fun uniform2fv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform2fv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform2i(location: Int, v0: Int, v1: Int): Unit = tempBufferAddress { glUniform2i(location, v0, v1) }
    override fun uniform2iv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform2iv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform3f(location: Int, v0: Float, v1: Float, v2: Float): Unit = tempBufferAddress { glUniform3f(location, v0, v1, v2) }
    override fun uniform3fv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform3fv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform3i(location: Int, v0: Int, v1: Int, v2: Int): Unit = tempBufferAddress { glUniform3i(location, v0, v1, v2) }
    override fun uniform3iv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform3iv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform4f(location: Int, v0: Float, v1: Float, v2: Float, v3: Float): Unit = tempBufferAddress { glUniform4f(location, v0, v1, v2, v3) }
    override fun uniform4fv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform4fv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniform4i(location: Int, v0: Int, v1: Int, v2: Int, v3: Int): Unit = tempBufferAddress { glUniform4i(location, v0, v1, v2, v3) }
    override fun uniform4iv(location: Int, count: Int, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniform4iv(location, count, value.unsafeAddress().uncheckedCast()) }
    override fun uniformMatrix2fv(location: Int, count: Int, transpose: Boolean, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniformMatrix2fv(location, count, transpose.narrow(), value.unsafeAddress().uncheckedCast()) }
    override fun uniformMatrix3fv(location: Int, count: Int, transpose: Boolean, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniformMatrix3fv(location, count, transpose.narrow(), value.unsafeAddress().uncheckedCast()) }
    override fun uniformMatrix4fv(location: Int, count: Int, transpose: Boolean, value: KmlNativeBuffer): Unit = tempBufferAddress { glUniformMatrix4fv(location, count, transpose.narrow(), value.unsafeAddress().uncheckedCast()) }
    override fun useProgram(program: Int): Unit = tempBufferAddress { glUseProgram(program) }
    override fun validateProgram(program: Int): Unit = tempBufferAddress { glValidateProgram(program) }
    override fun vertexAttrib1f(index: Int, x: Float): Unit = tempBufferAddress { glVertexAttrib1f(index, x) }
    override fun vertexAttrib1fv(index: Int, v: KmlNativeBuffer): Unit = tempBufferAddress { glVertexAttrib1fv(index, v.unsafeAddress().uncheckedCast()) }
    override fun vertexAttrib2f(index: Int, x: Float, y: Float): Unit = tempBufferAddress { glVertexAttrib2f(index, x, y) }
    override fun vertexAttrib2fv(index: Int, v: KmlNativeBuffer): Unit = tempBufferAddress { glVertexAttrib2fv(index, v.unsafeAddress().uncheckedCast()) }
    override fun vertexAttrib3f(index: Int, x: Float, y: Float, z: Float): Unit = tempBufferAddress { glVertexAttrib3f(index, x, y, z) }
    override fun vertexAttrib3fv(index: Int, v: KmlNativeBuffer): Unit = tempBufferAddress { glVertexAttrib3fv(index, v.unsafeAddress().uncheckedCast()) }
    override fun vertexAttrib4f(index: Int, x: Float, y: Float, z: Float, w: Float): Unit = tempBufferAddress { glVertexAttrib4f(index, x, y, z, w) }
    override fun vertexAttrib4fv(index: Int, v: KmlNativeBuffer): Unit = tempBufferAddress { glVertexAttrib4fv(index, v.unsafeAddress().uncheckedCast()) }
    override fun vertexAttribPointer(index: Int, size: Int, type: Int, normalized: Boolean, stride: Int, pointer: Int): Unit = tempBufferAddress { glVertexAttribPointer(index, size, type, normalized.narrow(), stride, pointer.uncheckedCast()) }
    override fun viewport(x: Int, y: Int, width: Int, height: Int): Unit = tempBufferAddress { glViewport(x, y, width, height) }
}
val glActiveTexture: PFNGLACTIVETEXTUREPROC by lazy { wglGetProcAddressAny("glActiveTexture").uncheckedCast<PFNGLACTIVETEXTUREPROC>() }
val glAttachShader: PFNGLATTACHSHADERPROC by lazy { wglGetProcAddressAny("glAttachShader").uncheckedCast<PFNGLATTACHSHADERPROC>() }
val glBindAttribLocation: PFNGLBINDATTRIBLOCATIONPROC by lazy { wglGetProcAddressAny("glBindAttribLocation").uncheckedCast<PFNGLBINDATTRIBLOCATIONPROC>() }
val glBindBuffer: PFNGLBINDBUFFERPROC by lazy { wglGetProcAddressAny("glBindBuffer").uncheckedCast<PFNGLBINDBUFFERPROC>() }
val glBindFramebuffer: PFNGLBINDFRAMEBUFFERPROC by lazy { wglGetProcAddressAny("glBindFramebuffer").uncheckedCast<PFNGLBINDFRAMEBUFFERPROC>() }
val glBindRenderbuffer: PFNGLBINDRENDERBUFFERPROC by lazy { wglGetProcAddressAny("glBindRenderbuffer").uncheckedCast<PFNGLBINDRENDERBUFFERPROC>() }
val glBlendColor: PFNGLBLENDCOLORPROC by lazy { wglGetProcAddressAny("glBlendColor").uncheckedCast<PFNGLBLENDCOLORPROC>() }
val glBlendEquation: PFNGLBLENDEQUATIONPROC by lazy { wglGetProcAddressAny("glBlendEquation").uncheckedCast<PFNGLBLENDEQUATIONPROC>() }
val glBlendEquationSeparate: PFNGLBLENDEQUATIONSEPARATEPROC by lazy { wglGetProcAddressAny("glBlendEquationSeparate").uncheckedCast<PFNGLBLENDEQUATIONSEPARATEPROC>() }
val glBlendFuncSeparate: PFNGLBLENDFUNCSEPARATEPROC by lazy { wglGetProcAddressAny("glBlendFuncSeparate").uncheckedCast<PFNGLBLENDFUNCSEPARATEPROC>() }
val glBufferData: PFNGLBUFFERDATAPROC by lazy { wglGetProcAddressAny("glBufferData").uncheckedCast<PFNGLBUFFERDATAPROC>() }
val glBufferSubData: PFNGLBUFFERSUBDATAPROC by lazy { wglGetProcAddressAny("glBufferSubData").uncheckedCast<PFNGLBUFFERSUBDATAPROC>() }
val glCheckFramebufferStatus: PFNGLCHECKFRAMEBUFFERSTATUSPROC by lazy { wglGetProcAddressAny("glCheckFramebufferStatus").uncheckedCast<PFNGLCHECKFRAMEBUFFERSTATUSPROC>() }
val glCompileShader: PFNGLCOMPILESHADERPROC by lazy { wglGetProcAddressAny("glCompileShader").uncheckedCast<PFNGLCOMPILESHADERPROC>() }
val glCompressedTexImage2D: PFNGLCOMPRESSEDTEXIMAGE2DPROC by lazy { wglGetProcAddressAny("glCompressedTexImage2D").uncheckedCast<PFNGLCOMPRESSEDTEXIMAGE2DPROC>() }
val glCompressedTexSubImage2D: PFNGLCOMPRESSEDTEXSUBIMAGE2DPROC by lazy { wglGetProcAddressAny("glCompressedTexSubImage2D").uncheckedCast<PFNGLCOMPRESSEDTEXSUBIMAGE2DPROC>() }
val glCreateProgram: PFNGLCREATEPROGRAMPROC by lazy { wglGetProcAddressAny("glCreateProgram").uncheckedCast<PFNGLCREATEPROGRAMPROC>() }
val glCreateShader: PFNGLCREATESHADERPROC by lazy { wglGetProcAddressAny("glCreateShader").uncheckedCast<PFNGLCREATESHADERPROC>() }
val glDeleteBuffers: PFNGLDELETEBUFFERSPROC by lazy { wglGetProcAddressAny("glDeleteBuffers").uncheckedCast<PFNGLDELETEBUFFERSPROC>() }
val glDeleteFramebuffers: PFNGLDELETEFRAMEBUFFERSPROC by lazy { wglGetProcAddressAny("glDeleteFramebuffers").uncheckedCast<PFNGLDELETEFRAMEBUFFERSPROC>() }
val glDeleteProgram: PFNGLDELETEPROGRAMPROC by lazy { wglGetProcAddressAny("glDeleteProgram").uncheckedCast<PFNGLDELETEPROGRAMPROC>() }
val glDeleteRenderbuffers: PFNGLDELETERENDERBUFFERSPROC by lazy { wglGetProcAddressAny("glDeleteRenderbuffers").uncheckedCast<PFNGLDELETERENDERBUFFERSPROC>() }
val glDeleteShader: PFNGLDELETESHADERPROC by lazy { wglGetProcAddressAny("glDeleteShader").uncheckedCast<PFNGLDELETESHADERPROC>() }
val glDetachShader: PFNGLDETACHSHADERPROC by lazy { wglGetProcAddressAny("glDetachShader").uncheckedCast<PFNGLDETACHSHADERPROC>() }
val glDisableVertexAttribArray: PFNGLDISABLEVERTEXATTRIBARRAYPROC by lazy { wglGetProcAddressAny("glDisableVertexAttribArray").uncheckedCast<PFNGLDISABLEVERTEXATTRIBARRAYPROC>() }
val glEnableVertexAttribArray: PFNGLENABLEVERTEXATTRIBARRAYPROC by lazy { wglGetProcAddressAny("glEnableVertexAttribArray").uncheckedCast<PFNGLENABLEVERTEXATTRIBARRAYPROC>() }
val glFramebufferRenderbuffer: PFNGLFRAMEBUFFERRENDERBUFFERPROC by lazy { wglGetProcAddressAny("glFramebufferRenderbuffer").uncheckedCast<PFNGLFRAMEBUFFERRENDERBUFFERPROC>() }
val glFramebufferTexture2D: PFNGLFRAMEBUFFERTEXTURE2DPROC by lazy { wglGetProcAddressAny("glFramebufferTexture2D").uncheckedCast<PFNGLFRAMEBUFFERTEXTURE2DPROC>() }
val glGenBuffers: PFNGLGENBUFFERSPROC by lazy { wglGetProcAddressAny("glGenBuffers").uncheckedCast<PFNGLGENBUFFERSPROC>() }
val glGenerateMipmap: PFNGLGENERATEMIPMAPPROC by lazy { wglGetProcAddressAny("glGenerateMipmap").uncheckedCast<PFNGLGENERATEMIPMAPPROC>() }
val glGenFramebuffers: PFNGLGENFRAMEBUFFERSPROC by lazy { wglGetProcAddressAny("glGenFramebuffers").uncheckedCast<PFNGLGENFRAMEBUFFERSPROC>() }
val glGenRenderbuffers: PFNGLGENRENDERBUFFERSPROC by lazy { wglGetProcAddressAny("glGenRenderbuffers").uncheckedCast<PFNGLGENRENDERBUFFERSPROC>() }
val glGetActiveAttrib: PFNGLGETACTIVEATTRIBPROC by lazy { wglGetProcAddressAny("glGetActiveAttrib").uncheckedCast<PFNGLGETACTIVEATTRIBPROC>() }
val glGetActiveUniform: PFNGLGETACTIVEUNIFORMPROC by lazy { wglGetProcAddressAny("glGetActiveUniform").uncheckedCast<PFNGLGETACTIVEUNIFORMPROC>() }
val glGetAttachedShaders: PFNGLGETATTACHEDSHADERSPROC by lazy { wglGetProcAddressAny("glGetAttachedShaders").uncheckedCast<PFNGLGETATTACHEDSHADERSPROC>() }
val glGetAttribLocation: PFNGLGETATTRIBLOCATIONPROC by lazy { wglGetProcAddressAny("glGetAttribLocation").uncheckedCast<PFNGLGETATTRIBLOCATIONPROC>() }
val glGetUniformLocation: PFNGLGETUNIFORMLOCATIONPROC by lazy { wglGetProcAddressAny("glGetUniformLocation").uncheckedCast<PFNGLGETUNIFORMLOCATIONPROC>() }
val glGetBufferParameteriv: PFNGLGETBUFFERPARAMETERIVPROC by lazy { wglGetProcAddressAny("glGetBufferParameteriv").uncheckedCast<PFNGLGETBUFFERPARAMETERIVPROC>() }
val glGetFramebufferAttachmentParameteriv: PFNGLGETFRAMEBUFFERATTACHMENTPARAMETERIVPROC by lazy { wglGetProcAddressAny("glGetFramebufferAttachmentParameteriv").uncheckedCast<PFNGLGETFRAMEBUFFERATTACHMENTPARAMETERIVPROC>() }
val glGetProgramInfoLog: PFNGLGETPROGRAMINFOLOGPROC by lazy { wglGetProcAddressAny("glGetProgramInfoLog").uncheckedCast<PFNGLGETPROGRAMINFOLOGPROC>() }
val glGetRenderbufferParameteriv: PFNGLGETRENDERBUFFERPARAMETERIVPROC by lazy { wglGetProcAddressAny("glGetRenderbufferParameteriv").uncheckedCast<PFNGLGETRENDERBUFFERPARAMETERIVPROC>() }
val glGetProgramiv: PFNGLGETPROGRAMIVPROC by lazy { wglGetProcAddressAny("glGetProgramiv").uncheckedCast<PFNGLGETPROGRAMIVPROC>() }
val glGetShaderiv: PFNGLGETSHADERIVPROC by lazy { wglGetProcAddressAny("glGetShaderiv").uncheckedCast<PFNGLGETSHADERIVPROC>() }
val glGetShaderInfoLog: PFNGLGETSHADERINFOLOGPROC by lazy { wglGetProcAddressAny("glGetShaderInfoLog").uncheckedCast<PFNGLGETSHADERINFOLOGPROC>() }
val glGetShaderPrecisionFormat: PFNGLGETSHADERPRECISIONFORMATPROC by lazy { wglGetProcAddressAny("glGetShaderPrecisionFormat").uncheckedCast<PFNGLGETSHADERPRECISIONFORMATPROC>() }
val glGetShaderSource: PFNGLGETSHADERSOURCEPROC by lazy { wglGetProcAddressAny("glGetShaderSource").uncheckedCast<PFNGLGETSHADERSOURCEPROC>() }
val glGetUniformfv: PFNGLGETUNIFORMFVPROC by lazy { wglGetProcAddressAny("glGetUniformfv").uncheckedCast<PFNGLGETUNIFORMFVPROC>() }
val glGetUniformiv: PFNGLGETUNIFORMIVPROC by lazy { wglGetProcAddressAny("glGetUniformiv").uncheckedCast<PFNGLGETUNIFORMIVPROC>() }
val glGetVertexAttribfv: PFNGLGETVERTEXATTRIBFVPROC by lazy { wglGetProcAddressAny("glGetVertexAttribfv").uncheckedCast<PFNGLGETVERTEXATTRIBFVPROC>() }
val glGetVertexAttribiv: PFNGLGETVERTEXATTRIBIVPROC by lazy { wglGetProcAddressAny("glGetVertexAttribiv").uncheckedCast<PFNGLGETVERTEXATTRIBIVPROC>() }
val glGetVertexAttribPointerv: PFNGLGETVERTEXATTRIBPOINTERVPROC by lazy { wglGetProcAddressAny("glGetVertexAttribPointerv").uncheckedCast<PFNGLGETVERTEXATTRIBPOINTERVPROC>() }
val glIsBuffer: PFNGLISBUFFERPROC by lazy { wglGetProcAddressAny("glIsBuffer").uncheckedCast<PFNGLISBUFFERPROC>() }
val glIsFramebuffer: PFNGLISFRAMEBUFFERPROC by lazy { wglGetProcAddressAny("glIsFramebuffer").uncheckedCast<PFNGLISFRAMEBUFFERPROC>() }
val glIsProgram: PFNGLISPROGRAMPROC by lazy { wglGetProcAddressAny("glIsProgram").uncheckedCast<PFNGLISPROGRAMPROC>() }
val glIsRenderbuffer: PFNGLISRENDERBUFFERPROC by lazy { wglGetProcAddressAny("glIsRenderbuffer").uncheckedCast<PFNGLISRENDERBUFFERPROC>() }
val glIsShader: PFNGLISSHADERPROC by lazy { wglGetProcAddressAny("glIsShader").uncheckedCast<PFNGLISSHADERPROC>() }
val glLinkProgram: PFNGLLINKPROGRAMPROC by lazy { wglGetProcAddressAny("glLinkProgram").uncheckedCast<PFNGLLINKPROGRAMPROC>() }
val glReleaseShaderCompiler: PFNGLRELEASESHADERCOMPILERPROC by lazy { wglGetProcAddressAny("glReleaseShaderCompiler").uncheckedCast<PFNGLRELEASESHADERCOMPILERPROC>() }
val glRenderbufferStorage: PFNGLRENDERBUFFERSTORAGEPROC by lazy { wglGetProcAddressAny("glRenderbufferStorage").uncheckedCast<PFNGLRENDERBUFFERSTORAGEPROC>() }
val glSampleCoverage: PFNGLSAMPLECOVERAGEPROC by lazy { wglGetProcAddressAny("glSampleCoverage").uncheckedCast<PFNGLSAMPLECOVERAGEPROC>() }
val glShaderBinary: PFNGLSHADERBINARYPROC by lazy { wglGetProcAddressAny("glShaderBinary").uncheckedCast<PFNGLSHADERBINARYPROC>() }
val glShaderSource: PFNGLSHADERSOURCEPROC by lazy { wglGetProcAddressAny("glShaderSource").uncheckedCast<PFNGLSHADERSOURCEPROC>() }
val glStencilFuncSeparate: PFNGLSTENCILFUNCSEPARATEPROC by lazy { wglGetProcAddressAny("glStencilFuncSeparate").uncheckedCast<PFNGLSTENCILFUNCSEPARATEPROC>() }
val glStencilMaskSeparate: PFNGLSTENCILMASKSEPARATEPROC by lazy { wglGetProcAddressAny("glStencilMaskSeparate").uncheckedCast<PFNGLSTENCILMASKSEPARATEPROC>() }
val glStencilOpSeparate: PFNGLSTENCILOPSEPARATEPROC by lazy { wglGetProcAddressAny("glStencilOpSeparate").uncheckedCast<PFNGLSTENCILOPSEPARATEPROC>() }
val glUniform1f: PFNGLUNIFORM1FPROC by lazy { wglGetProcAddressAny("glUniform1f").uncheckedCast<PFNGLUNIFORM1FPROC>() }
val glUniform1fv: PFNGLUNIFORM1FVPROC by lazy { wglGetProcAddressAny("glUniform1fv").uncheckedCast<PFNGLUNIFORM1FVPROC>() }
val glUniform1i: PFNGLUNIFORM1IPROC by lazy { wglGetProcAddressAny("glUniform1i").uncheckedCast<PFNGLUNIFORM1IPROC>() }
val glUniform1iv: PFNGLUNIFORM1IVPROC by lazy { wglGetProcAddressAny("glUniform1iv").uncheckedCast<PFNGLUNIFORM1IVPROC>() }
val glUniform2f: PFNGLUNIFORM2FPROC by lazy { wglGetProcAddressAny("glUniform2f").uncheckedCast<PFNGLUNIFORM2FPROC>() }
val glUniform2fv: PFNGLUNIFORM2FVPROC by lazy { wglGetProcAddressAny("glUniform2fv").uncheckedCast<PFNGLUNIFORM2FVPROC>() }
val glUniform2i: PFNGLUNIFORM2IPROC by lazy { wglGetProcAddressAny("glUniform2i").uncheckedCast<PFNGLUNIFORM2IPROC>() }
val glUniform2iv: PFNGLUNIFORM2IVPROC by lazy { wglGetProcAddressAny("glUniform2iv").uncheckedCast<PFNGLUNIFORM2IVPROC>() }
val glUniform3f: PFNGLUNIFORM3FPROC by lazy { wglGetProcAddressAny("glUniform3f").uncheckedCast<PFNGLUNIFORM3FPROC>() }
val glUniform3fv: PFNGLUNIFORM3FVPROC by lazy { wglGetProcAddressAny("glUniform3fv").uncheckedCast<PFNGLUNIFORM3FVPROC>() }
val glUniform3i: PFNGLUNIFORM3IPROC by lazy { wglGetProcAddressAny("glUniform3i").uncheckedCast<PFNGLUNIFORM3IPROC>() }
val glUniform3iv: PFNGLUNIFORM3IVPROC by lazy { wglGetProcAddressAny("glUniform3iv").uncheckedCast<PFNGLUNIFORM3IVPROC>() }
val glUniform4f: PFNGLUNIFORM4FPROC by lazy { wglGetProcAddressAny("glUniform4f").uncheckedCast<PFNGLUNIFORM4FPROC>() }
val glUniform4fv: PFNGLUNIFORM4FVPROC by lazy { wglGetProcAddressAny("glUniform4fv").uncheckedCast<PFNGLUNIFORM4FVPROC>() }
val glUniform4i: PFNGLUNIFORM4IPROC by lazy { wglGetProcAddressAny("glUniform4i").uncheckedCast<PFNGLUNIFORM4IPROC>() }
val glUniform4iv: PFNGLUNIFORM4IVPROC by lazy { wglGetProcAddressAny("glUniform4iv").uncheckedCast<PFNGLUNIFORM4IVPROC>() }
val glUniformMatrix2fv: PFNGLUNIFORMMATRIX2FVPROC by lazy { wglGetProcAddressAny("glUniformMatrix2fv").uncheckedCast<PFNGLUNIFORMMATRIX2FVPROC>() }
val glUniformMatrix3fv: PFNGLUNIFORMMATRIX3FVPROC by lazy { wglGetProcAddressAny("glUniformMatrix3fv").uncheckedCast<PFNGLUNIFORMMATRIX3FVPROC>() }
val glUniformMatrix4fv: PFNGLUNIFORMMATRIX4FVPROC by lazy { wglGetProcAddressAny("glUniformMatrix4fv").uncheckedCast<PFNGLUNIFORMMATRIX4FVPROC>() }
val glUseProgram: PFNGLUSEPROGRAMPROC by lazy { wglGetProcAddressAny("glUseProgram").uncheckedCast<PFNGLUSEPROGRAMPROC>() }
val glValidateProgram: PFNGLVALIDATEPROGRAMPROC by lazy { wglGetProcAddressAny("glValidateProgram").uncheckedCast<PFNGLVALIDATEPROGRAMPROC>() }
val glVertexAttrib1f: PFNGLVERTEXATTRIB1FPROC by lazy { wglGetProcAddressAny("glVertexAttrib1f").uncheckedCast<PFNGLVERTEXATTRIB1FPROC>() }
val glVertexAttrib1fv: PFNGLVERTEXATTRIB1FVPROC by lazy { wglGetProcAddressAny("glVertexAttrib1fv").uncheckedCast<PFNGLVERTEXATTRIB1FVPROC>() }
val glVertexAttrib2f: PFNGLVERTEXATTRIB2FPROC by lazy { wglGetProcAddressAny("glVertexAttrib2f").uncheckedCast<PFNGLVERTEXATTRIB2FPROC>() }
val glVertexAttrib2fv: PFNGLVERTEXATTRIB2FVPROC by lazy { wglGetProcAddressAny("glVertexAttrib2fv").uncheckedCast<PFNGLVERTEXATTRIB2FVPROC>() }
val glVertexAttrib3f: PFNGLVERTEXATTRIB3FPROC by lazy { wglGetProcAddressAny("glVertexAttrib3f").uncheckedCast<PFNGLVERTEXATTRIB3FPROC>() }
val glVertexAttrib3fv: PFNGLVERTEXATTRIB3FVPROC by lazy { wglGetProcAddressAny("glVertexAttrib3fv").uncheckedCast<PFNGLVERTEXATTRIB3FVPROC>() }
val glVertexAttrib4f: PFNGLVERTEXATTRIB4FPROC by lazy { wglGetProcAddressAny("glVertexAttrib4f").uncheckedCast<PFNGLVERTEXATTRIB4FPROC>() }
val glVertexAttrib4fv: PFNGLVERTEXATTRIB4FVPROC by lazy { wglGetProcAddressAny("glVertexAttrib4fv").uncheckedCast<PFNGLVERTEXATTRIB4FVPROC>() }
val glVertexAttribPointer: PFNGLVERTEXATTRIBPOINTERPROC by lazy { wglGetProcAddressAny("glVertexAttribPointer").uncheckedCast<PFNGLVERTEXATTRIBPOINTERPROC>() }