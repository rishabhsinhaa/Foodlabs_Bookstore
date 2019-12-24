package com.example.bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


    }

    public void signup_fn(View view){
        Intent i = new Intent(this,SignUp.class);
        startActivity(i);
    }

    public void login_fn(View view){
        String username=((EditText)findViewById(R.id.username_input)).getText().toString();
        String password=((EditText)findViewById(R.id.password_input)).getText().toString();
        SQLiteDatabase MyDatabase=openOrCreateDatabase("info",MODE_PRIVATE,null);
        Cursor resultSet=MyDatabase.rawQuery("SELECT password from user_info where username='"+username+"';",null);
        resultSet.moveToFirst();
        if(resultSet.getCount()==0) {
            Toast.makeText(getApplicationContext(), "Username or password is incorrect", Toast.LENGTH_SHORT).show();
        }
        else{
            String database_password=resultSet.getString(0);
            if(!password.equals(database_password)){
                Toast.makeText(getApplicationContext(),"Username or password is incorrect",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(),"Correct detauls",Toast.LENGTH_SHORT).show();
            }
        }
        resultSet.close();
        MyDatabase.close();
    }

}
