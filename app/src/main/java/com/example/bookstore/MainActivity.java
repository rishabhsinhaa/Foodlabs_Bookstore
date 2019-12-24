package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login_layout(View view)
    {
        Intent i =new Intent(this,Login.class);
        startActivity(i);
    }

    public void browse_layout(View view){
        Intent i=new Intent(this,Browse.class);
        startActivity(i);
    }
}
