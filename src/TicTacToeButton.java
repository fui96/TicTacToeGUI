import javax.swing.*;


/**
 * class for custom buttons for TicTacToe
 */
public class TicTacToeButton extends JButton {
    private boolean isTaken = false;
    private int row,col;

    //Images
    ImageIcon X = new ImageIcon("Asset 1@.75x.png");
    ImageIcon O = new ImageIcon("Asset 2@.75x.png");
    ImageIcon Blank = new ImageIcon("Asset 3@.75x.png");

    /**
     * Constructor for custom TicTacToe Button
     * @param row the row coordinate for the button
     * @param col the column coordinate for the button
     */
    public TicTacToeButton(int row, int col) {
        super();
        this.row = row;
        this.col = col;
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
        isTaken = true;
    }

}
