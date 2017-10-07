package hakamsingh.example.com.a20687137singhp2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class finalactivity extends AppCompatActivity {
     String ename=LoginActivity.email;
   int round;
    TextView tv,tv2;
    private SQLiteDatabase db;
    private Cursor c;
    Button btn;
    int s;String n,query;

    private static final String x="SELECT * FROM scores";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        tv=(TextView)findViewById(R.id.textView3);
        tv2=(TextView)findViewById(R.id.textView2);
        btn=(Button)findViewById(R.id.button1);
        Bundle data=getIntent().getExtras();
        s=data.getInt("score");
        n=data.getString("Name");


        tv.setText("Score: "+s+"/5");
       // createdatabase();
        createDatabase2();
        findRoundNumber();
       ++round;
       insertDB2();

     /*   c=db.rawQuery(x, null);
        if(!c.moveToFirst())
        {
            query="INSERT INTO scores VALUES('"+n+"','"+s+"')";
            db.execSQL(query);
            tv2.setText("You got a high score!");
        }
        else
        {
            c.moveToLast();
            if(s>Integer.parseInt(c.getString(1)))
            {
                query="UPDATE scores SET name='"+n+"', score='"+s+"' where score='"+c.getString(1)+"'";
                db.execSQL(query);
                tv2.setText("You got a high score!");
            }
        }*/
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent i=new Intent(getApplicationContext(), MainActmain.class);
                i.putExtra("Name", n);
                startActivity(i);
            }
        });
    }

//////////////TRY////////////

    protected void createDatabase2()
    {
        db=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scoreOfAll(candName VARCHAR, roundNo NUMBER, scoreCand NUMBER)");

    }

   protected void findRoundNumber()
   {


        Cursor cursor=db.rawQuery("SELECT * FROM scoreOfALL WHERE candName=?",new String[] {ename});
       round=cursor.getCount();



   }
    protected void insertDB2()
    {
        //tv.setText("Getting the quiz ready...");

            String query="INSERT INTO scoreOfAll(candName,roundNo,scoreCand) values('"+ename+"','"+round+"','"+s+"');)";

            db.execSQL(query);
       Toast.makeText(getApplicationContext(), "Score added to database!", Toast.LENGTH_SHORT).show();


    }


 ////////////// TRY////////////


    public void onBackPressed() {

        Intent i1=new Intent(finalactivity.this,MainActmain.class);
        startActivity(i1);
        finish();
    }

    protected void createdatabase()
    {
        db=openOrCreateDatabase("ScoresDB.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS scores(name VARCHAR, score NUMBER)");
    }

}
