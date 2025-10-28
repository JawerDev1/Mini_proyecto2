import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("DRAGON QUEST");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(25, 25, 112)); // azul oscuro

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(25, 25, 112));
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        // Título del juego
        JLabel titulo = new JLabel("DRAGON QUEST");
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        titulo.setForeground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titulo, gbc);

        // Botón: Iniciar Batalla
        JButton btnIniciar = new JButton("Iniciar Batalla");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 18));
        btnIniciar.setBackground(new Color(72, 61, 139));
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setPreferredSize(new Dimension(220, 45));

        gbc.gridy = 1;
        panel.add(btnIniciar, gbc);

        // Botón: Créditos
        JButton btnCreditos = new JButton("Créditos");
        btnCreditos.setFont(new Font("Arial", Font.BOLD, 18));
        btnCreditos.setBackground(new Color(72, 61, 139));
        btnCreditos.setForeground(Color.WHITE);
        btnCreditos.setFocusPainted(false);
        btnCreditos.setPreferredSize(new Dimension(220, 45));

        gbc.gridy = 2;
        panel.add(btnCreditos, gbc);

        // Botón: Salir
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 18));
        btnSalir.setBackground(new Color(72, 61, 139));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setPreferredSize(new Dimension(220, 45));

        gbc.gridy = 3;
        panel.add(btnSalir, gbc);

        // Acciones de los botones
        btnIniciar.addActionListener((ActionEvent e) -> {
            AudioPlayer.getInstance().playLoop("music/musica_batalla.wav", false);

            new InterfazJuego().setVisible(true); // abre la ventana de batalla
            dispose(); // cierra el menú principal
        });

        btnCreditos.addActionListener((ActionEvent e) -> {
            mostrarCreditos();
        });

        btnSalir.addActionListener((ActionEvent e) -> {
            int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    private void mostrarCreditos() {
        String mensaje = """
                Créditos del Proyecto
                --------------------------
                Integrantes:
                - Jhon Jawer Cuero Gómez
                - Kevin Andres Rosero Romo
                
                Universidad del Valle
                Proyecto: Dragon Quest
                """;

        JOptionPane.showMessageDialog(this, mensaje, "Créditos", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal().setVisible(true));
    }
}