package hakamsingh.example.com.a20687137singhp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;





public class MainActmain extends AppCompatActivity {
    private Button btnLogout,btn2,btn3;
    TextView dispname;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_l);



        dispname = (TextView)findViewById(R.id.textView);
        dispname.setText(LoginActivity.email);


        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);

        session = new Session(this);
        if(!session.loggedin()){
            logout();
        }
        btnLogout = (Button)findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(), GameStart.class);
                //i.putExtra("Name", n);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(),HighScores.class);
                //i.putExtra("Name", n);
                startActivity(i);
            }
        });
    }

    private void logout(){
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(MainActmain.this, LoginActivity.class));
    }

}
