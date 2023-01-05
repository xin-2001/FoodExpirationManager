package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class listPageActivity extends Activity {
    private Button menuButton,addFoodButton;
    private TextView homeButton,totalButton,searchButton,formButton,IdTextView;
    private String SQL="-1";
    private LinearLayout menuLayout,listMainLayout;
    private boolean menu_Bool=false;

    private RecyclerView recyclerView;

    // db_helper
    FEMDatabaseHelper DB;
    // array test

    ArrayList<String> ID,objType,name,tag,buyDate,expiration,num,ps,archived,timelimit;
    GoodAdapter goodAdapter;
    // array test
    //ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();//測試

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_page);

        menuButton=findViewById(R.id.menu_Button);
        addFoodButton=findViewById(R.id.addfood_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);
        recyclerView = findViewById(R.id.list_recycleview);
        IdTextView=findViewById(R.id.ID_TextView);
        listMainLayout=findViewById(R.id.list_MainLayout);

        // DATABASE;
        DB = new FEMDatabaseHelper(listPageActivity.this);
        ID = new ArrayList<>();
        objType= new ArrayList<>();
        name= new ArrayList<>();
        tag= new ArrayList<>();
        buyDate= new ArrayList<>();
        expiration= new ArrayList<>();
        num= new ArrayList<>();
        ps= new ArrayList<>();
        archived= new ArrayList<>();
        timelimit = new ArrayList<>();

        //for search
        String TAG,NAME,whichclass,LIMIT;




        //接收資料
        // TAG,NAME,whichclass,timelimit
        Intent intent=getIntent();
        TAG=intent.getStringExtra("tag");
        NAME=intent.getStringExtra("name");
        /*
        if (TAG.equals("NULL")){
            NAME=intent.getStringExtra("name");
        if(NAME.equals("NULL")){
            TAG=intent.getStringExtra("tag");
        }
        */

        whichclass=intent.getStringExtra("whichclass");
        LIMIT=intent.getStringExtra("timelimit");
        String X = TAG+NAME+whichclass+LIMIT;
        //Toast toast=Toast.makeText(getApplicationContext(),"FOT"+X,Toast.LENGTH_SHORT);
        //toast.show();

        if(X!="nullnull-1"){
            SQL = sqlMaker(TAG,NAME,whichclass,LIMIT);
        }
        //toast=Toast.makeText(getApplicationContext(),SQL,Toast.LENGTH_SHORT);
        //toast.show();




        //儲存data到array等拿出來用
        if(SQL ==  "SELECT * , julianday(expiration) - julianday(date('now','start of day')) AS timelimit"
                + " FROM food"
                + " WHERE ") {
            //沒SQL
            storeDataToArrays(0,"");
        }
        else {
            //自帶SQL
            storeDataToArrays(1,SQL);
        }
        //啟用good adapter去拿取
        goodAdapter = new GoodAdapter(listPageActivity.this,ID,objType,name,tag,buyDate,expiration,num,ps,archived,timelimit);
        recyclerView.setAdapter(goodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(listPageActivity.this));



        //目錄頁
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"您已在'總覽'頁面",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,searchPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,formPageActivity.class);
                startActivity(i);
                finish();
            }
        });



        //目錄的開啟及收合
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu_Bool==false){
                    menuLayout.setTranslationX(0);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 500;
                    menuLayout.setLayoutParams(params);
                    menu_Bool=true;
                }else{
                    menuLayout.setTranslationX(-1);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 1;
                    menuLayout.setLayoutParams(params);
                    menu_Bool=false;
                    //還要寫一個點其他地方目錄收起來的
                }


            }
        });
        //點其他地方目錄收起來的
        listMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu_Bool==true) {
                    menuLayout.setTranslationX(-1);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
                    params.width = 1;
                    menuLayout.setLayoutParams(params);
                    menu_Bool = false;
                }
            }
        });
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,dataInsertPageActivity.class);
                i.putExtra("ID","NULL");
                startActivity(i);
                finish();
            }
        });




    }

    private String sqlMaker(String tag, String name, String whichclass, String timelimit) {
        /*
        String SQL2 = "SELECT * , julianday(expiration) - julianday(date('now','start of day')) AS timelimit"
                + " FROM " + TABLE_NAME
                + " WHERE archived = 0 "
                + " AND timelimit <= 7"
                + " ORDER BY " + COLUMN_expiration + " ASC"
                ;
        */
        String SQL3="";
        int p_2=-1,p=-1;
        String part1="",part2="",part3="",x="";
        String debug="";

        if(Objects.equals(tag, "NULL")){
            part1 = "name LIKE '%" + name + "%' ";
            p=p+1;
            debug = debug +"z";
        }
        if(Objects.equals(name, "NULL")){
            part1 = "tag LIKE '%" + tag + "%' ";
            p=p+1;
            debug = debug +"z";
        }
        if(whichclass!=null) {
            if (whichclass.indexOf("0") != -1) {
                p_2 = p_2 + 1;
                x = "objType = 'drink'";
                debug = debug + "b";
            }
            if (whichclass.indexOf("1") != -1) {
                p_2 = p_2 + 1;
                if (p_2 == 0) {
                    x = "objType = 'freshfood'";
                } else {
                    x = x + " OR objType = 'freshfood'";
                }
                debug = debug + "c";
            }
            if (whichclass.indexOf("2") != -1) {
                p_2 = p_2 + 1;
                if (p_2 == 0) {
                    x = "objType = 'deli'";
                } else {
                    x = x + " OR objType = 'deli'";
                }
                debug = debug + "d";
            }
            if (whichclass.indexOf("3") != -1) {
                p_2 = p_2 + 1;
                if (p_2 == 0) {
                    x = "objType = 'sauce'";
                } else {
                    x = x + " OR objType = 'sauce'";
                }
                debug = debug + "e";
            }
            if (whichclass.indexOf("4") != -1) {
                p_2 = p_2 + 1;
                if (p_2 == 0) {
                    x = "objType = 'dessert'";
                } else {
                    x = x + " OR objType = 'dessert'";
                }
                debug = debug + "f";
            }
            if (whichclass.indexOf("5") != -1) {
                p_2 = p_2 + 1;
                if (p_2 == 0) {
                    x = "objType = 'otherfood'";
                } else {
                    x = x + " OR objType = 'otherfood'";
                }
                debug = debug + "g";
            }
        }
        if(p_2>=0){
            p=p+1;
            part2 = x;
        }

        if(p==1){
            x = part1 +" OR "+ part2 ;
            debug = debug+"x";
        }

        if(timelimit!=null) {
            if (timelimit.equals("0")) {
                part3 = "timelimit <= 0";
                p = p + 1;
                debug = debug + "h";
            }
            if (timelimit.equals("5")) {
                part3 = "timelimit <= 5";
                p = p + 1;
                debug = debug + "h";
            }
            if (timelimit.equals("7")) {
                part3 = "timelimit <= 7";
                p = p + 1;
                debug = debug + "h";
            }
            if (timelimit.equals("14")) {
                part3 = "timelimit <= 14";
                p = p + 1;
                debug = debug + "h";
            }
            if (timelimit.equals("30")) {
                part3 = "timelimit <= 30";
                p = p + 1;
                debug = debug + "h";
            }
            if (timelimit.equals("60")) {
                part3 = "timelimit <= 60";
                p = p + 1;
                debug = debug + "h";
            }
        }

        //Toast.makeText(this,debug,Toast.LENGTH_SHORT).show();


        SQL3 = "SELECT * , julianday(expiration) - julianday(date('now','start of day')) AS timelimit"
                + " FROM food"
                + " WHERE "
                ;
        if(p==2){
            SQL3 = SQL3 + part1 + " OR (" + part2 + " AND " +part3 +")";
        }
        if(p==1){
            if(debug.indexOf('x') != -1){
                SQL3 = SQL3 + x ;  //part1+part2
            }
            else{
                if(debug.indexOf('a') != -1){
                    SQL3 = SQL3 + part1 + " OR " + part3;//part1+part3
                }
                else{
                    SQL3 = SQL3 + part2 + " AND " + part3;//part2+part3
                }
            }
        }
        if(p==0){
            if(debug.indexOf('z') != -1){
                //part1
                SQL3 = SQL3 + part1;
            }
            else if(debug.indexOf('h') != -1){
                //part3
                SQL3 = SQL3 + part3;
            }
            else {
                SQL3 = SQL3 +part2;
            }
        }
        return SQL3;

    }

    void storeDataToArrays(int x,String SQL){
        Cursor cursor;
        if(x == 1){
            cursor= DB.selectData(3,SQL);
        }
        else {
            cursor = DB.selectData(1, "");
        }
        if (cursor.getCount() == 0){
            //Toast.makeText(this,"Failed>:(",Toast.LENGTH_SHORT).show();
        }else{
            //ID,objType,name,tag,buyDate,expiration,num,ps,archived
            while(cursor.moveToNext()){
                ID.add(cursor.getString(0));
                objType.add(cursor.getString(1));
                name.add(cursor.getString(2));
                tag.add(cursor.getString(3));
                buyDate.add(cursor.getString(4));
                expiration.add(cursor.getString(5));
                num.add(cursor.getString(6));
                ps.add(cursor.getString(7));
                archived.add(cursor.getString(8));
            }
        }

    }


    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){
        if(menu_Bool==true) {
            menuLayout.setTranslationX(-1);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
            params.width = 1;
            menuLayout.setLayoutParams(params);
            menu_Bool = false;
        }else{
            Intent i=new Intent(listPageActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

    }

}