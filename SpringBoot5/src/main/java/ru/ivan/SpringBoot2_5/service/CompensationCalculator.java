package ru.ivan.SpringBoot2_5.service;

import ru.ivan.SpringBoot2_5.model.Positions;

public class CompensationCalculator implements CompensationCalculatorInterface {

    @Override
    public double calculateQuarterlyBonus(Positions position, double baseSalary) {
        // Проверка, является ли позиция управленческой
        if (!position.isManager()) {
            throw new IllegalArgumentException("Данная позиция не является управленческой.");
        }

        // Пример расчета квартальной премии
        double bonusCoefficient = 0.1; // 10% от базовой зарплаты
        return baseSalary * bonusCoefficient;
    }
}

