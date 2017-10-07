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
import android.widget.EditText;

/**
 * Created by hakam on 27-Oct-16.
 */

public class show_scores extends Activity {

    Button getS;
    String uname;
    private EditText uscore;
    private SQLiteDatabase db1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.scores_show);
        uscore = (EditText)findViewById(R.id.editText);
        getS = (Button) findViewById(R.id.button);

        createDatabase();
        getS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = uscore.getText().toString();
                Cursor ch1=getAlldata();
                if(ch1.getCount()==0)
                { showmessage("Scores of Rounds","Nothing Found FOR THIS USERNAME");
                    // write comment
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                // buffer.append("candName :"+ ch1.getString(0)+" ");
                while(ch1.moveToNext())
                {

                    buffer.append("roundNo :"+ ch1.getString(1)+" ");
                    buffer.append("scoreCand :"+ ch1.getString(2)+"\n");


                }
                showmessage("Scores of Rounds",buffer.toString());




            }
        });

    }
    protected void createDatabase()
    {
        db1=openOrCreateDatabase("Sall.db", Context.MODE_PRIVATE, null);

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
        Cursor ch = db1.rawQuery("select * from scoreOfAll where candName=?",new String[] {uname});
        return ch;
    }




    }
