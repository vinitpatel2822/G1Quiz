package com.mac.group7.g1quiz;
import com.mac.group7.g1quiz.*;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.app.Activity;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.*;
import static android.content.ContentValues.TAG;

public class QuizQuestion extends Activity {

    public boolean isRunning = false;
    public PrintWriter out;
    public BufferedReader inA;
    public boolean readQue = true;
    MainQuiz mQ = new MainQuiz();
    public String Que;
    public Socket socket = new Socket();
    private boolean connectionOk = false;
    public TextView txtV;
    private NumberPicker ansPicker;
    public int selectedValued;
    private Button btnSubmit;
    //private LinearLayout pickerLayout;
    //FragmentManager fM = getSupportFragmentManager();
    //FragmentTransaction fT = fM.beginTransaction();
    //QuestionFragment qF = new QuestionFragment();
    public int firstTime;
    //public MessageCallback  listener = null
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);
        btnSubmit=(Button) findViewById(R.id.BtnSubmit);
        Bundle bundle = getIntent().getExtras();
        firstTime = bundle.getInt("firstTime");
        txtV = (TextView) findViewById(R.id.txtQuestion);
        ansPicker = (NumberPicker) findViewById(R.id.ansPicker);

       // fT.add(R.id.fragmentContainer,qF).commit();


     //  InetSocketAddress addr = new InetSocketAddress("137.207.82.53", 12121);
      // new ConnectToIpTask().execute(addr);
        if(firstTime==1)
        {
            firstQuestion();
            firstTime++;
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedValued++;
                String result= Integer.toString(selectedValued);
                out.println(result);
                System.out.println("Value Of FirstTime : "+firstTime);
                if(firstTime>1)
                {

                    Toast.makeText(QuizQuestion.this,"Call",Toast.LENGTH_SHORT).show();


                    nextQuestion();
                }


                     /*   Intent refresh = new Intent(QuizQuestion.this, QuizQuestion.class);
                        startActivity(refresh);
                        finish();*/
            }
        });
        /*  outQ = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            outQ.println("q");
            outQ.flush();*/
   /*     btnSubmit=(Button) findViewById(R.id.BtnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result= Integer.toString(selectedValued);
                out.println(result);
            }
        });*/


    }
    public void firstQuestion () {
       if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            //   if(firstTime==1) {
            socket.connect(new InetSocketAddress("137.207.82.53", 12121), 5000);
            inA = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            //   Log.d(TAG,"First Line:" + inA.readLine());
            out.println("s");
            // Log.d(TAG,"Second Line:" + inA.readLine());
            out.println("Umang");
            String st;
            String st1 = new String();

            int i;

            for (i = 0; i < 2; i++) {
                inA.readLine();
            }
            for (i = 0; i < 2; i++) {
                //st = inA.readLine();
                st = inA.readLine();
                st1 = st1.concat(st);
                Log.d(TAG, "Question: " + st1);
                // System.out.println(st);
                st = null;
            }
            //  qF.queTxt.setText(st1);
            txtV.setText(st1);

            int size = 4;
            String[] values = new String[size];
            for (i = 0; i < size; i++) {
                values[i] = inA.readLine();
                System.out.println("Options :"+ values[i]);
            }
            System.out.println("Select Option : "+inA.readLine());


            ansPicker.setDisplayedValues(values);
            ansPicker.setMinValue(0);
            ansPicker.setMaxValue(size - 1);
            ansPicker.setWrapSelectorWheel(false);
            // int selectedValue = ansPicker.getValue();

            ansPicker.setOnValueChangedListener(new OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    selectedValued = ansPicker.getValue();
                   // Toast.makeText(QuizQuestion.this, Integer.toString(selectedValued), Toast.LENGTH_LONG).show();
                }
            });

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }
        return;
    }
    public void nextQuestion()
    {
        firstTime++;
        String st;
        String st1 = new String();

       try {

           int i;
           for(i=0;i<4;i++)
           {
               System.out.println("First For Loop : "+ inA.readLine());
           }
           for (i = 0; i < 2; i++) {
               st = inA.readLine();
               st1 = st1.concat(st);
               Log.d(TAG, "Question: " + st1);
               // System.out.println(st);
               st = null;
           }
           txtV.setText(st1);
           int size = 4;
           String[] values = new String[size];
           for (i = 0; i < size; i++) {
               values[i] = inA.readLine();
               System.out.println("Options :"+values[i]);
           }
           System.out.println("Select Option : "+inA.readLine());
           //ansPicker = (NumberPicker) LayoutInflater.from(this).inflate(R.layout.picker, null, false);

           ansPicker.setDisplayedValues(values);
           ansPicker.setMinValue(0);
           ansPicker.setMaxValue(size - 1);
           ansPicker.setWrapSelectorWheel(false);
           // int selectedValue = ansPicker.getValue();
           ansPicker.setOnValueChangedListener(new OnValueChangeListener() {
               @Override
               public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                   selectedValued = ansPicker.getValue();
                   // Toast.makeText(QuizQuestion.this, Integer.toString(selectedValued), Toast.LENGTH_LONG).show();
               }
           });

           //firstTime++;
           return;
       }
       catch (Exception ex)
       {
           ex.printStackTrace();
       }


    }

}
