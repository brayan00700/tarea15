package tarea15;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tarea15_3 extends JFrame {
    private final String[] opciones = {"Piedra", "Papel", "Tijera"};
    private JLabel lblResultado = new JLabel("Elige una opción:", SwingConstants.CENTER);

    public Tarea15_3() {
        setTitle("Piedra, Papel o Tijera");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        lblResultado.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblResultado, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        for (String opcion : opciones) {
            JButton boton = new JButton(opcion);
            boton.addActionListener(e -> jugar(opcion));
            panelBotones.add(boton);
        }
        add(panelBotones, BorderLayout.CENTER);
    }
    private void jugar(String eleccionJugador) {
        String eleccionMaquina = opciones[new Random().nextInt(3)];
        String resultado;

        if (eleccionJugador.equals(eleccionMaquina)) {
            resultado = "Empate: Ambos eligieron " + eleccionJugador + ".";
        } else if ((eleccionJugador.equals("Piedra") && eleccionMaquina.equals("Tijera")) ||
                   (eleccionJugador.equals("Papel") && eleccionMaquina.equals("Piedra")) ||
                   (eleccionJugador.equals("Tijera") && eleccionMaquina.equals("Papel"))) {
            resultado = "¡Ganaste! Máquina eligió " + eleccionMaquina + ".";
        } else {
            resultado = "Perdiste. Máquina eligió " + eleccionMaquina + ".";
        }

        lblResultado.setText(resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tarea15_3().setVisible(true));
    }
}
