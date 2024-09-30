package ru.ivan.SpringBoot2_5.service;

import org.springframework.stereotype.Service;
import ru.ivan.SpringBoot2_5.model.Positions;
@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}