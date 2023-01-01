package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class formPageActivity extends Activity {

    private Button menuButton;
    private LinearLayout menuLayout;
    private boolean menu_Bool=false;
    private TextView homeButton;
    private TextView totalButton;
    private TextView searchButton;
    private TextView formButton;
    private Button sentButton;
    private EditText userNameEditText;
    private EditText userEmailEditText;
    private RadioButton questionRadioButton;
    private RadioButton suggestionRadioButton;
    private EditText userQusContentEditText;
    private TextView errorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_page);

        menuButton=findViewById(R.id.menu_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);
        sentButton=findViewById(R.id.sent_Button);
        userNameEditText=findViewById(R.id.userNameEditText);
        userEmailEditText=findViewById(R.id.userEmailEditText);
        questionRadioButton=findViewById(R.id.questionRadioButton);
        suggestionRadioButton=findViewById(R.id.suggestionRadioButton);
        userQusContentEditText=findViewById(R.id.userQusContentEditText);
        errorTextView=findViewById(R.id.errorTextView);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,listPageActivity.class);
                startActivity(i);
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(formPageActivity.this,searchPageActivity.class);
                startActivity(i);
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"您已在'意見反映'頁面",Toast.LENGTH_SHORT);
                toast.show();
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

        //點擊送出按鈕時，檢查資料是否填寫
        sentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!userNameEditText.getText().toString().isEmpty() && !userEmailEditText.getText().toString().isEmpty() && !userQusContentEditText.getText().toString().isEmpty()){
                    errorTextView.setText("已將您的提問送出");
                    //送出提問/建議資訊

                    userNameEditText.setText("");
                    userEmailEditText.setText("");
                    userQusContentEditText.setText("");
                    questionRadioButton.setChecked(true);

                }else{
                    errorTextView.setText("請輸入");
                    if(userNameEditText.getText().toString().isEmpty()){
                        errorTextView.append("'暱稱' ");
                    }
                    if(userEmailEditText.getText().toString().isEmpty()){
                        errorTextView.append("'信箱' ");
                    }
                    if(userQusContentEditText.getText().toString().isEmpty()){
                        errorTextView.append("'反映內容'");
                    }
                }
            }
        });


    }
    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){

    }
}