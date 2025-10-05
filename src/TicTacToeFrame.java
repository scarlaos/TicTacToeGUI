import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame implements ActionListener {
/** @author scarlaos

   Assignment 1 making a gui for tic tac toe game
 */


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
    int tieWins = 0;
    int score = 0;
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
        add(TTTBoard,BorderLayout.CENTER);
        add(buttonPanel,BorderLayout.SOUTH);

        setSize(400,400);
        setVisible(true);
    }

    //for actionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if(win) return; //will not accept clicks after winning game

        JButton clickedButton = (JButton)e.getSource();

        if(!clickedButton.getText().equals("")){ //makes sure there's no invalid moves

            JOptionPane.showMessageDialog(this,"This square has already been taken!",
                    "Invalid Move!",JOptionPane.WARNING_MESSAGE);
            return;
        }


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
        if(checkWin()){
            win = true;
            if(player.equals("X")){
                XplayerWins ++;
            }else {
                OplayerWins ++;
            }
            //game is won
            int playAgain = JOptionPane.showConfirmDialog(this,"Player" + player + " won!\nWould you like to play again?","The game is over.",
                    JOptionPane.YES_NO_OPTION);

            if(playAgain == JOptionPane.YES_OPTION){
                reset();
            }else{
                System.exit(0);
            }

            return;
        }



        //game results in a full board tie
        if(count == 9 &&!win){
            win = true;
            tieWins ++;
            int playAgain = JOptionPane.showConfirmDialog(this,"The game resulted in a tie!\nWould you like to play again?",
                "Game is Tied!", JOptionPane.YES_NO_OPTION);
            if(playAgain == JOptionPane.YES_OPTION){
                reset();
            }
            else{
                System.exit(0);
            }

            return;
        }
        //game results in 7 play tie
        if(count >= 7 && !win){
            JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};

            boolean possibleWin = false;

            if(
            // horizontals
                    (!buttonOne.getText().equals("")&& buttonOne.getText().equals(buttonTwo.getText())&& buttonTwo.getText().equals(buttonThree.getText())) ||
                    (!buttonFour.getText().equals("")&& buttonFour.getText().equals(buttonFive.getText())&& buttonFive.getText().equals(buttonSix.getText())) ||
                    (!buttonSeven.getText().equals("")&& buttonSeven.getText().equals(buttonEight.getText()) && buttonEight.getText().equals(buttonNine.getText()))||
            // verticles
                    (!buttonOne.getText().equals("") && buttonOne.getText().equals(buttonFour.getText()) && buttonFour.getText().equals(buttonSeven.getText())) ||
                    (!buttonTwo.getText().equals("") && buttonTwo.getText().equals(buttonFive.getText())&& buttonFive.getText().equals(buttonEight.getText())) ||
                    (!buttonThree.getText().equals("")&& buttonThree.getText().equals(buttonSix.getText()) && buttonSix.getText().equals(buttonNine.getText())) ||
            // diagonals
                    (!buttonOne.getText().equals("") && buttonOne.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonNine.getText())) ||
                    (!buttonThree.getText().equals("")&& buttonThree.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonSeven.getText()))
            ){
                possibleWin = true; //someone can still win
            }

            if(!possibleWin){
                win = true;
                tieWins++;
                int playAgain = JOptionPane.showConfirmDialog(this,"The game resulted in a Seven play tie!\nWould you like to play again?",
                        "Game is Tied!", JOptionPane.YES_NO_OPTION);

                if(playAgain == JOptionPane.YES_OPTION){
                    reset();
                }
                else{
                    System.exit(0);
                }
                return;
            }
        }
    }

    public boolean checkWin(){
        JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};

        // verticals
        if(!buttonOne.getText().equals("") && buttonOne.getText().equals(buttonFour.getText()) && buttonFour.getText().equals(buttonSeven.getText())) return true;
        if(!buttonTwo.getText().equals("") && buttonTwo.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonEight.getText())) return true;
        if(!buttonThree.getText().equals("") && buttonThree.getText().equals(buttonSix.getText()) && buttonSix.getText().equals(buttonNine.getText())) return true;

        // horizontals
        if(!buttonOne.getText().equals("") && buttonOne.getText().equals(buttonTwo.getText()) && buttonTwo.getText().equals(buttonThree.getText())) return true;
        if(!buttonFour.getText().equals("") && buttonFour.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonSix.getText())) return true;
        if(!buttonSeven.getText().equals("") && buttonSeven.getText().equals(buttonEight.getText()) && buttonEight.getText().equals(buttonNine.getText())) return true;

        // diagonals
        if(!buttonOne.getText().equals("") && buttonOne.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonNine.getText())) return true;
        if(!buttonThree.getText().equals("") && buttonThree.getText().equals(buttonFive.getText()) && buttonFive.getText().equals(buttonSeven.getText())) return true;

        return false;
    }

    public void createButtonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,3));

        // add this if you're awake olivia: buttonPanel.add(resetButton);
        resetButton.addActionListener((ActionEvent e)-> {
            int reply = JOptionPane.showConfirmDialog(null,"Are you sure you want to reset the board?","Confirmation: ",JOptionPane.YES_NO_OPTION);

            if(reply == JOptionPane.YES_OPTION){
                reset();
                count = 0;
            }
        });
        Font fontButtons = new Font("Arial", Font.BOLD,18);
        resetButton.setFont(fontButtons);
        scoreButton.setFont(fontButtons);
        quitButton.setFont(fontButtons);


        buttonPanel.add(resetButton);
        buttonPanel.add(quitButton);
        quitButton.addActionListener((ActionEvent e)-> {
            System.exit(0);
        });

        buttonPanel.add(scoreButton); //scoreboard
        scoreButton.addActionListener((ActionEvent e)-> {
            JOptionPane.showMessageDialog(this,"Player X Wins: " + XplayerWins + "\nPlayer O Wins: " + OplayerWins + "\nTies: " + tieWins,
                    "ScoreBoard", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public void reset(){
        //adding all buttons for easy reset
        JButton[] allButtons = {buttonOne,buttonTwo,buttonThree,buttonFour,buttonFive,buttonSix,buttonSeven,buttonEight,buttonNine};
        for(JButton button : allButtons){
            button.setText("");
            button.setEnabled(true);
        }win = false;
        count = 0;
    }

}
