package mini_projects.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserService {


    List<String> usernameList = new ArrayList<String>();
    List<String> emailList = new ArrayList<String>();
    List<String> passwordList = new ArrayList<String>();

    public static void showMenu() {
        System.out.println("Welcome");
        System.out.println("1- Uye ol");
        System.out.println("2- Giris");
        System.out.println("3- Cikis");
        System.out.println("Seciminiz: ");
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ad soyad giriniz.");
        String name = scanner.nextLine();

        String usrername;
        boolean existUsername;
        do {
            System.out.println("Kullanici adini giriniz.");
            usrername = scanner.nextLine();
            existUsername = usernameList.contains(usrername);
            if (existUsername) {
                System.out.println("Kullanici adi zaten var. Tekrar deneyiniz.");
            } else {
                usernameList.add(usrername);
            }
        } while (existUsername);

        String email;
        boolean isValidEmail;
        boolean existEmail;
        do {

            System.out.println("Email adini giriniz.");
            email = scanner.nextLine().trim();
            isValidEmail = validateEmail(email);

            existEmail = emailList.contains(email);

            if (existEmail) {
                isValidEmail = false;
                System.out.println("Bu email daha once kullanilmis");
            }

        } while (!isValidEmail);

        String password;
        boolean isValidPsw= true;
        do {
            System.out.println("Sifrenizi giriniz..");
            password = scanner.nextLine();

            validatePassword(password);
            
        } while (!isValidPsw);


        User user = new User(name, usrername, email, password);
        usernameList.add(usrername);
        emailList.add(email);
        passwordList.add(password);

        System.out.println(user);
        System.out.println("Tebrikler kayit isleminiz gerceklesmistir..");
        System.out.println("Kullanici adi veya email ile sisteme giris yapabilirsiniz..");
    }


    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kullanici adini giriniz.");
        String usernameOrEmail = scanner.nextLine();

      boolean isMail =  emailList.contains(usernameOrEmail);
      boolean isUsername =   usernameList.contains(usernameOrEmail);

        if (isMail || isUsername){
            while (true){
                System.out.println("Sifre giriniz..");
                String password = scanner.nextLine();

                int idx;
                if (isUsername){
                    idx= usernameList.indexOf(usernameOrEmail);
                }else{
                    idx= usernameList.indexOf(usernameOrEmail);
                }
                if (passwordList.get(idx).equals(password)){
                    System.out.println("Giriş yapıldı..");
                    break;
                }else{
                    System.out.println("Sifre yanlış..");
                }
            }

        }else {
            System.out.println("sisteme kayitli kullanici bulunamadı..");
        }
    }


    public static boolean validateEmail(String email) {
        boolean isValidEmail;
        boolean space = email.contains(" ");
        boolean isContainAt = email.contains("@");

        if (space) {
            System.out.println("Eamail bosluk icermez.");
            isValidEmail = false;
        } else if (!isContainAt) {
            System.out.println("Email @ icermelidir..");
            isValidEmail = false;
        } else {
            String fistPart = email.split("@")[0];
            String secondPart = email.split("@")[1];
            boolean checkStart = fistPart.replaceAll("[a-zA-Z0-9_.-]", "").length() == 0;
            boolean checkEnd = secondPart.equals("gmail.com") ||
                    secondPart.equals("yahoo.com") ||
                    secondPart.equals("hotmail.com");

            if (!checkStart) {
                System.out.println("Email kucuk harf, buyuk harf, rakam ve bunlar disinda karakter iceremez.");
            } else if (!checkEnd) {
                System.out.println("email uygun yazilmali.");
            }
            isValidEmail = checkEnd && checkStart;
        }
        return isValidEmail;
    }

    public static boolean validatePassword(String password) {

        boolean isValidEmail;
        boolean space = password.contains(" ");
        boolean lengthGt6 = password.length() >= 6;
        boolean existUpper = password.replaceAll("[^A-Z]", "").length() > 0;
        boolean existLower = password.replaceAll("[^A-Z]", "").length() > 0;
        boolean existDigit = password.replaceAll("[^0-9]", "").length() > 0;
        boolean existSymbol = password.replaceAll("[\\P{Punct}]", "").length() > 0;
        if (space) {
            System.out.println("SIfre bosluk icermez.");
        }else if (!lengthGt6) {
            System.out.println("Sifre en az 6 karakter olmalıdır.");
        }else if (!existUpper){
            System.out.println("Sifre en az bir buyuk harf icermelidir.");
        }else if (!existLower){
            System.out.println("Sifre en az bir kucuk harf icermelidir.");
        }else if (!existDigit){
            System.out.println("Sifre en az bir rakam  icermelidir.");
        }else if (!existSymbol){
            System.out.println("Sifre en az bir sembol  icermelidir.");
        }

        isValidEmail=!space && !lengthGt6 &&!existUpper &&!existLower &&!existDigit &&! existSymbol;

        if (!isValidEmail){
            System.out.println("tekrar deneyiniz.");
        }

        return isValidEmail;
    }
}
