import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JFrame{

//    Cheker[][] chekers;

    PlayingField field;

    static boolean MOUSE = false;
    public static final int WINDOW_WIDTH = PlayingField.CELL_SIZE * 8;
    public static final int WINDOW_HEIGHT = WINDOW_WIDTH + 30;

    public Game(PlayingField field) {
        this.field = field;
    }

    public Game() {
        this.setBackground(Color.white);
        this.setVisible(true);
        this.setBounds(550,550,100,50);
        field = new PlayingField();
        this.add(field);
        this.setResizable(false);
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                MOUSE = true;
                System.out.println(1);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                MOUSE = false;
                System.out.println(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }


}
