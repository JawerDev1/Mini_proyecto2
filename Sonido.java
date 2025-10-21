import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sonido {
    public static void reproducir(String rutaArchivo) {
        try {
            File archivo = new File(rutaArchivo);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo);
            AudioFormat baseFormat = audioStream.getFormat();

            // Convertir automáticamente a formato compatible con Java (16-bit PCM)
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream decodedAudioStream = AudioSystem.getAudioInputStream(decodeFormat, audioStream);

            Clip clip = AudioSystem.getClip();
            clip.open(decodedAudioStream);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
