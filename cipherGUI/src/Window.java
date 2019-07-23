import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Window extends JFrame {

    private JButton button = new JButton("Encrypt");
    private JButton button2 = new JButton("Decrypt");
    private JTextField key = new JTextField("", 5);
    private JTextField input = new JTextField("", 5);
    private JTextField res = new JTextField("", 5);
    private JLabel label4 = new JLabel("");
    private JLabel label = new JLabel("Input text");
    private JLabel label2 = new JLabel("Result");
    private JLabel label3 = new JLabel("Key");
    private static boolean isCaesarCipher = true;
    private static boolean isAtbashCipher;
    private static boolean isMorse;
    private static boolean isVigenereCipher;
    private static boolean isA1z26Cipher;

    public Window() {
        super("CiphersEncDec");
        this.setBounds(100,100,600,200);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] ciphers = {
                "Caesar's Cipher",
                "Atbash Cipher",
                "Morse",
                "Vigenère Cipher",
                "A1Z26 Cipher"
        };
        JComboBox comboBox = new JComboBox(ciphers);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(5,2,2,2));
        res.setEditable(false);
        container.add(label);
        container.add(input);
        container.add(label3);
        container.add(key);
        container.add(label2);
        container.add(res);
        container.add(comboBox);
        button.addActionListener(new EncryptButtonListener());
        button2.addActionListener(new DecryptButtonListener());
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox.getSelectedItem().equals("Caesar's Cipher")) {
                    isCaesarCipher = true;
                    isAtbashCipher = false;
                    isMorse = false;
                    isVigenereCipher = false;
                    isA1z26Cipher = false;
                    key.setEditable(true);
                }
                if (comboBox.getSelectedItem().equals("Atbash Cipher")) {
                    isCaesarCipher = false;
                    isAtbashCipher = true;
                    isMorse = false;
                    isVigenereCipher = false;
                    isA1z26Cipher = false;
                    key.setEditable(false);
                }
                if (comboBox.getSelectedItem().equals("Morse")) {
                    isCaesarCipher = false;
                    isAtbashCipher = false;
                    isMorse = true;
                    isVigenereCipher = false;
                    isA1z26Cipher = false;
                    key.setEditable(false);
                }
                if (comboBox.getSelectedItem().equals("Vigenère Cipher")) {
                    isCaesarCipher = false;
                    isAtbashCipher = false;
                    isMorse = false;
                    isVigenereCipher = true;
                    isA1z26Cipher = false;
                    key.setEditable(true);
                }
                if (comboBox.getSelectedItem().equals("A1Z26 Cipher")) {
                    isCaesarCipher = false;
                    isAtbashCipher = false;
                    isMorse = false;
                    isVigenereCipher = false;
                    isA1z26Cipher = true;
                    key.setEditable(false);
                }
            }
        });

        container.add(button);
        container.add(label4);
        container.add(button2);
        setVisible(true);
    }

        class EncryptButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCaesarCipher == true) {
                    res.setText(CiphersEnc.caesarCipherEnc(input.getText(), Byte.parseByte(key.getText())));
                }
                if (isAtbashCipher == true) {
                    res.setText(CiphersEnc.atbashCipherEnc(input.getText()));
                }
                if (isMorse == true) {
                    res.setText(CiphersEnc.morseEnc(input.getText()));
                }
                if (isVigenereCipher == true) {
                    res.setText(CiphersEnc.vigenereCipherEnc(input.getText(), key.getText()));
                }
                if (isA1z26Cipher == true) {
                    res.setText(CiphersEnc.a1z26CipherEnc(input.getText()));
                }
            }
        }

        class DecryptButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isCaesarCipher == true) {
                    res.setText(CiphersDec.caesarCipherDec(input.getText(), Byte.parseByte(key.getText())));
                }
                if (isAtbashCipher == true) {
                    res.setText(CiphersDec.atbashCipherDec(input.getText()));
                }
                if (isMorse == true) {
                    res.setText(CiphersDec.morseDec(input.getText()));
                }
                if (isVigenereCipher == true) {
                    res.setText(CiphersDec.vigenereCipherDec(input.getText(), key.getText()));
                }
                if (isA1z26Cipher == true) {
                    res.setText(CiphersDec.a1z26CipherDec(input.getText()));
                }
            }
        }
    }