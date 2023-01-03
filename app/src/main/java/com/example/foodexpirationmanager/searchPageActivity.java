package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;
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
    private LinearLayout menuLayout;
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

        //到期日的單選
        dateDistanceRadioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                date14RadioButton.setChecked(false);
                date30RadioButton.setChecked(false);
                date60RadioButton.setChecked(false);
            }
        });
        dateDistanceRadioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                date0RadioButton.setChecked(false);
                date5RadioButton.setChecked(false);
                date7RadioButton.setChecked(false);
            }
        });

    }
    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){
        Intent i=new Intent(searchPageActivity.this,MainActivity.class);
        startActivity(i);
        this.finish();
    }
}