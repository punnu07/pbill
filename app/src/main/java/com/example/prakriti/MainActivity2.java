package com.example.prakriti;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.widget.Toast;
import android.net.Uri;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View ;
import android.webkit.WebView ;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;

import android.webkit.WebViewClient;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.*;



public class MainActivity2 extends AppCompatActivity {

    private Object App;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_main2);

        Button startBtn = (Button) findViewById(R.id.sendEmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                sendEmail();

            }
        });



    }//end of onCreate





    protected void sendEmail() {
        File path=new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/prakriti/invoice.pdf");
        Uri myuri=Uri.fromFile(path);
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Prakriti Invoice");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
        emailIntent.putExtra(Intent.EXTRA_STREAM, myuri);
        try {
            //startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            startActivityForResult(Intent.createChooser(emailIntent, "Send mail..."), 800);
           Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity2.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }



    }//end of function



    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 800) {
            Intent intent = new Intent(context, MainActivity3.class);
            startActivity(intent);
            finish();

        }
    }//end of function




}// end of class







