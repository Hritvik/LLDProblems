package currency.convertor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CurrencyConvertor {

    Map<String, Map<String, Float>> outerRateMap;

    CurrencyConvertor() {
        outerRateMap = new HashMap<>();
    }

    public static void main(String[] args) {
        CurrencyConvertor sol = new CurrencyConvertor();
        sol.insertCurrencyExchangeRate("INR", 100, "USD", 2);
        sol.insertCurrencyExchangeRate("USD", 10, "EURO", 7);
        sol.insertCurrencyExchangeRate("EURO", 100, "SGD", 2);
        System.out.println(sol.outerRateMap);

        System.out.println(sol.getCovnvertedCurrency("INR", 10, "USD"));
        System.out.println(sol.getCovnvertedCurrency("INR", 10, "SGD"));
    }

    public void insertCurrencyExchangeRate(String sourceCurrency, float sourceAmount, String targetCurrency, float targetAmount) {
        if (!outerRateMap.containsKey(sourceCurrency)) {
            Map<String, Float> innerRateMap = new HashMap<>();
            innerRateMap.put(targetCurrency, targetAmount / sourceAmount);
            outerRateMap.put(sourceCurrency, innerRateMap);
        } else {
            Map<String, Float> innerRateMap = outerRateMap.get(sourceCurrency);
            if (!innerRateMap.containsKey(targetCurrency)) {
                innerRateMap.put(targetCurrency, targetAmount / sourceAmount);
                outerRateMap.put(sourceCurrency, innerRateMap);
            } else {
                System.out.println("System already knows the rate for this pair :: " + innerRateMap.get(targetCurrency));
            }
        }
    }

    private Float findCurrencyLink(Set<String> visitedCurrency, String sourceCurrency, String targetCurrency) {
        visitedCurrency.add(sourceCurrency);
        Map<String, Float> innerRateMap = outerRateMap.get(sourceCurrency);
        if (innerRateMap.containsKey(targetCurrency)) {
            return innerRateMap.get(targetCurrency);
        }
        for (String currency : innerRateMap.keySet()) {
            if (visitedCurrency.contains(currency)) {
                continue;
            }
            Float linkRate = outerRateMap.get(sourceCurrency).get(currency);
            Float rate = linkRate * findCurrencyLink(visitedCurrency, currency, targetCurrency);
            if (rate != -1) {
                return rate;
            }
        }
        return (float) -1;
    }

    public Float getCovnvertedCurrency(String sourceCurrency, float sourceAmount, String targetCurrency) {
        Float rate = getCovnvertedCurrencyInternal(sourceCurrency, targetCurrency);
        if (rate != -1) {
            return sourceAmount * rate;
        } else {
            rate = getCovnvertedCurrencyInternal(targetCurrency, sourceCurrency);
            if (rate != -1) {
                return sourceAmount / rate;
            } else {
                System.out.println("Rate could not be found for the given currencies");
                return (float) -1;
            }
        }
    }

    private Float getCovnvertedCurrencyInternal(String sourceCurrency, String targetCurrency) {
        if (outerRateMap.containsKey(sourceCurrency)) {
            Map<String, Float> innerRateMap = outerRateMap.get(sourceCurrency);
            if (innerRateMap.containsKey(targetCurrency)) {
                return innerRateMap.get(targetCurrency);
            }
        } else {
            System.out.println("System does not know any exchange rate for the sourceCurrency " + sourceCurrency);
        }
        //Currency Pair was not found directly;
        Set<String> visitedCurrency = new HashSet<>();
        return findCurrencyLink(visitedCurrency, sourceCurrency, targetCurrency);
    }
}