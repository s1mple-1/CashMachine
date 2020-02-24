package task2613.command;

import task2613.ConsoleHelper;
import task2613.CurrencyManipulator;
import task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;

import static task2613.CashMachine.RESOURCE_PATH;

class InfoCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "info_en");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
        } else {
            for (CurrencyManipulator manipulator : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
                String code = manipulator.getCurrencyCode();
                if (manipulator.hasMoney()) {
                    int value = manipulator.getTotalAmount();
                    ConsoleHelper.writeMessage(code + " - " + value);
                } else ConsoleHelper.writeMessage(res.getString("no.money"));

            }
        }

    }
}
