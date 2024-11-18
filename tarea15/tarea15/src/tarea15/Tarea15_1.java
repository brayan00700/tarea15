package tarea15;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Tarea15_1 extends JFrame {
    private JButton[][] botones = new JButton[3][3];
    private char[][] tablero = new char[3][3];
    private char turno = 'X';

    public Tarea15_1() {
        setTitle("3 en Raya");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        iniciarJuego();
    }

    private void iniciarJuego() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = ' ';
                botones[i][j] = new JButton("");
                botones[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                int fila = i, col = j;
                botones[i][j].addActionListener(e -> jugar(fila, col));
                add(botones[i][j]);
            }
        }
    }

    private void jugar(int fila, int col) {
        if (tablero[fila][col] != ' ') return;
        tablero[fila][col] = turno;
        botones[fila][col].setText(String.valueOf(turno));
        if (comprobarGanador(turno)) {
            mostrarResultado(turno + " gano");
        } else if (esEmpate()) {
            mostrarResultado("Empate");
        } else {
            turno = (turno == 'X') ? 'O' : 'X';
            if (turno == 'O') jugarMaquina();
        }
    }

    private void jugarMaquina() {
        Random random = new Random();
        int fila, col;
        do {
            fila = random.nextInt(3);
            col = random.nextInt(3);
        } while (tablero[fila][col] != ' ');
        jugar(fila, col);
    }

    private boolean comprobarGanador(char turno) {
        for (int i = 0; i < 3; i++)
            if ((tablero[i][0] == turno && tablero[i][1] == turno && tablero[i][2] == turno) ||
                (tablero[0][i] == turno && tablero[1][i] == turno && tablero[2][i] == turno))
                return true;
        return (tablero[0][0] == turno && tablero[1][1] == turno && tablero[2][2] == turno) ||
               (tablero[0][2] == turno && tablero[1][1] == turno && tablero[2][0] == turno);
    }

    private boolean esEmpate() {
        for (char[] fila : tablero)
            for (char celda : fila)
                if (celda == ' ') return false;
        return true;
    }

    private void mostrarResultado(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
        dispose();
        SwingUtilities.invokeLater(() -> new Tarea15_1().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tarea15_1().setVisible(true));
    }
}