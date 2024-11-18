package tarea15;
import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class Tarea15_2 extends JFrame {
    private final String palabra = "HOMBRE";
    private final HashSet<Character> adivinadas = new HashSet<>();
    private int intentos = 0;

    private JPanel panelDibujo = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            dibujarAhorcado(g);
        }
    };
    private JLabel lblPalabra = new JLabel(getPalabraConGuiones(), SwingConstants.CENTER);
    private JTextField txtLetra = new JTextField(1);

    public Tarea15_2() {
        setTitle("Ahorcado"); setSize(300, 400); setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        panelDibujo.setPreferredSize(new Dimension(300, 200)); add(panelDibujo, BorderLayout.NORTH);
        lblPalabra.setFont(new Font("Arial", Font.BOLD, 24)); add(lblPalabra, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel();
        panelInferior.add(new JLabel("Letra:")); panelInferior.add(txtLetra);
        JButton btnIntentar = new JButton("Intentar");
        btnIntentar.addActionListener(e -> jugar(txtLetra.getText().toUpperCase().charAt(0)));
        panelInferior.add(btnIntentar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void dibujarAhorcado(Graphics g) {
        g.drawLine(50, 180, 150, 180); g.drawLine(100, 50, 100, 180); g.drawLine(100, 50, 200, 50); g.drawLine(200, 50, 200, 80);
        if (intentos > 0) g.drawOval(175, 80, 50, 50); 
        if (intentos > 1) g.drawLine(200, 130, 200, 160); 
        if (intentos > 2) g.drawLine(200, 140, 170, 120); 
        if (intentos > 3) g.drawLine(200, 140, 230, 120); 
        if (intentos > 4) g.drawLine(200, 160, 180, 200); 
        if (intentos > 5) g.drawLine(200, 160, 220, 200); 
    }

    private String getPalabraConGuiones() {
        StringBuilder sb = new StringBuilder();
        for (char c : palabra.toCharArray()) sb.append(adivinadas.contains(c) ? c : "_").append(" ");
        return sb.toString();
    }

    private void jugar(char letra) {
        if (adivinadas.contains(letra) || !Character.isLetter(letra)) return;
        adivinadas.add(letra);
        if (!palabra.contains(String.valueOf(letra))) intentos++;
        lblPalabra.setText(getPalabraConGuiones());
        panelDibujo.repaint();
        if (intentos >= 6 || !lblPalabra.getText().contains("_")) finalizarJuego();
    }

    private void finalizarJuego() {
        JOptionPane.showMessageDialog(this, intentos >= 6 ? "Perdiste. Era: " + palabra : "¡Ganaste!");
        dispose(); new Tarea15_2().setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tarea15_2().setVisible(true));
    }
}
