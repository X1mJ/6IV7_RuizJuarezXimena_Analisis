package rsa;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.math.BigInteger;

public class Descifrar extends JFrame {

    private JButton btnRegresar;
    private JButton btnDescifrar;
    private JTextField contd, contn;
    private JTextField contcif;
    private JTextArea contdes;
    String cadenacifrada;
    BigInteger d, n;

    public Descifrar() {
        setTitle("Desencriptar");
        setSize(620, 540);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Desencriptar mensaje", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);

        panel.add(Box.createVerticalStrut(25));
        panel.add(crearCampoTexto("Ingrese d:", contd = new JTextField()));
        panel.add(crearCampoTexto("Ingrese n:", contn = new JTextField()));
        panel.add(crearCampoTexto("Texto cifrado:", contcif = new JTextField()));

        panel.add(Box.createVerticalStrut(10));
        btnDescifrar = new JButton("Descifrar");
        btnDescifrar.setBackground(new Color(255, 105, 180));
        btnDescifrar.setForeground(Color.WHITE);
        btnDescifrar.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnDescifrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDescifrar.setFocusPainted(false);
        panel.add(btnDescifrar);

        panel.add(Box.createVerticalStrut(20));
        JLabel resultado = new JLabel("Texto descifrado:");
        resultado.setForeground(Color.WHITE);
        resultado.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panel.add(resultado);

        contdes = new JTextArea(4, 20);
        contdes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        contdes.setWrapStyleWord(true);
        contdes.setLineWrap(true);
        contdes.setEditable(false);
        contdes.setBackground(new Color(50, 50, 50));
        contdes.setForeground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(contdes);
        panel.add(scroll);

        panel.add(Box.createVerticalStrut(15));
        btnRegresar = new JButton("Volver");
        btnRegresar.setBackground(new Color(189, 252, 201));
        btnRegresar.setForeground(Color.BLACK);
        btnRegresar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnRegresar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegresar.setFocusPainted(false);
        panel.add(btnRegresar);

        add(panel);

        btnRegresar.addActionListener(e -> regresarVentana());
        btnDescifrar.addActionListener(e -> descifrarNumero());

        setVisible(true);
    }

    private JPanel crearCampoTexto(String texto, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        panel.add(label, BorderLayout.NORTH);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        field.setBackground(new Color(50, 50, 50));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        panel.add(field, BorderLayout.CENTER);

        return panel;
    }

    public void descifrarNumero() {
        try {
            cadenacifrada = contcif.getText().trim();
            String dTexto = contd.getText().trim();
            String nTexto = contn.getText().trim();

            if (cadenacifrada.isEmpty() || dTexto.isEmpty() || nTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ Todos los campos deben estar llenos para descifrar correctamente.");
                return;
            }

            String[] dividir = cadenacifrada.split(" ");
            BigInteger[] cifrado = new BigInteger[dividir.length];
            for (int i = 0; i < dividir.length; i++) {
                cifrado[i] = new BigInteger(dividir[i]);
            }

            d = new BigInteger(dTexto);
            n = new BigInteger(nTexto);

            FuncionRSA rsa = new FuncionRSA();
            String descifrado = rsa.descifrar(cifrado, d, n);
            contdes.setText(descifrado);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠ Asegúrate de ingresar solo números válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error al descifrar. Verifica los datos.");
        }
    }

    private void regresarVentana() {
        new App();
        this.setVisible(false);
    }
}
