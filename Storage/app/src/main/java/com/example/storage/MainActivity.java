package com.example.storage;
import android.content.ContentValues;
import android.content.Context;
//import android.support.v4.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    EditText e1, e2, e3, e4;
    String d1, d2, d3, d4, del;
    Button b;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText) findViewById(R.id.i1);
        e2 = (EditText) findViewById(R.id.i2);
        e4 = (EditText) findViewById(R.id.i4);
        e3 = (EditText) findViewById(R.id.i3);
        t = (TextView) findViewById(R.id.t1);
        b = (Button) findViewById(R.id.b1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d1 = e1.getText().toString();
                d2 = e2.getText().toString();
                d3 = e3.getText().toString();
                d4 = e4.getText().toString();
                mydbhandler handler = new mydbhandler(MainActivity.this);
                handler.addemp(d1, d2, d3, d4);
                e1.setText(" ");
                e2.setText(" ");
                e3.setText(" ");
                e4.setText(" ");
            }
        });
    }

    public void delete(View view) {
        del = e1.getText().toString();
        mydbhandler handler = new mydbhandler(this);
        handler.deleteUser(del);
    }

    public void loademp(View view) {
        mydbhandler dbHandler = new mydbhandler(this);
        t.setText(dbHandler.load());
    }
}