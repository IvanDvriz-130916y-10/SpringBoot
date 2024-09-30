package ru.ivan.SpringBoot2_5.service;

import org.junit.jupiter.api.Test;
import ru.ivan.SpringBoot2_5.model.Positions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnnualBonusServiceTest {

    @Test
    void calculate() {
        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;
        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workDays);
        double expected = 360493.8271604938;
        assertThat(result).isEqualTo(expected);
    }
}