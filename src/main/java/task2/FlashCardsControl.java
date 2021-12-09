package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlashCardsControl {
    public static String fileNotFound = "File not found.";
    public static String fileName = "File name:";
    private final Scanner scanner = new Scanner(System.in);
    private final List<Flashcard> flashcards;
    private final List<String> logs;

    public FlashCardsControl() {
        this.flashcards = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    void start() {
        while (true) {
            printMessage("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            String option = getInput().toLowerCase();
            switch (option) {
                case "add" -> addFlashcard();
                case "remove" -> removeFlashcard();
                case "import" -> importFromFile();
                case "export" -> exportToFile();
                case "ask" -> ask();
                case "exit" -> {
                    printMessage("Bye bye!");
                    scanner.close();
                    exportWhileExit("file.txt");
                    return;
                }
                case "log" -> saveLog();
                case "hardest card" -> printHardestCard();
                case "reset stats" -> resetStats();
                default -> {
                }
            }
        }
    }

    private void resetStats() {
        flashcards.forEach(o -> o.setWrongAnswers(0));
        printMessage("Card statistics have been reset.");
    }

    private void printHardestCard() {
        int max = 0;
        for (Flashcard flashcard : flashcards) {
            int wrongAnswers = flashcard.getWrongAnswers();
            if (wrongAnswers > max) {
                max = flashcard.getWrongAnswers();
            }
        }

        if (max == 0) {
            printMessage("There are no cards with errors.");
        } else {
            int finalMax = max;
            ArrayList<String> maxCards = flashcards.stream()
                    .filter(o -> o.getWrongAnswers() == finalMax)
                    .map(Flashcard::getTerm).collect(Collectors.toCollection(ArrayList::new));
            if (maxCards.size() > 1) {
                String output1 = "The hardest cards are \"" + maxCards + "\". You have " +
                        max + " errors answering them.";
                printMessage(output1);
            } else {
                String output2 = "The hardest card is \"" + maxCards + "\". You have " +
                        max + " errors answering it.";
                printMessage(output2);
            }
        }
    }

    private void saveLog() {
        printMessage(fileName);
        String fileName = getInput();
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            for (String log : logs) {
                printWriter.println(log);
            }
            printMessage("The log has been saved.");
        } catch (FileNotFoundException e) {
            printMessage(fileNotFound);
        }
    }

    private void exportToFile() {
        printMessage(fileName);
        String fileName = getInput();
        exportWhileExit(fileName);
    }

    public void exportWhileExit(String fileName) {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file)) {
            int n = 0;
            for (Flashcard flashcard : flashcards) {
                printWriter.println(flashcard.getTerm() + ":" + flashcard.getDefinition() + ":"
                        + flashcard.getWrongAnswers());
                n++;
            }
            String output = String.format("%d cards have been saved.", n);
            printMessage(output);
        } catch (FileNotFoundException e) {
            printMessage(fileNotFound);
        }
    }

    public void importFromFile() {
        printMessage(fileName);
        String fileName = getInput();
        importWithArgs(fileName);
    }

    public void importWithArgs(String fileName) {
        File file = new File(fileName);
        try (Scanner sc = new Scanner(file)) {
            int n = 0;
            while (sc.hasNext()) {
                String[] card = sc.nextLine().strip().split(":");
                String term = card[0];
                String definition = card[1];
                int wrongAnswers = Integer.parseInt(card[2]);
                if (flashcards.stream().anyMatch(o -> term.equals(o.getTerm()))) {
                    Flashcard flashcard = flashcards.stream().filter(o -> term.equals(o.getTerm()))
                            .findFirst().orElse(null);
                    flashcards.remove(flashcard);
                }
                Flashcard newFlashcard = new Flashcard(term, definition);
                newFlashcard.setWrongAnswers(wrongAnswers);
                flashcards.add(newFlashcard);
                n++;
            }
            String output = String.format("%d cards have been loaded.", n);
            printMessage(output);
        } catch (FileNotFoundException e) {
            printMessage(fileNotFound);
        }

    }

    private void removeFlashcard() {
        printMessage("Which card?");
        String term = getInput();
        if (flashcards.stream().anyMatch(o -> term.equals(o.getTerm()))) {
            Flashcard flashcard = flashcards.stream().filter(o -> term.equals(o.getTerm())).findFirst().orElse(null);
            flashcards.remove(flashcard);
            printMessage("The card has been removed.");
        } else {
            String output = String.format("Can't remove \"%s\": there is no such card.", term);
            printMessage(output);
        }
    }

    private void addFlashcard() {
        printMessage("The card:");
        String term = getInput();
        String definition;

        if (flashcards.stream().anyMatch(o -> term.equals(o.getTerm()))) {
            String output = String.format("The card \"%s\" already exists.", term);
            printMessage(output);
        } else {
            printMessage("The definition of the card:");
            definition = getInput();
            String finalDefinition = definition;
            if (flashcards.stream().anyMatch(o -> finalDefinition.equals(o.getDefinition()))) {
                String output = String.format("The definition \"%s\" already exists.", definition);
                printMessage(output);
            } else {
                String output = String.format("The pair (\"%s\":\"%s\") has been added.", term, definition);
                printMessage(output);
                flashcards.add(new Flashcard(term, definition));
            }
        }
    }

    private void ask() {
        if (flashcards.size() > 0) {
            printMessage("How many times to ask?");
            int times = Integer.parseInt(getInput());
            int j = 0;
            for (int i = 0; i < times; i++) {
                if (j == flashcards.size()) {
                    j = 0;
                }
                String output = String.format("Print the definition of \"%s\":", flashcards.get(j).getTerm());
                printMessage(output);
                String answer = getInput();
                if (answer.equals(flashcards.get(j).getDefinition())) {
                    printMessage("Correct!");
                } else {
                    String tempTerm = flashcards.stream().filter(o -> o.getDefinition().equals(answer))
                            .map(Flashcard::getTerm).findFirst().orElse(null);
                    if (tempTerm != null) {
                        String output1 = String.format(
                                "Wrong. The right answer is \"%s\", but your definition is correct for \"%s\".",
                                flashcards.get(i).getDefinition(), tempTerm);
                        flashcards.get(i).addWrongAnswer();
                        printMessage(output1);
                    } else {
                        String output2 = String.format("Wrong. The right answer is \"%s\".",
                                flashcards.get(j).getDefinition());
                        flashcards.get(j).addWrongAnswer();
                        printMessage(output2);
                    }
                }
                j++;
            }
        } else {
            System.out.println("Add cards, please.");

        }
    }

    private void printMessage(String message) {
        logs.add(message);
        System.out.println(message);
    }

    private String getInput() {
        String message = scanner.nextLine().strip();
        logs.add(message);
        return message;
    }
}
