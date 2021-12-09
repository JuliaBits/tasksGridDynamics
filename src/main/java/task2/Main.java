package task2;


public class Main {
    public static void main(String[] args) {
        FlashCardsControl flashCards = new FlashCardsControl();

        if (args.length == 4) {
            if (args[0].equals("-import") & args[2].equals("-export")) {
                flashCards.importWithArgs(args[1]);
                flashCards.exportWhileExit(args[3]);
            } else if (args[0].equals("-export") & args[2].equals("-import")) {
                flashCards.importWithArgs(args[3]);
                flashCards.exportWhileExit(args[1]);
            }
        }
        if (args.length == 2) {
            if (args[0].equals("-export")) {
                flashCards.start();
                flashCards.exportWhileExit(args[1]);
            } else if (args[0].equals("-import")) {
                flashCards.importWithArgs(args[1]);
            }

        } else {
            flashCards.start();
        }
    }
}



