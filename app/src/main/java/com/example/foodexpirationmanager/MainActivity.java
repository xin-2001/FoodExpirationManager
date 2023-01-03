package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends Activity {

    private Button menuButton;
    private Button addFoodButton;
    private LinearLayout menuLayout;
    private boolean menu_Bool=false;
    private TextView homeButton;
    private TextView totalButton;
    private TextView searchButton;
    private TextView formButton;
    private RecyclerView homeRecyclerView;
    private HomeListAdapter homeListAdapter;

    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();//測試

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuButton=findViewById(R.id.menu_Button);
        addFoodButton=findViewById(R.id.addfood_Button);
        menuLayout=(LinearLayout)findViewById(R.id.menu_Layout);
        homeButton=findViewById(R.id.home_Button);
        totalButton=findViewById(R.id.total_Button);
        searchButton=findViewById(R.id.search_Button);
        formButton=findViewById(R.id.form_Button);

        makeData();
        //設置RecycleView
        homeRecyclerView = findViewById(R.id.home_recycleview);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homeRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        homeListAdapter = new HomeListAdapter();
        // Linear型態，第二個參數控制垂直或水平，第三個參數為是否reverse順序
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // Grid型態，第二個參數控制一列顯示幾項
        homeRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        homeRecyclerView.setAdapter(homeListAdapter);



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast.makeText(getApplicationContext(),"您已在'主頁'頁面",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,listPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,searchPageActivity.class);
                startActivity(i);
                finish();
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,formPageActivity.class);
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
                Intent i=new Intent(MainActivity.this,dataInsertPageActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    //測試------------------------------------------------
    private void makeData() {
        String[] sort;
        sort=new String[]{"deli","dessert","sauce","freshfood","otherfoodicon"};
        for (int i=1;i<=8;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",String.valueOf(i));
            hashMap.put("sort",sort[i%5]);
            hashMap.put("name","pizza");
            hashMap.put("date","2023/01/0"+i);
            arrayList.add(hashMap);
        }

    }

    //------------------------------------------------
    private class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder>{


        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView sortIdTextView,itemNameTextView,dateTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                sortIdTextView = itemView.findViewById(R.id.sort_photo_TextView);
                itemNameTextView = itemView.findViewById(R.id.item_Name_TextView);
                dateTextView  = itemView.findViewById(R.id.date_TextView);
            }
        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.home_page_recycler,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String photo;
            int[] sort_images;
            int photo_num=0;
            sort_images = new int[]{R.drawable.deli, R.drawable.sauce,
                    R.drawable.freshfood, R.drawable.dessert,R.drawable.otherfoodicon};

            photo=arrayList.get(position).get("sort");
            if(photo=="deli")photo_num=0;
            if(photo=="sauce")photo_num=1;
            if(photo=="freshfood")photo_num=2;
            if(photo=="dessert")photo_num=3;
            if(photo=="otherfoodicon")photo_num=4;
            holder.sortIdTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), sort_images[photo_num]));

            holder.itemNameTextView.setText(arrayList.get(position).get("name"));
            holder.dateTextView.setText(arrayList.get(position).get("date"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }
}