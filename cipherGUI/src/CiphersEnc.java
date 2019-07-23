import java.util.HashMap;
import java.util.Map;

public class CiphersEnc {

    public static String caesarCipherEnc(String text, final byte key) {
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
            }
        }
        return String.valueOf(res);
    }

    static String atbashCipherEnc(String text) {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                res += (char) ((25 - (text.charAt(i) - 65)+ 1) + 64);
            }
        }
        return res;
    }

    static String morseEnc(String text) {
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
        return String.valueOf(res);
    }

    static String vigenereCipherEnc(String text, String key) {
        String res = "";
        text = text.toLowerCase();
        key = key.toLowerCase();
        final int keyLen = key.length();
        for (int i = 0; i < key.length(); i++) {
            if (!(key.charAt(i) >= 'a' && key.charAt(i) <= 'z')) {
                return "The key is not suitable";
            }
        }
        for (int i = 0; i < text.length(); i++) {
            res += (char) (((text.charAt(i) + key.charAt(i % keyLen) - 2 * 97) % 26) + 97);
        }
        return res;
    }

    static String a1z26CipherEnc(String text) {
        StringBuilder res = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            res.append((text.charAt(i) - 64) + "-");
        }
        return String.valueOf(res.deleteCharAt(res.length() - 1));
    }

}
