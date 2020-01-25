package com.example.ahar.aharfoodorderproviderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    //write code

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //write code

        //
    }


    //write code
    public void addFoodButtonClicked(View view){
        Intent addFoodIntent = new Intent(MainActivity.this,AddFoodActivity.class);
        startActivity(addFoodIntent);
    }
    public void viewOrders(View view){
        Intent orderFoodIntent = new Intent(MainActivity.this,OrderFoodActivity.class);
        startActivity(orderFoodIntent);
    }

    //
}
