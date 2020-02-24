package task2613;

import task2613.exception.NotEnoughMoneyException;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations = new TreeMap<>(Collections.reverseOrder()); //<value - count>

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            int c = denominations.get(denomination) + count;
            denominations.put(denomination, c);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            value += entry.getKey() * entry.getValue();
        }
        return value;
    }

    public boolean hasMoney() {
        return denominations.size() != 0;
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> resultMap = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            int num = entry.getKey();
            int count = expectedAmount / num;
            if (entry.getValue() >= count && count != 0) {
                expectedAmount = expectedAmount - count * num;
                resultMap.put(num, count);
            }
            if (expectedAmount == 0) {
                break;
            }
        }
        if (expectedAmount != 0) {
            throw new NotEnoughMoneyException();
        }


        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            int denVal = denominations.get(key);
            if ((denVal - value) == 0) {
                denominations.remove(key);
            } else {
                denominations.put(key, (denVal - value));
            }
        }


        System.out.println(resultMap.toString());
        return resultMap;
    }

}
