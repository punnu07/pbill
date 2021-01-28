package com.example.prakriti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity  {



    static int times=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int num_rows=1;
        //create a database initially
       setContentView(R.layout.activity_main4);
        final Context context = this;
        final EditText[] et = new EditText[1];
        int new_id_constant=30000;
        int new_id_value=40000;
        final int[] original_new_id_value = {new_id_value};
        final int[] original_new_id_constant = {new_id_constant};


        Cell cell1, cell2;
        Paragraph p;

        float[] pointColumnWidths = {106F,106F};
        String str;

       DatabaseAdaptor db = new DatabaseAdaptor(this);

      //  db.truncatetable();

       ArrayList<String> constantArrayList= db.getAllConstants();
       ArrayList <String> valueArrayList=db.getAllValues();


        if(constantArrayList.isEmpty())
       {

           Toast.makeText(MainActivity4.this,"empty", Toast.LENGTH_LONG).show();
            db.addConstant("BaseYear", "2020-2021");
           times++;

       }

       //



       //Toast.makeText(MainActivity4.this,ss, Toast.LENGTH_LONG).show();

         constantArrayList= db.getAllConstants();
         valueArrayList=db.getAllValues();



        final TableLayout[] tl = {findViewById(R.id.constant_value_table)};
        final TableRow[] tr = {new TableRow(context)};
        tr[0].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            for(int i=0;i<num_rows;i++) {
            //create new exittext
            tr[0] = new TableRow(context);
            tr[0].setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            et[0] = new EditText(context);
            et[0].setText(constantArrayList.get(i));
            et[0].setTextSize(14);
            et[0].setId(new_id_constant);
            new_id_constant++;
            tr[0].addView(et[0]);



            et[0] = new EditText(context);
            et[0].setText(valueArrayList.get(i));
            et[0].getFreezesText();
            et[0].setTextSize(14);
            et[0].setId(new_id_value);
            new_id_value++;
            tr[0].addView(et[0]);
            tl[0].addView(tr[0]);
        }

            //set the button text
          Button bt=findViewById(R.id.change);
            bt.setWidth(400);
            bt.setText("Change");


        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                int a,b;
                EditText ett,ettt1, ettt2;
                //when the button  was clicked if it was change then empty the value field and change the button to update
                if(bt.getText().equals("Change")) {

                     a=original_new_id_value[0];
                    for (int i = 0; i < num_rows; i++) {
                        ett = (EditText) findViewById(a);
                        ett.setText("");
                        a++;
                    }
                    bt.setWidth(400);

                    bt.setText("Update");
                }

                //when the button clicked if it was update then
                //insert into db;
                else if(bt.getText().equals("Update")) {

                    a=original_new_id_value[0];
                    b=original_new_id_constant[0];
                    for (int i = 0; i < num_rows; i++) {
                        ettt1=(EditText) findViewById(b);
                        ettt2=(EditText) findViewById(a);

                        db.insertConstant(ettt1.getText().toString(),ettt2.getText().toString());

                        a++;
                        b++;
                    }


                    View tab = findViewById(R.id.constant_value_table);
                    ViewGroup parent = (ViewGroup) tab.getParent();
                    if (parent != null) {
                        parent.removeView(tab);
                        parent.removeView(bt);
                        Toast.makeText(MainActivity4.this,"Updated", Toast.LENGTH_LONG).show();

                        //go to the main screen
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);

                    }

                } //end of if
                else{

                }

            } //end of click

        });





        //Toast.makeText(MainActivity4.this,et.getText().toString(), Toast.LENGTH_LONG).show();


    }
}