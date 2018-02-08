/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(numberOfCoffees));
    }

    /**
     * This method returns summary of user order
     */
    private String createOrderSummary(int price){
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        return "Name : " + nameEditText.getText() + "\n" + "Quantity : " + numberOfCoffees + "\n" + "Total : " + NumberFormat.getCurrencyInstance().format(calculatePrice(numberOfCoffees)) + "\n" + "Thank You!";
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void incrementQuantity(View view) {
        numberOfCoffees++;
        displayQuantity(numberOfCoffees);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrementQuantity(View view) {
        if(numberOfCoffees > 0){
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    /**
     * This methods calculates the price of a number of coffees
     */
    private int calculatePrice(int quantity) {
        return quantity * 5;
    }

}
