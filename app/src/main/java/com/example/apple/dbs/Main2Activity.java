package com.example.apple.dbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Apple on 7/29/16.
 */
public class Main2Activity extends AppCompatActivity {

    private Intent intent;
    private Bundle bundle;
    int[] hist;
    int answer;
    String answername;
    Button bt1,bt2;
    TextView tv2,tv3,tv4,tv5,tv6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        intent = this.getIntent();
        bundle = intent.getExtras();

        hist = bundle.getIntArray("hist");
        answer = bundle.getInt("ans");
        answername = bundle.getString("answer");

        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);
        tv4 = (TextView)findViewById(R.id.textView4);
        tv5 = (TextView)findViewById(R.id.textView5);
        tv6 = (TextView)findViewById(R.id.textView6);

        tv2.setText("INTJ："+hist[0]+"人");
        tv3.setText("INFT："+hist[1]+"人");
        tv4.setText("ESTJ："+hist[2]+"人");
        tv5.setText("ESFP："+hist[3]+"人");
        tv6.setText("INTP："+hist[4]+"人");


        bt1 = (Button)findViewById(R.id.button);
        bt2 = (Button)findViewById(R.id.button2);

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                hist[answer]++;
                bundle.putIntArray("hist2", hist); //回傳hist
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                Main2Activity.this.finish();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //若是放棄則保持原本hist的值
                bundle.putIntArray("hist2", hist); //回傳hist
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                Main2Activity.this.finish();
            }
        });



    }



}