package com.example.agilesprintersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agilesprintersapp.Adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageTask;

import java.util.Calendar;
import java.util.HashMap;

public class Preview extends AppCompatActivity {
    private ImageButton btn_attach_pic;
    private String checker = "";
    private String myUrl = "";
    private StorageTask uploadTask;
    private Uri fileUri;
    public String userid;
    private String messageSenderID;
    private String messageReceiverID;
    Button Exit;
    Button btn_send;
    TextView caption;
    FirebaseUser fuser;
    MessageAdapter messageAdapter;
    ImageView imageview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);


        Exit = (Button) findViewById(R.id.close);
        btn_send =(Button)findViewById(R.id.button3);
        caption = (TextView) findViewById(R.id.Caption);

        imageview=findViewById(R.id.imageView56);
        Intent intent = getIntent();
        String image_path= intent.getStringExtra("imagePath");
        Uri fileUri = Uri.parse(image_path);
        imageview.setImageURI(fileUri) ;

       /* Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("resId");
            imageView.setImageResource(resId);
        }*/

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Preview.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*Uri a = fileUri;
                Intent i = new Intent(Preview.this, MessageActivity.class);
                i.putExtra("imagePath", a.toString());
                startActivity(i);*/

                //Display it in recycle view of message activity

            }
        });





    }

}
