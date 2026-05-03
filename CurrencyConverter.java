import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame {

private JComboBox<String> currencyFrom;
private JComboBox<String> currencyTo;
private JTextField amountField;
private JButton convertButton;
private JLabel resultLabel;

private Map<String, Double> exchangeRates;
public CurrencyConverter() {
setTitle("Global Currency Converter");
setSize(400, 200);
setLocationRelativeTo(null);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new GridLayout(5, 2, 10, 10));

initializeExchangeRates();
addComponents();
addEventHandlers();
}
private void initializeExchangeRates() {
exchangeRates = new HashMap<>();
exchangeRates.put("USD", 1.0);
exchangeRates.put("EUR", 0.9);
exchangeRates.put("GBP", 0.8);
exchangeRates.put("JPY", 130.0);
exchangeRates.put("AUD", 1.5);
exchangeRates.put("CAD", 1.3);
exchangeRates.put("CHF", 0.95);
exchangeRates.put("CNY", 6.8);
exchangeRates.put("INR", 80.0);
exchangeRates.put("SEK", 10.0);
}

private void addComponents() {
currencyFrom = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
currencyTo = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
amountField = new JTextField();
convertButton = new JButton("Convert");
resultLabel = new JLabel("Result will appear here");

add(new JLabel("From:"));
add(currencyFrom);
add(new JLabel("To:"));
add(currencyTo);
add(new JLabel("Amount:"));
add(amountField); 
add(convertButton);
add(resultLabel);
}

private void addEventHandlers() {
convertButton.addActionListener(new ActionListener() {
@Override
public void actionPerformed(ActionEvent e) {
try {
String fromCurrency = (String) currencyFrom.getSelectedItem();
String toCurrency = (String) currencyTo.getSelectedItem();
double amount = Double.parseDouble(amountField.getText());
double result = convertCurrency(fromCurrency, toCurrency, amount);
resultLabel.setText(String.format("Result: %.2f %s", result , toCurrency));
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(CurrencyConverter.this, "Please enter a valid amount","Error",JOptionPane.ERROR_MESSAGE);
}
}
}
);
}
private double convertCurrency(String from, String to, double amount) {
double rateFrom = exchangeRates.get(from);
double rateTo = exchangeRates.get(to);
return amount * (rateTo / rateFrom);
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() ->{
new CurrencyConverter().setVisible(true);
}
);
}
}