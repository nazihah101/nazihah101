package com.example.admin.mydailyexpenses;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class ListViewExpensesActivity extends AppCompatActivity {

    private static final String strExpId = ExpensesDB.colExpId;
    private static final String strExpName = ExpensesDB.colExpName;
    private static final String strExpPrice = ExpensesDB.colExpPrice;


    ExpensesDB fn;
    ListView lvExp;
    ArrayList<HashMap<String,String>> alExp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_expenses);

        fn = new ExpensesDB(getApplicationContext());
        lvExp = (ListView)findViewById(R.id.lstVwExpense);
        alExp = new ArrayList<HashMap<String, String>>();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                String strSql = "Select * from "+ExpensesDB.tblname;
                Cursor currExp = fn.getReadableDatabase().rawQuery(strSql,null);

                while (currExp.moveToNext()) {
                    HashMap<String,String>map = new HashMap<String, String>();
                    map.put(strExpId,currExp.getString(currExp.getColumnIndex(ExpensesDB.colExpId)));
                    map.put(strExpName,currExp.getString(currExp.getColumnIndex(ExpensesDB.colExpName)));
                    map.put(strExpPrice,currExp.getString(currExp.getColumnIndex(ExpensesDB.colExpPrice)));

                    alExp.add(map);
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListAdapter adapter = new SimpleAdapter(ListViewExpensesActivity.this,alExp,R.layout.listviewexpenseinfo,
                                new String []{strExpId,strExpName,strExpPrice},
                                new int[]{R.id.txtvwExpID,R.id.txtvwExpName,R.id.txtvwExpPrice});

                        lvExp.setAdapter(adapter);
                    }
                });

            }
        };
        Thread thr = new Thread(run);
        thr.start();



        lvExp.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),alExp.get(i).get(strExpName),Toast.LENGTH_SHORT).show();//show the selected image in toast according to position

                HashMap<String,String> map = alExp.get(i);


                Intent intent = new Intent(ListViewExpensesActivity.this, activity_edit.class);
                intent.putExtra("hashmap", map);
                startActivity(intent);

            }
        });
    }
}
