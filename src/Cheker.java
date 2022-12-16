import javax.lang.model.type.NullType;
import java.awt.*;
import java.util.ArrayList;

public class Cheker {

    Color color;
    int row;
    int col;

    public Cheker(Color color, int col, int row) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public static Cheker[][] startChekers() {
        Cheker[][] chekers = new Cheker[8][8];
        for (int row = 0; row <= 7; row++) {
            for (int col = 0; col <= 7; col++) {
                if (row % 2 == 1 && col % 2 == 0 || row % 2 == 0 && col % 2 == 1) {
                    if (row < 3) {
                        chekers[col][row] = new Cheker(Color.BLACK, col, row);
                    } else if (row > 4) {
                        chekers[col][row] = new Cheker(Color.WHITE, col, row);
                    }
                } else {
                    chekers[row][col] = null;
                }
            }
        }
        return chekers;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Color getColor() {
        return color;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
