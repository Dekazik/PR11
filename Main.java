import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean end = false;

        System.out.print("Введіть шлях до файлу (наприклад, aboba.txt): ");
        String path = sc.nextLine();

        while (!end) {
            System.out.println("\n======== ГОЛОВНЕ МЕНЮ ========");
            System.out.println("1. Відредагувати файл\n2. Прочитати файл\n3. Вийти");
            System.out.print("Ваш вибір: ");

            int choice;
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Некоректний вибір! Спробуйте ще раз.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    editFile(path, sc);
                    break;
                case 2:
                    readFile(path);
                    break;
                case 3:
                    System.out.println("До зустрічі!");
                    end = true;
                    break;
                default:
                    System.out.println("Некоректний вибір! Спробуйте ще раз.");
            }
        }
        sc.close();
    }

    public static void editFile(String path, Scanner sc) {
        System.out.println("\n1. Перезаписати файл\n2. Додати у файл\n3. Повернутися");
        System.out.print("Ваш вибір: ");

        int editChoice;
        if (sc.hasNextInt()) {
            editChoice = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Некоректний вибір! Спробуйте ще раз.");
            sc.nextLine();
            return;
        }

        if (editChoice == 3) {
            return;
        }

        boolean append;
        if (editChoice == 2) {
            append = true;
        } else {
            append = false;
        }


        FileWriter fw = null;
        try {
            fw = new FileWriter(path, append);
            System.out.println("Введіть текст (щоб завершити введення, натисніть Enter на порожньому рядку):");

            while (true) {
                String text = sc.nextLine();
                if (text.isEmpty()){
                    break;
                }
                fw.write(text + "\n");
            }
            System.out.println("Файл успішно оновлено!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Помилка закриття файлу: " + e.getMessage());
            }
        }
    }

    public static void readFile(String path) {
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            System.out.println("\n===== Вміст файлу =====");
            String s = "";

            while (fr.ready()) {
                s += (char) fr.read();
            }
            System.out.println(s);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено! " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка читання файлу! " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Помилка закриття файлу! " + e.getMessage());
            }
        }
    }
}
