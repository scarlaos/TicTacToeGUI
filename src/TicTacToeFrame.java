import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {
    JFrame ticTacToe = new JFrame("Tic-Tac-Toe Game");


    //panels
    JPanel TTTBoard;
    JPanel buttonPanel;

    //buttons
    JButton buttonOne = new JButton("");
    JButton buttonTwo = new JButton("");
    JButton buttonThree = new JButton("");
    JButton buttonFour = new JButton("");
    JButton buttonFive = new JButton("");
    JButton buttonSix = new JButton("");
    JButton buttonSeven = new JButton("");
    JButton buttonEight = new JButton("");
    JButton buttonNine = new JButton("");

    //control buttons
    JButton quitButton = new JButton("Quit");
    JButton resetButton = new JButton("Reset");
    JButton scoreButton = new JButton("Score");


    int count = 0;
    int XplayerWins =0;
    int OplayerWins = 0;
    boolean win = false;
    String player;

    public TicTacToeFrame(){
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        TTTBoard = new JPanel();
        TTTBoard.setLayout(new GridLayout(3,3));
        //adds buttons to the board
        TTTBoard.add(buttonOne);
        TTTBoard.add(buttonTwo);
        TTTBoard.add(buttonThree);
        TTTBoard.add(buttonFour);
        TTTBoard.add(buttonFive);
        TTTBoard.add(buttonSix);
        TTTBoard.add(buttonSeven);
        TTTBoard.add(buttonEight);
        TTTBoard.add(buttonNine);

        //I am going to make these buttons into an array for the action listen to reduce the repetitiveness

        JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};
        for(JButton button : allButtons){
            button.addActionListener(this);
        }

        createButtonPanel(); // this will hold the reset quit and score buttons
        ticTacToe.add(TTTBoard,BorderLayout.CENTER);
        ticTacToe.add(buttonPanel,BorderLayout.SOUTH);

        setVisible(true);
    }

    //for actionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(win) return; //will not accept clicks after winning game

        JButton clickedButton = (JButton)e.getSource();

        if(!clickedButton.getText().equals("")) return; //enforces no clicked


        if(count % 2 == 0){ //i think this should determine the players turn... haven't tested it yet.
            player = "X";
        }else{
            player = "O";
        }
        clickedButton.setText(player); //this was easier than doing every button manually
        count++;

        /* JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};
        *for(JButton button : allButtons){
        *    button.setText("");
        *    button.setEnabled(true);
        *}
        */ //might need this here, I havent gotten to that
        checkWin();

    }

    public void checkWin(){
        JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};

        //verticals
        if(buttonOne.getText() == buttonFour.getText() && buttonFour.getText() == buttonSeven.getText()){
            win = true;

        }
        else if(buttonTwo.getText() == buttonFive.getText() && buttonFive.getText() == buttonEight.getText()){
            win = true;
        }
        else if(buttonThree.getText() == buttonSix.getText() && buttonSix.getText() == buttonNine.getText()){
            win = true;
        }

        //horizontals
        else if(buttonOne.getText() == buttonTwo.getText() && buttonTwo.getText() == buttonThree.getText()){
            win = true;
        }
        else if(buttonFour.getText() == buttonFive.getText() && buttonFive.getText() == buttonSix.getText()){
            win = true;
        }
        else if(buttonSeven.getText() == buttonEight.getText() && buttonEight.getText() == buttonNine.getText()){
            win = true;
        }

        //diagonals

        else if(buttonOne.getText() == buttonFive.getText() && buttonFive.getText() == buttonNine.getText()){
            win = true;
        }
        else if(buttonThree.getText() == buttonFive.getText() && buttonFive.getText() == buttonSeven.getText()){
            win = true;
        }
        else{
            win = false;
        }
    }

    public void createButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        buttonPanel.add(resetButton);
        resetButton.addActionListener((ActionEvent e)-> {
            int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to reset the board?","Confirmation: ",JOptionPane.YES_NO_OPTION);

            if(reply == JOptionPane.YES_OPTION){
                reset();
                count = 0;
            }
        });
    }

    public void reset(){
        //adding all buttons for easy reset
        JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};
        for(JButton button : allButtons){
            button.setText("");
            button.setEnabled(true);
        }win = false;
    }

}
