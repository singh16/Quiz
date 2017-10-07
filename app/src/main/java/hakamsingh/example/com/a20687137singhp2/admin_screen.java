package hakamsingh.example.com.a20687137singhp2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by hakam on 15-Oct-16.
 */

public class admin_screen extends Activity {
    Button add,timer,t1; //dont confuse times button with timer as it is the high score adder button Hakam
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_admin);



        add = (Button) findViewById(R.id.adbtn);
        timer = (Button) findViewById(R.id.timset);
        t1 = (Button) findViewById(R.id.button4);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(admin_screen.this,admin_operation.class);
                startActivity(i1);

            }
        });

       t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(admin_screen.this,time_changer.class);
                startActivity(i3);

            }
        });

        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i2 = new Intent(admin_screen.this,show_scores.class);
                startActivity(i2);


            }
        });

    }
    public void onBackPressed() {

        Intent i1=new Intent(admin_screen.this,LoginActivity.class);
        startActivity(i1);
        finish();
    }

}
