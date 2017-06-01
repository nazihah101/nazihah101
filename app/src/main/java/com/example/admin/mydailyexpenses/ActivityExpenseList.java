package com.example.admin.mydailyexpenses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityExpenseList extends AppCompatActivity {

    String strPrice,strExpName,strExpenses = "";
    //Double total = 0.00;
    SQLiteDatabase financenew;
    ExpensesDB fn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fn = new ExpensesDB(getApplicationContext());

        Runnable run = new Runnable() {
            @Override
            public void run() {

                financenew = openOrCreateDatabase("financenew" , MODE_PRIVATE, null);
                Cursor resultSet = fn.getReadableDatabase().rawQuery("Select * from chapter;",null);

                if (resultSet.moveToFirst())
                {
                    do {
                        strExpName = resultSet.getString(resultSet.getColumnIndex("chapter_name"));
                        strPrice = resultSet.getString(resultSet.getColumnIndex("chapter_notes"));



                        strExpenses += "Chapter : " +strExpName + "  Description : "+strPrice+"\n\n";

                    }while (resultSet.moveToNext());
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        TextView txtVwExpenses = (TextView)findViewById(R.id.tvtView1);
                        txtVwExpenses.setText(strExpenses);
                        txtVwExpenses.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);


                    }
                });
            }
        };

        Thread thr = new Thread(run);
        thr.start();


    }

}
