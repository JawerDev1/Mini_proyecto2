import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DragonQuestGUI extends JFrame {
    private JTextArea areaBatalla;
    private JButton btnAtacar, btnDefender, btnHabilidad;
    private JLabel lblHeroe, lblEnemigo;

    public DragonQuestGUI() {
        setTitle("Dragon Quest VIII - Batalla");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel superior con info de personajes
        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        lblHeroe = new JLabel("Héroe: HP 120 | MP 50");
        lblEnemigo = new JLabel("Enemigo: HP 100 | MP 0");
        panelInfo.add(lblHeroe);
        panelInfo.add(lblEnemigo);
        add(panelInfo, BorderLayout.NORTH);

        // Área central de texto (mensajes de batalla)
        areaBatalla = new JTextArea();
        areaBatalla.setEditable(false);
        areaBatalla.setText("¡Comienza la batalla!\n");
        JScrollPane scroll = new JScrollPane(areaBatalla);
        add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel();
        btnAtacar = new JButton("Atacar");
        btnDefender = new JButton("Defender");
        btnHabilidad = new JButton("Usar Habilidad");
        panelBotones.add(btnAtacar);
        panelBotones.add(btnDefender);
        panelBotones.add(btnHabilidad);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos de botones (acciones básicas)
        btnAtacar.addActionListener(e -> areaBatalla.append("El héroe ataca al enemigo.\n"));
        btnDefender.addActionListener(e -> areaBatalla.append("El héroe se defiende.\n"));
        btnHabilidad.addActionListener(e -> areaBatalla.append("El héroe usa una habilidad.\n"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DragonQuestGUI().setVisible(true);
        });
    }
}
