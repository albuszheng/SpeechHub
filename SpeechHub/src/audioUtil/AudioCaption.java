package audioUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.*;


/**
 * Created by albus on 11/28/2016.
 */
public class AudioCaption implements Runnable {
    Thread thread;

    public void run() {
        TargetDataLine line;
        double duration = 0;
        AudioInputStream audioInputStream = null;

        // define the required attributes for our line,
        // and make sure a compatible line is supported.

        AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;
        float rate = 44100.0f;
        int channels = 2;
        int frameSize = 4;
        int sampleSize = 16;
        boolean bigEndian = true;

        AudioFormat format = new AudioFormat(encoding, rate, sampleSize, channels, (sampleSize / 8)
                * channels, rate, bigEndian);

        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Line matching " + info + " not supported.");
            System.exit(1);
            return;
        }

        // get and open the target data line for capture.

        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format, line.getBufferSize());
        } catch (LineUnavailableException ex) {
            System.out.println("Unable to open the line: " + ex);
            System.exit(1);
            return;
        } catch (SecurityException ex) {
            System.out.println(ex.toString());
            System.exit(1);
            //JavaSound.showInfoDialog();
            return;
        } catch (Exception ex) {
            System.out.println(ex.toString());
            System.exit(1);
            return;
        }

        // play back the captured audio data
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int frameSizeInBytes = format.getFrameSize();
        int bufferLengthInFrames = line.getBufferSize() / 8;
        int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
        byte[] data = new byte[bufferLengthInBytes];
        int numBytesRead;

        line.start();

        while (thread != null) {
            if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
        }

        // we reached the end of the stream.
        // stop and close the line.
        line.stop();
        line.close();
        line = null;

        // stop and close the output stream
        try {
            out.flush();
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // load bytes into the audio input stream for playback

        byte audioBytes[] = out.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);

        long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format
                .getFrameRate());
        duration = milliseconds / 1000.0;

        try {
            audioInputStream.reset();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        try {
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File("RecordAudio.wav"));
        } catch (Exception e){
            e.printStackTrace();
            return;
        }
    }

    public void start() {
//        errStr = null;
        thread = new Thread(this);
        thread.setName("Capture");
        thread.start();
    }

    public void stop() {
        thread = null;
    }


    public static void main(String[] args){
        AudioCaption ac = new AudioCaption();
        ac.start();
        ac.run();
    }
}
