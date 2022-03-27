package task1;

import java.util.Scanner;

public class CoffeeMachine {
    private final Scanner sc = new Scanner(System.in);
    private int coffeeLeft = 120;
    private int waterLeft = 400;
    private int milkLeft = 540;
    private int money = 550;
    private int disposableCups = 9;

    public CoffeeMachine() {
    }

    public CoffeeMachine(int coffeeLeft, int waterLeft, int milkLeft, int money, int disposableCups) {
        this.coffeeLeft = coffeeLeft;
        this.waterLeft = waterLeft;
        this.milkLeft = milkLeft;
        this.money = money;
        this.disposableCups = disposableCups;
    }

    public int getCoffeeLeft() {
        return coffeeLeft;
    }

    public int getWaterLeft() {
        return waterLeft;
    }

    public int getMilkLeft() {
        return milkLeft;
    }

    public int getMoney() {
        return money;
    }
    public int getDisposableCups() {
        return disposableCups;
    }


    public void setMoney(int money) {
        this.money = money;
    }

    private void remaining() {
        System.out.println("The coffee machine has: \n" + waterLeft + " ml of water\n" + milkLeft + " ml of milk");
        System.out.println(coffeeLeft + " g of coffee beans\n" + disposableCups + " disposable cups\n" + money + "$ of money");
    }

    private void makeCoffee(CoffeeType order) {
        System.out.println("I have enough resources, making you a coffee! ");
        coffeeLeft -= order.getCoffeeAmount();
        waterLeft -= order.getWaterAmount();
        disposableCups -= order.getCupsAmount();
        money += order.getPrice();
    }

    private boolean areEnoughResources(CoffeeType order) {
        boolean result = false;
        if (coffeeLeft >= order.getCoffeeAmount() && waterLeft >= order.getWaterAmount() && disposableCups >= order.getCupsAmount()) {
            result = true;
        } else {
            if (coffeeLeft < order.getCoffeeAmount()) {
                System.out.println("Sorry, not enough coffee!");
            }
            if (waterLeft < order.getWaterAmount()) {
                System.out.println("Sorry, not enough water!");
            }
            if (milkLeft < order.getMilkAmount()) {
                System.out.println("Sorry, not enough milk!");
            }
            if (disposableCups < order.getCupsAmount()) {
                System.out.println("Sorry, not enough cups!");
            }
        }
        return result;
    }

    private void buy() {
        while (true) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
            String action = sc.next();
            if (!action.equals("back")) {
                CoffeeType order = CoffeeType.findByAction(action);
                if (order != null) {
                    if (areEnoughResources(order)) {
                        makeCoffee(order);
                    }
                    break;
                }
            } else {
                break;
            }
        }
    }

    private void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        waterLeft += sc.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milkLeft += sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        coffeeLeft += sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        disposableCups += sc.nextInt();
    }

    private void take() {
        System.out.println("I gave you $" + money);
        setMoney(0);
    }

    public void start() {
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String input = sc.next();
            switch (input) {
                case "fill":
                    fill();
                    break;
                case "buy":
                    buy();
                    break;
                case "take":
                    take();
                    break;
                case "remaining":
                    remaining();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Check your input!");
                    break;
            }
        }
    }
}

