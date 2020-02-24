package task2613;

import task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.class.getPackage().getName() + ".resources.common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
            String res = bis.readLine();
            if (res.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
            return res;
        } catch (IOException e) {
            return "Ошибка чтения";
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        while (true) {
            writeMessage(res.getString("choose.currency.code"));
            String valueCode = readString();
            if (valueCode.length() == 3) {
                return valueCode.toUpperCase();
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] result;

        while (true) {
            //writeMessage("Введите два целых положительных числа через пробел. Первое номинал. Второе количество купюр");
            writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
            result = readString().split(" ");

            if (result.length != 2) {
                writeMessage(res.getString("invalid.data"));
                //writeMessage("Вы ввели больше 2 чисел. Попробуйте еще раз.");
            } else {
                try {
                    int first = Integer.parseInt(result[0]);
                    int second = Integer.parseInt(result[1]);
                    if (first > 0 && second > 0) {
                        break;
                    }
                    writeMessage(res.getString("invalid.data"));
                } catch (NumberFormatException e) {
                    writeMessage(res.getString("invalid.data"));
                }
            }
        }
        return result;
    }

    public static Operation askOperation() throws InterruptOperationException {
        Operation operation;
        while (true) {
            writeMessage(res.getString("choose.operation"));
            writeMessage("\n1 - INFO\n2 - DEPOSIT\n3 - WITHDRAW\n4 - EXIT");
            try {
                int operationNumber = Integer.parseInt(readString());
                operation = Operation.getAllowableOperationByOrdinal(operationNumber);
                break;
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
        return operation;
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
