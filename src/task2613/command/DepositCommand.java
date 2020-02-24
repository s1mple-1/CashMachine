package task2613.command;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static task2613.CashMachine.RESOURCE_PATH;

class DepositCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "deposit_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        String[] value = ConsoleHelper.getValidTwoDigits(code);
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        manipulator.addAmount(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
        int result = Integer.parseInt(value[0]) * Integer.parseInt(value[1]);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), result, code));
    }
}
