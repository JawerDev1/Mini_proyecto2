
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sonido {
    public static void reproducir(String rutaArchivo) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(rutaArchivo));
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}