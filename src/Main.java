import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int row = 0, column = 0;
        double ratio = 0;
        System.out.println("Benvenuto al gioco del campo minato, seleziona la difficoltà: \n");
        System.out.println(" 1 - Facile (griglia 9*9, circa 10 mine)\n 2 - Normale (griglia 16*16, circa 40 mine)\n 3 - Difficile (griglia 24*24, circa 99 mine)");
        Scanner scanChoice = new Scanner(System.in);
        int choice = scanChoice.nextInt();
        switch(choice){
            case 1:
                row = 9;
                column = 9;
                ratio = 0.12;
                break;
            case 2:
                row = 16;
                column = 16;
                ratio = 0.15;
                break;
            case 3: row = 24;
                    column = 24;
                    ratio = 0.17;
                break;
            default:
                System.out.println("Scelta errata, riprovare.");
        }
        Field mineField = new Field(row, column, ratio);
        int numberOfMines = mineField.getMines();
        System.out.println(mineField.toString());
        int rowChosed;
        int columnChosed;
        do {
            System.out.println("Inserisci la riga");
            Scanner scan = new Scanner((System.in));
            rowChosed = scan.nextInt();
            System.out.println("Inserisci la colonna");
            columnChosed = scan.nextInt();
            mineField.uncover(rowChosed, columnChosed);
            mineField.isFlag();
            if(mineField.getPoints() == ((row*column) - numberOfMines))
                System.out.println("HAI VINTO!!!");
            if(mineField.isMine(rowChosed, columnChosed)) {
                System.out.println("\nBUUMMETEEEEEE!!!!!!!!!!");
            }
            System.out.println(mineField.toString());
            System.out.println("\nPunteggio: " + mineField.getPoints());
        } while(mineField.getPoints() < ((row*column) - numberOfMines) && !mineField.isMine(rowChosed, columnChosed));
    }
}
