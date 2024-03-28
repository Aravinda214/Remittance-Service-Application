package com.example.Remittance.serviceimpl;
import org.springframework.stereotype.Service;

import com.example.Remittance.enums.Currency;
import com.example.Remittance.service.CurrencyConversionService;

@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Override
    public double convertToUsd(double amount, Currency currency) {
        switch (currency) {
            case INR:
                return amount * 0.013; // Example conversion rate from INR to USD
            case EUR:
                return amount * 1.1; // Example conversion rate from EUR to USD
            case USD:
            default:
                return amount;
        }
    }

	@Override
	public double convertAmountFromUsd(double amount, Currency currency) {
		switch (currency) {
        case INR:
            return amount;
        case USD:
            return amount * 74.85;
        case EUR:
            return amount * 85.73;
        default:
            throw new IllegalArgumentException("Unsupported currency: " + currency);
		}
	}
}
