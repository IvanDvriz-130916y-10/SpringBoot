package ru.ivan.SpringBoot2_5.service;

import org.junit.jupiter.api.Test;
import ru.ivan.SpringBoot2_5.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class CompensationCalculatorInterfaceTest {

    @Test
    void calculateQuarterlyBonus() {
        CompensationCalculatorInterface quarterlyBonusService = new CompensationCalculator();

        // Убедитесь, что используете управленческую позицию
        Positions managerPosition = Positions.MANAGER; // Изменено с HR на MANAGER
        double baseSalary = 500000.0;

        // Расчёт премии
        double actualBonus = quarterlyBonusService.calculateQuarterlyBonus(managerPosition, baseSalary); // Получаем фактическую премию

        // Ожидаемое значение - 10% от базовой зарплаты
        double expectedBonus = baseSalary * 0.1;
        assertEquals(expectedBonus, actualBonus, 0.001); // Проверяем, что ожидание соответствует результату
    }
}
