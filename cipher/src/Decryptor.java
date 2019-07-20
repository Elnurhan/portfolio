import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Decryptor implements Runnable {
    Thread thread;
    private String text_for_dec;
    private byte cipher_choice;


    Decryptor(String text, byte cipher_choice) {
        thread = new Thread(this);
        this.text_for_dec = text;
        this.cipher_choice = cipher_choice;
        thread.start();
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (cipher_choice == 1) {
            System.out.print("Введите ключ: ");
            byte key = 0;
            try {
                key = Byte.parseByte(reader.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
            caesarCipherDec(text_for_dec, key);
        }

        else if (cipher_choice == 2) {
            System.out.print("Введите ключ: ");
            String key = null;
            try {
                key = reader.readLine();
            } catch(Exception e) {
                e.printStackTrace();
            }
            vigenereCipherDec(text_for_dec, key);
        }

        else if (cipher_choice == 3) {
            morseCipherDec(text_for_dec);
        }

        else if (cipher_choice == 4) {
            atbashCipherDec(text_for_dec);
        }

        else if (cipher_choice == 5) {
            a1z26CipherDec(text_for_dec);
        }
    }

    private static void caesarCipherDec(String text, final byte key) {
        if (text == null)
            System.out.println("");
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if(text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') { // Проверяем, входит ли в диапазон букв данный смивол
                if((int) (text.charAt(i) + key) > 90) { // Если символ выходит за диапазон букв
                    res.append((char) (text.charAt(i) - 25 - (key-1)));
                } else {
                    res.append((char) (text.charAt(i) - key));
                }
            } else {
                System.out.println("Данный символ невозможно зашифровать!");
                break;
            }
        }
        System.out.println(res.toString());
    }

    private void vigenereCipherDec(final String text, final String key) {
        String decrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            decrypt += (char) (((text.charAt(i) - key.charAt(i % keyLen) + 26) % 26) + 97);
        }
        System.out.println(decrypt);
    }

    private void morseCipherDec(String text) {
        Map<String, Character> morse = new HashMap<>();
        morse.put(".-", 'a');
        morse.put("-...", 'b');
        morse.put("-.-.", 'c');
        morse.put("-..", 'd');
        morse.put(".", 'e');
        morse.put("..-.", 'f');
        morse.put("--.", 'g');
        morse.put("....", 'h');
        morse.put("..", 'i');
        morse.put(".---", 'j');
        morse.put("-.-", 'k');
        morse.put(".-..", 'l');
        morse.put("--", 'm');
        morse.put("-.", 'n');
        morse.put("---", 'o');
        morse.put(".--.", 'p');
        morse.put("--.-", 'q');
        morse.put(".-.", 'r');
        morse.put("...", 's');
        morse.put("-", 't');
        morse.put("..-", 'u');
        morse.put("...-", 'v');
        morse.put(".--", 'w');
        morse.put("-.--", 'x');
        morse.put("-..-", 'y');
        morse.put("--..", 'z');
        StringBuilder res = new StringBuilder();
        text = text.toLowerCase();
        String[] morse_text = text.split(" ");
        for (int i = 0; i < morse_text.length; i++) {
            if(morse.containsKey(morse_text[i])) {
                res.append(morse.get(morse_text[i]) + " ");
            }
        }
        System.out.println(res);
    }

    private void atbashCipherDec(String text) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                res += (char) ((25 - (text.charAt(i) - 65)+ 1) + 64);
            }
        }
        System.out.println(res);
    }

    private void a1z26CipherDec(String text) {
        StringBuilder res = new StringBuilder();
        String[] index = text.split("-");
        int temp;
        for (int i = 0; i < index.length; i++) {
            temp = Integer.parseInt(index[i]) + 64;
            res.append((char)temp);
        }
        System.out.println(res);
    }
}
