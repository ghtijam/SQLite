package com.majithg.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    private String dbName = "db1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrieveData();

            }
        });

    }


    public void retrieveData(){

        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM tName", null);

        if(cursor.moveToFirst()){

            do{
                // cycle through all records
//                String clm1 =cursor.getString(0);
                String clm1 =cursor.getString(cursor.getColumnIndex("clm1"));
//                String clm2 =cursor.getString(2);
                String clm2 =cursor.getString(cursor.getColumnIndex("clm2"));
//                int clm3 =cursor.getInt(1);
//                String clm3 =cursor.getString(cursor.getColumnIndex("clm3"));

                int i = cursor.getPosition()+1;

//                Toast.makeText(getBaseContext(), "Column1: "+clm1 +" Column2: "+clm2 +" Column3: "+clm3, Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(), "Raw"+i+": "+clm1 +" "+clm2 +" ", Toast.LENGTH_LONG).show();
            }while (cursor.moveToNext());
        }
        else {

            Toast.makeText(getBaseContext(),"Error retrieving data ... ", Toast.LENGTH_SHORT).show();
        }

//        String username = cursor.getString(1);

//        Toast.makeText(this, "users: "+ username , Toast.LENGTH_SHORT).show();

        /*while (cursor.moveToFirst()) {
            Toast.makeText(this, "Columns: " + cursor.getString(cursor.getColumnIndex("clm1")), Toast.LENGTH_SHORT).show();
        }*/


//        what the hell is this ...


        /*
        do{

            int cIndex = cursor.getColumnIndex("clm2");
//            Toast.makeText(this, "Columns: "+ i , Toast.LENGTH_SHORT).show();

            Toast.makeText(this, "users: "+cursor.getString(cIndex) , Toast.LENGTH_SHORT).show();

            // +cursor.getString(cIndex)



        }while (cursor.moveToNext());
*/
        db.close();





    }


    public void insertData(){

        SQLiteDatabase db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);

        //        db.execSQL("CREATE TABLE IF NOT EXIST "+tName+" ("+clm1+" TEXT, "+clm2+" TEXT, "+clm3+" INT(3));");

        db.execSQL("CREATE TABLE IF NOT EXISTS tName (clm1 TEXT, clm2 TEXT, Clm3 INTEGER(3))");

        db.execSQL("INSERT INTO tName VALUES('mmm','aaa','18')");
        db.execSQL("INSERT INTO tName VALUES('nnn','mmm','19')");
        db.execSQL("INSERT INTO tName VALUES('ppp','kkk','20')");

        db.close();

        Toast.makeText(this, dbName+" DataBase has been created..!",Toast.LENGTH_SHORT).show();
    }


}
