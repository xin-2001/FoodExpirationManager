package com.example.foodexpirationmanager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
    private TextView IdTextView;
    private RecyclerView recyclerView;
    //private TotalListAdapter totalListAdapter;
    FEMDatabaseHelper DB;
    // array test
    ArrayList<String> ID,objType,name,tag,buyDate,expiration,num,ps,archived;
    GoodAdapter goodAdapter;
    // array test
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();//測試
    public String this_id,this_objType,this_name,this_tag,this_buyDate,this_expiration,this_num,this_ps,this_archived;

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

        //makeData();
        //儲存data到array等拿出來用
        storeDataToArrays();
        //啟用goodadapter去拿取
        goodAdapter = new GoodAdapter(listPageActivity.this,ID,objType,name,tag,buyDate,expiration,num,ps,archived);
        recyclerView.setAdapter(goodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(listPageActivity.this));

        //設置RecyclerView_舊
        /*
        totalRecyclerView = findViewById(R.id.list_recycleview);
        totalRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        totalListAdapter = new TotalListAdapter();
        totalRecyclerView.setAdapter(totalListAdapter);
        */
        //設置RecyclerView_舊
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
                startActivity(i);
                finish();
            }
        });
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Intent i=new Intent(listPageActivity.this,dataInsertPageActivity.class);
                //i.putExtra("ID",this_id);//報告完再繼續，這裡要put到下一頁，下一頁要寫判斷是從哪進來，以及接資料
                startActivity(i);
            }
        });






    }
    void storeDataToArrays(){
        Cursor cursor = DB.selectDATA();
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
        /*this_id=goodAdapter.this_id;
        this_objType=goodAdapter.this_objType;
        this_name=goodAdapter.this_name;
        this_tag=goodAdapter.this_tag;
        this_buyDate=goodAdapter.this_buyDate;
        this_expiration=goodAdapter.this_expiration;
        this_num=goodAdapter.this_num;
        this_ps=goodAdapter.this_ps;
        this_archived=goodAdapter.this_archived;*/

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
            private View listView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                listPhotoTextView = itemView.findViewById(R.id.list_photo_TextView);
                listNameTextView = itemView.findViewById(R.id.list_Name_TextView);
                listShopdateTextView  = itemView.findViewById(R.id.list_ShopDate_TextView);
                listQuantityTextView = itemView.findViewById(R.id.list_Quantity_TextView);
                listEffectivedateTextView = itemView.findViewById(R.id.list_Effectivedate_TextView);
                listTagTextView  = itemView.findViewById(R.id.list_Tag_TextView);
                listView  = itemView;
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
            if(photo=="drink")photo_num=5;
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

    //重寫onBackPressed，禁止手機內建上一頁功能
    public void onBackPressed(){
        Intent i=new Intent(listPageActivity.this,MainActivity.class);
        startActivity(i);
        this.finish();
    }

}