package ru.ivan.SpringBoot2_5.service;

import org.junit.jupiter.api.Test;
import ru.ivan.SpringBoot2_5.model.Positions;

import static org.junit.jupiter.api.Assertions.*;

class CompensationCalculatorInterfaceTest {

    @Test
    void calculateQuarterlyBonus() {
        CompensationCalculatorInterface quarterlyBonusService = new CompensationCalculator();

        // ���������, ��� ����������� �������������� �������
        Positions managerPosition = Positions.MANAGER; // �������� � HR �� MANAGER
        double baseSalary = 500000.0;

        // ������ ������
        double actualBonus = quarterlyBonusService.calculateQuarterlyBonus(managerPosition, baseSalary); // �������� ����������� ������

        // ��������� �������� - 10% �� ������� ��������
        double expectedBonus = baseSalary * 0.1;
        assertEquals(expectedBonus, actualBonus, 0.001); // ���������, ��� �������� ������������� ����������
    }
}
