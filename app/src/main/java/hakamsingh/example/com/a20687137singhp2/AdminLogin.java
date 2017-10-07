package hakamsingh.example.com.a20687137singhp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AdminLogin extends AppCompatActivity {

    Button check;
    EditText passwordcheck;

    //private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);

        check = (Button) findViewById(R.id.checkpass);
        passwordcheck = (EditText) findViewById(R.id.pass);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                display();
            }
        });
    }

    public void display() {

        String toastMessage = passwordcheck.getText().toString();

        if(toastMessage.equals("Waterloo")) {

            Intent i1 = new Intent(AdminLogin.this, admin_screen.class);
            startActivity(i1);
            //  Toast.makeText(this, "Correct Password", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong Password..!!", Toast.LENGTH_SHORT).show();
        }
    }
}


