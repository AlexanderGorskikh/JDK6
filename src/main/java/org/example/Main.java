package org.example;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * В качестве задачи предлагаю вам реализовать код для демонстрации парадокса
 * Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться в
 * верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
 * Необходимо: Создать свой Java Maven или Gradle проект; Подключите зависимость
 * lombok и возможно какую то математическую библиотеку (напр. commons-math3)
 * Самостоятельно реализовать прикладную задачу; Сохранить результат игр в одну
 * из коллекций или в какой то библиотечный класс Вывести на экран статистику по
 * победам и поражениям В качестве ответа прислать ссылку на репозиторий, в
 * котором присутствует все важные файлы проекта (напр pom.xml)
 */

/**
 * Код достаточно небольшой чтобы уместить его в одном классе:
 */

@NoArgsConstructor
public class Main {
    private static final List<Boolean> changeChoice = new ArrayList<Boolean>();
    private static final List<Boolean> notChangeChice = new ArrayList<Boolean>();
    private static final Random rnd = new Random();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            changeChoice.add(game(true));
            notChangeChice.add(game(false));
        }
        long winWithChoice = changeChoice.stream().filter(e -> e).count();
        long winWithoutChoice = notChangeChice.stream().filter(e -> e).count();
        System.out.println("Количество выйгрышей с изменением выбора: " + winWithChoice);
        System.out.println("Количество выйгрышей без изменением выбора: " + winWithoutChoice);
    }

    private static boolean game(boolean choice) {
        boolean[] doors = new boolean[3];
        doors[rnd.nextInt(3)] = true;
        int tmp = rnd.nextInt(0, 3); // выбор одной двери из предложенных
        int wieDoor = -1; // дверь которую открывает ведущий
        for (int i = 0; i < 2; i++) {
            if (!doors[i]) {
                wieDoor = i;
            }
        }
        int nextChoice = tmp;
        if (choice) { // выбираем другую дверь (с условием что она не будет той же самой)
            for (int i = 0; i < 2; i++) {
                if (i == wieDoor || i == nextChoice) {
                    continue;
                }
                nextChoice = i;
            }
        }
        return doors[nextChoice];
    }
}