package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toolbar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class listPageActivity extends Activity {
    private Button menuButton;
    private Button addFoodButton;
    private LinearLayout menuLayout;
    private boolean menu_Bool=false;
    private TextView homeButton;
    private TextView totalButton;
    private TextView searchButton;
    private TextView formButton;
    private RecyclerView totalRecyclerView;
    private TotalListAdapter totalListAdapter;
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



        makeData();
        //設置RecycleView
        totalRecyclerView = findViewById(R.id.list_recycleview);
        totalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        totalListAdapter = new TotalListAdapter();
        totalRecyclerView.setAdapter(totalListAdapter);

        //目錄頁
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,MainActivity.class);
                startActivity(i);
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
            }
        });
        formButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,formPageActivity.class);
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
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(listPageActivity.this,dataInsertPageActivity.class);
                startActivity(i);
            }
        });




    }
    //測試------------------------------------------------
    private void makeData() {
        String[] sort;
        sort=new String[]{"deli","dessert","sauce","freshfood","otherfoodicon"};
        for (int i=1;i<=9;i++){
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("id",String.valueOf(i));
            hashMap.put("photo",sort[i%5]);
            hashMap.put("name","pizza");
            hashMap.put("quantity","5");
            hashMap.put("shopdate","2022/11/0"+i);
            hashMap.put("effectivedate","2023/05/0"+i);
            hashMap.put("tag","123");
            arrayList.add(hashMap);
        }

    }

    //------------------------------------------------
    private class TotalListAdapter extends RecyclerView.Adapter<TotalListAdapter.ViewHolder>{


        class ViewHolder extends RecyclerView.ViewHolder{
            private TextView listPhotoTextView,listNameTextView,listShopdateTextView,
                    listQuantityTextView,listEffectivedateTextView,listTagTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                listPhotoTextView = itemView.findViewById(R.id.list_photo_TextView);
                listNameTextView = itemView.findViewById(R.id.list_Name_TextView);
                listShopdateTextView  = itemView.findViewById(R.id.list_ShopDate_TextView);
                listQuantityTextView = itemView.findViewById(R.id.list_Quantity_TextView);
                listEffectivedateTextView = itemView.findViewById(R.id.list_Effectivedate_TextView);
                listTagTextView  = itemView.findViewById(R.id.list_Tag_TextView);
            }
        }
        @NonNull
        @Override
        public TotalListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_page_recycler,parent,false);
            return new TotalListAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TotalListAdapter.ViewHolder holder, int position) {
            String photo;
            int[] sort_images;
            int photo_num=0;
            sort_images = new int[]{R.drawable.deli, R.drawable.sauce,
                    R.drawable.freshfood, R.drawable.dessert,R.drawable.otherfoodicon};

            photo=arrayList.get(position).get("photo");
            if(photo=="deli")photo_num=0;
            if(photo=="sauce")photo_num=1;
            if(photo=="freshfood")photo_num=2;
            if(photo=="dessert")photo_num=3;
            if(photo=="otherfoodicon")photo_num=4;
            holder.listPhotoTextView.setBackground(ContextCompat.getDrawable(getApplicationContext(), sort_images[photo_num]));

            holder.listNameTextView.setText(arrayList.get(position).get("name"));
            holder.listEffectivedateTextView.append(arrayList.get(position).get("effectivedate"));
            holder.listQuantityTextView.append(arrayList.get(position).get("quantity"));
            holder.listShopdateTextView.append(arrayList.get(position).get("shopdate"));
            holder.listTagTextView.setText(arrayList.get(position).get("tag"));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

}