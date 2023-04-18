import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static boolean check(String login, String password, String confirmPassword) {
        // Подсмотрел эту конструкцию на StackOverFlow
        Pattern pattern = Pattern.compile("\\w*");
        Matcher loginMatcher = pattern.matcher(login);
        Matcher passwordMatcher = pattern.matcher(password);

        if (loginMatcher.matches()) {
            if (!(login.length() <= 20)) {
                throw new WrongLoginException();
            }
        } else throw new IllegalArgumentException("Логин может содержать в себе только латинские буквы, цифры и знак подчеркивания");

        if (passwordMatcher.matches()) {
            if (!(password.length() < 20)) {
                System.out.println("Пароль должен быть строго меньше 20 символов.");
            }
        } else throw new IllegalArgumentException("Пароль может содержать в себе только латинские буквы, цифры и знак подчеркивания");

        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException();
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        System.out.print("Повторите пароль: ");
        String confirmPassword = scanner.nextLine();

        try {
            check(login, password, confirmPassword);
        } catch (WrongLoginException e) {
            System.out.println("Логин превышает 20 символов.");
        } catch (WrongPasswordException e) {
            System.out.println("Пароль введен неверно.");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Метод завершил работу.");
        }
    }
}