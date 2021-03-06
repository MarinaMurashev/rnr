package com.springapp.mvc.web.service;

import com.springapp.mvc.web.model.Constants;
import com.springapp.mvc.web.model.Employee;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class EmployeeService {
    public Employee createEmployee(LocalDate startDate, String rolloverDays, Map<LocalDate, Double> daysOff, String initialAccrualRate) {
        double convertedRolloverDays = parseStringWithDefaultValue(rolloverDays, 0d);
        double convertedInitialAccrualRate = parseStringWithDefaultValue(initialAccrualRate, Constants.DEFAULT_ACCRUAL_RATE);

        return new Employee(startDate, convertedRolloverDays, daysOff, convertedInitialAccrualRate);
    }

    private double parseStringWithDefaultValue(String userEntry, double defaultValue){
        double convertedValue = defaultValue;

        boolean userEnteredAValue = !userEntry.equals("");

        if(userEnteredAValue) {
            convertedValue = Double.parseDouble(userEntry);
        }

        return convertedValue;
    }
}