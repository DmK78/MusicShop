package com.example.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userName");
        String goodsName = receivedOrderIntent.getStringExtra("goodsName");
        int quantity = receivedOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = receivedOrderIntent.getDoubleExtra("orderPrice", 0);
        double price = receivedOrderIntent.getDoubleExtra("price", 0);


        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Customer name: " + userName + "\n" + "Goods name: " + goodsName + "\n" + "Quantity: " + quantity + "\n" + "Price: " + price + "\n" + "Order price: " + orderPrice);
    }

    public void submitOrder(View view) {
    }
}
