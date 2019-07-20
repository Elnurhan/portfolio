import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Encryptor implements Runnable {
    Thread thread;
    private String text_for_enc;
    private byte cipher_choice;

    Encryptor(String text, byte cipher_choice) {
        thread = new Thread(this);
        this.text_for_enc = text;
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
            caesarCipherEnc(text_for_enc, key);
        } else if (cipher_choice == 2) {
            System.out.print("Введите ключ: ");
            String key = null;
            try {
                key = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            vigenereCipherEnc(text_for_enc, key);
        } else if (cipher_choice == 3) {
            morseCipherEnc(text_for_enc);
        } else if (cipher_choice == 4) {
            atbashCipherEnc(text_for_enc);
        } else if (cipher_choice == 5) {
            a1z26CipherEnc(text_for_enc);
        }
    }

    private static void caesarCipherEnc(String text, final byte key) {
        if (text == null)
            System.out.println("");
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') { // Проверяем, входит ли в диапазон букв данный смивол
                if ((int) (text.charAt(i) + key) > 90) { // Если символ выходит за диапазон букв
                    res.append((char) (text.charAt(i) - 25 + (key - 1)));
                } else {
                    res.append((char) (text.charAt(i) + key));
                }
            } else {
                System.out.println("Данный символ невозможно зашифровать!");
                break;
            }
        }
        System.out.println(res.toString());
    }

    private void vigenereCipherEnc(final String text, final String key) {
        String encrypt = "";
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            encrypt += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * 97) % 26) + 97);
        }
        System.out.println(encrypt);
    }

    private void morseCipherEnc(String text) {
        Map<Character, String> morse = new HashMap<>();
        morse.put('a', ".-");
        morse.put('b', "-...");
        morse.put('c', "-.-.");
        morse.put('d', "-..");
        morse.put('e', ".");
        morse.put('f', "..-.");
        morse.put('g', "--.");
        morse.put('h', "....");
        morse.put('i', "..");
        morse.put('j', ".---");
        morse.put('k', "-.-");
        morse.put('l', ".-..");
        morse.put('m', "--");
        morse.put('n', "-.");
        morse.put('o', "---");
        morse.put('p', ".--.");
        morse.put('q', "--.-");
        morse.put('r', ".-.");
        morse.put('s', "...");
        morse.put('t', "-");
        morse.put('u', "..-");
        morse.put('v', "...-");
        morse.put('w', ".--");
        morse.put('x', "-.--");
        morse.put('y', "-..-");
        morse.put('z', "--..");
        StringBuilder res = new StringBuilder();
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            if (morse.containsKey(text.charAt(i))) {
                res.append(morse.get(text.charAt(i)) + " ");
            }
        }
        System.out.println(res);
    }

    private void atbashCipherEnc(String text) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                res += (char) ((25 - (text.charAt(i) - 65)+ 1) + 64);
            }
        }
        System.out.println(res);
    }

    private void a1z26CipherEnc(String text) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            res.append((text.charAt(i) - 64) + "-");
        }
        System.out.println(res.deleteCharAt(res.length() - 1));
    }
}