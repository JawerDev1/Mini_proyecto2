import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class CombateGUI extends JFrame {

    private Heroe[] heroes;
    private Enemigo[] enemigos;
    private JTextArea areaBatalla;
    private JButton btnAtacar;
    private JButton btnCurar;

    private int turnoHeroe = 0; 
    private Random random = new Random();

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
        panelBotones.add(btnAtacar);
        panelBotones.add(btnCurar);
        add(panelBotones, BorderLayout.SOUTH);

        mostrarMensaje("Comienza la batalla.\n");
        mostrarMensaje("Turno de " + heroes[turnoHeroe].getNombre() + ".\n");

        btnAtacar.addActionListener((ActionEvent e) -> realizarAtaqueHeroe());
        btnCurar.addActionListener((ActionEvent e) -> realizarCuracion());
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
        if (danio < 0) danio = 0;

        enemigo.recibirDanio(danio);
        mostrarMensaje(heroe.getNombre() + " ataca a " + enemigo.getNombre() + " causando " + danio + " de daño.\n");

        if (!enemigo.estaVivo()) {
            mostrarMensaje(enemigo.getNombre() + " ha sido derrotado.\n");
        }

        if (todosMuertos(enemigos)) {
            mostrarMensaje("Los héroes han ganado la batalla.\n");
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

        turnoEnemigo();
        pasarTurno();
    }

    private void turnoEnemigo() {
        Enemigo enemigo = enemigos[random.nextInt(enemigos.length)];
        while (!enemigo.estaVivo()) {
            enemigo = enemigos[random.nextInt(enemigos.length)];
        }

        Heroe heroe = heroes[random.nextInt(heroes.length)];
        while (!heroe.estaVivo()) {
            heroe = heroes[random.nextInt(heroes.length)];
        }

        int danio = enemigo.getAtaque() - heroe.getDefensa();
        if (danio < 0) danio = 0;

        heroe.recibirDanio(danio);
        mostrarMensaje(enemigo.getNombre() + " ataca a " + heroe.getNombre() + " causando " + danio + " de daño.\n");

        if (!heroe.estaVivo()) {
            mostrarMensaje(heroe.getNombre() + " ha sido derrotado.\n");
        }

        if (todosMuertos(heroes)) {
            mostrarMensaje("Los enemigos han ganado la batalla.\n");
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
            if (p.estaVivo()) return false;
        }
        return true;
    }

    private void desactivarBotones() {
        btnAtacar.setEnabled(false);
        btnCurar.setEnabled(false);
    }

    private void mostrarMensaje(String mensaje) {
        areaBatalla.append(mensaje);
        areaBatalla.setCaretPosition(areaBatalla.getDocument().getLength());
    }

    public static void main(String[] args) {
        Heroe[] heroes = {
            new Heroe(TipoHeroe.HEROE, "Andres", 120, 50, 20, 15, 12),
            new Heroe(TipoHeroe.HEROE, "Yangus", 150, 20, 25, 10, 8),
            new Heroe(TipoHeroe.HEROE, "Jessica", 100, 80, 15, 10, 14),
            new Heroe(TipoHeroe.HEROE, "Angelo", 110, 60, 18, 12, 13)
        };

        Enemigo[] enemigos = {
            new Enemigo(TipoEnemigo.SLIME, "Slime Azul", 80, 10, 12, 8, 10),
            new Enemigo(TipoEnemigo.GOBLIN, "Goblin", 100, 15, 14, 9, 11),
            new Enemigo(TipoEnemigo.BRUJO, "Brujo", 90, 40, 10, 7, 13),
            new Enemigo(TipoEnemigo.DRAGON, "Dragon", 200, 30, 25, 18, 6)
        };

        SwingUtilities.invokeLater(() -> new CombateGUI(heroes, enemigos).setVisible(true));
    }
}
