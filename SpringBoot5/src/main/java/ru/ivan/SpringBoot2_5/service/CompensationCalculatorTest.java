package ru.ivan.SpringBoot2_5.service;

import org.testng.annotations.Test;
import ru.ivan.SpringBoot2_5.model.Positions;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.Assert.assertThrows;
import static org.testng.Assert.assertEquals;


class CompensationCalculatorTest {

    // Тест для расчета квартальной премии для менеджера
    @Test
    void testCalculateQuarterlyBonusForManager() {
        CompensationCalculatorInterface calculator = new CompensationCalculator();
        double baseSalary = 5000.0; // Пример базовой зарплаты
        double expectedBonus = 500.0; // Ожидаемая премия (10% от 5000)

        double actualBonus = calculator.calculateQuarterlyBonus(Positions.MANAGER, baseSalary);
        assertEquals(expectedBonus, actualBonus, "Квартальная премия для менеджера рассчитана неверно.");
    }

    // Тест для расчета квартальной премии для не менеджера
    @Test
    void testCalculateQuarterlyBonusForNonManager() {
        CompensationCalculatorInterface calculator = new CompensationCalculator();
        double baseSalary = 5000.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.calculateQuarterlyBonus(Positions.DEV, baseSalary);
        });

        String expectedMessage = "Данная позиция не является управленческой.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Исключение должно содержать корректное сообщение.");
    }

    // Тест для расчета квартальной премии для другого управленца (например, CEO)
    @Test
    void testCalculateQuarterlyBonusForCEO() {
        CompensationCalculatorInterface calculator = new CompensationCalculator();
        double baseSalary = 10000.0; // Пример базовой зарплаты
        double expectedBonus = 1000.0; // Ожидаемая премия (10% от 10000)

        double actualBonus = calculator.calculateQuarterlyBonus(Positions.CEO, baseSalary);
        assertEquals(expectedBonus, actualBonus, "Квартальная премия для CEO рассчитана неверно.");
    }
}