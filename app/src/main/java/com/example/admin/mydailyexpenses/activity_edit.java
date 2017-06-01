package com.example.admin.mydailyexpenses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class activity_edit extends AppCompatActivity {

    EditText txtEditName, txtEditPrice;
    TextView lblEditHidden;
    Button btnUpdate;
    ExpensesDB myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        myDatabase = new ExpensesDB(getApplicationContext());

        txtEditName = (EditText) findViewById(R.id.edtEditExpenseName);
        txtEditPrice = (EditText) findViewById(R.id.edtEditExpensesPrice);

        lblEditHidden = (TextView) findViewById(R.id.lblEditHiddenID);
        btnUpdate = (Button) findViewById(R.id.btnEditUpdate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnUpdateData();
            }
        });

        Intent intent = getIntent();
        HashMap<String,String> map = (HashMap<String,String>) intent.getSerializableExtra("hashmap");

        String e_name, e_price,  e_id;

        e_name = map.get(ExpensesDB.colExpName);
        e_price = map.get(ExpensesDB.colExpPrice);

        e_id = map.get(ExpensesDB.colExpId);

        txtEditName.setText(e_name);
        txtEditPrice.setText(e_price);

        lblEditHidden.setText(e_id);
        lblEditHidden.setVisibility(View.INVISIBLE);

    }

    private void fnUpdateData() {

        Runnable run = new Runnable() {
            @Override
            public void run() {
                String e_id, e_name, e_price;

                e_id = lblEditHidden.getText().toString();
                e_name = txtEditName.getText().toString();
                e_price = txtEditPrice.getText().toString();


                String strQuery = "Update "+ExpensesDB.tblname+" set "+ExpensesDB.colExpName+" = '"+e_name+"', " +
                        " "+ExpensesDB.colExpPrice+" = '"+e_price+"' " +
                        "Where "+ExpensesDB.colExpId+" = '"+e_id+"';";

                myDatabase.fnExecuteSql(strQuery, getApplicationContext());

                runOnUiThread(new Runnable(){
                    public void run(){
                        Toast showSuccess = Toast.makeText(getApplicationContext(), "Information successfully Saved!", Toast.LENGTH_LONG);
                        showSuccess.show();

                    }
                });
            }
        };

        Thread thr = new Thread(run);
        thr.start();
    }

    @Override
    public void onBackPressed() {

        Intent backToMain =  new Intent(this, ExpensesMainActivity.class);;
        backToMain.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        backToMain.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(backToMain);

    }
}
