import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Clase singleton para controlar la música del juego.
 * Permite reproducir, detener y reiniciar pistas en bucle.
 */
public class AudioPlayer {

    private static AudioPlayer instance;
    private Clip clip;

    private AudioPlayer() {}

    /** Obtiene la instancia única del reproductor. */
    public static synchronized AudioPlayer getInstance() {
        if (instance == null) instance = new AudioPlayer();
        return instance;
    }

    /**
     * Reproduce una pista de audio en bucle continuo.
     * 
     * @param ruta Ruta del archivo. Puede ser:
     *             - "music/musica_batalla.wav" → desde la raíz del proyecto.
     *             - "/audio/musica_batalla.wav" → desde el classpath (usar loadFromClasspath = true).
     * @param loadFromClasspath true si el audio está dentro de /resources del proyecto.
     */
    public synchronized void playLoop(String ruta, boolean loadFromClasspath) {
        stop(); // Detiene cualquier clip anterior para evitar solapamientos.

        try {
            AudioInputStream audioIn;

            // --- Cargar desde archivo físico ---
            if (!loadFromClasspath) {
                File file = new File(ruta);
                if (!file.exists()) {
                    System.err.println("Archivo de audio no encontrado: " + file.getAbsolutePath());
                    return;
                }
                audioIn = AudioSystem.getAudioInputStream(file);
            }
            // --- Cargar desde recursos dentro del classpath ---
            else {
                URL resource = getClass().getResource(ruta);
                if (resource == null) {
                    System.err.println("Audio no encontrado en classpath: " + ruta);
                    return;
                }
                audioIn = AudioSystem.getAudioInputStream(resource);
            }

            // --- Aseguramos compatibilidad de formato (PCM) ---
            AudioFormat baseFormat = audioIn.getFormat();
            AudioFormat decodedFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );

            AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat, audioIn);

            // --- Crear y reproducir clip ---
            clip = AudioSystem.getClip();
            clip.open(din);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Bucle infinito
            clip.start();

            // --- Cerramos streams (Clip ya mantiene los datos cargados) ---
            din.close();
            audioIn.close();

            System.out.println("Reproduciendo música: " + ruta);

        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + ruta);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de audio: " + ruta);
        } catch (LineUnavailableException e) {
            System.err.println("No se pudo acceder al dispositivo de audio.");
        }
    }

    /** Detiene la música actual y libera los recursos. */
    public synchronized void stop() {
        try {
            if (clip != null) {
                if (clip.isRunning()) clip.stop();
                clip.close();
                clip = null;
                System.out.println("Música detenida.");
            }
        } catch (Exception e) {
            System.err.println("Error al detener la música: " + e.getMessage());
        }
    }

    /** Reinicia la pista desde el principio (si ya está cargada). */
    public synchronized void restart() {
        if (clip == null) return;
        clip.stop();
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        System.out.println("Música reiniciada desde el inicio.");
    }

    /** Indica si hay una pista sonando actualmente. */
    public synchronized boolean isPlaying() {
        return clip != null && clip.isRunning();
    }
}