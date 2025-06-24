package rsa;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.math.BigInteger;

public class Cifrar extends JFrame {

    private JButton btnRegresar;
    private JButton btnCifrar;
    private JLabel txtresp, txtresq, txtresn, txtresfi, txtrese, txtresd;
    private JTextField numacifrar;
    private JTextArea numcifrado;
    BigInteger n, p, q;
    BigInteger fi;
    BigInteger e, d;
    String mensaje;

    public Cifrar() {
        setTitle("Encriptar mensaje");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(30, 30, 30));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        mainPanel.add(crearEncabezado(), BorderLayout.NORTH);
        mainPanel.add(crearContenido(), BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel crearEncabezado() {
        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(new Color(30, 30, 30));

        btnRegresar = new JButton("Volver");
        btnRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRegresar.setBackground(new Color(255, 105, 180));
        btnRegresar.setForeground(Color.WHITE);
        btnRegresar.setFocusPainted(false);
        btnRegresar.addActionListener(e -> regresarVentana());

        top.add(btnRegresar, BorderLayout.WEST);

        JLabel title = new JLabel("Encriptar", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        top.add(title, BorderLayout.CENTER);

        return top;
    }

    private JPanel crearContenido() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));

        panel.add(crearCampo("p", txtresp = new JLabel()));
        panel.add(crearCampo("q", txtresq = new JLabel()));
        panel.add(crearCampo("n", txtresn = new JLabel()));
        panel.add(crearCampo("fi", txtresfi = new JLabel()));
        panel.add(crearCampo("e", txtrese = new JLabel()));
        panel.add(crearCampo("d", txtresd = new JLabel()));

        panel.add(Box.createVerticalStrut(15));

        JLabel lblEntrada = new JLabel("Número a encriptar:");
        lblEntrada.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblEntrada.setForeground(Color.WHITE);
        lblEntrada.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblEntrada);

        numacifrar = new JTextField();
        numacifrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        numacifrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(numacifrar);

        panel.add(Box.createVerticalStrut(15));

        btnCifrar = new JButton("Encriptar");
        btnCifrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCifrar.setBackground(new Color(189, 252, 201));
        btnCifrar.setForeground(Color.BLACK);
        btnCifrar.setFocusPainted(false);
        btnCifrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCifrar.addActionListener(e -> cifrarNumero());
        panel.add(btnCifrar);

        panel.add(Box.createVerticalStrut(20));

        JLabel lblResultado = new JLabel("Mensaje encriptado:");
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblResultado.setForeground(Color.WHITE);
        lblResultado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblResultado);

        numcifrado = new JTextArea(3, 30);
        numcifrado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        numcifrado.setLineWrap(true);
        numcifrado.setWrapStyleWord(true);
        numcifrado.setEditable(false);
        numcifrado.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(numcifrado);
        panel.add(scroll);

        return panel;
    }

    private JPanel crearCampo(String nombre, JLabel valorLabel) {
        JPanel campo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        campo.setBackground(new Color(30, 30, 30));

        JLabel etiqueta = new JLabel(nombre + ": ");
        etiqueta.setFont(new Font("Segoe UI", Font.BOLD, 14));
        etiqueta.setForeground(Color.WHITE);
        campo.add(etiqueta);

        valorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        valorLabel.setForeground(Color.LIGHT_GRAY);
        campo.add(valorLabel);

        return campo;
    }

    private void cifrarNumero() {
        mensaje = numacifrar.getText().trim();

        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Por favor, ingresa un número a cifrar.");
            return;
        }

        try {
            int numero = Integer.parseInt(mensaje);
            if (numero < 0 || numero > 999) {
                JOptionPane.showMessageDialog(this, "⚠ El número debe estar entre 0 y 999.");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "🚫 Solo se permiten números enteros.");
            return;
        }

        FuncionRSA rsa = new FuncionRSA();
        rsa.generarPrimos();
        rsa.generarClaves();

        p = rsa.getP();
        q = rsa.getQ();
        n = rsa.getN();
        fi = rsa.getFi();
        e = rsa.getE();
        d = rsa.getD();

        txtresp.setText(p.toString());
        txtresq.setText(q.toString());
        txtresn.setText(n.toString());
        txtresfi.setText(fi.toString());
        txtrese.setText(e.toString());
        txtresd.setText(d.toString());

        BigInteger[] resultado = rsa.cifrar(mensaje);
        StringBuilder cifrado = new StringBuilder();
        for (BigInteger b : resultado) {
            cifrado.append(b.toString()).append(" ");
        }
        numcifrado.setText(cifrado.toString().trim());
    }

    private void regresarVentana() {
        new App();
        this.setVisible(false);
    }
}
