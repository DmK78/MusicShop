package com.example.musicshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinnerAct;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.nameEditText);
        createSpinner();
        createMap();
    }


    void createSpinner() {
        spinnerAct = findViewById(R.id.spinner);
        spinnerAct.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Keyboard");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAct.setAdapter(spinnerAdapter);
    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("Guitar", 500.0);
        goodsMap.put("Drums", 1000.0);
        goodsMap.put("Keyboard", 1150.0);
    }


    public void increaseQuantity(View view) {
        TextView quantityTextview = findViewById(R.id.quantityTextView);
        quantity = quantity + 1;
        quantityTextview.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + price * quantity);

    }

    public void decreaseQuantity(View view) {
        TextView quantityTextview = findViewById(R.id.quantityTextView);
        quantity = quantity - 1;
        if (quantity < 0) quantity = 0;
        quantityTextview.setText("" + quantity);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + price * quantity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinnerAct.getSelectedItem().toString();
        price = (Double) goodsMap.get(goodsName);
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText("" + price * quantity);
        ImageView goodsImageView = findViewById(R.id.goodsView);

        switch (goodsName) {
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.fritur);
                break;
            case "Drums":
                goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "Keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.fritur);
                break;

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = price * quantity;
        order.price=price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName",order.userName);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity",order.quantity);
        orderIntent.putExtra("orderPrice",order.orderPrice);
        orderIntent.putExtra("price",order.price);

        startActivity(orderIntent);

    }
}
