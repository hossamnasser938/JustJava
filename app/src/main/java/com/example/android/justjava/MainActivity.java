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
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;
    private int numberOfOrders = 0;
    LinearLayout primaryLinearLayout;
    Button orderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        primaryLinearLayout = (LinearLayout) findViewById(R.id.primary_linear_layout);
        orderButton = (Button) findViewById(R.id.order_button);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        numberOfOrders++;
        primaryLinearLayout.addView(createNewTextView(createOrderSummary()) , primaryLinearLayout.getChildCount() - 1);
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
     * This method returns summary of user order
     */
    private String createOrderSummary(){
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        String name = nameEditText.getText().toString();
        String price = NumberFormat.getCurrencyInstance().format(calculatePrice(numberOfCoffees));
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        return "Order" + numberOfOrders + "Summary\n" + "\nName : " + name + "\nAdd whipped cream? "  + hasWhippedCream + "\nAdd chocolate? "  + hasChocolate + "\nQuantity : " + numberOfCoffees + "\nTotal : " + price + "\nThank You!\n---------";
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
    private int calculatePrice(int quantity) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        int price = quantity * 5;
        if(whippedCreamCheckBox.isChecked())
            price += quantity;    //whippedCream costs $1
        if(chocolateCheckBox.isChecked())
            price += quantity * 2;    //chocolate costs $2
        return price;
    }

}
