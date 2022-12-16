import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        Cheker[][] chekers = Cheker.startChekers();

        PlayingField field = new PlayingField();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayingField().setVisible(true);
            }
        });
    }
}