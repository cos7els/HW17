package Task1;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern IP_PATTERN = Pattern.compile("([0-9]+\\.)+[0-9]+");
    private static final Pattern VALID_IP_PATTERN = Pattern.compile("^(((25[0-5])|(2[0-4]\\d)|(1?\\d?\\d)|(\\d{1,2}))\\.){3}" +
            "((25[0-5])|(2[0-4]\\d)|(1?\\d?\\d)|(\\d{1,2}))$");

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        String read;
        System.out.print("""
                ------------------------------
                  Welcome to IP checker v1.0
                ------------------------------
                """);
        try (Scanner in = new Scanner(System.in)) {
            do {
                System.out.print("Enter the IP address for check or 'exit': ");
                read = in.nextLine().trim();
                if (IP_PATTERN.matcher(read).find()) {
                    ipCheck(read);
                } else if (read.equalsIgnoreCase("exit")) {
                    System.out.print("Are you sure want to exit Y/N: ");
                    if (in.nextLine().equalsIgnoreCase("n")) {
                        read = "continue";
                    }
                } else {
                    System.out.printf("'%s' is not IP address\n", read);
                }
            } while (!read.equalsIgnoreCase("exit"));
        }

    }

    private static void ipCheck(String ip) {
        Matcher matcher = VALID_IP_PATTERN.matcher(ip);
        System.out.printf("%s is %s\n", ip, matcher.find() ? "correct IP address" : "incorrect IP address");
    }

}
