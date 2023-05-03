package mini_projects.login;

import java.util.Scanner;

public class LoginMain {

    public static void main(String[] args) {


        start();
    }

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();

        int select;
        do {
            UserService.showMenu();
            select = scanner.nextInt();
            switch (select) {
                case 1:
                    userService.register();
                    break;

                case 2:
                    userService.login();
                    break;
                case 3:
                    System.out.println("iyi gunler dileriz...");
                    break;
                default:
                    System.out.println("hatali giriş yaptınız...");

            }
        } while (select != 3);
    }
}
