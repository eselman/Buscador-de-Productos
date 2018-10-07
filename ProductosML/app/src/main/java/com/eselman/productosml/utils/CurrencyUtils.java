package com.eselman.productosml.utils;

import android.annotation.TargetApi;
import android.icu.util.Currency;
import android.os.Build;

import java.util.Locale;
import java.util.Set;

public class CurrencyUtils {
    private static CurrencyUtils instance;

    public static CurrencyUtils getInstance() {
        if (instance == null) {
            instance = new CurrencyUtils();
        }

        return instance;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static String getCurrencySymbol(String currencyId) {
        String currencySymbol = null;
        Currency currency = Currency.getInstance(Locale.getDefault());
        Set<Currency> availableCurrencies =  currency.getAvailableCurrencies();
        for (Currency curr: availableCurrencies ) {
            if (curr.getCurrencyCode().equals(currencyId)) {
                currencySymbol = curr.getSymbol();
                break;
            }
        }

        if(currencySymbol == null) {
            currencySymbol = currencyId;
        }

        return currencySymbol;
    }
}
