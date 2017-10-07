package hakamsingh.example.com.a20687137singhp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button login, register,adlogin;
    private EditText etEmail, etPass;
    private DbHelper db;
    private Session session;
    static String email;
    //SharedPreferences sharedpreferences;
    //public static final String MyPREFERENCES="MyPrefs";
    //public static final String Name = "nameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DbHelper(this);
        session = new Session(this);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnReg);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        adlogin=(Button)findViewById(R.id.adlog);
        login.setOnClickListener(this);
        register.setOnClickListener(this);



        adlogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(LoginActivity.this, AdminLogin.class);
                startActivity(i1);
            }
        });

        if(session.loggedin()){

            startActivity(new Intent(LoginActivity.this,MainActmain.class));
            //HomeScreen
            finish();
        }
    }

    public void onBackPressed() {

        finish();
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            default:
        }
    }

    private void login(){
        email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if(db.getUser(email,pass)) {
            session.setLoggedin(true);

            //sharedpreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            //String val=sharedpreferences.getString(Name, null);

            if(email!=null)
            {
                Intent i=new Intent(getApplicationContext(), MainActmain.class);
                i.putExtra("Name", email);
                Intent i1=new Intent(getApplicationContext(), HighScores.class);
                i1.putExtra("Name", email);
                startActivity(i);
            }

            // startActivity(new Intent(LoginActivity.this, MainActmain.class));
            //finish();

            //MainActmain
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }
}

