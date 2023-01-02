package com.example.foodexpirationmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class dataInsertPageActivity extends Activity {
    //選單
    private Button menuButton;
    private LinearLayout menuLayout;
    private boolean menu_Bool=false;
    private TextView homeButton;
    private TextView totalButton;
    private TextView searchButton;
    private TextView formButton;
    //類別選擇
    private RadioGroup sort1RadioGroup;
    private RadioGroup sort2RadioGroup;
    private RadioButton drinkRadioButton;
    private RadioButton dessertRadioButton;
    private RadioButton freshRadioButton;
    private RadioButton foodRadioButton;
    private RadioButton sauceRadioButton;
    private RadioButton otherRadioButton;
    //驗證控制項
    private EditText itemNameEditText;
    private EditText tagNameEditText;
    private TextView quantAddTextView;
    private EditText quantityEditText;
    private TextView quantSubTextView;
    private TextView shopDateTextView;
    private TextView effectiveDateTextView;
    private EditText noteEditText;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Calendar calendar = Calendar.getInstance();
    private int dateChoose=0,dateChecked=0;
    private Button sentButton;
    private TextView starErrorTextView;
    private Button cancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_insert_page);

        menuButton=findViewById(R.id.menu_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);
        sort1RadioGroup=findViewById(R.id.sort1_RadioGroup);
        sort2RadioGroup=findViewById(R.id.sort2_RadioGroup);
        drinkRadioButton=findViewById(R.id.drink_RadioButton);
        dessertRadioButton=findViewById(R.id.dessert_RadioButton);
        freshRadioButton=findViewById(R.id.fresh_RadioButton);
        foodRadioButton=findViewById(R.id.food_RadioButton);
        sauceRadioButton=findViewById(R.id.sauce_RadioButton);
        otherRadioButton=findViewById(R.id.other_RadioButton);
        itemNameEditText=findViewById(R.id.itemname_EditText);
        tagNameEditText=findViewById(R.id.tagname_EditText);
        quantAddTextView=findViewById(R.id.quantity_add_TextView);
        quantityEditText=findViewById(R.id.quantity_EditText);
        quantSubTextView=findViewById(R.id.quantity_sub_TextView);
        shopDateTextView=findViewById(R.id.shopdate_TextView);
        effectiveDateTextView=findViewById(R.id.effectivedate_TextView);
        noteEditText=findViewById(R.id.note_EditText);
        sentButton=findViewById(R.id.sent_Button);
        starErrorTextView=findViewById(R.id.star_error_TextView);
        cancelButton=findViewById(R.id.cancel_Button);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,listPageActivity.class);
                startActivity(i);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,searchPageActivity.class);
                startActivity(i);
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,formPageActivity.class);
                startActivity(i);
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
        //類別選擇
        sort1RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                foodRadioButton.setChecked(false);
                sauceRadioButton.setChecked(false);
                otherRadioButton.setChecked(false);
            }
        });
        sort2RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                drinkRadioButton.setChecked(false);
                dessertRadioButton.setChecked(false);
                freshRadioButton.setChecked(false);
            }
        });
        //數量增減
        quantSubTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity=Integer.parseInt(quantityEditText.getText().toString());
                if (quantity > 1){
                    quantity=quantity-1;
                    quantityEditText.setText(Integer.toString(quantity));
                }
            }
        });
        quantAddTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity=Integer.parseInt(quantityEditText.getText().toString());
                quantity=quantity+1;
                quantityEditText.setText(Integer.toString(quantity));

            }
        });
        //選擇日期
        datePicker = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy/MM/dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
                if(dateChoose==1){
                    shopDateTextView.setText(sdf.format(calendar.getTime()));
                }else if(dateChoose==2){
                    effectiveDateTextView.setText(sdf.format(calendar.getTime()));
                    dateChecked=1;
                }

            }

        };
        shopDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateChoose=1;
                DatePickerDialog dialog = new DatePickerDialog(dataInsertPageActivity.this,
                        datePicker,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        effectiveDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateChoose=2;
                DatePickerDialog dialog = new DatePickerDialog(dataInsertPageActivity.this,
                        datePicker,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        //資料送出
        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!itemNameEditText.getText().toString().isEmpty() && dateChecked!=0){
                    starErrorTextView.setTextColor(Color.rgb(170,170,170));
                    //送出新增資訊

                    //清除目前資料
                    drinkRadioButton.isChecked();
                    itemNameEditText.setText("");
                    tagNameEditText.setText("");
                    quantityEditText.setText("1");
                    shopDateTextView.setHint("選擇日期");
                    shopDateTextView.setText("");
                    effectiveDateTextView.setHint("選擇日期");
                    effectiveDateTextView.setText("");
                    dateChecked=0;
                    noteEditText.setText("");
                    Toast toast=Toast.makeText(getApplicationContext(),"新增成功",Toast.LENGTH_SHORT);
                    toast.show();
                }else{
                    starErrorTextView.setTextColor(Color.rgb(255,0,0));
                }
            }
        });

        //取消按鍵
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataInsertPageActivity.this.finish();
            }
        });

    }

}