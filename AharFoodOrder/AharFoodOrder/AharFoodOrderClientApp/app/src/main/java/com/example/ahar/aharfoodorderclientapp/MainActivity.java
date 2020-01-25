package com.example.ahar.aharfoodorderclientapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    //write code

    private EditText name,phone,email,pass,address;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder alert;

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //write code
        name=(EditText) findViewById(R.id.editName);
        phone=(EditText) findViewById(R.id.editPhone);
        email=(EditText) findViewById(R.id.editEmail);
        pass=(EditText) findViewById(R.id.editPass);
        address=(EditText) findViewById(R.id.editAddress);
        mAuth=FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("User");


        //
    }

    //write code

    public void signupButtonClicked(View view){

        alert=new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Registration");
        alert.setMessage("Registration confirm");
        alert.setIcon(R.drawable.alert);
        alert.setCancelable(false);


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final  String name_text = name.getText().toString().trim();
                final String phone_text = phone.getText().toString().trim();
                final  String email_text = email.getText().toString().trim();
                final String pass_text = pass.getText().toString().trim();
                final String address_text = address.getText().toString().trim();
                if (!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(phone_text) && !TextUtils.isEmpty(email_text) && !TextUtils.isEmpty(pass_text) && !TextUtils.isEmpty(address_text)){
                    mAuth.createUserWithEmailAndPassword(email_text,pass_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        // @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String user_id=mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user = mDatabase.child(user_id);
                                current_user.child("name").setValue(name_text);
                                current_user.child("phone").setValue(phone_text);
                                current_user.child("email").setValue(email_text);
                                current_user.child("password").setValue(pass_text);
                                current_user.child("address").setValue(address_text);
                                Toast.makeText(MainActivity.this,"Register done",Toast.LENGTH_LONG).show();
                                Intent login = new Intent(MainActivity.this,LoginActivity.class);
                                startActivity(login);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this,"Try again",Toast.LENGTH_LONG).show();
                }


            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //nothing
                dialog.cancel();
            }
        });




        AlertDialog alert5Dialog = alert.create();
        alert5Dialog.show();


    }
    public void signinButtonClicked (View view){
        Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }
    //
}
