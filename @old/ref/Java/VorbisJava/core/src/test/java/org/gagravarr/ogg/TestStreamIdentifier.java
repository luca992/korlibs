/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gagravarr.ogg;


/**
 * Tests that we can correctly identify the types of valid streams
 */
public class TestStreamIdentifier extends AbstractIdentificationTest {
    public void testIdentifyInvalidFiles() throws Exception {
        // Can't work on FLAC native, no packets
        OggFile flac = new OggFile(getTestFlacNativeFile());
        OggPacket p = flac.getPacketReader().getNextPacket();
        assertNull(p);
        
        // Can't work on dummy data, no packets
        OggFile dummy = new OggFile(getDummy());
        p = dummy.getPacketReader().getNextPacket();
        assertNull(p);
        
        
        // So fake a packet!
        byte[] dummyBytes = new byte[8];
        IOUtils.readFully(getDummy(), dummyBytes);
        p = new OggPacket(dummyBytes);
        
        // Won't work if not the first in the stream
        assertEquals(false, p.isBeginningOfStream());
        try {
            OggStreamIdentifier.identifyType(p);
            fail("Shouldn't be able to identify not first packet");
        } catch (IllegalArgumentException e) {}
        
        // Make it the start of stream, will get unknown
        p.setIsBOS();
        assertEquals(OggStreamIdentifier.UNKNOWN, OggStreamIdentifier.identifyType(p));
    }

    public void testIdentifySingleStreamFiles() throws Exception {
        assertTypeOfFirstStream(OggStreamIdentifier.UNKNOWN,
                                new OggFile(getTestOggFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.OGG_VORBIS,
                                new OggFile(getTestVorbisFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.SPEEX_AUDIO,
                                new OggFile(getTestSpeexFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.OPUS_AUDIO,
                                new OggFile(getTestOpusFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.OGG_FLAC,
                                new OggFile(getTestFlacOggFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.DAALA_VIDEO,
                                new OggFile(getTestDaalaFile()));
        assertTypeOfFirstStream(OggStreamIdentifier.THEORA_VIDEO,
                                new OggFile(getTestTheoraFile()));
    }

    public void testIdentifyMultiStreamFiles() throws Exception {
        // File with 2 streams
        OggFile ts = new OggFile(getTestTheoraSkeletonFile());
        
        OggPacket p = ts.getPacketReader().getNextPacket();
        assertEquals(OggStreamIdentifier.SKELETON, OggStreamIdentifier.identifyType(p));
        p = ts.getPacketReader().getNextPacket();
        assertEquals(OggStreamIdentifier.THEORA_VIDEO, OggStreamIdentifier.identifyType(p));
        
        // File with 3 streams
        ts = new OggFile(getTestTheoraSkeletonCMMLFile());
        
        p = ts.getPacketReader().getNextPacket();
        assertEquals(OggStreamIdentifier.SKELETON, OggStreamIdentifier.identifyType(p));
        p = ts.getPacketReader().getNextPacket();
        assertEquals(OggStreamIdentifier.CMML, OggStreamIdentifier.identifyType(p));
        p = ts.getPacketReader().getNextPacket();
        assertEquals(OggStreamIdentifier.THEORA_VIDEO, OggStreamIdentifier.identifyType(p));
    }

    /**
     * We don't currently support detecting mid-stream
     */
    public void testIdentifyMidStream() throws Exception {
        OggFile vorbis = new OggFile(getTestVorbisFile());
        
        OggPacket p = vorbis.getPacketReader().getNextPacket();
        assertNotNull(p);
        assertEquals(true, p.isBeginningOfStream());
        assertEquals(OggStreamIdentifier.OGG_VORBIS, 
                     OggStreamIdentifier.identifyType(p));
        
        p = vorbis.getPacketReader().getNextPacket();
        assertNotNull(p);
        assertEquals(false, p.isBeginningOfStream());
        try {
            OggStreamIdentifier.identifyType(p);
            fail("Can't detect mid stream");
        } catch (IllegalArgumentException e) {}
    }
}
