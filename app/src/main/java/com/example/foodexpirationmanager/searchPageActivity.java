package com.example.foodexpirationmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class searchPageActivity extends Activity {
    private Button menuButton;
    private Button sqlsearchB;
    private LinearLayout menuLayout,searchMainLayout;
    private boolean menu_Bool=false;
    private TextView homeButton;
    private TextView totalButton;
    private TextView searchButton;
    private TextView formButton;
    private RadioGroup dateDistanceRadioGroup1;
    private RadioGroup dateDistanceRadioGroup2;
    private RadioButton date0RadioButton;
    private RadioButton date5RadioButton;
    private RadioButton date7RadioButton;
    private RadioButton date14RadioButton;
    private RadioButton date30RadioButton;
    private RadioButton date60RadioButton;
    private EditText keywordText;
    private CheckBox drinkCheckBox;
    private CheckBox freshCheckBox;
    private CheckBox foodCheckBox;
    private CheckBox sauceCheckBox;
    private CheckBox dessertCheckBox;
    private CheckBox otherCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);
        sqlsearchB = findViewById(R.id.sqlsearchButton);
        menuButton=findViewById(R.id.menu_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);
        dateDistanceRadioGroup1=findViewById(R.id.date_distance_RadioGroup1);
        dateDistanceRadioGroup2=findViewById(R.id.date_distance_RadioGroup2);
        date0RadioButton=findViewById(R.id.date0_RadioButton);
        date5RadioButton=findViewById(R.id.date5_RadioButton);
        date7RadioButton=findViewById(R.id.date7_RadioButton);
        date14RadioButton=findViewById(R.id.date14_RadioButton);
        date30RadioButton=findViewById(R.id.date30_RadioButton);
        date60RadioButton=findViewById(R.id.date60_RadioButton);
        drinkCheckBox=findViewById(R.id.drink_CheckBox);
        freshCheckBox=findViewById(R.id.fresh_CheckBox);
        foodCheckBox=findViewById(R.id.food_CheckBox);
        sauceCheckBox=findViewById(R.id.sauce_CheckBox);
        dessertCheckBox=findViewById(R.id.dessert_CheckBox);
        otherCheckBox=findViewById(R.id.other_CheckBox);
        searchMainLayout=findViewById(R.id.search_MainLayout);
        keywordText=findViewById(R.id.keywordseach_EditText);

        //選單
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchPageActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchPageActivity.this,listPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"您已在'搜尋'頁面",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchPageActivity.this,formPageActivity.class);
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
        searchMainLayout.setOnClickListener(new View.OnClickListener() {
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

        //到期日的單選
        date0RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(true);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(false);
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(false);
            }
        });
        date5RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(true);
                date7RadioButton.setChecked(false);
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(false);
            }
        });
        date7RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(true);
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(false);
            }
        });
        date14RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(false);
                date14RadioButton.setChecked(true);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(false);
            }
        });
        date30RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(false);
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(true);
                date60RadioButton.setChecked(false);
            }
        });
        date60RadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(false);
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(true);
            }
        });


        //select功能在這
        sqlsearchB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(searchPageActivity.this,listPageActivity.class);
                String keyword="",whichclass="",timelimit="-1";

                if (!keywordText.getText().toString().isEmpty()){
                keyword = keywordText.getText().toString().trim();
                if (keyword.charAt(0) == '#' ){
                    //this is tag but name
                    keyword = keyword.substring(1);
                    i.putExtra("tag",keyword);
                    i.putExtra("name","NULL");
                }else{
                    //this is name but tag
                    i.putExtra("tag","NULL");
                    i.putExtra("name",keyword);
                }
                }
                // drinkCheckBox freshCheckBox foodCheckBox sauceCheckBox dessertCheckBox otherCheckBox;
                if(drinkCheckBox.isChecked()){
                    whichclass = whichclass + "0";
                }
                if(freshCheckBox.isChecked()){
                    whichclass = whichclass + "1";
                }
                if(foodCheckBox.isChecked()){
                    whichclass = whichclass + "2";
                }
                if(sauceCheckBox.isChecked()){
                    whichclass = whichclass + "3";
                }
                if(dessertCheckBox.isChecked()){
                    whichclass = whichclass + "4";
                }
                if(otherCheckBox.isChecked()){
                    whichclass = whichclass + "5";
                }
                i.putExtra("whichclass",whichclass);
                //radio
                if(date0RadioButton.isChecked()){
                    timelimit = "0";
                }if(date5RadioButton.isChecked()){
                    timelimit = "5";
                }if(date7RadioButton.isChecked()){
                    timelimit = "7";
                }if(date14RadioButton.isChecked()){
                    timelimit = "14";
                }if(date30RadioButton.isChecked()){
                    timelimit = "30";
                }if(date60RadioButton.isChecked()){
                    timelimit = "60";
                }
                // if timelimit = -1 代表沒有選篩選
                i.putExtra("timelimit",timelimit);



                Toast toast=Toast.makeText(getApplicationContext(),keyword+whichclass+timelimit,Toast.LENGTH_SHORT);
                toast.show();

                startActivity(i);
                finish();
            }
        });

    }
    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){
        if(menu_Bool==true) {
            menuLayout.setTranslationX(-1);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menuLayout.getLayoutParams();
            params.width = 1;
            menuLayout.setLayoutParams(params);
            menu_Bool = false;
        }else {
            Intent i = new Intent(searchPageActivity.this, MainActivity.class);
            startActivity(i);
            this.finish();
        }
    }
}