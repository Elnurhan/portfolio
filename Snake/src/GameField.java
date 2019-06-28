import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

// Класс, отвечающий за игровое поле
public class GameField extends JPanel implements ActionListener {
    private final int SIZE = 320;
    private final int DOT_SIZE = 16; // Размер еды и одной части змейки
    private final int ALL_DOTS = 400; // Сколько игровых единиц может появиться на поле
    private Image dot;
    private Image apple;
    private int appleX; // X - позиция яблока
    private int appleY; // Y - позиция яблока
    private int[] x = new int[ALL_DOTS]; // X - позиция змейки
    private int[] y = new int[ALL_DOTS]; // Y - позиция змейки
    private int dots; // Размер змейки на данный момент
    private Timer timer; // Таймер
    // Переменные для отслеживания движения змейки(изначально - право)
    private boolean left;
    private boolean right = true;
    private boolean up;
    private boolean down;
    private boolean in_game = true;


    public GameField() {
        setBackground(Color.BLACK); // Задний фон
        loadImages(); // Загрузка картинок
        initGame();
        addKeyListener(new FieldKeyListener()); // Добавляем отслеживание за клавишами
        setFocusable(true);

    }

    public void initGame() { // Значения переменных при инициализации игры
        dots = 3; // Начальное кол-во точек
        for (int i = 0; i < dots; i++) { // Начальная позиция змейки
            x[i] = 48 - i*DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(200, this);
        timer.start();
        createApple();
    }

    public void createApple() { // Метод для создания яблок
        appleX = new Random().nextInt(20)*DOT_SIZE;  // Рандомная генерация яблок по всему полю. 20 - это максимальное кол-во яблок
        appleY = new Random().nextInt(20)*DOT_SIZE;  // Рандомная генерация яблок по всему полю. 20 - это максимальное кол-во яблок
    }

    public void loadImages() { // Загрузка картинок
        ImageIcon iia = new ImageIcon("apple.png"); // Путь к картинке с едой
        apple = iia.getImage(); // Сохраняем картинку яблока в переменной apple
        ImageIcon iid = new ImageIcon("dot.png"); // Путь к картинке с частью змеи
        dot = iid.getImage(); // Сохраняем картинку
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (in_game) {
            g.drawImage(apple, appleX, appleY, this); // Рисуем яблоки
            for (int i = 0; i < dots; i++) { // Перерисовка змейки
                g.drawImage(dot, x[i], y[i], this);
            }
            String score = "Score: " + (dots - 3);
            Font f = new Font("Arial", Font.ITALIC, 10);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(score, 150, 300);
        } else {
            // Если игра закончена, отрисовываем "Game Over"
            String str = "Game Over";
            Font f = new Font("Arial", Font.BOLD, 14);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, 125, SIZE / 2);
        }
    }

    public void move() { // Передвижение
        for (int i = dots; i > 0 ; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        // Поворот головы змейки
        if(left) {
            x[0] -= DOT_SIZE;
        }
        if(right) {
            x[0] += DOT_SIZE;
        }
        if(up) {
            y[0] -= DOT_SIZE;
        }
        if(down) {
            y[0] += DOT_SIZE;
        }
    }

    public void checkApple() { // Проверяем, съела ли змея яблоко
        if (x[0] == appleX && y[0] == appleY) { // Есди координаты головы совпадают с координатами яблока, то:
            dots++;
            createApple();
        }
    }



    public void checkCollisions() { // Проверка на столкновения с границей карты или же с хвостом
        for (int i = dots; i > 0; i--) { // Столкновение с самой собой
            if (i > 0 && x[0] == x[i] && y[0] == y[i]) {
                in_game = false;
            }
        }

        // Столкновение с границей
        if(x[0] > SIZE) {
            in_game = false;
        }
        if(x[0] < 0) {
            in_game = false;
        }
        if(y[0] > SIZE) {
            in_game = false;
        }
        if(y[0] > SIZE) {
            in_game = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) { // Сам процесс игры
        if(in_game){
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter { // Класс для отслеживания нажатия на клавиши

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode(); // Какая клавшиа нажата
            if(key == KeyEvent.VK_LEFT && !right) { // Если змейка движется вправо, то сразу двигаться влево она не может
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left) { // Если змейка движется влево, то сразу двигаться вправо она не может
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down) { // Если змейка движется вверх, то сразу двигаться вниз она не может
                up = true;
                right = false;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up) { // Если змейка движется вниз, то сразу двигаться вверх она не может
                down = true;
                right = false;
                left = false;
            }
        }
    }
}
