import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;

public class VentanaCombate extends JFrame {

    private JTextArea zonaMensajes;
    private JButton btnAtacar, btnHuir;
    private JPanel panelBotonesAtaque, panelSeleccionEnemigo;

    private java.util.List<Personaje> heroes;
    private java.util.List<Personaje> enemigos;
    private Personaje heroeActual;

    private int indiceHeroeActual = 0; // controla el turno de los héroes

    public VentanaCombate() {
        setTitle("DragonQuest - Combate");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- Panel superior ----------
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblTitulo = new JLabel("DRAGON QUEST - COMBATE");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        panelTitulo.add(lblTitulo);
        add(panelTitulo, BorderLayout.NORTH);

        // ---------- Zona central ----------
        zonaMensajes = new JTextArea();
        zonaMensajes.setEditable(false);
        zonaMensajes.setBackground(Color.LIGHT_GRAY);
        JScrollPane scroll = new JScrollPane(zonaMensajes);
        add(scroll, BorderLayout.CENTER);

        // ---------- Panel inferior ----------
        JPanel panelInferior = new JPanel();
        btnAtacar = new JButton("Atacar");
        btnHuir = new JButton("Huir");
        panelInferior.add(btnAtacar);
        panelInferior.add(btnHuir);
        add(panelInferior, BorderLayout.SOUTH);

        // ---------- Panel lateral ----------
        panelBotonesAtaque = new JPanel(new GridLayout(0, 1));
        panelBotonesAtaque.setVisible(false);
        panelSeleccionEnemigo = new JPanel(new GridLayout(0, 1));
        panelSeleccionEnemigo.setVisible(false);
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(panelBotonesAtaque, BorderLayout.NORTH);
        panelDerecho.add(panelSeleccionEnemigo, BorderLayout.CENTER);
        add(panelDerecho, BorderLayout.EAST);

        // Inicializamos personajes
        inicializarPersonajes();
        heroeActual = heroes.get(0);
        mostrarEstado();

        // Acción Atacar
        btnAtacar.addActionListener(e -> mostrarBotonesAtaque());
        // Acción Huir
        btnHuir.addActionListener(e -> {
            zonaMensajes.append("\nHas huido del combate...\n");
            btnAtacar.setEnabled(false);
            btnHuir.setEnabled(false);
        });
    }

    private void inicializarPersonajes() {
        heroes = new ArrayList<>();
        enemigos = new ArrayList<>();

        heroes.add(new Heroe());
        heroes.add(new Yangus());
        heroes.add(new Jessica());
        heroes.add(new Angelo());

        enemigos.add(new Slime());
        enemigos.add(new Darcky());
        enemigos.add(new PatyPunk());
        enemigos.add(new SpikedHare());
    }

    private void mostrarEstado() {
        zonaMensajes.append("\n--- Estado Actual ---\n");
        for (Personaje h : heroes) {
            zonaMensajes.append(h.getNombre() + " HP: " + h.getHp() + " MP: " + h.getMp() + "\n");
        }
        for (Personaje e : enemigos) {
            zonaMensajes.append(e.getNombre() + " HP: " + e.getHp() + "\n");
        }
        zonaMensajes.append("------------------------\n");
        zonaMensajes.append("Turno de: " + heroeActual.getNombre() + "\n");
    }

    private void mostrarBotonesAtaque() {
        panelBotonesAtaque.removeAll();
        panelSeleccionEnemigo.setVisible(false);
        panelBotonesAtaque.setVisible(true);

        JButton ataque1 = new JButton("Ataque Físico");
        JButton ataque2 = new JButton("Habilidad Especial");

        ataque1.addActionListener(e -> mostrarEnemigosParaAtacar("fisico"));
        ataque2.addActionListener(e -> ejecutarHabilidadEspecial());

        panelBotonesAtaque.add(ataque1);
        panelBotonesAtaque.add(ataque2);
        panelBotonesAtaque.revalidate();
        panelBotonesAtaque.repaint();
    }

    private void mostrarEnemigosParaAtacar(String tipoAtaque) {
        panelSeleccionEnemigo.removeAll();
        panelSeleccionEnemigo.setVisible(true);

        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                JButton btnEnemigo = new JButton(enemigo.getNombre());
                btnEnemigo.addActionListener(e -> ejecutarAtaque(heroeActual, enemigo, tipoAtaque));
                panelSeleccionEnemigo.add(btnEnemigo);
            }
        }
        panelSeleccionEnemigo.revalidate();
        panelSeleccionEnemigo.repaint();
    }

    // Ejecuta la habilidad especial del héroe actual
    private void ejecutarHabilidadEspecial() {
        if (heroeActual instanceof Heroe) {
            ((Heroe) heroeActual).habilidadCura();
            zonaMensajes.append(heroeActual.getNombre() + " usa Curar y recupera vida.\n");
        } else if (heroeActual instanceof Yangus) {
            mostrarEnemigosParaAtacar("golpePoderoso");
            return;
        } else if (heroeActual instanceof Jessica) {
            mostrarEnemigosParaAtacar("frizz");
            return;
        } else if (heroeActual instanceof Angelo) {
            ((Angelo) heroeActual).habilidadCura();
            zonaMensajes.append(heroeActual.getNombre() + " usa Sanar en el grupo.\n");
        }

        verificarMuertos();
        siguienteTurnoHeroe();
    }

    private void ejecutarAtaque(Personaje atacante, Personaje objetivo, String tipo) {
        switch (tipo) {
            case "fisico":
                atacante.atacar(objetivo);
                zonaMensajes.append(atacante.getNombre() + " ataca a " + objetivo.getNombre() + "\n");
                break;
            case "golpePoderoso":
                ((Yangus) atacante).golpePoderoso(objetivo);
                zonaMensajes.append(atacante.getNombre() + " usa Golpe Poderoso contra " + objetivo.getNombre() + "\n");
                break;
            case "frizz":
                ((Jessica) atacante).frizz(objetivo);
                zonaMensajes.append(atacante.getNombre() + " lanza Frizz a " + objetivo.getNombre() + "\n");
                break;
        }

        verificarMuertos();
        siguienteTurnoHeroe();
    }

    private void siguienteTurnoHeroe() {
        indiceHeroeActual++;
        if (indiceHeroeActual < heroes.size()) {
            heroeActual = heroes.get(indiceHeroeActual);
            if (!heroeActual.estaVivo()) {
                siguienteTurnoHeroe();
                return;
            }
            mostrarEstado();
        } else {
            indiceHeroeActual = 0;
            turnoEnemigos();
            heroeActual = heroes.get(indiceHeroeActual);
            mostrarEstado();
        }
    }

    private void turnoEnemigos() {
        zonaMensajes.append("\n--- Turno de los enemigos ---\n");
        for (Personaje enemigo : enemigos) {
            if (enemigo.estaVivo()) {
                Personaje objetivo = heroes.get(new Random().nextInt(heroes.size()));
                if (objetivo.estaVivo()) {
                    enemigo.atacar(objetivo);
                    zonaMensajes.append(enemigo.getNombre() + " ataca a " + objetivo.getNombre() + "\n");
                }
            }
        }
        verificarMuertos();
        zonaMensajes.append("--- Fin del turno enemigo ---\n");
    }

    private void verificarMuertos() {
        enemigos.removeIf(e -> !e.estaVivo());
        heroes.removeIf(h -> !h.estaVivo());

        if (enemigos.isEmpty()) {
            zonaMensajes.append("\nHas derrotado a todos los enemigos.\n");
            btnAtacar.setEnabled(false);
        } else if (heroes.isEmpty()) {
            zonaMensajes.append("\nTodos los héroes han caído. Derrota.\n");
            btnAtacar.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaCombate().setVisible(true));
    }
}
