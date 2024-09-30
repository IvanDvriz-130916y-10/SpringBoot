package ru.ivan.SpringBoot2_5.service;

import org.junit.jupiter.api.Test;
import ru.ivan.SpringBoot2_5.model.Positions;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.time.Year;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {

        Positions positions = Positions.HR;
        double bonus = 2.0;
        int workDays = 243;
        double salary = 100000.00;

        int daysInYear = Year.now().length(); // Определение количества дней в текущем году

        double result = new AnnualBonusServiceImpl().calculate(positions, salary, bonus, workDays);

        // Пересчет ожидаемого значения на основе актуального количества дней в году
        double expected = salary * bonus * daysInYear * positions.getPositionCoefficient() / workDays;

        assertThat(result).isEqualTo(expected);
    }
}