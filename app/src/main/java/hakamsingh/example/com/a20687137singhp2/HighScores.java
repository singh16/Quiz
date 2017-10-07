package hakamsingh.example.com.a20687137singhp2;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HighScores extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor c;
    Button tv;
    long i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);
        tv = (Button) findViewById(R.id.button6);
        createDatabase();

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor ch1 = getAlldata();
                if (ch1.getCount() == 0) {
                    showmessage("Scores of Rounds", "Nothing Found FOR THIS USERNAME");
                    // write comment
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                // buffer.append("candName :"+ ch1.getString(0)+" ");
                while (ch1.moveToNext()) {

                    buffer.append("Round No :" + ch1.getString(1) + " ");
                    buffer.append(" Score :" + ch1.getString(2) + "\n");


                }
                showmessage("Scores of Rounds", buffer.toString());
            }

            });
    }
    /*public void onBackPressed() {

        Intent i1=new Intent(HighScores.this,MainActmain.class);
        startActivity(i1);
        finish();
    }*/


    protected void createDatabase()
    {
        db=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);

    }


    public void showmessage(String title, String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();


    }

    public Cursor getAlldata()
    {
        Cursor ch = db.rawQuery("select * from scoreOfAll where candName=?",new String[] {LoginActivity.email});
        return ch;
    }

}
