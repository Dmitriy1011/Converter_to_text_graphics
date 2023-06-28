package ru.netology.graphics;

import ru.netology.graphics.image.ColorScheme;
import ru.netology.graphics.image.ConvertImg;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {

        ColorScheme colorScheme = new ColorScheme();
        ConvertImg convertImg = new ConvertImg();

        TextGraphicsConverter converter = new ConvertImg(); // Создайте тут объект вашего класса конвертера

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем


//         Или то же, но с выводом на экран:
        String url = "https://i.ibb.co/6DYM05G/edu0.jpg";
//        String url = "https://raw.githubusercontent.com/netology-code/java-diplom/main/pics/simple-test.png";
        String imgTxt = converter.convert(url);
        System.out.println(imgTxt);
    }
}
