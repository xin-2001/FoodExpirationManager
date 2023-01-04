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
import java.util.HashMap;

public class listPageActivity extends Activity {
    private Button menuButton,addFoodButton;
    private TextView homeButton,totalButton,searchButton,formButton,IdTextView;

    private LinearLayout menuLayout;
    private boolean menu_Bool=false;

    private RecyclerView recyclerView;

    // db_helper
    FEMDatabaseHelper DB;
    // array test
    ArrayList<String> ID,objType,name,tag,buyDate,expiration,num,ps,archived;
    GoodAdapter goodAdapter;
    // array test
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();//測試

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

        //儲存data到array等拿出來用
        storeDataToArrays();
        //啟用good adapter去拿取
        goodAdapter = new GoodAdapter(listPageActivity.this,ID,objType,name,tag,buyDate,expiration,num,ps,archived);
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
    void storeDataToArrays(){
        Cursor cursor = DB.selectData(1);
        if (cursor.getCount() == 0){
            Toast.makeText(this,"Failed>:(",Toast.LENGTH_SHORT).show();
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
        Intent i=new Intent(listPageActivity.this,MainActivity.class);
        startActivity(i);
        this.finish();
    }

}