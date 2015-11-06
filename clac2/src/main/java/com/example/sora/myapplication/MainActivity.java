package com.example.sora.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num1, num2;
    Button plus, sub, mul, div;
    TextView textResult;
    String n1, n2;
    Double result;


    Button[] nBtn = new Button[10];
    Integer[] nBtnID = {R.id.b0, R.id.b1, R.id.b2, R.id.b3, R.id.b4,
                            R.id.b5, R.id.b6, R.id.b7, R.id.b8, R.id.b9};

    MyButtonListener bListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);

        bListener = new MyButtonListener();

        plus = (Button)findViewById(R.id.plus);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);

        plus.setOnTouchListener(bListener);
        sub.setOnTouchListener(bListener);
        mul.setOnTouchListener(bListener);
        div.setOnTouchListener(bListener);

        textResult = (TextView)findViewById(R.id.result);

        for(int i=0; i<nBtnID.length; i++)
            nBtn[i] = (Button)findViewById(nBtnID[i]);

        for(int i=0; i<nBtnID.length; i++){
            final int index;
            index = i;

            nBtn[index].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(num1.isFocused()) {
                        n1 = num1.getText().toString() + nBtn[index].getText().toString();
                        num1.setText(n1);
                    } else if(num2.isFocused()) {
                        n2 = num2.getText().toString() + nBtn[index].getText().toString();
                        num2.setText(n2);
                    } else {
                        Toast.makeText(MainActivity.this, "Select EditText", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

    }

    class MyButtonListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            n1 = num1.getText().toString();
            n2 = num2.getText().toString();
            double dn1 = Double.parseDouble(n1);
            double dn2 = Double.parseDouble(n2);

            switch(v.getId()){
                case R.id.plus :
                    result = dn1 + dn2;
                    break;

                case R.id.sub :
                    result = dn1 - dn2;
                    break;

                case R.id.mul :
                    result = dn1 * dn2;
                    break;

                case R.id.div :
                    if(dn2 == 0)
                        Toast.makeText(MainActivity.this, "Divided by 0", Toast.LENGTH_SHORT).show();
                    else
                        result = dn1 / dn2;
                    break;
            }

            textResult.setText("계산 결과 : " + result.toString());

            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
