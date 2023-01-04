package com.example.foodexpirationmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;


public class FEMDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "FEMDB.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_objType = "objType";
    private static final String COLUMN_name = "name";
    private static final String COLUMN_tag = "tag";
    private static final String COLUMN_buyDate = "buyDate";
    private static final String COLUMN_expiration = "expiration";
    private static final String COLUMN_num = "num";
    private static final String COLUMN_ps = "ps";
    private static final String COLUMN_archived = "archived";


    public FEMDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      寫出sqlite的query
        String ctSQL =  "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_objType + " TEXT NOT NULL, "
                + COLUMN_name + " TEXT NOT NULL, "
                + COLUMN_tag + " TEXT, "
                + COLUMN_buyDate + " TEXT, "
                + COLUMN_expiration + " TEXT NOT NULL, "
                + COLUMN_num + " INTEGER NOT NULL, "
                + COLUMN_ps + " TEXT, "
                + COLUMN_archived + " INTEGER NOT NULL DEFAULT 0 )";
//      執行以上sql語句 & debug
        db.execSQL(ctSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // changer data id = -1 是insert 其他數字則是update
    void changeData(int id , String objType, String name , String tag, String buyDate , String expiration , int num , String ps, int archived){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        long result = -1; //預設失敗
        cv.put(COLUMN_objType,objType);
        cv.put(COLUMN_name,name);
        cv.put(COLUMN_tag,tag);
        cv.put(COLUMN_buyDate,buyDate);
        cv.put(COLUMN_expiration,expiration);
        cv.put(COLUMN_num,num);
        cv.put(COLUMN_ps,ps);
        cv.put(COLUMN_archived,archived);

        //i = 0 → insert
        //i = n → update
        if(id == -1) {
            result = db.insert(TABLE_NAME, null, cv);
        }
        else {
            result = db.update(TABLE_NAME,cv,"ID = " + id ,null);
        }

        if (result == -1 ){
            Toast.makeText(context,"Failed>:(",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"YES! :)",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor selectData(){
        //SQL1 select出所有food且以未封存→已封存+有效日期由小到大排序
        String SQL1 = "SELECT * FROM " + TABLE_NAME
              //+ " WHERE archived =  0"
                + " ORDER BY archived ASC ,"
                + COLUMN_expiration + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db!=null){
                cursor = db.rawQuery(SQL1, null);
        }
        return cursor;
    }
    Cursor selectGivenData(){
        String SQL1 = "SELECT * FROM " + TABLE_NAME
                + " WHERE archived = 0 "
                + " ORDER BY " + COLUMN_expiration + " ASC" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db!=null){

            cursor = db.rawQuery(SQL1, null);
        }
        return cursor;
    }

}
