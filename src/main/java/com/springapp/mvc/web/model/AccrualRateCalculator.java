package com.springapp.mvc.web.model;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import static com.google.common.primitives.Doubles.max;
import static java.lang.Math.min;
import static org.joda.time.Days.daysBetween;

@Component
public class AccrualRateCalculator {
    final double YEAR_IN_DAYS = 365.25;
    final double MAX_VACATION_DAYS = 30d;

    public double calculateDailyAccrualRate(Employee employee, LocalDate endDate) {
        double tenure = daysBetween(employee.getStartDate(), endDate).getDays();
        double initialAccrualRate = employee.getInitialAccrualRate();
        if (tenure < YEAR_IN_DAYS) {
            return max(10.0, initialAccrualRate) / YEAR_IN_DAYS;
        } else if (tenure < 3 * YEAR_IN_DAYS) {
            return max(15.0, initialAccrualRate) / YEAR_IN_DAYS;
        } else if (tenure < 6 * YEAR_IN_DAYS) {
            return max(20.0, initialAccrualRate) / YEAR_IN_DAYS;
        } else {
            return max(25.0, initialAccrualRate) / YEAR_IN_DAYS;
        }
    }

    public double calculateVacationDayCap(Employee employee, LocalDate endDate){
        double currentAccrualRate = calculateDailyAccrualRate(employee, endDate);
        return min(currentAccrualRate * 1.5 * YEAR_IN_DAYS, MAX_VACATION_DAYS);
    }
}
