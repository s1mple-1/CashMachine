package task2613.command;

import task2613.ConsoleHelper;
import task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

import static task2613.CashMachine.RESOURCE_PATH;

public class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(RESOURCE_PATH + "login_en");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            try {
                String cardNumber = ConsoleHelper.readString();
                String pinCode = ConsoleHelper.readString();

                if (validCreditCards.containsKey(cardNumber) &&
                        validCreditCards.getString(cardNumber).equals(pinCode)) {
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), cardNumber));
                    break;
                } else {
                    ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), cardNumber));
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                    ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
                }
            } catch (NumberFormatException e) {
                ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
