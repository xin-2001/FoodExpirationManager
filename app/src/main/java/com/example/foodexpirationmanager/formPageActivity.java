package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class formPageActivity extends Activity {

    private LinearLayout menuLayout;
    private boolean menu_Bool=false;
    private Button menuButton,sentButton;
    private TextView homeButton,totalButton,searchButton,formButton,errorTextView;
    private EditText userNameEditText,userEmailEditText,userQusContentEditText;
    private RadioButton questionRadioButton,suggestionRadioButton;

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
                    String nickname = userNameEditText.getText().toString();
                    String email = userEmailEditText.getText().toString();
                    String content = userQusContentEditText.getText().toString();
                    String fbClass = "提問";
                    if (suggestionRadioButton.isChecked() == true ){
                        fbClass = "建議";
                    }
                    errorTextView.setText("已將您的提問送出");
                    feedbackData(nickname,email,fbClass,content);

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

    private void feedbackData(String nickname, String email, String fbClass, String content) {
        //google app script 部署後的
        String url = "https://script.google.com/macros/s/AKfycbwAvJ0gNfEFnnXrIM7qqAnSbEReZych_YlzE298MD1mRKtrF6rUYgq4rFaMFLfDnycUZQ/exec?";
        //串接get需要的東西
        url = url + "action=create"
                   +"&nickname="+nickname
                   +"&email="+email
                   +"&fbClass="+fbClass
                   +"&content="+content;
        //下去做get
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(formPageActivity.this, response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(formPageActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){

    }
}