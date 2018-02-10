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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 0;
    LinearLayout primarylinearLayout;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        primarylinearLayout = (LinearLayout) findViewById(R.id.primary_linear_layout);
        orderButton = (Button) findViewById(R.id.order_button);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        primarylinearLayout.addView(createNewTextView("Total " + NumberFormat.getCurrencyInstance().format(calculatePrice(numberOfCoffees)) + "\n" + "Thankyou!") , 6);
    }

    /**
     * This function creates a new text view
     */
    private TextView createNewTextView(String text){
        TextView t = new TextView(this);
        t.setText(text);
        t.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        t.setPadding((int)getResources().getDimension(R.dimen.justJavaPadding) , (int)getResources().getDimension(R.dimen.justJavaPadding) , 0 , 0);
        return t;
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
     * This methods calculates the price of a number of coffees
     */
    private int calculatePrice(int quantity)
    {
        return quantity * 5;
    }

}
