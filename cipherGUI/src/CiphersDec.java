import java.util.HashMap;
import java.util.Map;

public class CiphersDec {
    static String caesarCipherDec(String text, final byte key) {
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
        return String.valueOf(res);
    }

    static String atbashCipherDec(String text) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                res += (char) ((25 - (text.charAt(i) - 65)+ 1) + 64);
            }
        }
        return res;
    }

    static String morseDec(String text) {
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
        return String.valueOf(res);
    }

    static String vigenereCipherDec(String text, final String key) {
        String res = "";
        text = text.toLowerCase();
        final int keyLen = key.length();
        for (int i = 0, len = text.length(); i < len; i++) {
            if (text.charAt(i) >= 'a' && text.charAt(i) <= 'z') {
                res += (char) (((text.charAt(i) - key.charAt(i % keyLen) + 26) % 26) + 97);
            } else {
                return "Ключ не подходит";
            }
        }
        return res;
    }

    static String a1z26CipherDec(String text) {
        StringBuilder res = new StringBuilder();
        String[] index = text.split("-");
        int temp;
        for (int i = 0; i < index.length; i++) {
            temp = Integer.parseInt(index[i]) + 64;
            res.append((char)temp);
        }
        return String.valueOf(res);
    }
}
