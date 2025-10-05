import javax.swing.*;

public class TicTacToeRunner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tic-Tac-Toe");

        SwingUtilities.invokeLater(() ->{
            new TicTacToeFrame();
        });
    }
}
