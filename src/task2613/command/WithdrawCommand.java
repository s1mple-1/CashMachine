package task2613.command;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;
import task2613.exception.InterruptOperationException;
import task2613.exception.NotEnoughMoneyException;

import java.util.ResourceBundle;

import static task2613.CashMachine.RESOURCE_PATH;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "withdraw_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String code = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator manipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(code);
        while (true) {
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            try {
                int expectedAmount = Integer.parseInt(ConsoleHelper.readString());
                if (manipulator.isAmountAvailable(expectedAmount)) {
                    manipulator.withdrawAmount(expectedAmount);
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), expectedAmount, code));
                    CurrencyManipulatorFactory.checkAndDelete(code);
                    break;
                }
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e) {
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
            }
        }
    }
}
