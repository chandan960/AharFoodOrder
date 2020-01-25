package com.example.ahar.aharfoodorderproviderapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddFoodActivity extends AppCompatActivity {

    //write code
    private ImageButton foodImage;
    private static final int GALLREQ=1;
    private EditText name,desc,price;
    private Uri uri=null;
    private StorageReference storageReference = null;
    private DatabaseReference mRef;
    private FirebaseDatabase firebaseDatabase;
    private AlertDialog.Builder alert;



    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        //write code
        name=(EditText) findViewById(R.id.itemName);
        desc=(EditText) findViewById(R.id.itemDesc);
        price=(EditText) findViewById(R.id.itemPrice);
        storageReference= FirebaseStorage.getInstance().getReference();
        mRef=FirebaseDatabase.getInstance().getReference("Item");

        //
    }

    //write code
    public void imageButtonClicked(View view){
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,GALLREQ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLREQ && resultCode == RESULT_OK ){
            uri = data.getData();
            foodImage = (ImageButton) findViewById(R.id.foodImageButton);
            foodImage.setImageURI(uri);
        }
    }



    public void addItemButtonClicked(View view){


        alert=new AlertDialog.Builder(AddFoodActivity.this);
        alert.setTitle("Add Item");
        alert.setMessage("Do you want add this item ?");
        alert.setIcon(R.drawable.alert);
        alert.setCancelable(false);


        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final String name_text = name.getText().toString().trim();
                final String desc_text = desc.getText().toString().trim();
                final String price_text = price.getText().toString().trim();
                if(!TextUtils.isEmpty(name_text) && !TextUtils.isEmpty(desc_text) && !TextUtils.isEmpty(price_text)){
                    StorageReference filepath = storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadurl=taskSnapshot.getDownloadUrl();
                            Toast.makeText(AddFoodActivity.this,"Add food uploaded",Toast.LENGTH_LONG).show();
                            final DatabaseReference newPost = mRef.push();
                            newPost.child("name").setValue(name_text);
                            newPost.child("desc").setValue(desc_text);
                            newPost.child("price").setValue(price_text);
                            newPost.child("image").setValue(downloadurl.toString());


                        }
                    });
                }
                else{
                    Toast.makeText(AddFoodActivity.this,"Add food not uploaded",Toast.LENGTH_LONG).show();

                }

                finish();
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

    //
}
