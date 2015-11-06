package com.example.sora.myapplication2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, mail, dname, dmail;
    Button but;
    View dialogV, toastV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameEdit);
        mail = (EditText) findViewById(R.id.emailEdit);


        but = (Button) findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogV = (View) View.inflate(MainActivity.this, R.layout.dialog, null);
                AlertDialog.Builder dial = new AlertDialog.Builder(MainActivity.this);

                dname = (EditText) dialogV.findViewById(R.id.dialName);
                dmail = (EditText) dialogV.findViewById(R.id.dialEmail);

                dname.setText(name.getText().toString());
                dmail.setText(mail.getText().toString());

                dial.setTitle("사용자 정보 입력");
                dial.setView(dialogV);
                dial.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                name.setText(dname.getText().toString());
                                mail.setText(dmail.getText().toString());
                            }
                        });
                dial.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast toast = new Toast(MainActivity.this);
                                toastV = (View) View.inflate(MainActivity.this, R.layout.toast, null);

                                Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                                int xOff = (int) (Math.random() * display.getWidth());
                                int yOff = (int) (Math.random() * display.getHeight());

                                toast.setView(toastV);
                                toast.setGravity(Gravity.TOP | Gravity.LEFT, xOff, yOff);
                                toast.show();
                            }
                        });
                dial.show();

            }
        });
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
