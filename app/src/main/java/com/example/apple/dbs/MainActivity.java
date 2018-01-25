package com.example.apple.dbs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3;
    RadioButton rb1,rb2;
    RadioGroup rg;
    TextView tv;
    int [] select = {-1,-1,-1,-1,-1,-1};  //紀錄各題選的答案，-1代表未選,0~2代表三選項
    int page = 0;        //紀錄目前進行哪一題(0,1,2,3)
    int b3mode = 0; //紀錄目前第三個按紐在哪個狀態 (0->作答, 1->輸出結果)
    String [] question = {"我傾向於何處得到力量?","當我參加一個社交聚會時...","下列哪一個比較吸引人？","在聚會中，我通常："
    ,"我相信","我是哪種人？"};
    String [][] choice = {{"別人","我自己的想法"},{"我是會最晚離開的那一個","我疲倦了就會先回家"},{"與我的情人參加社交活動","在家看電視並享用食物"},{"很健談","較安靜"}
    ,{"我的直覺","我的觀察與經驗"},{"整體>細節","細節>整體"}};
    String [] answer = {"INTJ","INFT","ESTJ","ESFP","INTP"};
    int ans=0;
    int [] hist = {0,0,0,0,0};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        rb1 = (RadioButton)findViewById(R.id.radioButton1);
        rb2 = (RadioButton)findViewById(R.id.radioButton2);
        // rb3 = (RadioButton)findViewById(R.id.radioButton3);
        rg = (RadioGroup)findViewById(R.id.radioGroup);

        b1.setOnClickListener(buttonclick1);
        b2.setOnClickListener(buttonclick2);
        b3.setOnClickListener(buttonclick3);
        b2.setEnabled(false);

        rb1.setOnClickListener(radiobuttonclick1);
        rb2.setOnClickListener(radiobuttonclick2);
        // rb3.setOnClickListener(radiobuttonclick3);

        tv.setText(question[page]);
        rb1.setText(choice[page][0]);
        rb2.setText(choice[page][1]);
        // rb3.setText(choice[page][2]);
        rg.clearCheck();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==3&&resultCode==RESULT_OK)
        {
            int hist2[] = data.getExtras().getIntArray("hist2");
            for(int i = 0;i<5; i++)
                hist[i] = hist2[i];
            b3mode = 0;
            b1.setVisibility(View.VISIBLE);
            b3.setVisibility(View.VISIBLE);
            rg.setVisibility(View.VISIBLE);
            b2.setText("確認");
            b2.setEnabled(false);

            select[0]=-1;select[1]=-1;select[2]=-1;select[3]=-1;select[4]=-1;select[5]=-1;
            page = 0;
            tv.setText(question[page]);
            rb1.setText(choice[page][0]);
            rb2.setText(choice[page][1]);
            // rb3.setText(choice[page][2]);
            rg.clearCheck();
        }
    }



    private Button.OnClickListener buttonclick3 = new Button.OnClickListener() { //往後翻
        public void onClick(View v) {

            page = (page+1)%6;
            tv.setText(question[page]);
            rb1.setText(choice[page][0]);
            rb2.setText(choice[page][1]);
            // rb3.setText(choice[page][2]);
            rg.clearCheck();

            if (select[page] == 0)
                rb1.setChecked(true);
            else if (select[page] == 1)
                rb2.setChecked(true);
            // else if(select[page] == 2)
            //    rb3.setChecked(true);

        }
    };

    private Button.OnClickListener buttonclick1 = new Button.OnClickListener() { //往前翻
        public void onClick(View v) {

            page = (page+5)%6;
            tv.setText(question[page]);
            rb1.setText(choice[page][0]);
            rb2.setText(choice[page][1]);
            // rb3.setText(choice[page][2]);
            rg.clearCheck();

            if (select[page] == 0)
                rb1.setChecked(true);
            else if (select[page] == 1)
                rb2.setChecked(true);
            // else if(select[page] == 2)
            //    rb3.setChecked(true);
        }
    };

    private Button.OnClickListener buttonclick2 = new Button.OnClickListener() {
        public void onClick(View v) {

            if(b3mode==0)
            {
                b1.setVisibility(View.INVISIBLE);
                b3.setVisibility(View.INVISIBLE);
                rg.setVisibility(View.INVISIBLE);
                b2.setText("再玩一次");
                b3mode = 1;


                if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 0)
                    ans = 0;
                else if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 1 || select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 2 )
                    ans = 1;
                else if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 3 || select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 4 )
                    ans = 2;
                else if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 5 || select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 6 )
                    ans = 3;
                else if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 7 || select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 8 )
                    ans = 4;
                else if(select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 9 || select[0]+select[1]+select[2]+select[3]+select[4]+select[5] == 10 )
                    ans = 5;

                tv.setText("Your Personality Type is: "+answer[ans]);

            }

            else if(b3mode==1)
            {

                /*b3mode = 0;
                b1.setVisibility(View.VISIBLE);
                b3.setVisibility(View.VISIBLE);
                rg.setVisibility(View.VISIBLE);
                b2.setText("確認");
                b2.setEnabled(false);

                select[0]=-1;select[1]=-1;select[2]=-1;select[3]=-1;
                page = 0;
                tv.setText(question[page]);
                rb1.setText(choice[page][0]);
                rb2.setText(choice[page][1]);
                rb3.setText(choice[page][2]);
                rg.clearCheck();*/

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putIntArray("hist", hist); //將hist傳出
                bundle.putInt("ans", ans); //將ans傳出
                bundle.putString("answer", answer[ans]); //將answer[ans]傳出
                intent.putExtras(bundle);
                startActivityForResult(intent, 3);


            }

        }
    };


    private RadioButton.OnClickListener radiobuttonclick1 = new RadioButton.OnClickListener() {
        public void onClick(View v) {
            select[page] = 0;
            if(select[0]!= -1 && select[1]!= -1 && select[2]!= -1 && select[3]!= -1 && select[4]!= -1
                    && select[5]!= -1)
                b2.setEnabled(true);

        }
    };

    private RadioButton.OnClickListener radiobuttonclick2 = new RadioButton.OnClickListener() {
        public void onClick(View v) {
            select[page] = 1;
            if(select[0]!= -1 && select[1]!= -1 && select[2]!= -1 && select[3]!= -1 && select[4]!= -1
                    && select[5]!= -1)
                b2.setEnabled(true);


        }
    };
/*
    private RadioButton.OnClickListener radiobuttonclick3 = new RadioButton.OnClickListener() {
        public void onClick(View v) {
            select[page] = 2;
            if(select[0]!= -1 && select[1]!= -1 && select[2]!= -1 && select[3]!= -1)
                b2.setEnabled(true);

        }
    };
*/





}