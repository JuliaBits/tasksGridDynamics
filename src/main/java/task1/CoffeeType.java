package task1;

import java.util.Arrays;

public enum CoffeeType {
    ESPRESSO("1", 250, 0, 20, 1, 4),
    LATTE("2", 350, 20, 75, 1, 7),
    CAPPUCCINO("3", 200, 12, 100, 1, 6);

    private final String actionType;
    private final int waterAmount;
    private final int milkAmount;
    private final int coffeeAmount;
    private final int cupsAmount;
    private final int price;

    CoffeeType(String actionType, int waterAmount, int milkAmount, int coffeeAmount, int cupsAmount, int price) {
        this.actionType = actionType;
        this.waterAmount = waterAmount;
        this.milkAmount = milkAmount;
        this.coffeeAmount = coffeeAmount;
        this.cupsAmount = cupsAmount;
        this.price = price;
    }

    public static CoffeeType findByAction(String actionType) {
        return Arrays.stream(CoffeeType.values()).filter(s -> s.getActionType().equals(actionType)).findAny().orElse(null);
    }

    public int getPrice() {
        return price;
    }

    public String getActionType() {
        return actionType;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public int getCupsAmount() {
        return cupsAmount;
    }
}
