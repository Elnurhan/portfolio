import javax.swing.*;
import java.awt.*;

public class MainWindow {

    public MainWindow() {
        JFrame window = new JFrame();
        window.setTitle("Text Editor");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(600, 600);
        window.setLocation(400, 400);
        TextEditorArea tea = new TextEditorArea();
        window.setJMenuBar(tea.menu);
        window.add(tea.tabs);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow mw = new MainWindow();


    }
}
	