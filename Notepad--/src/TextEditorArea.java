import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextEditorArea extends JPanel {

    public JMenuBar menu;
    public JTabbedPane tabs = new JTabbedPane(); // Нужно для переключения между открытыми файлами
    private String NAME = "Untitled";
    private JFileChooser choose = new JFileChooser();

    public TextEditorArea() {
        // Создание меню
        menu = new JMenuBar();
        JMenu file = new JMenu("Файл");
        JMenuItem file_new = new JMenuItem("Создать файл");
        JMenuItem file_open = new JMenuItem("Открыть файл");
        JMenuItem file_save = new JMenuItem("Сохранить");
        file.add(file_new);
        file.add(file_open);
        file.add(file_save);
        menu.add(file);

        // ***Отслеживаем действия***.
        // При нажатии на "Новый файл" создаётся поле для ввода текста и имя файла сверху
        file_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Добавление скролла
                JTextArea text = new JTextArea();
                toText scroll = new toText(text, NAME);
                tabs.addTab(NAME, scroll);
            }
        });

        // Сохранение
        file_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabs.countComponents() != 0) {
                    toText text = (toText) tabs.getSelectedComponent(); // Преобразуем к JTextArea

                    String output = text.getText();

                    choose.showSaveDialog(null); // Вызов диалогового окна, для выбора папки, в которую будет сохранен файл

                    File file = choose.getSelectedFile(); // Получаем файл, который будем сохранять
                    // Само сохранение
                    try {
                        FileWriter fileWriter = new FileWriter(file); // Открываем наш файл
                        fileWriter.write(output); // Записываеи в него текст
                        fileWriter.close();
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
            }
        });

        // Открываем текстовый файл
        file_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    choose.showOpenDialog(null);
                    File file = choose.getSelectedFile();
                    String input = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))); // Записываем всё содержимое в переменную

                    JTextArea text = new JTextArea(input);

                    toText tt = new toText(text, file.getName());

                    tabs.addTab(file.getName(), tt);
                } catch (IOException eq) {
                    eq.printStackTrace();
                }
            }
        });
    }
}
