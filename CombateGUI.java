import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CombateGUI extends JFrame {
    private Heroe[] heroes;
    private Enemigo[] enemigos;
    private JTextArea areaBatalla;
    private JButton btnAtacar;
    private JButton btnCurar;
    private JButton btnHechizoSueño;
    private int turnoHeroe = 0;
    private Random random = new Random();
    // Bandera para controlar si el minijefe ya apareció
    private boolean minijefeDerrotado = false;

    public CombateGUI(Heroe[] heroes, Enemigo[] enemigos) {
        this.heroes = heroes;
        this.enemigos = enemigos;

        setTitle("Combate - Dragon Quest");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        areaBatalla = new JTextArea();
        areaBatalla.setEditable(false);
        areaBatalla.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaBatalla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAtacar = new JButton("Atacar");
        btnCurar = new JButton("Curar");
        btnHechizoSueño = new JButton("Hechizo Sueño");
        panelBotones.add(btnAtacar);
        panelBotones.add(btnCurar);
        panelBotones.add(btnHechizoSueño);
        add(panelBotones, BorderLayout.SOUTH);

        mostrarMensaje("Comienza la batalla.\n");
        mostrarMensaje("Turno de " + heroes[turnoHeroe].getNombre() + ".\n");

        btnAtacar.addActionListener((ActionEvent e) -> realizarAtaqueHeroe());
        btnCurar.addActionListener((ActionEvent e) -> realizarCuracion());
        btnHechizoSueño.addActionListener((ActionEvent e) -> lanzarHechizoSueño());
    }

    private void realizarAtaqueHeroe() {
        Heroe heroe = heroes[turnoHeroe];
        if (!heroe.estaVivo()) {
            mostrarMensaje(heroe.getNombre() + " está derrotado y no puede atacar.\n");
            pasarTurno();
            return;
        }

        Enemigo enemigo = enemigos[random.nextInt(enemigos.length)];
        while (!enemigo.estaVivo()) {
            enemigo = enemigos[random.nextInt(enemigos.length)];
        }

        int danio = heroe.getAtaque() - enemigo.getDefensa();
        if (danio < 0)
            danio = 0;

        enemigo.recibirDanio(danio);
        mostrarMensaje(heroe.getNombre() + " ataca a " + enemigo.getNombre() + " causando " + danio + " de daño.\n");

        Sonido.reproducir("sonidos/ataque.wav");

        if (!enemigo.estaVivo()) {
            mostrarMensaje(enemigo.getNombre() + " ha sido derrotado.\n");

        }

        verificarMinijefe();
        mostrarEstadoActual();

        if (todosMuertos(enemigos)) {
            mostrarMensaje("Los héroes han ganado la batalla.\n");
            Sonido.reproducir("sonidos/victoria.wav");
            desactivarBotones();
            return;
        }

        turnoEnemigo();
        pasarTurno();
    }

    private void realizarCuracion() {
        Heroe heroe = heroes[turnoHeroe];
        if (!heroe.estaVivo()) {
            mostrarMensaje(heroe.getNombre() + " está derrotado y no puede curarse.\n");
            pasarTurno();
            return;
        }

        int curacion = 20;
        heroe.curar(curacion);
        mostrarMensaje(heroe.getNombre() + " se cura " + curacion + " puntos de vida.\n");
        Sonido.reproducir("sonidos/curar.wav");

        mostrarEstadoActual();


        turnoEnemigo();
        pasarTurno();
    }

    private void lanzarHechizoSueño() {
        Heroe heroe = heroes[turnoHeroe];
        if (!heroe.estaVivo()) {
            mostrarMensaje(heroe.getNombre() + " está derrotado y no puede lanzar hechizos.\n");
            pasarTurno();
            return;
        }

        Enemigo enemigo = enemigos[random.nextInt(enemigos.length)];
        while (!enemigo.estaVivo()) {
            enemigo = enemigos[random.nextInt(enemigos.length)];
        }

        // El enemigo cae dormido
        enemigo.dormir();
        mostrarMensaje(heroe.getNombre() + " lanza hechizo de sueño sobre " + enemigo.getNombre() + ".\n");

        Sonido.reproducir("sonidos/hechizo.wav");

        mostrarEstadoActual();


        turnoEnemigo();
        pasarTurno();
    }

    private void turnoEnemigo() {
        Enemigo enemigo = enemigos[random.nextInt(enemigos.length)];
        while (!enemigo.estaVivo()) {
            enemigo = enemigos[random.nextInt(enemigos.length)];
        }

        if (enemigo.getEstado() == Estado.SUEÑO) {
            enemigo.intentarDespertar();
            if (enemigo.getEstado() == Estado.SUEÑO) {
                return; // Sigue dormido, no actúa
            }
        }

        Heroe heroe = heroes[random.nextInt(heroes.length)];
        while (!heroe.estaVivo()) {
            heroe = heroes[random.nextInt(heroes.length)];
        }

        int danio = enemigo.getAtaque() - heroe.getDefensa();
        if (danio < 0)
            danio = 0;

        heroe.recibirDanio(danio);
        mostrarMensaje(enemigo.getNombre() + " ataca a " + heroe.getNombre() + " causando " + danio + " de daño.\n");

        if (!heroe.estaVivo()) {
            mostrarMensaje(heroe.getNombre() + " ha sido derrotado.\n");
        }

        if (todosMuertos(heroes)) {
            mostrarMensaje("Los enemigos han ganado la batalla.\n");
            Sonido.reproducir("sonidos/derrota.wav");
            desactivarBotones();
        }
    }

    private void pasarTurno() {
        turnoHeroe++;
        if (turnoHeroe >= heroes.length) {
            turnoHeroe = 0;
        }
        if (!todosMuertos(heroes) && !todosMuertos(enemigos)) {
            mostrarMensaje("Turno de " + heroes[turnoHeroe].getNombre() + ".\n");
        }
    }

    private boolean todosMuertos(Personaje[] grupo) {
        for (Personaje p : grupo) {
            if (p.estaVivo())
                return false;
        }
        return true;
    }

    private void desactivarBotones() {
        btnAtacar.setEnabled(false);
        btnCurar.setEnabled(false);
        btnHechizoSueño.setEnabled(false);
    }

    private void mostrarMensaje(String mensaje) {
        areaBatalla.append(mensaje);
        areaBatalla.setCaretPosition(areaBatalla.getDocument().getLength());
    }

    // 🔹 Método para verificar si el MiniJefe debe aparecer
    private void verificarMinijefe() {
        if (!minijefeDerrotado && enemigosDerrotados() >= 3) {
            MiniJefe mini = new MiniJefe("Dragón Minijefe", 150, 50, 20, 15, 8);
            enemigos = new Enemigo[] { mini }; // reemplaza a los enemigos normales
            mostrarMensaje("¡Un MiniJefe aparece: " + mini.getNombre() + "!\n");
            minijefeDerrotado = true;
        }
    }

    // 🔹 Contar enemigos derrotados
    private int enemigosDerrotados() {
        int count = 0;
        for (Enemigo e : enemigos)
            if (!e.estaVivo())
                count++;
        return count;
    }

    // Muestra el estado actual de todos los héroes y enemigos
    private void mostrarEstadoActual() {
        mostrarMensaje("\n--- Estado actual ---\n");
        for (Heroe h : heroes) {
            mostrarMensaje(h.getNombre() + " (HP: " + h.getHp() + ")\n");
        }
        for (Enemigo e : enemigos) {
            mostrarMensaje(e.getNombre() + " (HP: " + e.getHp() + ")\n");
        }   
        mostrarMensaje("---------------------\n\n");
    }


    public static void main(String[] args) {
        Heroe[] heroes = {
                new Heroe(TipoHeroe.HEROE, "Andres", 120, 50, 20, 15, 12),
                new Heroe(TipoHeroe.HEROE, "Yangus", 150, 20, 25, 10, 8),
                new Heroe(TipoHeroe.HEROE, "Jessica", 100, 80, 15, 10, 14),
                new Heroe(TipoHeroe.HEROE, "Angelo", 110, 60, 18, 12, 13)
        };

        Enemigo[] enemigos = {
                new Enemigo(TipoEnemigo.SLIME, "Slime Azul", 50, 10, 12, 8, 10),
                new Enemigo(TipoEnemigo.GOBLIN, "Goblin", 80, 15, 14, 9, 11),
                new Enemigo(TipoEnemigo.BRUJO, "Brujo", 100, 40, 10, 7, 13),
                new Enemigo(TipoEnemigo.DRAGON, "Mini Dragon", 120, 50, 30, 20, 10)
        };

        SwingUtilities.invokeLater(() -> new CombateGUI(heroes, enemigos).setVisible(true));
    }
}
