package hakamsingh.example.com.a20687137singhp2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class QuizActivity extends AppCompatActivity {


    boolean tesths=false;
    int qid=0;
    TextView tv,tv2,tv3,tv4,tv5;
    RadioGroup rg;
    Button btn;
    //MediaPlayer mp;
    ProgressBar pb;
    long cnt;
    int scr,nscr,questions=0;
    private SQLiteDatabase db;
    private static final String x="SELECT * FROM questions";
    private Cursor c;
    CountDownTimer timer;
    TextView dispname;
    String n;
    int done[]=new int[11];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        tv=(TextView)findViewById(R.id.textView1); //For displaying question
        tv2=(TextView)findViewById(R.id.textView2); // For Scoring
        tv3=(TextView)findViewById(R.id.textView3); // timer count
        tv4=(TextView)findViewById(R.id.textView4); // Showing in text timming remaining and further set to timer up
        tv5=(TextView)findViewById(R.id.textView10);
        rg=(RadioGroup)findViewById(R.id.radioGroup1);
        btn=(Button)findViewById(R.id.button1);





        pb=(ProgressBar)findViewById(R.id.progressBar1);
        pb.setProgress(100);
        //Bundle data=getIntent().getExtras();
        //n=data.getString("Name");
        //tv.setText("");
        tv5.setText("Number of Questions Left out of 5 : "+questions);
        openDatabase(); // opening the database , the function is written in this file only
        c=db.rawQuery(x, null);
        cnt =c.getCount();
        String h=String.valueOf(cnt);
        c.moveToFirst();
        getQuestion();
        tv2.setText("Score: 0/5");
        startCount();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Toast t2  =Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT);
                final Toast t3 = Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT);
                int res=rg.getCheckedRadioButtonId();


                switch(res)
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Select atleast one option", Toast.LENGTH_SHORT).show();

                        return;
                }
                if(res==Integer.parseInt(c.getString(6)))
                {



                    t2.show();
                    scr=scr+1;
                    tv2.setText("Score: "+scr+"/5");
                    for (int i = 0; i < rg.getChildCount(); ++i) {

                        if (i == res) {

                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.GREEN);
                        }}
                }
                else
                {
                    t3.show();
                    for (int i = 0; i < rg.getChildCount(); ++i) {

                        if (i ==res) {

                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.RED);
                        }

                        if (i == Integer.parseInt(c.getString(6))) {
                            ((RadioButton) rg.getChildAt(i))
                                    .setTextColor(Color.GREEN);

                        }
                    }
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        t2.cancel();
                        t3.cancel();
                if(questions<5)
                {
                    //c.moveToNext();
                    rg.removeAllViews();
                    getQuestion();
                    timer.cancel();
                    pb.setProgress(100);
                    startCount();
                }
                else
                { //qid=0;
                    questions=0;
                    c.moveToFirst();
                  final Toast t1= Toast.makeText(getApplicationContext(), "You have completed the quiz", Toast.LENGTH_LONG);
                    t1.show();
                    timer.cancel();
                    Intent i=new Intent(getApplicationContext(),finalactivity.class);
                    i.putExtra("score", scr);
                    i.putExtra("Name", n);
                    startActivity(i);
                }
                    }
                }, 700);
            }
        });






   /*     btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Question was not answered", Toast.LENGTH_SHORT).show();
                tv2.setText("Score: "+scr);
                if(questions<10)
                {
                    //c.moveToNext();
                    rg.removeAllViews();
                    getQuestion();
                    timer.cancel();
                    pb.setProgress(100);
                    startCount();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "You have completed the quiz", Toast.LENGTH_LONG).show();
                    timer.cancel();
                    Intent i=new Intent(getApplicationContext(),finalactivity.class);
                    i.putExtra("score", scr);
                    i.putExtra("Name", n);
                    startActivity(i);
                }
            }
        });*/




    }

    protected void openDatabase() {
        db = openOrCreateDatabase("QuizDB.db", Context.MODE_PRIVATE, null);
    }
    protected void getQuestion()
    {
        /*qid=qid+1;

        c.moveToNext();
        tv.setText(c.getString(1));
        for(int i=0;i<=3;i++)
        {
            RadioButton rb=new RadioButton(this);
            rb.setId(i);
            rb.setText(c.getString(i+2));

            rg.addView(rb);
        }
        rg.clearCheck(); */

        boolean f=false;int qid;
        do {
            qid=(int) Math.floor(Math.random()*cnt);
            for(int i=0;i<questions;i++)
            {
                if(qid==done[i])
                {
                    f=true;
                    break;
                }
                else{f=false;}
            }
        } while (f);

        c.moveToFirst();
        boolean flag=true;
        while (flag) {
            if(qid!=Integer.parseInt(c.getString(0)))
            {
                if(!c.isLast())
                    c.moveToNext();
                else
                    c.moveToFirst();
            }
            else
                flag=false;
        }

        tv.setText(c.getString(1));





        for(int i=0;i<=3;i++)
         {
           RadioButton rb=new RadioButton(this);
            rb.setId(i);
            rb.setText(c.getString(i+2));
            rg.addView(rb);
           // rb.setTextColor(Color.RED);

        }

        rg.clearCheck();

        done[questions]=qid;
        tv5.setText("Number of questions attempted out of 5 : "+questions);
        questions++;
    }
    protected void startCount() {
        tv4.setText("Seconds Remaining");
        btn.setText("SUBMIT");
        timer=new CountDownTimer(time_changer.timerUser*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv3.setText("" + millisUntilFinished / 1000);
                nscr=(int)millisUntilFinished/2000;
                if(time_changer.timerUser >=10 && time_changer.timerUser <=13)
                pb.setProgress(pb.getProgress()- 11);
               else if(time_changer.timerUser >=14 && time_changer.timerUser <=17)
                    pb.setProgress(pb.getProgress()- 7);
               else if(time_changer.timerUser >=18 && time_changer.timerUser <=22)
                    pb.setProgress(pb.getProgress()- 5);
               else if(time_changer.timerUser >=23 && time_changer.timerUser <=26)
                    pb.setProgress(pb.getProgress()- 4);
                else if(time_changer.timerUser >=27 && time_changer.timerUser <=30)
                    pb.setProgress(pb.getProgress()- 3);
            }

            public void onFinish() {
                pb.setProgress(0);
                tv3.setText("");
                tv4.setText("Sorry...Time Up!");

                Toast.makeText(getApplicationContext(), "Question was not answered", Toast.LENGTH_SHORT).show();
                tv2.setText("Score: "+scr);
                if(questions<5)
                {
                    //c.moveToNext();
                    rg.removeAllViews();
                    getQuestion();
                    timer.cancel();
                    pb.setProgress(100);
                    startCount();
                }
                else
                {//qid=0;
                    questions=0;
                    c.moveToFirst();
                   final Toast t5= Toast.makeText(getApplicationContext(), "You have completed the quiz", Toast.LENGTH_LONG);
                    t5.show();
                    timer.cancel();
                    Intent i=new Intent(getApplicationContext(),finalactivity.class);
                    i.putExtra("score", scr);
                    i.putExtra("Name", n);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            t5.cancel();
                        }
                    }, 400);
                    startActivity(i);

                }
                //btn.setClickable(false);
                //btn.setText("Move to next");

                // getQuestion();
            }
        }

                .start();
    }

}

