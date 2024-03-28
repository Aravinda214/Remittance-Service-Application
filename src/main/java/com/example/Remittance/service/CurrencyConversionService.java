package com.example.Remittance.service;

import com.example.Remittance.enums.Currency;

public interface CurrencyConversionService {
    double convertToUsd(double amount, Currency currency);
	double convertAmountFromUsd(double amount, Currency currency);
}
