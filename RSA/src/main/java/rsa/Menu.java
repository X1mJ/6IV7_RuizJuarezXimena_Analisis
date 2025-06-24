package rsa;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

    private JButton btnCifrar;
    private JButton btnDescifrar;

    public Menu() {
        setTitle("🔐 Sistema RSA - Menú Principal");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con fondo y márgenes
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 248, 255)); // Azul claro
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Título centrado
        JLabel titulo = new JLabel("Bienvenido al sistema RSA", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(new Color(25, 25, 112)); // Azul oscuro
        panel.add(titulo, BorderLayout.NORTH);

        // Panel para los botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 30));
        botonesPanel.setBackground(new Color(240, 248, 255)); // Igual que el fondo principal

        btnCifrar = new JButton("Cifrar");
        btnCifrar.setPreferredSize(new Dimension(120, 50));
        btnCifrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnCifrar.setBackground(new Color(60, 179, 113)); // Verde
        btnCifrar.setForeground(Color.WHITE);
        botonesPanel.add(btnCifrar);

        btnDescifrar = new JButton("Descifrar");
        btnDescifrar.setPreferredSize(new Dimension(120, 50));
        btnDescifrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnDescifrar.setBackground(new Color(220, 20, 60)); // Rojo
        btnDescifrar.setForeground(Color.WHITE);
        botonesPanel.add(btnDescifrar);

        panel.add(botonesPanel, BorderLayout.CENTER);

        add(panel);
        configurarEventos();
        setVisible(true);
    }

    private void configurarEventos() {
        btnCifrar.addActionListener(e -> {
            this.setVisible(false);
            new Cifrar();
        });

        btnDescifrar.addActionListener(e -> {
            this.setVisible(false);
            new Descifrar();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
