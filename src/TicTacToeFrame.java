import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    private TicTacToeButton[][] board = new TicTacToeButton[3][3];
    private boolean isXTurn = true;
    private int turnCount = 0;
    private boolean isWin = false;
    private boolean isTie = false;

    //Panels
    JPanel MainPanel,TitlePanel,GamePanel,ConsolePanel;
    //TextAreas
    JTextArea GameTextArea;
    JScrollPane GameScrollPane;
    //Label
    JLabel MainTitle;
    //Static Buttons
    JButton QuitButton, ResetBoard;
    //main constructor

    /**
     * Creates and structures the window of the game
     */
    public TicTacToeFrame() {
        //Create Window
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Create and Structure Panels
        MainPanel = new JPanel();
        MainPanel.setLayout(new BorderLayout());
        add(MainPanel,BorderLayout.CENTER);
        CreateTitlePanel();
        MainPanel.add(MainTitle,BorderLayout.NORTH);
        CreateGamePanel();
        MainPanel.add(GamePanel,BorderLayout.CENTER);
        CreateConsolePanel();
        MainPanel.add(ConsolePanel,BorderLayout.SOUTH);


        setVisible(true);
    }

    //methods

    /**
     * Creates and structures the title panel
     */
    public void CreateTitlePanel(){
        TitlePanel = new JPanel();
        TitlePanel.setLayout(new BorderLayout());

        MainTitle = new JLabel("Tic-Tac-Toe",SwingConstants.CENTER);
        MainTitle.setFont(new Font("Serif", Font.BOLD, 22));

        TitlePanel.add(MainTitle,BorderLayout.CENTER);

    }

    /**
     * Creates and structures the gamepanel
     */
    public void CreateGamePanel(){
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridLayout(3,3,10,10));
        CreateBoard();

    }

    /**
     * Creates and structures the gameconsle panel
     */
    public void CreateConsolePanel(){
        ConsolePanel = new JPanel();
        GameTextArea = new JTextArea(10,20);
        GameScrollPane = new JScrollPane(GameTextArea);

        QuitButton = new JButton("Quit");
        ResetBoard = new JButton("Reset Board");

        QuitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});
        ResetBoard.addActionListener((ActionEvent ae) -> {ResetBoard();});

        ConsolePanel.add(GameScrollPane);
        ConsolePanel.add(QuitButton);
        ConsolePanel.add(ResetBoard);
    }

    /**
     * uses a for loop to create a 2d array of buttons with blank icons
     */
    public void CreateBoard(){
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeButton(row, col," ");
                board[row][col].addActionListener((ActionEvent ae) -> {
                    TicTacToeButton clickedButton = (TicTacToeButton) ae.getSource();
                    GameLogic(clickedButton);});
                GamePanel.add(board[row][col]);
            }
        }
    }

    /**
     * uses a for loop to reset all game logic and buttons
     */
    public void ResetBoard(){
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                board[row][col].setTakenBy(" ");
                board[row][col].setIconBlank();
            }
        }
        isXTurn = true;
        turnCount = 0;
        isWin = false;
        isTie = false;
    }

    /**
     * the main driver for the game, checks turn and checks for wins or ties
     * @param Space
     */
    public void GameLogic(TicTacToeButton Space){
        if(!isWin && !isTie) {
            if (!Space.isTaken()) {
                if (isXTurn) {
                    Space.setIconX();
                    Space.setTaken(true);
                    Space.setTakenBy("X");
                } else if (!isXTurn) {
                    Space.setIconO();
                    Space.setTaken(true);
                    Space.setTakenBy("O");
                }
                turnCount++;
                if (turnCount > 3) {
                    CheckForWin();
                }
                if (turnCount > 6) {
                    CheckForTie();
                }
                isXTurn = !isXTurn;


            } else {
                JOptionPane.showMessageDialog(null, "This Space is Already Taken", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }else{
             int playAgain = JOptionPane.showConfirmDialog(null,"Do you want to play again?","Play again?",JOptionPane.YES_NO_OPTION);
            if(playAgain == JOptionPane.YES_OPTION){
                ResetBoard();
            }
            else if(playAgain == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }

    }

    /**
     * checks rows,cols,and diagonals for a win state
     */
    public void CheckForWin(){
        if(RowCheck() || ColCheck() || DiagCheck()){
            System.out.println("Game Won");
            isWin = true;
            if(isXTurn){
               GameTextArea.append("X Wins!\n");
            }
            else if(!isXTurn){
                GameTextArea.append("O Wins!\n");
            }
        }
    }

    /**
     *looks for empty cells and then checks if adjacent cells will allow for a win condition around that cell
     */
    public void CheckForTie(){
        boolean rowtie = false;
        boolean coltie = false;
        String Diag1 = board[0][0].getTakenBy() + board[1][1].getTakenBy() + board[2][2].getTakenBy();
        String Diag2 = board[0][2].getTakenBy() + board[1][1].getTakenBy() + board[2][0].getTakenBy();
        boolean Diagonal1Tie = false;
        Diagonal1Tie = Diag1.contains("X") && Diag1.contains("O");
        boolean Diagonal2Tie = false;
        Diagonal2Tie = Diag2.contains("X") && Diag2.contains("O");
        if(Diagonal1Tie && Diagonal2Tie){
            isTie = true;
        }
        else if(!Diagonal1Tie || !Diagonal2Tie){
            isTie = false;
            return;
        }
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(board[row][col].isTaken()){
                    String rowContains = board[row][0].getTakenBy() + board[row][1].getTakenBy() + board[row][2].getTakenBy();
                    String colContains = board[0][col].getTakenBy() + board[1][col].getTakenBy() + board[2][col].getTakenBy();
                    rowtie = (rowContains.contains("X") && rowContains.contains("O"));
                    coltie = (colContains.contains("X") && colContains.contains("O"));
                    if(rowtie && coltie){
                        isTie = true;
                    }
                    else if(!rowtie || !coltie){
                        isTie = false;
                        return;
                    }
                }
            }
        }
        if(isTie){
            GameTextArea.append("Game is a tie!\n");
        }
    }

    /**
     *checks if there is a win state in any of the three rows
     * @return a win state found in the rows
     */
    public boolean RowCheck(){
        for(int row = 0; row < 3; row++) {
            if(board[row][0].getTakenBy().equals(board[row][1].getTakenBy())
            && board[row][1].getTakenBy().equals(board[row][2].getTakenBy())
            && !board[row][0].getTakenBy().equals(" ")){
                return true;
            }
        }
        return false;
    }

    /**
     *checks if there is a win state in any of the three cols
     * @return a win state found in the cols
     */
    public boolean ColCheck(){
        for(int col = 0; col < 3; col++) {
            if(board[0][col].getTakenBy().equals(board[1][col].getTakenBy())
            && board[1][col].getTakenBy().equals(board[2][col].getTakenBy())
            && !board[0][col].getTakenBy().equals(" ")){
                return true;
            }
        }

        return false;
    }

    /**
     * checks if there is a win state in either of the diagonals
     * @return a win state in the diagonals
     */
    public boolean DiagCheck(){
        if(board[0][0].getTakenBy().equals(board[1][1].getTakenBy())
        && board[1][1].getTakenBy().equals(board[2][2].getTakenBy())){
            return true;
        }
        if(board[0][2].getTakenBy().equals(board[1][1].getTakenBy())
        && board[1][1].getTakenBy().equals(board[2][0].getTakenBy())){
                return true;
        }

        return false;
    }

}