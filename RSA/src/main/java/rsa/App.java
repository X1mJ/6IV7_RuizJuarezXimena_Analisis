package rsa;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingUtilities;

public class App extends JFrame {

    private JButton btnCifrar;
    private JButton btnDescifrar;

    public App() {
        setTitle("Cifrado RSA");
        setSize(520, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel titulo = new JLabel("Cifrado RSA");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setForeground(new Color(255, 255, 255));
        panel.add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        botonesPanel.setBackground(new Color(30, 30, 30));

        btnCifrar = new JButton("Encriptar mensaje");
        btnCifrar.setBackground(new Color(255, 105, 180));
        btnCifrar.setPreferredSize(new Dimension(200, 60));
        btnCifrar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnCifrar.setForeground(Color.WHITE);
        btnCifrar.setFocusPainted(false);
        botonesPanel.add(btnCifrar);

        btnDescifrar = new JButton("Desencriptar mensaje");
        btnDescifrar.setBackground(new Color(189, 252, 201));
        btnDescifrar.setPreferredSize(new Dimension(250, 60));
        btnDescifrar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnDescifrar.setForeground(Color.BLACK);
        btnDescifrar.setFocusPainted(false);
        botonesPanel.add(btnDescifrar);

        panel.add(botonesPanel, BorderLayout.CENTER);
        add(panel);

        configurarEventos();
        setVisible(true);
    }

    private void configurarEventos() {
        btnCifrar.addActionListener(e -> abrirVentanaCifrar());
        btnDescifrar.addActionListener(e -> abrirVentanaDescifrar());
    }

    private void abrirVentanaCifrar() {
        this.setVisible(false);
        new Cifrar();
    }

    private void abrirVentanaDescifrar() {
        this.setVisible(false);
        new Descifrar();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
