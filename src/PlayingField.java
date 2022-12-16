import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayingField extends JPanel implements Runnable {

    Cheker[][] chekers;
    public static final int FIELD_LENGTH = 8;
    static boolean MOUSE = false;
    public static final int WINDOW_WIDTH = PlayingField.CELL_SIZE * 8;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH + 30;
    public static final int CELL_SIZE = 80;

    Cheker moveCheker;

    public PlayingField(Cheker[][] chekers) {
        this.chekers = chekers;
    }

    public PlayingField() {
        this.chekers = Cheker.startChekers();
        JFrame game = new JFrame("Window");

        game.add(this);

        game.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
        new Thread(this).start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(200, 150, 140));
        g.fillRect(0, 0, CELL_SIZE * FIELD_LENGTH, CELL_SIZE * FIELD_LENGTH);

        g.setColor(new Color(100, 100, 100));
        for (int row = 0; row < CELL_SIZE * FIELD_LENGTH; row += CELL_SIZE) {
            g.drawLine(0, row, CELL_SIZE * FIELD_LENGTH, row);
        }
        for (int col = 0; col < CELL_SIZE * FIELD_LENGTH; col += CELL_SIZE) {
            g.drawLine(col, 0, col, CELL_SIZE * FIELD_LENGTH);
        }

        g.setColor(new Color(200, 100, 100));
        for (int row = 0; row < FIELD_LENGTH; row++) {
            for (int col = 0; col < FIELD_LENGTH; col++) {
                if (row % 2 == 1 && col % 2 == 0 || row % 2 == 0 && col % 2 == 1) {
                    g.fillRect(row * CELL_SIZE, col * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        for (Cheker[] cheker : chekers) {
            for (Cheker ch : cheker) {
                if (ch != null) {
                    g.setColor(ch.getColor());
                    g.fillOval(ch.getCol() * CELL_SIZE + 10, ch.getRow() * CELL_SIZE + 10,
                            CELL_SIZE - 20, CELL_SIZE - 20);
                }
            }
        }
        if (MOUSE && moveCheker != null) {
            g.setColor(moveCheker.getColor());
            g.fillOval(MouseInfo.getPointerInfo().getLocation().x - 30,
                    MouseInfo.getPointerInfo().getLocation().y - 60,
                    CELL_SIZE - 20, CELL_SIZE - 20);
        }
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent arg0) {
                MOUSE = true;
                System.out.println(1);
                int x = MouseInfo.getPointerInfo().getLocation().x - 30;
                int y = MouseInfo.getPointerInfo().getLocation().y - 60;
                moveCheker = chekers[x / CELL_SIZE][y / CELL_SIZE];
                System.out.print(x / CELL_SIZE);
                System.out.println(y / CELL_SIZE);
                chekers[x / CELL_SIZE][y / CELL_SIZE] = null;
            }

            @Override
            public void mouseReleased(MouseEvent arg0) {
                MOUSE = false;
                int x = MouseInfo.getPointerInfo().getLocation().x;
                int y = MouseInfo.getPointerInfo().getLocation().y;
                if (moveCheker != null && isRightMove(x / CELL_SIZE, y / CELL_SIZE)) {
                    moveCheker.setRow(y / CELL_SIZE);
                    moveCheker.setCol(x / CELL_SIZE);
                    chekers[x / CELL_SIZE][y / CELL_SIZE] = moveCheker;
                    System.out.print(x / CELL_SIZE);
                    System.out.println(y / CELL_SIZE);
                }
//                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

        while (t == Thread.currentThread()) {
            if (MOUSE)
                repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRightMove(int x, int y) {
        int col = moveCheker.getCol();
        int row = moveCheker.getRow();
        if (chekers[col + 1][row + 1] == null && x == col + 1 && y == row + 1 ||
                chekers[col + 1][row - 1] == null && x == col + 1 && y == row - 1 ||
                chekers[col - 1][row + 1] == null && x == col - 1 && y == row + 1 ||
                chekers[col - 1][row - 1] == null && x == col - 1 && y == row - 1) {
            return true;
        } else {
            chekers[col][row] = moveCheker;
            return false;
        }
    }

}
