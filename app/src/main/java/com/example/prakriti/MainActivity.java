package com.example.prakriti;


//import com.itextpdf.kernel.colors.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.os.Environment;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.TypedValue;
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
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;


import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
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


    //height of lowest row
    float cell_height=100;

    //minimum table height
    float initial_table_heights=130;

    //max_table height
    float max_table_height=250;

    int max_number_of_particular_rows=4;
    int max_number_of_tax_rows=5;






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

                   createPdf3();
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


                if(numLinesInParticulars> max_number_of_particular_rows)
                {
                    return;
                }
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
                eett.setTextSize(14);
                eett.setHeight(60);
                //set new id
                et.setLayoutParams(new TableRow.LayoutParams(1));

                et.setId(extra_sl_count);
                et.setHeight(eett.getHeight());
                et.setWidth(eett.getWidth());
                et.setTextSize(14);
                tr.addView(et);



                et2=new EditText(context);

                eett= findViewById(R.id.description);
                eett.setTextSize(14);
                //set new id
                et2.setId(extra_description_count);
                et2.setLayoutParams(new TableRow.LayoutParams(2));
                et2.setTextSize(14);
                et2.setHeight(eett.getHeight());
                et2.setWidth(eett.getWidth());
                tr.addView(et2);



                et3=new EditText(context);
                eett= findViewById(R.id.amount);
                eett.setTextSize(14);
                //set new id
                et3.setId(extra_amount_count);
                et3.setLayoutParams(new TableRow.LayoutParams(3));
                et3.setHeight(eett.getHeight());
                et3.setTextSize(14);
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

                if(numLinesinTax>max_number_of_tax_rows)
                {
                    return;
                }
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
                eeettt.setTextSize(14);
                //set new id
                tet1.setLayoutParams(new TableRow.LayoutParams(1));

                tet1.setId(tax_count);
                tet1.setHeight(eeettt.getHeight());
                tet1.setWidth(eeettt.getWidth());

                tet1.setTextSize(14);
                trr.addView(tet1);



                tet2=new EditText(context);

                eeettt= findViewById(R.id.percentage);
                eeettt.setTextSize(14);
                //set new id
                tet2.setId(percentage_count);
                tet2.setLayoutParams(new TableRow.LayoutParams(2));

                tet2.setTextSize(14);

                tet2.setHeight(eeettt.getHeight());
                tet2.setWidth(eeettt.getWidth());
                trr.addView(tet2);



                tet3=new EditText(context);
                eeettt= findViewById(R.id.tax_amount);
                eeettt.setTextSize(14);
                //set new id
                tet3.setId(tax_amount_count);
                tet3.setLayoutParams(new TableRow.LayoutParams(3));
                tet3.setHeight(eeettt.getHeight());
                tet3.setTextSize(14);
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





    public  float convertSpToPixels(float sp, Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }



    private  void createPdf3() throws FileNotFoundException {
        PdfWriter pw;
        EditText et1,et2,et3,et4;
        String str, str2,str3,str4;
        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
        Table table1, table2, table3, table4, table5, table6;
        Paragraph p;


        Color headerBg = new Color();
        com.itextpdf.kernel.colors.Color myColor = new DeviceRgb(0, 55, 130);
        com.itextpdf.kernel.colors.Color myColorBlack = new DeviceRgb(0, 0, 0);

        et1=findViewById(R.id.client_address);
        et2=findViewById(R.id.project_details);
        et3=findViewById(R.id.invoice_no);



        float[] pointColumnWidths = {39F, 344F,212F};
        float[] pointColumnWidths2 = {106F,106F};
        float[] pointColumnWidths3 = {344F};
        float[] pointColumnWidths4 = {172F,172F};
        float[] pointColumnWidths5 = {172F};
        float[] pointColumnWidths6 = {39F};
        float[] pointColumnWidths7 = {212F};

        float [] pointColumnWidths8 ={383F,212F};
        float [] pointColumnWidths9 ={39F};

        //for the lowermost row

        final Context context = this;
        try {

            //File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Your directory name");
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "prakriti");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File filename = new File(folder, "invoice.pdf");

            if (filename.exists()) {
                filename.delete();
            }
            pw = new PdfWriter(filename);
            PdfDocument pd = new PdfDocument(pw);
            Document d = new Document(pd);


            /*
            PdfPage page = pd.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Rectangle rectangle = new Rectangle(36, 650, 100, 100);
            pdfCanvas.rectangle(rectangle);
            pdfCanvas.stroke();
            Canvas canvas = new Canvas(pdfCanvas, pd, rectangle);
            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD);
            Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
            Text author = new Text("Robert Louis Stevenson").setFont(font);
            Paragraph p = new Paragraph().add(title).add(" by ").add(author);
            canvas.add(p);
            canvas.close();
               */









            Paragraph pr1=new Paragraph(et1.getText().toString());
            //Toast.makeText(MainActivity.this,et1.getText().toString(), Toast.LENGTH_LONG).show();


            table4 =new Table(pointColumnWidths8);
            table4.setVerticalBorderSpacing(5);





            Text redText = new Text("GSTIN:-32AAUFP9623J1ZO\n").setFontColor(myColorBlack).setBold();
            str="STATE:-Kerala(32)\nSpecification:-SAC - 995411";
            cell2 = new Cell();
            p= new Paragraph(redText);
            p.add(str);
            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);

            table4.addCell(cell2);





            //add invoice

            str=et3.getText().toString();
            redText = new Text("INVOICE\n").setFontColor(myColor).setBold();

           str3="No:-";
            cell2 = new Cell();
            p= new Paragraph(redText);
            p.add(str3);
            p.add(str);

            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);

            table4.addCell(cell2);




            //add a blank row
            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);

            table4.addCell(cell3);

            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);

            table4.addCell(cell3);



            //client details
             redText = new Text("Client Name & Address\n").setFontColor(myColor).setBold().setUnderline();
            str2="Client Name & Address\n";
            str=et1.getText().toString();
            cell2 = new Cell();
            p= new Paragraph(redText);
            p.add(str);
            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);

            table4.addCell(cell2);



            //project details

            redText = new Text("Project Details\n").setFontColor(myColor).setBold().setUnderline();
            str=et2.getText().toString();
            cell2 = new Cell();
            p= new Paragraph(redText);
            p.add(str);
            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);
            table4.addCell(cell2);


            d.add(table4);

            d.add( new Paragraph( "\n" ) );


            //PdfPage pdfPage = pd.addNewPage();
            //set the column width
            Table table = new Table(pointColumnWidths);
            //table.setBorderLeft(new SolidBorder(1.70f));


            table1 =new Table(pointColumnWidths6);
            table2 =new Table(pointColumnWidths3);
            table3 =new Table(pointColumnWidths2);

            //table1.setBorder(new SolidBorder(1));
            //table2.setBorder(new SolidBorder(1));
            //table3.setBorder(new SolidBorder(1));






            //table1.setHeight(initial_table_heights);
            //table2.setHeight(initial_table_heights);
            //table3.setHeight(initial_table_heights);

            //header of the table
            cell1 = new Cell();
            p= new Paragraph("Sl No");
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table1.addCell(cell1);


            cell2 = new Cell();
            p= new Paragraph("Description");
            p.setTextAlignment(TextAlignment.CENTER);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table2.addCell(cell2);


            cell3 = new Cell();
            p= new Paragraph("Tax/Cess");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            table3.addCell(cell3);

            cell4 = new Cell();
            p= new Paragraph("Amount");
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            table3.addCell(cell4);








            //first add the first id data
            et = findViewById(R.id.sl);
            cell1 = new Cell();
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
                }
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table1.addCell(cell1);


            et = findViewById(R.id.description);
            cell2 = new Cell();
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table2.addCell(cell2);




            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            table3.addCell(cell3);



            et = findViewById(R.id.amount);
            cell4 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            if(str.isEmpty())
            {
                str="\n";
            }
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            table3.addCell(cell4);



            //Now get from 2nd row onwards
            // i must be >=2


            for(int i=2;i<=numLinesInParticulars;i++)
            {

                et = findViewById(11+(i-2));

                cell1 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table1.addCell(cell1);


                et = findViewById(111+(i-2));
                cell2 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell2.add(p);
                cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table2.addCell(cell2);



                cell3 = new Cell();
                p = new Paragraph(" \n ");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                table3.addCell(cell3);

                et = findViewById(1111+(i-2));
                cell4 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                table3.addCell(cell4);



            }//for loop




                cell1 = new Cell();
                str="\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHeight(initial_table_heights);
            if(numLinesInParticulars<=2)
            {
                cell1.setHeight(max_table_height);
            }

            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table1.addCell(cell1);


                cell2 = new Cell();
                str="\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell2.add(p);
                cell2.setHeight(initial_table_heights);
                if(numLinesInParticulars<=2)
                {
                    cell2.setHeight(max_table_height);
                }

                cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table2.addCell(cell2);


                cell3 = new Cell();
                p = new Paragraph(" \n ");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                cell3.setHeight(initial_table_heights);
            if(numLinesInParticulars<=2)
            {
                cell3.setHeight(max_table_height);
            }

            table3.addCell(cell3);


                cell4 = new Cell();
                str="\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                cell4.setHeight(initial_table_heights);
            if(numLinesInParticulars<=2)
            {
                cell4.setHeight(max_table_height);
            }


            table3.addCell(cell4);











            table2.setBorderRight(new SolidBorder(0.5f));
            table3.setBorderLeft(new SolidBorder(0.2f));


            table2.setBorderBottom(new SolidBorder(0.2f));

            cell5=new Cell();
            cell5.add(table1);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);



            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);



            cell5=new Cell();
            cell5.add(table3);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);






//added all the particulars correctly
//add tax details
            //sl no section for tax



            cell3 = new Cell();
            p = new Paragraph("\n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(new SolidBorder(1f));
            cell3.setBorderRight(new SolidBorder(0.5f));
            cell3.add(p);
            table.addCell(cell3);

  /*

            table6=new Table(pointColumnWidths9);

            for(int i=1;i<=numLinesinTax;i++) {

             cell6 = new Cell();
            p = new Paragraph("\n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell6.setBorder(Border.NO_BORDER);
            cell6.add(p);
            table6.addCell(cell6);
            }

            cell6 = new Cell();
            p = new Paragraph("\n");
            p.setTextAlignment(TextAlignment.CENTER);
            cell6.add(p);
            cell6.setHeight(cell_height);
            cell6.setBorder(Border.NO_BORDER);
            table6.addCell(cell6);

            table6.setBorderLeft(new SolidBorder(1f));
            //table6.setPadding(-3);
            cell3=new Cell();
            cell3.add(table6);
            table.addCell(cell3);

            */



            table2 = new Table(pointColumnWidths3); //single

           //first row of table2
            table3= new Table(pointColumnWidths4);  //divide into 2

            table4= new Table(pointColumnWidths5);

            table5= new Table(pointColumnWidths5);



            table2.setBorder(new SolidBorder(0.2f));
            table5.setBorder(new SolidBorder(0.5f));

            //add to left of table3
            //this is for seal
            cell3 = new Cell();
            p = new Paragraph("\n");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setPadding(0);


            cell3.setBorder(Border.NO_BORDER);
            table3.addCell(cell3);




            et = findViewById(R.id.tax);
            cell1 = new Cell();
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            cell1.add(p);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table5.addCell(cell1);


            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(55+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);

                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                // cell1.setPadding(0);
                // cell1.setBorder(Border.NO_BORDER);

                table5.addCell(cell1);
            }

            //add one more row
            cell1 = new Cell();
            str="\n";
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);

            cell1.add(p);
              cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);


            table5.addCell(cell1);



            //convert



            cell7=new Cell();
            cell7.add(table5);
            //cell7.setBackgroundColor(myColor);
            cell7.setPadding(-3);
            cell7.setBorder(Border.NO_BORDER);
            //cell7.setBorderRight(new SolidBorder(0.5f));



            table3.addCell(cell7);


            //first row of table2
            cell4=new Cell();
            cell4.add(table3);

            table2.addCell(cell4);


            //second row of table2
            //this is for full address
            cell3 = new Cell();
            p = new Paragraph("\n");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setHeight(cell_height);
            table2.addCell(cell3);


          //add table2 to table
            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setBorderRight(new SolidBorder( 0.2f));
            table.addCell(cell5);










                //create a table to add 2 rows of percentage and amount
             table2=new Table(pointColumnWidths7);
               //add tax percentage and amount to a table
             table3=new Table(pointColumnWidths2);

            table2.setBorder(new SolidBorder(0.5f));
            table3.setBorder(new SolidBorder(0.5f));




            et = findViewById(R.id.percentage);
                cell1 = new Cell();
                 str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                cell1.add(p);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
           table3.addCell(cell1);


            et = findViewById(R.id.tax_amount);
            cell1 = new Cell();
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            cell1.add(p);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table3.addCell(cell1);

            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(555+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);

                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table3.addCell(cell1);


                et = findViewById(5555+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);

                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table3.addCell(cell1);

            }

            //add one more row
            cell1 = new Cell();
            str="\n";
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table3.addCell(cell1);


            cell1 = new Cell();
            str="\n";
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table3.addCell(cell1);




            //convert table to a cell
            cell5=new Cell();
            cell5.add(table3);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table2.addCell(cell5);



            //second row of table2
            //this is for sign
            cell3 = new Cell();
            p = new Paragraph("Authorized Signatory");
            p.setTextAlignment(TextAlignment.CENTER);

            cell3.add(p);
            cell3.setHeight(cell_height);
            cell3.setBorderRight(new SolidBorder(0.5f));
            table2.addCell(cell3);


            //now create a new cell to add table2
            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(-1);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setBorderRight(new SolidBorder(0.5f));

            //add the new cell to final table
            table.addCell(cell5);

















            table.setBorder(new SolidBorder(0.75f));


            d.add(table);
            d.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }













    private  void createPdf2() throws FileNotFoundException {
        PdfWriter pw;
        EditText et;
        String str;
        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
        Table table2, table3, table4, table5, table6;

        Color headerBg = new Color();
        com.itextpdf.kernel.colors.Color myColor = new DeviceRgb(255, 100, 20);


        float[] pointColumnWidths = {39F, 344F,212F};
        float[] pointColumnWidths2 = {106F,106F};
        float[] pointColumnWidths3 = {344F};
        float[] pointColumnWidths4 = {172F,172F};
        float[] pointColumnWidths5 = {172F};

        final Context context = this;
        try {

            //File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "Your directory name");
            File folder = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "prakriti");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            File filename = new File(folder, "invoice.pdf");

            if (filename.exists()) {
                filename.delete();
            }
            pw = new PdfWriter(filename);
            PdfDocument pd = new PdfDocument(pw);
            Document d = new Document(pd);

            //PdfPage pdfPage = pd.addNewPage();
            //set the column width
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



            table2=new Table(pointColumnWidths2);

            cell3 = new Cell();
            p= new Paragraph("Tax/Cess");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            //cell3.setBackgroundColor(myColor);
            table2.addCell(cell3);

            cell4 = new Cell();
            p= new Paragraph("Amount");
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            //cell4.setBackgroundColor(myColor);
            table2.addCell(cell4);

            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);

             //add the content






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



          table2=new Table(pointColumnWidths2);



            cell3 = new Cell();
            p = new Paragraph(" ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBackgroundColor(myColor);
            table2.addCell(cell3);



            et = findViewById(R.id.amount);
            cell4 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            cell4.setBackgroundColor(myColor);
            table2.addCell(cell4);

            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(-1);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);


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

                table2=new Table(pointColumnWidths2);

                cell3 = new Cell();
                p = new Paragraph("");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                table2.addCell(cell3);

                et = findViewById(1111+(i-2));
                cell4 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                table2.addCell(cell4);

                cell5=new Cell();
                cell5.add(table2);
                cell5.setPadding(0);
                cell5.setBorder(Border.NO_BORDER);
                table.addCell(cell5);


            }//for loop




//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //tax

            cell3 = new Cell();
            p = new Paragraph("sl");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            table.addCell(cell3);

            table2 = new Table(pointColumnWidths3); //single





            //first row of table2
            table3= new Table(pointColumnWidths4);  //divide into 2

            table4= new Table(pointColumnWidths5);


                                                 //add to left of table3
            cell3 = new Cell();
            p = new Paragraph("seal");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setPadding(0);
            cell3.setBorder(Border.NO_BORDER);
            table4.addCell(cell3);


            table5= new Table(pointColumnWidths5);

            et = findViewById(R.id.tax);
            cell1 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);

            table5.addCell(cell1);


            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(55+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);

                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
               // cell1.setPadding(0);
               // cell1.setBorder(Border.NO_BORDER);

                table5.addCell(cell1);
            }



            cell6=new Cell();
            cell6.add(table4);
            cell6.setPadding(0);
            cell6.setBorder(Border.NO_BORDER);


            cell7=new Cell();
            cell7.add(table5);
            cell7.setPadding(0);
            cell7.setBorder(Border.NO_BORDER);
            cell7.setBorderRight(Border.NO_BORDER);

            table3.addCell(cell6);
            table3.setBorderRight(Border.NO_BORDER);
            table3.addCell(cell7);


            //first row of table2
           cell4=new Cell();
           cell4.add(table3);


           table2.addCell(cell4);

            //second row of table2
            cell3 = new Cell();
            p = new Paragraph("hh");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);

            //cell3.setPadding(0);
            //cell3.setBorder(Border.NO_BORDER);

            table2.addCell(cell3);



            cell5=new Cell();
            cell5.add(table2);


            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);



            cell3 = new Cell();
            p = new Paragraph("third");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            //cell3.setPadding(0);
            //cell3.setBorder(Border.NO_BORDER);

            table.addCell(cell3);












            d.add(table);
            d.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    





    
    

    private void createPdf() throws FileNotFoundException
    {

        PdfWriter pw;
       EditText et;
       String str;
       Cell cell1, cell2, cell3, cell4,cell5,cell6,cell7;
        Table table2,table3,table4,table5,table6;
        float [] pointColumnWidths2 = {172F,172F};
        float [] pointColumnWidths3 = {172F};
        float [] pointColumnWidths4 = {106F};

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

           //handle for lines in the tax sections



            //extremely outside sl no
            cell3 = new Cell();
            p = new Paragraph("");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            table.addCell(cell3);



            //create a new table at this point to encompass  the vacent space and the rows
            table2 = new Table(pointColumnWidths2);

            //add left of the inside table
            cell3 = new Cell();
            p = new Paragraph("");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.add(p);
            table2.addCell(cell3);


            //create another table for the right of inside thable
            table3 = new Table(pointColumnWidths3);


            et = findViewById(R.id.tax);
            cell1 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.setBorderRight(Border.NO_BORDER);
            cell1.setBorderTop(Border.NO_BORDER);
            cell1.setBorderLeft(Border.NO_BORDER);

            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table3.addCell(cell1);

            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(55+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);

                cell1.setBorderRight(Border.NO_BORDER);
                cell1.setBorderTop(Border.NO_BORDER);
                cell1.setBorderLeft(Border.NO_BORDER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table3.addCell(cell1);



            }


                //create a new cell to encapsulate table 3;
            cell4 = new Cell();
            cell4.add(table3);
            table2.addCell(cell4);


            cell5=new Cell();
            cell5.add(table2);
            table.addCell(cell5);



            //at this point add the percentage data
            // create another table2
            table4 = new Table(pointColumnWidths4);

            et = findViewById(R.id.percentage);
            cell1 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table4.addCell(cell1);


            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(555+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table4.addCell(cell1);

            }
            //convert table4 to a cell
            cell5=new Cell();
            cell5.add(table4);
            table.addCell(cell5);



            //now add the tax_amount
            table5 = new Table(pointColumnWidths4);
            et = findViewById(R.id.tax_amount);
            cell1 = new Cell();
            str = et.getText().toString();
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            table5.addCell(cell1);

            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(5555+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table5.addCell(cell1);

            }
            cell6=new Cell();
            cell6.add(table5);
            table.addCell(cell6);






            //add to the table the rows
            d.add(table);
            d.close();






        } catch (Exception e) {
            e.printStackTrace();
        }



    }



} //end of class