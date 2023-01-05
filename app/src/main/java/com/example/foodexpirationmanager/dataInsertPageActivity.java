package com.example.foodexpirationmanager;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
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

import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class dataInsertPageActivity extends Activity {
    //選單
    private Button menuButton;
    private LinearLayout menuLayout,dataInsertLayout;
    private boolean menu_Bool=false;
    private TextView homeButton,totalButton,searchButton,formButton;
    //類別選擇
    private LinearLayout sort1RadioGroup,sort2RadioGroup;
    private LinearLayout drinkRadioButton,dessertRadioButton,freshRadioButton;
    private LinearLayout foodRadioButton,sauceRadioButton,otherRadioButton;
    private TextView foodPhotoTextView,dessertPhotoTextView,freshPhotoTextView;
    private TextView drinkPhotoTextView,saucePhotoTextView,otherPhotoTextView;
    private String sort_now;
    //驗證控制項
    private EditText itemNameEditText,tagNameEditText,quantityEditText,noteEditText;
    private TextView quantAddTextView,quantSubTextView,shopDateTextView,effectiveDateTextView;
    private TextView starErrorTextView;
    private DatePickerDialog.OnDateSetListener datePicker;
    private Calendar calendar = Calendar.getInstance();
    private int dateChoose=0,dateChecked=0;
    private Button sentButton,cancelButton;
    private String ID,objType,name,tag,buyDate,expiration,num,ps,archived;


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
        foodPhotoTextView=findViewById(R.id.food_Photo_TextView);
        dessertPhotoTextView=findViewById(R.id.dessert_Photo_TextView);
        freshPhotoTextView=findViewById(R.id.fresh_Photo_TextView);
        drinkPhotoTextView=findViewById(R.id.drink_Photo_TextView);
        saucePhotoTextView=findViewById(R.id.sauce_Photo_TextView);
        otherPhotoTextView=findViewById(R.id.other_Photo_TextView);
        dataInsertLayout=findViewById(R.id.dataInsert_MainLinearLayout);

        //類別預設選取
        //foodRadioButton.setChecked(true);
        initBG();
        foodPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.deli_picked));
        sort_now="deli";

        //接收資料
        Intent intent=getIntent();
        ID=intent.getStringExtra("ID");
        if (ID.equals("NULL")){
            ID="-1";
            archived = "0";
            cancelButton.setText("取消");
        }else{
            cancelButton.setText("刪除");
            objType=intent.getStringExtra("objType");
            name=intent.getStringExtra("name");
            tag=intent.getStringExtra("tag");
            buyDate=intent.getStringExtra("buyDate");
            expiration=intent.getStringExtra("expiration");
            num=intent.getStringExtra("num");
            ps=intent.getStringExtra("ps");
            archived=intent.getStringExtra("archived");

            //類型辨識
            typeDetector();

            itemNameEditText.setText(name);
            tagNameEditText.setText(tag);
            quantityEditText.setText(num);
            shopDateTextView.setText(buyDate);
            effectiveDateTextView.setText(expiration);
            noteEditText.setText(ps);
            dateChecked=1;
        }




        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,listPageActivity.class);
                i.putExtra("whichclass","a");
                startActivity(i);
                finish();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,searchPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(dataInsertPageActivity.this,formPageActivity.class);
                startActivity(i);
                finish();
            }
        });



        //目錄的開啟及收合
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!menu_Bool){
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
        dataInsertLayout.setOnClickListener(new View.OnClickListener() {
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
        //類別選擇
        drinkRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                drinkPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.drink_picked));
                sort_now="drink";
            }
        });
        sauceRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                saucePhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sauce_picked));
                sort_now="sauce";
            }
        });
        foodRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                foodPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.deli_picked));
                sort_now="deli";
            }
        });
        freshRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                freshPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.freshfood_picked));
                sort_now="freshfood";
            }
        });
        dessertRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                dessertPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dessert_picked));
                sort_now="dessert";
            }
        });
        otherRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBG();
                otherPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.otherfoodicon_picked));
                sort_now="otherfoodicon";
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
                String myFormat = "yyyy-MM-dd";
                String myFormat2 = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.TAIWAN);
                SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.TAIWAN);
                if(dateChoose==1){
                    shopDateTextView.setText(sdf.format(calendar.getTime()));
                }else if(dateChoose==2){
                    effectiveDateTextView.setText(sdf2.format(calendar.getTime()));
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
                if(!itemNameEditText.getText().toString().isEmpty() && dateChecked!=0 && !(quantityEditText.getText().toString() =="0")){
                    starErrorTextView.setTextColor(Color.rgb(170,170,170));


                    //送出新增資訊
                    FEMDatabaseHelper DB = new FEMDatabaseHelper(dataInsertPageActivity.this);
                    String objType = "";
                    int classCounter = 1 ;
                    objType=sort_now;


                    // if counter correct insert data
                    if(classCounter == 1) {
                        DB.changeData(Integer.parseInt(ID),
                                objType.trim(),
                                itemNameEditText.getText().toString().trim(),
                                tagNameEditText.getText().toString().trim(),
                                shopDateTextView.getText().toString().trim(),
                                effectiveDateTextView.getText().toString().trim(),
                                Integer.valueOf(quantityEditText.getText().toString().trim()),
                                noteEditText.getText().toString().trim(),
                                Integer.parseInt(archived)
                        );
                        //這樣archive有若封存後修改會造成錯誤的問題<不確定>
                    }
                    Toast toast=Toast.makeText(getApplicationContext(),"新增成功",Toast.LENGTH_SHORT);
                    toast.show();
                    Intent i=new Intent(dataInsertPageActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();






                }else{
                    starErrorTextView.setTextColor(Color.rgb(255,0,0));
                }

            }
        });

        //取消按鍵
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ID == "-1") { //取消功能
                    //取消的頁面我移動到下面
                }
                else{//刪除功能
                    FEMDatabaseHelper DB = new FEMDatabaseHelper(dataInsertPageActivity.this);
                    DB.deleteData(Integer.parseInt(ID));
                }
                Intent i = new Intent(dataInsertPageActivity.this, listPageActivity.class);
                startActivity(i);
                finish();
            }
        });



    }

    //類別辨識
    //上面程式碼太長，移來下面
    private void typeDetector() {
        if(objType.equals("drink")){
            initBG();
            drinkPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.drink_picked));
            sort_now="drink";
        }
        if(objType.equals("deli")){
            initBG();
            foodPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.deli_picked));
            sort_now="deli";
        }
        if(objType.equals("dessert")){
            initBG();dessertPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dessert_picked));
            sort_now="dessert";
        }
        if(objType.equals("sauce")){
            initBG();
            saucePhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sauce_picked));
            sort_now="sauce";
        }
        if(objType.equals("freshfood")){
            initBG();
            freshPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.freshfood_picked));
            sort_now="freshfood";
        }
        if(objType.equals("otherfoodicon")){
            initBG();
            otherPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.otherfoodicon_picked));
            sort_now="otherfoodicon";
        }
    }

    //讓全部類別都回歸未被選取
    private void initBG() {
        drinkPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.drink));
        saucePhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.sauce));
        foodPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.deli));
        freshPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.freshfood));
        dessertPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.dessert));
        otherPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.otherfoodicon));
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
            Intent i=new Intent(dataInsertPageActivity.this,MainActivity.class);
            startActivity(i);
            this.finish();
        }

    }

}