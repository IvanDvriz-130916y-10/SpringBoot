package ru.ivan.SpringBoot2_5.service;

import ru.ivan.SpringBoot2_5.model.Positions;

public interface CompensationCalculatorInterface {
    double calculateQuarterlyBonus(Positions position, double baseSalary);
}

