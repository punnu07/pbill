package com.example.prakriti;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import org.w3c.dom.Element;


public class MainActivity extends AppCompatActivity {

    Button myButton, myButton2, myButton3;
    TextInputEditText til;
    TableLayout tl,tll;
    TableRow tr,trr;
    EditText eett, eeettt;
    EditText et,et2, et3,  tet1, tet2, tet3;

    static  int extra_sl_count=11;
    static int extra_description_count=111;
    static int extra_amount_count=1111;

    static  int tax_count=55;
    static int percentage_count=555;
    static int tax_amount_count=5555;


    static int numLinesInParticulars=1;
    static int numLinesinTax=1;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_main);
       addListenerOnButton();
    }

//start of function
    public void addListenerOnButton() {
        final Context context = this;
        myButton= findViewById(R.id.button);
   int a;
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {

                try {

                   createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, MainActivity2.class);
                startActivity(intent);

            }

        });



        myButton2= findViewById(R.id.add_row);
        myButton2.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0)
            {


                numLinesInParticulars++;

                 tl = (TableLayout) findViewById(R.id.tableLayout);
                tr = new TableRow(context);
                tr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                //create new exittext
                et=new EditText(context);
                //get the id of the existing edittext
                eett= findViewById(R.id.sl);
                //set new id
                et.setLayoutParams(new TableRow.LayoutParams(1));

                et.setId(extra_sl_count);
                et.setHeight(eett.getHeight());
                et.setWidth(eett.getWidth());
                tr.addView(et);



                et2=new EditText(context);

                eett= findViewById(R.id.description);
                //set new id
                et2.setId(extra_description_count);
                et2.setLayoutParams(new TableRow.LayoutParams(2));

                et2.setHeight(eett.getHeight());
                et2.setWidth(eett.getWidth());
                tr.addView(et2);



                et3=new EditText(context);
                eett= findViewById(R.id.amount);
                //set new id
                et3.setId(extra_amount_count);
                et3.setLayoutParams(new TableRow.LayoutParams(3));
                et3.setHeight(eett.getHeight());
                et3.setWidth(eett.getWidth());
                 tr.addView(et3);


                 extra_sl_count++;
                 extra_description_count++;
                 extra_amount_count++;

                 tl.addView(tr);
            }

        });

 //third button


        myButton3= findViewById(R.id.add_row3);
        myButton3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View arg0)
            {

                numLinesinTax++;
                tll = (TableLayout) findViewById(R.id.tableLayout4);
                trr = new TableRow(context);
                trr.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                //create new exittext
                tet1=new EditText(context);
                //get the id of the existing edittext
                eeettt= findViewById(R.id.tax);
                //set new id
                tet1.setLayoutParams(new TableRow.LayoutParams(1));

                tet1.setId(tax_count);
                tet1.setHeight(eeettt.getHeight());
                tet1.setWidth(eeettt.getWidth());
                trr.addView(tet1);



                tet2=new EditText(context);

                eeettt= findViewById(R.id.percentage);
                //set new id
                tet2.setId(percentage_count);
                tet2.setLayoutParams(new TableRow.LayoutParams(2));

                tet2.setHeight(eeettt.getHeight());
                tet2.setWidth(eeettt.getWidth());
                trr.addView(tet2);



                tet3=new EditText(context);
                eeettt= findViewById(R.id.tax_amount);
                //set new id
                tet3.setId(tax_amount_count);
                tet3.setLayoutParams(new TableRow.LayoutParams(3));
                tet3.setHeight(eeettt.getHeight());
                tet3.setWidth(eeettt.getWidth());
                trr.addView(tet3);


                tax_count++;
                percentage_count++;
                tax_amount_count++;

                tll.addView(trr);
            }

        });
//end of third button




    }  //end of function



    



    
    

    private void createPdf() throws FileNotFoundException
    {

        PdfWriter pw;
       EditText et;
       String str;
       Cell cell1, cell2, cell3, cell4;



       // til=findViewById(R.id.ip);
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




            //set the column width
            float [] pointColumnWidths = {39F,344F,106,106};
            Table table = new Table(pointColumnWidths);


            //header of the table
             cell1 = new Cell();
            Paragraph p= new Paragraph("Sl No");
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(cell1);


            cell2 = new Cell();
             p= new Paragraph("Description");
            p.setTextAlignment(TextAlignment.CENTER);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table.addCell(cell2);


             cell3 = new Cell();
            p= new Paragraph("Tax/Cess");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            table.addCell(cell3);

             cell4 = new Cell();
            p= new Paragraph("Amount");
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            table.addCell(cell4);





  //experimental row


           //only one particular
            if(numLinesInParticulars==1) {


                et = findViewById(R.id.sl);
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table.addCell(cell1);


                et = findViewById(R.id.description);
                cell2 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell2.add(p);
                cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table.addCell(cell2);


                cell3 = new Cell();
                p = new Paragraph("");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                table.addCell(cell3);


                et = findViewById(R.id.amount);
                cell4 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                table.addCell(cell4);
            }

            //number of particulars >1
            if(numLinesInParticulars>1)
            {

                //first add the first id data
                et = findViewById(R.id.sl);
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table.addCell(cell1);


                et = findViewById(R.id.description);
                cell2 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell2.add(p);
                cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table.addCell(cell2);


                cell3 = new Cell();
                p = new Paragraph("");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                table.addCell(cell3);


                et = findViewById(R.id.amount);
                cell4 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                table.addCell(cell4);

                //Now get from 2nd row onwards
                // i must be >=2

                for(int i=2;i<=numLinesInParticulars;i++)
                {

                    et = findViewById(11+(i-2));
                    cell1 = new Cell();
                    str = et.getText().toString();
                    p = new Paragraph(str);
                    p.setTextAlignment(TextAlignment.CENTER);
                    cell1.add(p);
                    cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    table.addCell(cell1);




                    et = findViewById(111+(i-2));
                    cell2 = new Cell();
                    str = et.getText().toString();
                    p = new Paragraph(str);
                    p.setTextAlignment(TextAlignment.CENTER);
                    cell2.add(p);
                    cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                    table.addCell(cell2);




                    cell3 = new Cell();
                    p = new Paragraph("");
                    p.setTextAlignment(TextAlignment.CENTER);
                    cell3.add(p);
                    table.addCell(cell3);


                    et = findViewById(1111+(i-2));
                    cell4 = new Cell();
                    str = et.getText().toString();
                    p = new Paragraph(str);
                    p.setTextAlignment(TextAlignment.CENTER);
                    cell4.add(p);
                    table.addCell(cell4);


                      

                }//for loop




            }//closing for greater than or equal to 2




           //add to the table the rows
            d.add(table);
            d.close();






        } catch (Exception e) {
            e.printStackTrace();
        }



    }



} //end of class