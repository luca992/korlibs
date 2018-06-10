/*
This file is part of jpcsp.

Jpcsp is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Jpcsp is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Jpcsp.  If not, see <http://www.gnu.org/licenses/>.
 */
package jpcsp.media.codec;

import jpcsp.Memory;

/**
 * Codec interface.
 * 
 * @author gid15
 *
 */
public interface ICodec {
	/**
	 * Initialize the codec.
	 * This method has to be called once before calling the decode() method.
	 * 
	 * @param bytesPerFrame  the number of bytes per input frame (block_align in ffmpeg)
	 * @param channels       the number of input channels
	 * @param outputChannels the number of output channels
	 * @param codingMode     Atrac3 1=JOINT_STEREO / 0=STEREO
	 * @return   0 success
	 *         < 0 error code
	 */
	public int init(int bytesPerFrame, int channels, int outputChannels, int codingMode);

	/**
	 * Decode a frame.
	 * 
	 * @param inputAddr   the address of the input buffer
	 * @param inputLength the maximum length of the input buffer
	 * @param outputAddr  the address where to store the decode samples
	 * @return            0  no frame decoded (end of input stream)
	 *                  < 0  error code
	 *                  > 0  number of bytes consumed from the input buffer
	 */
	public int decode(Memory mem, int inputAddr, int inputLength, int outputAddr);

	/**
	 * @return  the number of samples generated by one decode() call.
	 */
	public int getNumberOfSamples();
}