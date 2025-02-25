import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TicTacToeFrame extends JFrame {
    private TicTacToeButton[][] board = new TicTacToeButton[3][3];
    private boolean isXTurn = true;
    private int turnCount = 0;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    private boolean isWin = false;


    //Panels
    JPanel MainPanel,TitlePanel,GamePanel,ConsolePanel;
    //TextAreas
    JTextArea GameTextArea;
    JScrollPane GameScrollPane;
    //Label
    JLabel MainTitle,TurnDisplay;
    //Static Buttons
    JButton QuitButton, ResetBoard;
    //main constructor
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
    public void CreateTitlePanel(){
        TitlePanel = new JPanel();
        TitlePanel.setLayout(new BorderLayout());

        MainTitle = new JLabel("Tic-Tac-Toe",SwingConstants.CENTER);
        MainTitle.setFont(new Font("Serif", Font.BOLD, 22));

        TitlePanel.add(MainTitle,BorderLayout.CENTER);

    }
    public void CreateGamePanel(){
        GamePanel = new JPanel();
        GamePanel.setLayout(new GridLayout(3,3,10,10));
        CreateBoard();

    }
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
    }
    public void GameLogic(TicTacToeButton Space){
        if(turnCount > 3){
            CheckForWin();
        }
        if(turnCount >= 7){
            CheckForTie();
        }
        if(!isWin)
            if(!Space.isTaken()){
                if(isXTurn){
                    Space.setIconX();
                    Space.setTakenBy("X");
                    turnCount++;
                    isXTurn = false;
                }
                else if(!isXTurn){
                    Space.setIconO();
                    Space.setTakenBy("O");
                    turnCount++;
                    isXTurn = true;
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"This Space is Already Taken","Error",JOptionPane.ERROR_MESSAGE);
            }
        else{
            JOptionPane.showMessageDialog(null,"The game is over","Error",JOptionPane.ERROR_MESSAGE);
        }

    }
    public void CheckForWin(){
        if(RowCheck() || ColCheck() || DiagCheck()){
            System.out.println("Game Won");
            isWin = true;
            if(isXTurn){
               GameTextArea.append("O Wins!");
            }
            else if(!isXTurn){
                GameTextArea.append("X Wins!");
            }
        }
        else{
            GameTextArea.append("No Win Yet");
        }

    }
    public void CheckForTie(){

    }

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