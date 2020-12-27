package com.example.prakriti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    Button myButton;
    TextInputEditText til;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_main);
       addListenerOnButton();
    }


    public void addListenerOnButton() {
        final Context context = this;
        myButton= findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                try {
                    //createPdf();
                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }



                Intent intent = new Intent(context, MainActivity2.class);
                startActivity(intent);

            }

        });

    }  //end of function



    
    
    private void createFile() throws FileNotFoundException
    {


        final Context context = this;
        try {
            //File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Your directory name");
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "prakriti");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File filename = new File(folder, "a.txt");
            /*FileWriter fw=new FileWriter(filename);
            fw.append("hello");
            fw.flush();
            fw.close();
            */

            if(filename.exists())
            {
                filename.delete();
            }
            boolean b = filename.createNewFile();
            FileOutputStream fos = new FileOutputStream(filename);
            String str = "test data";
            fos.write(str.getBytes());
            fos.close();

            /*
            if(b)
            Toast.makeText(MainActivity.this, "file created", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "false", Toast.LENGTH_LONG).show();
            */

            String filePath=context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)+"/prakriti/a.txt";
            Toast.makeText(MainActivity.this, filePath, Toast.LENGTH_LONG).show();



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    


    
    

    private void createPdf() throws FileNotFoundException {
        PdfWriter pw;

        til=findViewById(R.id.ip);
        final Context context = this;
        try {


            //File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Your directory name");
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "prakriti");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File filename = new File(folder, "invoice.pdf");

            if(filename.exists())
            {
                filename.delete();
            }

            pw = new PdfWriter(filename);
            PdfDocument pd=new PdfDocument(pw);
            Document d =new Document(pd);
           // Paragraph pg=new Paragraph("First Invoice");
            Paragraph pg=new Paragraph(til.getText().toString());
            d.add(pg);
            d.close();


            /*
            if(b)
            Toast.makeText(MainActivity.this, "file created", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "false", Toast.LENGTH_LONG).show();
            */




        } catch (Exception e) {
            e.printStackTrace();
        }



    }



} //end of class