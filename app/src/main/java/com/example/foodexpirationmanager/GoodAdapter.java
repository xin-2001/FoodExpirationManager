package com.example.foodexpirationmanager;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//超讚處理器
public class GoodAdapter extends RecyclerView.Adapter<GoodAdapter.DataViewHolder> {

    private Context context;
    private ArrayList ID,objType,name,tag,buyDate,expiration,num,ps,archived;


    GoodAdapter(Context context,
                ArrayList ID,
                ArrayList objType,
                ArrayList name,
                ArrayList tag,
                ArrayList buyDate,
                ArrayList expiration,
                ArrayList num,
                ArrayList ps,
                ArrayList archived){
        this.context = context;
        this.ID = ID;
        this.objType = objType;
        this.name = name;
        this.tag = tag;
        this.buyDate = buyDate;
        this.expiration = expiration;
        this.num = num;
        this.ps = ps;
        this.archived = archived;

    }


    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_page_recycler,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GoodAdapter.DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //holder.list_photo_TextView.setText(String.valueOf(objType.get(position)));
        String photo;
        int[] sort_images;
        int photo_num=0;
        sort_images = new int[]{R.drawable.deli, R.drawable.sauce,
                R.drawable.freshfood, R.drawable.dessert,R.drawable.otherfoodicon,R.drawable.drink};

        photo=String.valueOf(objType.get(position));
        if(photo.equals("deli")) photo_num=0;
        if(photo.equals("sauce")) photo_num=1;
        if(photo.equals("freshfood"))photo_num=2;
        if(photo.equals("dessert"))photo_num=3;
        if(photo.equals("otherfoodicon"))photo_num=4;
        if(photo.equals("drink"))photo_num=5;

        //holder.list_photo_TextView.setText(String.valueOf(objType.get(position)));
        holder.list_photo_TextView.setBackground(ContextCompat.getDrawable(context.getApplicationContext(), sort_images[photo_num]));
        holder.list_Name_TextView.setText(String.valueOf(name.get(position)));
        holder.list_Quantity_TextView.append(String.valueOf(num.get(position)));
        holder.list_ShopDate_TextView.append(String.valueOf(buyDate.get(position)));
        holder.list_Effectivedate_TextView.append(String.valueOf(expiration.get(position)));
        holder.list_Tag_TextView.setText(String.valueOf(tag.get(position)));
        holder.list_ps_TextView.setText(String.valueOf(ps.get(position)));


        //item的點擊事件
        holder.itemView.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View view) {

                Intent i = new Intent ();
                i.setClass (view.getContext (),dataInsertPageActivity.class);
                i.putExtra("ID", String.valueOf(ID.get(position)));
                i.putExtra("objType", String.valueOf(objType.get(position)));
                i.putExtra("name",String.valueOf(name.get(position)));
                i.putExtra("tag",String.valueOf(tag.get(position)));
                i.putExtra("buyDate",String.valueOf(buyDate.get(position)));
                i.putExtra("expiration",String.valueOf(expiration.get(position)));
                i.putExtra("num",String.valueOf(num.get(position)));
                i.putExtra("ps",String.valueOf(ps.get(position)));
                i.putExtra("archived",String.valueOf(archived.get(position)));
                view.getContext ().startActivity (i);
                /*Toast toast=Toast.makeText(context,String.valueOf(ID.get(position)),Toast.LENGTH_SHORT);
                toast.show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView list_photo_TextView,list_Name_TextView,list_Quantity_TextView,
                list_ShopDate_TextView,list_Effectivedate_TextView,list_Tag_TextView,list_ps_TextView;
        private View goodView;
        private Button sub1Button,deleteButton;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            list_photo_TextView = itemView.findViewById(R.id.list_photo_TextView);
            list_Name_TextView = itemView.findViewById(R.id.list_Name_TextView);
            list_Quantity_TextView = itemView.findViewById(R.id.list_Quantity_TextView);
            list_ShopDate_TextView = itemView.findViewById(R.id.list_ShopDate_TextView);
            list_Effectivedate_TextView = itemView.findViewById(R.id.list_Effectivedate_TextView);
            list_Tag_TextView = itemView.findViewById(R.id.list_Tag_TextView);
            list_ps_TextView=itemView.findViewById(R.id.list_ps_TextView);
            goodView=itemView;
            sub1Button=itemView.findViewById(R.id.sub1_Button);
            deleteButton=itemView.findViewById(R.id.delete_Button);

            sub1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number;
                    number=Integer.parseInt(String.valueOf(num.get(getAdapterPosition())));
                    number=number-1;
                    list_Quantity_TextView.setText(String.valueOf(number));
                    //這裡要同步刷新資料庫中的資料
                }
            });
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //刪除資料
                }
            });

        }
    }
}
