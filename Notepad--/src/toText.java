import javax.swing.*;

// *** Преобразование JScrollPane к JTextArea ***

public class toText extends JScrollPane {
    private final String name;

    private final JTextArea text;

    public toText(JTextArea text, String name) {
        super(text);
        this.text = text;
        this.name = name;
    }

    public String getText() {
        return text.getText();
    }

}
