import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Field mineField = new Field(16, 16);
        System.out.println(mineField.toString());
        do {
            System.out.println("Inserisci la riga");
            Scanner scan = new Scanner((System.in));
            int row = scan.nextInt();
            System.out.println("Inserisci la colonna");
            int column = scan.nextInt();
            mineField.uncheker(row, column);
            System.out.println(mineField.toString());
        } while(true);
    }
}
