package com.example.ahar.aharfoodorderclientapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class SingleFoodActivity extends AppCompatActivity {

    //write code
    private String food_key=null;
    private DatabaseReference mDatabase,userData;
    private TextView singleFoodTitle,singleFoodDesc,singleFoodPrice;
    private ImageView singleFoodImage;
    private Button orderButton;
    private FirebaseAuth mAuth;
    private FirebaseUser current_user;
    private DatabaseReference mRef;
    private String food_name,food_price,food_desc,food_image;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_food);
        //write code
        food_key=getIntent().getExtras().getString("FoodId");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Item");
        singleFoodTitle=(TextView) findViewById(R.id.singleTitle);
        singleFoodDesc=(TextView) findViewById(R.id.singleDesc);
        singleFoodPrice=(TextView) findViewById(R.id.singlePrice);
        singleFoodImage=(ImageView) findViewById(R.id.singleImageView);

        mAuth=FirebaseAuth.getInstance();

        current_user=mAuth.getCurrentUser();
        userData= FirebaseDatabase.getInstance().getReference().child("User").child(current_user.getUid());
        mRef=FirebaseDatabase.getInstance().getReference().child("Order");

        mDatabase.child(food_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                food_name = (String) dataSnapshot.child("name").getValue();
                food_price = (String) dataSnapshot.child("price").getValue();
                food_desc = (String) dataSnapshot.child("desc").getValue();
                food_image = (String) dataSnapshot.child("image").getValue();

                singleFoodTitle.setText(food_name);
                singleFoodDesc.setText(food_desc);
                singleFoodPrice.setText(food_price);
                Picasso.with(SingleFoodActivity.this).load(food_image).into(singleFoodImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //
    }


    //write code


/*
    public void orderItemClicked(View view){
            final DatabaseReference newOrder = mRef.push();
            userData.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    newOrder.child("itemname").setValue(food_name);
                    newOrder.child("username").setValue(dataSnapshot.child("name").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                    newOrder.child("address").setValue(dataSnapshot.child("address").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                    newOrder.child("phone").setValue(dataSnapshot.child("phone").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(SingleFoodActivity.this, MenuActivity.class));
                            Toast.makeText(SingleFoodActivity.this, "Order succefully done", Toast.LENGTH_LONG).show();

                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


    }
    */

    public void orderItemClicked(View view){
        Intent loginIntent = new Intent(SingleFoodActivity.this,MainActivity.class);
        startActivity(loginIntent);
    }

    public void cancelItemClicked(View view){
        Intent loginIntent = new Intent(SingleFoodActivity.this,MenuActivity.class);
        startActivity(loginIntent);
    }

    public void orderCall(View view) {

        String number = "01709120378";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
    //
}
