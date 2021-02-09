package com.example.prakriti;


//import com.itextpdf.kernel.colors.Color;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.fonts.Font;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Currency;
import android.os.Build;
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
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
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

//import com.itextpdf.text.Image;

import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;

import org.w3c.dom.Element;

import static com.itextpdf.styledxmlparser.css.CommonCssConstants.FONT;


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
    float initial_table_heights=20;

    //max_table height
    float max_table_height=150;

    float intermediate_table_height=120;

    int max_number_of_particular_rows=3;
    int max_number_of_tax_rows=5;

    int client_address_lines=0;
    int project_details_lines=0;


    int max_client_address_lines=5;
    int max_project_details_lines=5;
//number conversion logic

    private static final String[] units = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine"
    };
    private static final String[] twoDigits = {
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };
    private static final String[] tenMultiples = {
            "",
            "",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };
    private static final String[] placeValues = {
            "",
            " thousand",
            " lakh",
            " crore",
            " arab",
            " kharab"
    };

    private static String convertNumber(long number) {
        String word = "";
        int index = 0;
        boolean firstIteration = true;
        int divisor;
        do {
            divisor = firstIteration ? 1000 : 100;
            // take 3 or 2 digits based on iteration
            int num = (int)(number % divisor);
            if (num != 0){
                String str = ConversionForUptoThreeDigits(num);
                word = str + placeValues[index] + word;
            }
            index++;
            // next batch of digits
            number = number/divisor;
            firstIteration = false;
        } while (number > 0);
        return word;
    }

    private static String ConversionForUptoThreeDigits(int number) {
        String word = "";
        int num = number % 100;
        if(num < 10){
            word = word + units[num];
        }
        else if(num < 20){
            word = word + twoDigits[num%10];
        }else{
            word = tenMultiples[num/10] + units[num%10];
        }

        word = (number/100 > 0)? units[number/100] + " hundred" + word : word;
        return word;
    }



    //end of number conversion logic











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

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View arg0)
            {

                try {
                    createPdf3();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(context, MainActivity2.class);
                startActivity(intent);

            }

        });



        myButton= findViewById(R.id.Settings);

        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0)
            {



                Intent intent = new Intent(context, MainActivity4.class);
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


//create the pdf
    @RequiresApi(api = Build.VERSION_CODES.N)
    private  void createPdf3() throws IOException {
        PdfWriter pw;
        EditText et1,et2,et3,et4;
        String str, str2,str3,str4;
        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
        Table table1, table2, table3, table4, table5, table6, table12, table13, table14,table15;

        Paragraph p;

        UnitValue Inwordscellheight;
        int total_lines=20;

        Color headerBg = new Color();
        com.itextpdf.kernel.colors.Color myColor = new DeviceRgb(0, 55, 130);
        com.itextpdf.kernel.colors.Color myColorBlack = new DeviceRgb(0, 0, 0);
        com.itextpdf.kernel.colors.Color myColorRed = new DeviceRgb(255, 0, 0);

        et1=findViewById(R.id.client_address);
        et2=findViewById(R.id.project_details);
        et3=findViewById(R.id.invoice_no);
        et4=findViewById(R.id.date);


        final String REGULAR = "res/font/rupee.ttf";
        FontProgram fontProgram = FontProgramFactory.createFont(REGULAR);
        PdfFont font = PdfFontFactory.createFont(fontProgram,PdfEncodings.IDENTITY_H,true);



        /*
        float[] pointColumnWidths = {39F, 344F,212F};
        float[] pointColumnWidths2 = {106F,106F};
        float[] pointColumnWidths3 = {344F};
        float[] pointColumnWidths4 = {172F,172F};
        float[] pointColumnWidths5 = {172F};
        float[] pointColumnWidths6 = {39F};
        float[] pointColumnWidths7 = {212F};
        */





/*
        float[] pointColumnWidths = {39F, 300F,200F};
        float[] pointColumnWidths7 = {200F};
        float[] pointColumnWidths2 = {100F,100F};
        float[] pointColumnWidths3 = {300F};
        float[] pointColumnWidths4 = {150F,150F};
        float[] pointColumnWidths5 = {150F};
        float[] pointColumnWidths6 = {39F};
*/


        float[] pointColumnWidths = {39F, 280F,190F};
        float[] pointColumnWidths7 = {200F};
        float[] pointColumnWidths2 = {95F,95F};
        float[] pointColumnWidths3 = {280F};
        float[] pointColumnWidths4 = {140F,140F};
        float[] pointColumnWidths5 = {140F};
        float[] pointColumnWidths6 = {39F};







        float [] pointColumnWidths8 ={383F,212F};
        float [] pointColumnWidths9 ={39F};
        float [] pointColumnWidths10 ={106F};





        //used for first level of logo
        float [] pointColumnWidths11 ={500F,95F};

        //used for footer
        float [] pointColumnWidths18 ={100F, 395F, 100F};

        //used for amount in words
        float[] pointColumnWidths17 = {200F,0f};


        Image image, image2, image3,image4,image5;
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
            d.setMargins(10,20,10,15);

/*
           String imageFile = "logonew.jpg";
            ImageData data = ImageDataFactory.create(imageFile);
            Image img = new Image(data);
            d.add(img);
*/

            image=null;
            try {

                Drawable dr = getResources().getDrawable(R.drawable.logo);
                BitmapDrawable bitDw = ((BitmapDrawable) dr);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                ImageData imgdata = ImageDataFactory.create(stream.toByteArray());

                 image=new Image(imgdata);
              //  image.setFixedPosition(400f,750f);
                image.setHeight(100f);
                image.setWidth(100f);

                //d.add(image);

            } catch (Exception e) {
                e.printStackTrace();
            }





            image2=null;
            try {

                Drawable dr = getResources().getDrawable(R.drawable.address);
                BitmapDrawable bitDw = ((BitmapDrawable) dr);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                ImageData imgdata = ImageDataFactory.create(stream.toByteArray());

                image2=new Image(imgdata);
                //  image.setFixedPosition(400f,750f);
                image2.setHeight(100f);
                image2.setWidth(100f);

                //d.add(image);

            } catch (Exception e) {
                e.printStackTrace();
            }


            image3=null;
            try {

                Drawable dr = getResources().getDrawable(R.drawable.bottom);
                BitmapDrawable bitDw = ((BitmapDrawable) dr);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                ImageData imgdata = ImageDataFactory.create(stream.toByteArray());

                image3=new Image(imgdata);
                //  image.setFixedPosition(400f,750f);
                image3.setHeight(13f);
                image3.setWidth(350f);

                //d.add(image);

            } catch (Exception e) {
                e.printStackTrace();
            }








            image4=null;
            try {

                Drawable dr = getResources().getDrawable(R.drawable.seal2);
                BitmapDrawable bitDw = ((BitmapDrawable) dr);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                ImageData imgdata = ImageDataFactory.create(stream.toByteArray());

                image4=new Image(imgdata);
                //  image.setFixedPosition(400f,750f);
                image4.setHeight(90f);
                image4.setWidth(90f);

                //d.add(image);

            } catch (Exception e) {
                e.printStackTrace();
            }




            image5=null;
            try {

                Drawable dr = getResources().getDrawable(R.drawable.sign2);
                BitmapDrawable bitDw = ((BitmapDrawable) dr);
                Bitmap bmp = bitDw.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);


                ImageData imgdata = ImageDataFactory.create(stream.toByteArray());

                image5=new Image(imgdata);
                //  image.setFixedPosition(400f,750f);
                image5.setHeight(70f);
                image5.setWidth(70f);

                //d.add(image);

            } catch (Exception e) {
                e.printStackTrace();
            }




            Paragraph pr1=new Paragraph(et1.getText().toString());
            //Toast.makeText(MainActivity.this,et1.getText().toString(), Toast.LENGTH_LONG).show();



            table6 =new Table(pointColumnWidths11);
            table6.setVerticalBorderSpacing(5);



            //add address
            cell2 = new Cell();
            str="Prakriti Architects and Builders\n6757 C, Shalom, Judgemukku\nBMC PO Thrikkakara \nErnakulam-682021,Kerala\nTel: +91 9947111155\nEmail:mail@prakriti.net.in\n";
            p= new Paragraph(str);
            p.setFontSize(8f);

            Text tt= new Text("www.prakriti.net.in").setFontColor(myColor);
            p.add(tt);

            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);
            //table6.addCell(cell2);


            cell1=new Cell();
            cell1.add(image2);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table6.addCell(cell1);





            //add logo
            cell1=new Cell();
            cell1.add(image);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table6.addCell(cell1);


/*
            //add a blank row
            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);
            table6.addCell(cell3);
            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);
            table6.addCell(cell3);

*/

             //add table6
            d.add(table6);

            //d.add( new Paragraph( "\n" ) );


            table4 =new Table(pointColumnWidths8);
            table4.setVerticalBorderSpacing(5);

            table4.setMarginLeft(18);

            //add gst
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




            DatabaseAdaptor db = new DatabaseAdaptor(this);
            ArrayList<String> constantArrayList= db.getAllConstants();
            ArrayList <String> valueArrayList=db.getAllValues();
            if(constantArrayList.isEmpty())
            {

                Toast.makeText(MainActivity.this,"empty", Toast.LENGTH_LONG).show();
                db.addConstant("BaseYear", "2020-2021");


            }




            //add invoice

            Text invNum=new Text(et3.getText().toString()).setFontColor(myColorRed);
            str="PAB-";
            Text str6=new Text(et3.getText().toString()).setFontColor(myColorRed);
            String str7="/"+valueArrayList.get(0);;

            redText = new Text("INVOICE\n").setFontColor(myColor).setBold();


           str3="No:-";


            cell2 = new Cell();
            p= new Paragraph(redText);
            p.add(str3);
            p.add(str);
            p.add(str6);
            p.add(str7);

            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = df.format(c);
            p.add("\n");
            p.add(et4.getText().toString());
            //p.add(formattedDate);


            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);

            table4.addCell(cell2);




            /*
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
     */


            //client details
             redText = new Text("Client Name & Address\n").setFontColor(myColor).setBold().setUnderline();
            p= new Paragraph(redText);

            str=et1.getText().toString();
            String [] address_details=str.split("\n");
            client_address_lines=address_details.length;
            if(address_details.length>=max_client_address_lines)
            {
              for(int i=0;i<max_client_address_lines;i++)
              {
                  p.add(address_details[i]);
                  p.add("\n");
              }
            }
            else
            {
                for(int i=0;i<address_details.length;i++)
                {
                    p.add(address_details[i]);
                    p.add("\n");
                }

            }

            cell2 = new Cell();

            //p.add(str);
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

            String [] project_details=str.split("\n");
            project_details_lines=project_details.length;
            if(project_details.length>=max_project_details_lines)
            {
                for(int i=0;i<max_project_details_lines;i++)
                {
                    p.add(project_details[i]);
                    p.add("\n");
                }
            }
            else
            {
                for(int i=0;i<project_details.length;i++)
                {
                    p.add(project_details[i]);
                    p.add("\n");
                }

            }



            //p.add(str);
            p.setTextAlignment(TextAlignment.LEFT);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);
            table4.addCell(cell2);


            d.add(table4);

          //  d.add( new Paragraph( "\n" ) );


            //PdfPage pdfPage = pd.addNewPage();
            //set the column width
            Table table = new Table(pointColumnWidths);

            table.setBorder(new SolidBorder(0.25f));

            table.setMarginLeft(18);


            table1 =new Table(pointColumnWidths6);
            table2 =new Table(pointColumnWidths3);
            table3 =new Table(pointColumnWidths2);


            table1.setBorderRight(new SolidBorder(0.25f));
            table2.setBorderRight(new SolidBorder(0.25f));

            //header of the table
            cell1 = new Cell();
            p= new Paragraph("Sl No");
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));

            table1.addCell(cell1);


            cell2 = new Cell();
            p= new Paragraph("Description");
            p.setTextAlignment(TextAlignment.CENTER);
            cell2.add(p);
            cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell2.setBorder(Border.NO_BORDER);
            cell2.setBorderBottom(new SolidBorder(0.25f));
            table2.addCell(cell2);


            cell3 = new Cell();
            p= new Paragraph("Tax/Cess");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setBorderBottom(new SolidBorder(0.25f));
            cell3.add(p);
            table3.addCell(cell3);

            cell4 = new Cell();
            p= new Paragraph("Amount");
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.setBorder(Border.NO_BORDER);
            cell4.setBorderBottom(new SolidBorder(0.25f));
            cell4.setBorderLeft(new SolidBorder(0.25f));
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
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
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
            cell2.setBorder(Border.NO_BORDER);
            cell2.setBorderBottom(new SolidBorder(0.25f));
            table2.addCell(cell2);




            cell3 = new Cell();
            p = new Paragraph(" \n ");
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setBorderBottom(new SolidBorder(0.25f));
            cell3.add(p);
            table3.addCell(cell3);



            et = findViewById(R.id.amount);
            cell4 = new Cell();
            cell4.setBorder(Border.NO_BORDER);
            cell4.setBorderBottom(new SolidBorder(0.25f));
            cell4.setBorderLeft(new SolidBorder(0.25f));
            str = et.getText().toString();
            p = new Paragraph(str);
            if(str.isEmpty())
            {
                str="\n";
            }
            p.setTextAlignment(TextAlignment.CENTER);
            cell4.add(p);
            table3.addCell(cell4);



            UnitValue heightofcell=cell4.getHeight();
            //Toast.makeText(MainActivity.this, (CharSequence) heightofcell, Toast.LENGTH_LONG).show();

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
                cell1.setBorder(Border.NO_BORDER);
                cell1.setBorderBottom(new SolidBorder(0.25f));
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
                cell2.setBorder(Border.NO_BORDER);
                cell2.setBorderBottom(new SolidBorder(0.25f));
                table2.addCell(cell2);


                cell3 = new Cell();
                cell3.setBorder(Border.NO_BORDER);
                cell3.setBorderBottom(new SolidBorder(0.25f));
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
                cell4.setBorder(Border.NO_BORDER);
                cell4.setBorderBottom(new SolidBorder(0.25f));
                cell4.setBorderLeft(new SolidBorder(0.25f));
                cell4.add(p);
                table3.addCell(cell4);


            }//for loop





            //spare cell to set height



               /*
               //here set the heights of the tables
               //max height case
             if((client_address_lines==5 || project_details_lines==5) && numLinesInParticulars==5)
             {
                 initial_table_heights=0;
             }
            */
                     //now min height case

            int max;
             if(client_address_lines>=project_details_lines)
             {
                 max=client_address_lines;
             }
             else
             {
                 max=project_details_lines;
             }

             max=max+numLinesInParticulars+numLinesinTax;

             int max1=total_lines-max;












            for(int i=0;i<max1;i++) {

                cell1 = new Cell();
                str = "\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);
                cell1.setBorder(Border.NO_BORDER);
                if(i==max1-1)
                {
                    cell1.setBorderBottom(new SolidBorder(0.25f));
                }
                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table1.addCell(cell1);


                cell2 = new Cell();
                str = "\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell2.add(p);
                cell2.setBorder(Border.NO_BORDER);
                if(i==max1-1) {

                    cell2.setBorderBottom(new SolidBorder(0.25f));
                }

                cell2.setHorizontalAlignment(HorizontalAlignment.CENTER);
                table2.addCell(cell2);


                cell3 = new Cell();
                p = new Paragraph(" \n ");
                p.setTextAlignment(TextAlignment.CENTER);
                cell3.add(p);
                cell3.setBorder(Border.NO_BORDER);
                if(i==max1-1) {

                    cell3.setBorderBottom(new SolidBorder(0.25f));
                }
                table3.addCell(cell3);


                cell4 = new Cell();
                str = "\n";
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell4.add(p);
                cell4.setBorder(Border.NO_BORDER);
                if(i==max1-1) {

                    cell4.setBorderBottom(new SolidBorder(0.25f));
                }
                table3.addCell(cell4);

            }




            cell5=new Cell();
            cell5.add(table1);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);


            //add table2
            cell5=new Cell();
            cell5.add(table2);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);
            table.addCell(cell5);



            //add table3
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

            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setBorderRight(new SolidBorder(0.25f));
            table.addCell(cell3);






            table12 = new Table(pointColumnWidths3); //single

            table13= new Table(pointColumnWidths4);  //divide into 2

            table14= new Table(pointColumnWidths5);

            table15= new Table(pointColumnWidths5);


            //set all the table border to null
            table12.setBorder(Border.NO_BORDER);
            table13.setBorder(Border.NO_BORDER);
            table14.setBorder(Border.NO_BORDER);
            table15.setBorder(Border.NO_BORDER);




            //add to left of table3
            //this is for seal
            cell3 = new Cell();

            p = new Paragraph(" ");
            if(numLinesinTax>=3)
            {
              p.add("\n");
                p.add(image4);
            }

            p.setTextAlignment(TextAlignment.CENTER);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setBorderRight(new SolidBorder(0.25f));
            cell3.setBorderBottom(new SolidBorder(0.25f));
            //add left of table 13
            table13.addCell(cell3);




            //add initial type of tax
            et = findViewById(R.id.tax);
            cell1 = new Cell();
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            cell1.add(p);
            p.setTextAlignment(TextAlignment.RIGHT);
            cell1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            table15.addCell(cell1);


            //add all the remaining tax items
            for(int i=2;i<=numLinesinTax;i++) {

                et = findViewById(55+(i-2));
                cell1 = new Cell();
                str = et.getText().toString();
                if(str.isEmpty())
                {
                    str="\n";
                }
                p = new Paragraph(str);
                p.setTextAlignment(TextAlignment.RIGHT);

                cell1.add(p);
                cell1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
                cell1.setBorder(Border.NO_BORDER);
                cell1.setBorderBottom(new SolidBorder(0.25f));
                table15.addCell(cell1);
            }

            //add one more row for offseting the full amount in words
            cell1 = new Cell();
            //str="\n";
            str="In words";
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.RIGHT);

            cell1.add(p);
              cell1.setHorizontalAlignment(HorizontalAlignment.RIGHT);
              cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
                table15.addCell(cell1);



            //convert
            cell7=new Cell();
            cell7.add(table15);
            //cell7.setBackgroundColor(myColor);

            cell7.setPadding(0);
            cell7.setBorder(Border.NO_BORDER);
            //cell7.setBorderTop(Border.NO_BORDER);



            //add right of table13
            table13.addCell(cell7);


            //first row of table2
            cell4=new Cell();
            cell4.setPadding(-2);
            cell4.setBorder(Border.NO_BORDER);

            cell4.add(table13);
               table12.addCell(cell4);


            //second row of table12
            //this is for full address

            Text black = new Text("GSTIN:-32AAUFP9623J1ZO\n").setFontColor(myColorBlack).setBold();
            str="STATE:-Kerala(32)\nSpecification:-SAC - 995411";

            p= new Paragraph(redText);
            p.add(str);
            p.setTextAlignment(TextAlignment.LEFT);



            cell3 = new Cell();
            p = new Paragraph("Prakriti Architects & Builders\n Current A/c:-");

            Text bl1=new Text("215402100023150\n").setFontColor(myColorBlack).setBold();
            str2="UCO Bank, Thrikkakara Branch\n Ernakulam\n IFSC:-";
            Text bl2=new Text("UCBA0002154").setFontColor(myColorBlack).setBold();

            p.add(bl1);
            p.add(str2);
            p.add(bl2);

            p.setTextAlignment(TextAlignment.LEFT);
            cell3.add(p);
            cell3.setBorder(Border.NO_BORDER);
            //cell3.setBorderTop(new SolidBorder(0.25f));
            cell3.setHeight(cell_height);
            table12.addCell(cell3);



          //add table12 to table
            cell5=new Cell();
            cell5.setBorder(Border.NO_BORDER);
            cell5.setBorderRight(new SolidBorder(0.25f));
            cell5.add(table12);
            table.addCell(cell5);






//for right side


            /*


                //create a table to add 2 rows of percentage and amount
             table2=new Table(pointColumnWidths7);
               //add tax percentage and amount to a table
             table3=new Table(pointColumnWidths2);
            table4=new Table(pointColumnWidths10);
            //add tax percentage and amount to a table
            table5=new Table(pointColumnWidths10);



            table3.setBorder(Border.NO_BORDER);
            table2.setBorder(Border.NO_BORDER);
            table4.setBorder(Border.NO_BORDER);
            table5.setBorder(Border.NO_BORDER);


            table3.setBorderBottom(new SolidBorder(0.25f));

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
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            cell1.setBorderRight(new SolidBorder(0.25f));

           table4.addCell(cell1);


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
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            //cell1.setHeight(findViewById(R.id.tax).getHeight());

            table5.addCell(cell1);


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
                cell1.setBorder(Border.NO_BORDER);
                cell1.setBorderBottom(new SolidBorder(0.25f));
                //cell1.setBorderTop(new SolidBorder(0.25f));
                cell1.setBorderRight(new SolidBorder(0.25f));


                table4.addCell(cell1);


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
                cell1.setBorder(Border.NO_BORDER);
                cell1.setBorderBottom(new SolidBorder(0.25f));

                table5.addCell(cell1);

            }

            //at this point et points to the final amount
            str = et.getText().toString();

            //long final_amount = Long.parseLong(str);
            //String digit_in_words=convertNumber(final_amount);




            //add one more row
            cell1 = new Cell();
            str="\n";
            //str="Rs/-";
            p = new Paragraph(str);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table4.addCell(cell1);

            cell1 = new Cell();
            str="\n";
            p = new Paragraph(str);
            //p=new Paragraph(digit_in_words);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            table5.addCell(cell1);



            //convert table to a cell
            cell5=new Cell();
            cell5.add(table4);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);

            cell6=new Cell();
            cell6.add(table5);
            cell6.setPadding(0);
            cell6.setBorder(Border.NO_BORDER);





            //add left and right of table3
            table3.addCell(cell5);
            table3.addCell(cell6);







            //convert table3 to a cell and aadd as the first row of table2
            cell5=new Cell();
            cell5.add(table3);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(-2);
            table2.addCell(cell5);




            cell1 = new Cell();
            str="hhhhh\n";
            p = new Paragraph(str);
            //p=new Paragraph(digit_in_words);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            //cell1.setWidth(250);

            cell1.setPaddingBottom(4);
            table2.addCell(cell1);


            //second row of table2

            //this is for sign
            cell3 = new Cell();
            p = new Paragraph("\n\n\n\n Authorized Signatory");
            p.setTextAlignment(TextAlignment.CENTER);

            //cell3.setPadding(-2);
            cell3.add(p);
            cell3.setHeight(cell_height);
            cell3.setBorder(Border.NO_BORDER);
            //cell3.setBorderTop(new SolidBorder(0.25f));
            //cell3.setPaddingTop(-15);
             table2.addCell(cell3);


//this works
             */




            //new right side

            //comment point

            table2=new Table(pointColumnWidths7);
            table3=new Table(pointColumnWidths2);
            table4=new Table(pointColumnWidths10);
            table5=new Table(pointColumnWidths10);
            table6=new Table(pointColumnWidths17);



            table2.setBorder(Border.NO_BORDER);
            table4.setBorder(Border.NO_BORDER);
            table5.setBorder(Border.NO_BORDER);
            table3.setBorder(Border.NO_BORDER);
            table6.setBorder(Border.NO_BORDER);



            table3.setBorderBottom(new SolidBorder(0.25f));


            //start of content

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
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            cell1.setBorderRight(new SolidBorder(0.25f));

            table4.addCell(cell1);


            et = findViewById(R.id.tax_amount);
            cell1 = new Cell();
            str2 = et.getText().toString();

            str=CurrencyConvert(str2);
            if(str.isEmpty())
            {
                str="\n";
            }

            str2="\u20B9";
            Text te=new Text(str2).setFont(font);
            te.setFontSize(10.5f);

            if(!str.isEmpty())
            {
                p = new Paragraph(te);
            }

            p.add(str);


            p.setTextAlignment(TextAlignment.CENTER);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            //cell1.setHeight(findViewById(R.id.tax).getHeight());

            table5.addCell(cell1);

            Inwordscellheight=cell1.getHeight();

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
                cell1.setBorder(Border.NO_BORDER);
                if(i<numLinesinTax)
                cell1.setBorderBottom(new SolidBorder(0.25f));
                //cell1.setBorderTop(new SolidBorder(0.25f));
                cell1.setBorderRight(new SolidBorder(0.25f));


                table4.addCell(cell1);


                et = findViewById(5555+(i-2));
                cell1 = new Cell();
                str2 = et.getText().toString();
                str=CurrencyConvert(str2);

                if(str.isEmpty())
                {
                    str="\n";
                }

                if(!str.isEmpty()) {
                    p = new Paragraph(te);
                }
                p.add(str);
                p.setTextAlignment(TextAlignment.CENTER);
                cell1.add(p);


                cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
                cell1.setBorder(Border.NO_BORDER);
                if(i<numLinesinTax)
                cell1.setBorderBottom(new SolidBorder(0.25f));

                table5.addCell(cell1);

            }

            //end of the content







            cell5=new Cell();
            cell5.add(table4);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(0);
            table3.addCell(cell5);

            cell5=new Cell();
            cell5.add(table5);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(0);
            table3.addCell(cell5);








            //table3
            cell5=new Cell();
            cell5.add(table3);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(-1);
            table6.addCell(cell5);


            //right of table3
            cell3 = new Cell();
            str="";
            p = new Paragraph(str);
            cell3.add(p);
            //p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setPadding(-2);
            //cell3.setBorderBottom(new SolidBorder(0.25f));
            //cell3.setBorderRight(new SolidBorder(0.25f));
            table6.addCell(cell3);


            //in words
            cell3 = new Cell();
            et=findViewById(R.id.Inwords);
            str = et.getText().toString();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            p.setFontSize(8);
            cell3.add(p);
            p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setHeight(19);

            //cell3.setBorderBottom(new SolidBorder(0.25f));
            //cell3.setBorderRight(new SolidBorder(0.25f));
            table6.addCell(cell3);


            //right of words
            cell3 = new Cell();
            str="";
            p = new Paragraph(str);
            cell3.add(p);
            //p.setTextAlignment(TextAlignment.CENTER);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setPadding(-1);
            //cell3.setBorderBottom(new SolidBorder(0.25f));
            //cell3.setBorderRight(new SolidBorder(0.25f));
            table6.addCell(cell3);






            table6.setBorderBottom(new SolidBorder(0.25f));


            cell5=new Cell();
            cell5.add(table6);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(-1);
            table2.addCell(cell5);



            cell3 = new Cell();
            //cell3.add(image5);
            p = new Paragraph("");
            p.add(image5);
            p.add("\n");
            p.add("[Authorized Signatory]");
            p.setTextAlignment(TextAlignment.CENTER);
            //cell3.setPadding(-2);
            cell3.add(p);
            cell3.setHeight(cell_height);
            cell3.setBorder(Border.NO_BORDER);
            //cell3.setBorderTop(new SolidBorder(0.25f));
            //cell3.setPadding(-2);
            table2.addCell(cell3);





//comment point




            //end of new right side
           //checking out again
//comment point

            /*
            //create a table to add 2 rows of percentage and amount
            table2=new Table(pointColumnWidths7);
            //add tax percentage and amount to a table
            table3=new Table(pointColumnWidths2);
            table4=new Table(pointColumnWidths10);
            //add tax percentage and amount to a table
            table5=new Table(pointColumnWidths10);



            table3.setBorder(Border.NO_BORDER);
            table2.setBorder(Border.NO_BORDER);
            table4.setBorder(Border.NO_BORDER);
            table5.setBorder(Border.NO_BORDER);


            table3.setBorderBottom(new SolidBorder(0.25f));

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
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            cell1.setBorderRight(new SolidBorder(0.25f));

            table4.addCell(cell1);


            et = findViewById(R.id.tax_amount);
            cell1 = new Cell();
            str = et.getText().toS44tring();
            if(str.isEmpty())
            {
                str="\n";
            }
            p = new Paragraph(str);
            cell1.add(p);
            p.setTextAlignment(TextAlignment.CENTER);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);
            cell1.setBorderBottom(new SolidBorder(0.25f));
            //cell1.setHeight(findViewById(R.id.tax).getHeight());

            table5.addCell(cell1);


            //convert table to a cell
            cell5=new Cell();
            cell5.add(table4);
            cell5.setPadding(0);
            cell5.setBorder(Border.NO_BORDER);

            cell6=new Cell();
            cell6.add(table5);
            cell6.setPadding(0);
            cell6.setBorder(Border.NO_BORDER);


            //add left and right of table3
            table3.addCell(cell5);
            table3.addCell(cell6);



            //convert table3 to a cell and aadd as the first row of table2
            cell5=new Cell();
            cell5.add(table3);
            cell5.setBorder(Border.NO_BORDER);
            cell5.setPadding(-2);
            table2.addCell(cell5);

*/

//comment point




///////////////////////////////
            //now create a new cell to add table2
            cell5=new Cell();
            cell5.add(table2);
            cell5.setBorder(Border.NO_BORDER);

            //add the new cell to final table
            table.addCell(cell5);









            //final adding of the table



            //add table1
            d.add(table);





         //d.add(new Paragraph("\n"));

            //NEW table for footer

            table4 =new Table(pointColumnWidths18);

            cell1=new Cell();
            str=" \n ";
             p = new Paragraph(str);
           cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);

            table4.addCell(cell1);





            cell1=new Cell();
            str=" \n";
            p = new Paragraph(str);
            cell1.add(p);
            cell1.add(image3);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
            cell1.setBorder(Border.NO_BORDER);

            cell4.setPadding(3);
            table4.addCell(cell1);




            cell1=new Cell();
            str=" \n";
            p = new Paragraph(str);
            cell1.add(p);
            cell1.setHorizontalAlignment(HorizontalAlignment.CENTER);
           cell1.setBorder(Border.NO_BORDER);

            table4.addCell(cell1);

            d.add(table4);



            /*
            //add footer
            Paragraph footer = new Paragraph("ARCHITECTS . DEVELOPERS . PROJECT MANAGERS")
                    .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                    .setFontSize(15);



            Rectangle pageSize = pd.getPage(1).getPageSize();
            float x = pageSize.getWidth() / 2;
            float y = pageSize.getBottom()+15 ;
            d.showTextAligned(footer, x, y, 1, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
            */



            d.close();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }//end of function






    //code to convert to currency string
    @RequiresApi(api = Build.VERSION_CODES.N)
    private String CurrencyConvert(String SourceString) throws IOException {
        int i, j;
        int k=0;

        String str2;

        /*
        final String REGULAR = "res/font/rupee.ttf";
        FontProgram fontProgram = FontProgramFactory.createFont(REGULAR);
        PdfFont font = PdfFontFactory.createFont(fontProgram,PdfEncodings.IDENTITY_H,true);
           */




        int DecimalIndex = 0;
        char[] DestString = new char[100];
        char[] DestString2 = new char[100];


        //first check whether the source string has commas
        //if so return it back the same way
        if(SourceString.isEmpty())
            return SourceString;

        if (SourceString.contains(","))
            return  SourceString;
        if (!SourceString.contains(".")) {
            //check for length
            if (SourceString.length() <= 3)
                return SourceString;
            else {


                //get the last three chars
               k=0;
               i=SourceString.length()-1;
               for(j=0;j<3;j++) {
                   DestString[k++] = SourceString.charAt(i);
                   i--;
               }
                DestString[k++] = ',';
                j = 0;

                while (i >= 0) {
                    DestString[k++] = SourceString.charAt(i);
                    i--;
                    j++;
                    if (j == 2) {
                        DestString[k++] = ',';
                        j=0;
                    }

                } //end of while


                //reverse the string
                if(DestString[k-1]==',')
                {
                    k=k-1;
                }
                for(i=0;i<k;i++)
                {
                    DestString2[i]=DestString[(k-1)-i];
                }



            }//end of if
        }//end of decimal index 0
        else{



            if (SourceString.length() <= 5)
                return SourceString;


            k=0;
            i=SourceString.length()-1;
            while(SourceString.charAt(i)!='.')
            {
                DestString[k++] = SourceString.charAt(i);
                i--;
            }
            DestString[k++] ='.';
                    i--;

            for(j=0;j<3;j++) {
                DestString[k++] = SourceString.charAt(i);
                i--;
            }
            DestString[k++] = ',';
            j = 0;

            while (i >= 0) {
                DestString[k++] = SourceString.charAt(i);
                i--;
                j++;
                if (j == 2) {
                    DestString[k++] = ',';
                    j = 0;
                }

            }        //end of while



            if(DestString[k-1]==',')
            {
                k=k-1;
            }


            for (i = 0; i < k; i++) {
               DestString2[i] = DestString[(k - 1) - i];
              }


            //DestString[0]='0';
        }//end of else{}



        //str[0]='0';
        str2=String.valueOf(DestString2);


        return str2;
    } //END OF FUNCION









    private static String rupeeFormat(String value){
        value=value.replace(",","");
        char lastDigit=value.charAt(value.length()-1);
        String result = "";
        int len = value.length()-1;
        int nDigits = 0;

        for (int i = len - 1; i >= 0; i--)
        {
            result = value.charAt(i) + result;
            nDigits++;
            if (((nDigits % 2) == 0) && (i > 0))
            {
                result = "," + result;
            }
        }
        return (result+lastDigit);
    }







} //end of class