package task1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoffeeTypeTest {
    @Test
    public void shouldGetPrice() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals(7, coffeeType.getPrice());
    }

    @Test
    public void shouldGetActionType() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals("2", coffeeType.getActionType());
    }

    @Test
    public void shouldGetWaterAmount() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals(350, coffeeType.getWaterAmount());
    }

    @Test
    public void shouldGetMilkAmount() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals(20, coffeeType.getMilkAmount());
    }

    @Test
    public void shouldGetCoffeeAmount() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals(75, coffeeType.getCoffeeAmount());
    }

    @Test
    public void shouldGetCupsAmount() {
        CoffeeType coffeeType = CoffeeType.LATTE;
        assertEquals(1, coffeeType.getCupsAmount());
    }

    @Test
    public void shouldFindByAction() {
        assertEquals(CoffeeType.LATTE, CoffeeType.findByAction("2"));
    }
}
