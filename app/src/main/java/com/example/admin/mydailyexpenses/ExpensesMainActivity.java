package com.example.admin.mydailyexpenses;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpensesMainActivity extends AppCompatActivity {

    //SQLiteDatabase dbMyExpenses;
    //Date date = new Date();
    //String newDate =  new SimpleDateFormat("dd-MM-yyyy").format(date);
    //String newTime =  new SimpleDateFormat("HH:mm:ss").format(date);

    EditText edtChapter,edtNotes;

    WebServiceCall wsc = new WebServiceCall();
    String strMsg;
    ExpensesDB financenew;
    JSONObject jsnObj = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_main);
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

        financenew = new ExpensesDB(getApplicationContext());
        edtChapter = (EditText)findViewById(R.id.edtChapter);
        edtNotes = (EditText)findViewById(R.id.edtNotes);}






    public void fnSave(View view) {


        Runnable run2 = new Runnable() {

            String strRespond = "";

            @Override
            public void run() {

                String strExpense = edtChapter.getText().toString();
                String strPrice = edtNotes.getText().toString();


                int intNewId = financenew.fnTotalRow() + 1;
                String strQry = "INSERT INTO " + ExpensesDB.tblname + " values('" + intNewId + "','" + strExpense + "','" + strPrice + "');";


                financenew.fnExecuteSql(strQry, getApplicationContext());

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("selectFn", "fnAddExpense"));
                params.add(new BasicNameValuePair("varChapter", strExpense));
                params.add(new BasicNameValuePair("varNotes", strPrice));


                try {
                    jsnObj = wsc.makeHttpRequest(wsc.fnGetURL(), "POST", params);
                    strRespond = jsnObj.getString("respond");
                } catch (JSONException e) {
                    Log.d("JSON Call Error", "Error !");
                    Toast showSuccess2 = Toast.makeText(getApplicationContext(), "No Connection", Toast.LENGTH_SHORT);
                    showSuccess2.show();

                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast showSuccess = Toast.makeText(getApplicationContext(), "Success Save"
                                + "Respond From Server : " + strRespond, Toast.LENGTH_SHORT);
                        showSuccess.show();

                    }
                });

            }
        };

        Thread thrSave = new Thread(run2);
        thrSave.start();

    }

    public void fnQuiz (View view)
    {
        Intent nextPage = new Intent(ExpensesMainActivity.this , QuizActivity.class);
        startActivity(nextPage);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expenses_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exp_list) {
            fnExpenseListExp(this.getCurrentFocus());
            
            return true;
        }
        else if (id == R.id.ListViewExp) {
            fnActvListExp(this.getCurrentFocus());
        }

        return super.onOptionsItemSelected(item);
    }

    private void fnExpenseListExp(View currentFocus) {
        Intent intent = new Intent(this,ActivityExpenseList.class);
        startActivityForResult(intent,0);
    }

    private void fnActvListExp(View currentFocus) {
        Intent intent = new Intent(this,ListViewExpensesActivity.class);
        startActivityForResult(intent,0);
    }

}
