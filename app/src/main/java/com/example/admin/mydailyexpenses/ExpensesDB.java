package com.example.admin.mydailyexpenses;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by admin on 3/24/2017.
 */

public class ExpensesDB extends SQLiteOpenHelper {

    public static final String dbName = "financenew";
    public static final String tblname = "chapter";
    public static final String colExpName = "chapter_name";
    public static final String colExpPrice = "chapter_notes";
    public static final String colExpId = "chapter_id";


    public ExpensesDB(Context context) {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+tblname+" ("+colExpId+" VARCHAR,"+colExpName+" " +
                "VARCHAR,"+colExpPrice+" VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS expenses");
    }

    public Cursor getDataById (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("Select * From "+tblname+" where "+colExpId+"+ "+id,null);

        return cur;
    }

    public void fnExecuteSql(String strSql,Context appContext){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(strSql);
        } catch (Exception e) {
            Log.d("Unnable To Runn Query", "Error!");
        }
    }

    public  int fnTotalRow(){
        int intRow;
        SQLiteDatabase db = this.getReadableDatabase();
        intRow = (int) DatabaseUtils.queryNumEntries(db,tblname);

        return intRow;
    }

}
