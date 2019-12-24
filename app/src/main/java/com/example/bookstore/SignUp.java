package com.example.bookstore;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
    }

    public void signup_func(View view){
        SQLiteDatabase MyDatabase= openOrCreateDatabase("info",MODE_PRIVATE,null);
        MyDatabase.execSQL("CREATE TABLE IF NOT EXISTS user_info(username VARCHAR ,password VARCHAR,name VARCHAR,PRIMARY KEY(username));");
        EditText username_edittext=(EditText)findViewById(R.id.username_signup);
        String username=username_edittext.getText().toString();
        String password=((EditText)findViewById(R.id.password_signup)).getText().toString();
        String re_password=((EditText)findViewById(R.id.re_password_singup)).getText().toString();
        String name=((EditText)findViewById(R.id.name_signup)).getText().toString();
        Cursor resultSet=MyDatabase.rawQuery("SELECT username from user_info where username='"+username+"';",null);
        if(resultSet.getCount()!=0)
        {
            Toast.makeText(getApplicationContext(),"Username already taken",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(password.length()>=8) {
                if (!password.equals(re_password)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyDatabase.execSQL("INSERT INTO user_info VALUES('"+username+"','"+password+"','"+name+"');");
                    Toast.makeText(getApplicationContext(), "User created successfully", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(this,Login.class);
                    MyDatabase.close();
                    resultSet.close();
                    startActivity(i);
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Password should be atleast 8 letters",Toast.LENGTH_SHORT).show();;
            }
            //Toast.makeText(getApplicationContext(),"Username not already taken",Toast.LENGTH_SHORT).show();
        }

    }


}
