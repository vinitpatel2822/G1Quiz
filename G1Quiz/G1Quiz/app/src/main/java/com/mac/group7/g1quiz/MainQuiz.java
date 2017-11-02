package com.mac.group7.g1quiz;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;

public class MainQuiz extends Activity {

    private Button btnQuiz;
    private Button btnHelp;
    private Button btnQuit;
    public boolean isRunning = false;
    public static final  String SERVER_IP = "192.168.178.11";
    public static final int SERVERPORT = 5000;
    public Socket socket =null;
    Thread ClientThread = null;
    String st = null;
    public PrintWriter out;
    //private volatile Socket socket = null;
    private boolean connectionOk = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnQuiz=(Button) findViewById(R.id.btnStartQuiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(MainQuiz.this, QuizQuestion.class);

                int tmp=1;
                i.putExtra("firstTime",tmp);
                startActivity(i);

                finish();
            }
        });

        btnHelp=(Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent i = new Intent(MainQuiz.this, HelpActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        });

        btnQuit=(Button) findViewById(R.id.btnExit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                moveTaskToBack(true);


            }
        });
    }


}


