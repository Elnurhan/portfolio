import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("[1] - Encrypt\n" +
                           "[2] - Decrypt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice = Byte.parseByte(reader.readLine());
        System.out.print("Введите текст: ");
        String text = reader.readLine();
        System.out.println();
        System.out.println("******Теперь выберите нужный Вам шифр******");
        System.out.println("[1] - Шифр Цезаря\n" +
                           "[2] - Шифр Виженера\n" +
                           "[3] - Азбука Морзе\n" +
                           "[4] - Шифр Атбаш\n" +
                           "[5] - Шифр A1Z26");
        byte cipher_choice = Byte.parseByte(reader.readLine());
        if(choice == 1) {
            new Encryptor(text, cipher_choice);
        } else if(choice == 2) {
            new Decryptor(text, cipher_choice);
        }
    }
}
