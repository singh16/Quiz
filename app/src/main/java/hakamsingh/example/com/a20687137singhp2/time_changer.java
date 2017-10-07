package hakamsingh.example.com.a20687137singhp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hakam on 28-Oct-16.
 */

public class time_changer extends Activity{
static int timerUser=25;
    Button timeSet;
    EditText timeNo;
    String s;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changer_timer);
        timeSet = (Button) findViewById(R.id.button5);
        timeNo=(EditText) findViewById(R.id.editText2);

        timeSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s= timeNo.getText().toString();
                timerUser=Integer.parseInt(s);
                Intent i1 = new Intent(time_changer.this,LoginActivity.class);
                startActivity(i1);

            }
        });

    }
}
