/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 1;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderButton = (Button) findViewById(R.id.order_button);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        String name = nameEditText.getText().toString();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        String price = NumberFormat.getCurrencyInstance().format(calculatePrice(hasWhippedCream , hasChocolate));
        String subject = getResources().getString(R.string.email_subject) + name;
        String body = createOrderSummary(name , price , hasWhippedCream , hasChocolate);
        sendEmail(subject , body);
    }


    /**
     * This function sends ordery summary as an email
     */
    private void sendEmail(String subject , String body){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT , subject);
        intent.putExtra(Intent.EXTRA_TEXT , body);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }


    /**
     * This method returns summary of user order
     */
    private String createOrderSummary(String name , String price , boolean hasWhippedCream , boolean hasChocolate){
        return getResources().getString(R.string.order_summary) + "\n\n" + getResources().getString(R.string.name) + " : " + name + "\n" + getResources().getString(R.string.add) + getResources().getString(R.string.whipped_cream) + "?"  + hasWhippedCream + "\n" + getResources().getString(R.string.add) + getResources().getString(R.string.chocolate) + "?"  + hasChocolate + "\n" + getResources().getString(R.string.quantity) + " : " + numberOfCoffees + "\n" + getResources().getString(R.string.total) + " : " + price + "\n" + getResources().getString(R.string.thank_you) + "\n---------";
    }


    /**
     * This method is called when the + button is clicked.
     */
    public void incrementQuantity(View view) {
        if(numberOfCoffees < 100) {
            numberOfCoffees++;
            displayQuantity(numberOfCoffees);
        }
        else{
            Toast.makeText(this , getResources().getString(R.string.more_than_100_toast) , Toast.LENGTH_LONG).show();
        }
    }


    /**
     * This method is called when the - button is clicked.
     */
    public void decrementQuantity(View view) {
        if(numberOfCoffees > 1){
            numberOfCoffees--;
            displayQuantity(numberOfCoffees);
        }
        else{
            Toast.makeText(this , getResources().getString(R.string.less_than_1_toast) , Toast.LENGTH_LONG).show();
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
    private int calculatePrice(boolean hasWippedCream , boolean hasChocolate) {
        int basePrice = 5;
        if(hasWippedCream)
            basePrice += 1;    //whippedCream costs $1
        if(hasChocolate)
            basePrice += 2;    //chocolate costs $2
        return basePrice * numberOfCoffees;
    }

}
