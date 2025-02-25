import javax.swing.*;


/**
 * class for custom buttons for TicTacToe
 */
public class TicTacToeButton extends JButton {
    private boolean isTaken = false;
    private int row,col;
    private String takenBy = "";

    //Images
    private ImageIcon X = new ImageIcon("src/Assets/Asset 1@0.75x.png");
    private ImageIcon O = new ImageIcon("src/Assets/Asset 2@0.75x.png");
    private ImageIcon Blank = new ImageIcon("src/Assets/Asset 3@0.75x.png");

    /**
     * Constructor for custom TicTacToe Button
     * @param row the row coordinate for the button
     * @param col the column coordinate for the button
     */
    public TicTacToeButton(int row, int col, String takenBy) {
        super();
        this.row = row;
        this.col = col;
        this.takenBy = takenBy;
        setIconBlank();
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setIconO(){
        setIcon(O);
        isTaken = true;
    }

    public void setIconX(){
        setIcon(X);
        isTaken = true;
    }
    public void setIconBlank(){
        setIcon(Blank);
        isTaken = false;
    }
    public void setTakenBy(String takenBy) {
        this.takenBy = takenBy;
    }
    public String getTakenBy() {
        return takenBy;
    }
}
