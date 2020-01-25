package com.example.ahar.aharfoodorderproviderapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrderFoodActivity extends AppCompatActivity {

    //write code

    private RecyclerView mFoodList;
    private DatabaseReference mDatabase;
    private AlertDialog.Builder alert;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);
        //write code

        mFoodList=(RecyclerView) findViewById(R.id.orderLayout);
        mFoodList.setHasFixedSize(true);
        mFoodList.setLayoutManager(new LinearLayoutManager(this));
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Order");


        //


    }

    //write code

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Order , OrderViewHolder> FRBA = new FirebaseRecyclerAdapter<Order, OrderViewHolder>(

                Order.class,
                R.layout.singleorderlayout,
                OrderViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(OrderViewHolder viewHolder, Order model, int position) {
                viewHolder.setUserName(model.getUsername());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setAddress(model.getAddress());
                viewHolder.setItemName(model.getItemname());
            }
        };
        mFoodList.setAdapter(FRBA);
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder{

        View orderView;
        public OrderViewHolder(View itemView) {
            super(itemView);
            orderView=itemView;
        }

        public void setUserName(String username){
            TextView username_content = (TextView) orderView.findViewById(R.id.orderUserName);
            username_content.setText(username);
        }

        public void setPhone(String phone){
            TextView phone_content = (TextView) orderView.findViewById(R.id.orderUserPhone);
            phone_content.setText(phone);
        }
        public void setAddress(String address){
            TextView address_content = (TextView) orderView.findViewById(R.id.orderUserAddress);
            address_content.setText(address);
        }
        public void setItemName(String itemname){
            TextView itemname_content = (TextView) orderView.findViewById(R.id.orderItemName);
            itemname_content.setText(itemname);
        }

    }

    public void Delivery(View view){


        alert=new AlertDialog.Builder(OrderFoodActivity.this);
        alert.setTitle("Delivery");
        alert.setMessage("Delivery confirm");
        alert.setIcon(R.drawable.alert);
        alert.setCancelable(false);


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing
                //finish();








                Toast.makeText(getApplicationContext(), "Delivery Done", Toast.LENGTH_LONG).show();



            }
        });


        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
/*

        alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing
                Toast.makeText(OrderFood.this,"you have clicked cancel button",Toast.LENGTH_SHORT).show();
            }
        });

        */



        AlertDialog alert5Dialog = alert.create();
        alert5Dialog.show();



    }




    //
}
