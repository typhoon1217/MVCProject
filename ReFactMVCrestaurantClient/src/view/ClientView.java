package view;

import java.util.Scanner;

public class ClientView {
    private Scanner scanner;

    public ClientView() {
        scanner = new Scanner(System.in);
    }

    public String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}