package task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    private CurrencyManipulatorFactory() {
    }

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (map.containsKey(currencyCode.toUpperCase())) {
            return map.get(currencyCode.toUpperCase());
        }
        CurrencyManipulator currencyManipulator = new CurrencyManipulator(currencyCode.toUpperCase());
        map.put(currencyCode.toUpperCase(), currencyManipulator);
        return currencyManipulator;
    }

    public static void checkAndDelete(String currencyCode) {
        if (!map.get(currencyCode).hasMoney()) {
            map.remove(currencyCode);
        }
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values();
    }
}
