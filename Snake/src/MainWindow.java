import javax.swing.*;


// Это класс предназначенный для главного окна
public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("Snake"); // Title
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Для того, чтобы игра закрывалась при нажатии на крестик
        setSize(345, 370); // Размеры окна
        setLocation(400, 400); // Где появится наше окно
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();
    }
}
