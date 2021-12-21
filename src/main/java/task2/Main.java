package task2;


public class Main {
    private static final String IMPORT = "-import";
    private static final String EXPORT = "-export";

    public static void main(String[] args) {
        FlashCardsControl flashCards = new FlashCardsControl();

        if (args.length == 4) {
            if (args[0].equals(IMPORT) && args[2].equals(EXPORT)) {
                flashCards.importWithArgs(args[1]);
                flashCards.exportWhileExit(args[3]);
            } else if (args[0].equals(EXPORT) && args[2].equals(IMPORT)) {
                flashCards.importWithArgs(args[3]);
                flashCards.exportWhileExit(args[1]);
            }
        }

        if (args.length == 2) {
            if (args[0].equals(EXPORT)) {
                flashCards.start();
                flashCards.exportWhileExit(args[1]);
            } else if (args[0].equals(IMPORT)) {
                flashCards.importWithArgs(args[1]);
            }
        } else {
            flashCards.start();
        }
    }
}



