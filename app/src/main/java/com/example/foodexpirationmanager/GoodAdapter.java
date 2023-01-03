package com.example.foodexpirationmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
    public void onBindViewHolder(@NonNull GoodAdapter.DataViewHolder holder, int position) {
        holder.list_photo_TextView.setText(String.valueOf(objType.get(position)));
        holder.list_Name_TextView.setText(String.valueOf(name.get(position)));
        holder.list_Quantity_TextView.setText(String.valueOf(num.get(position)));
        holder.list_ShopDate_TextView.setText(String.valueOf(buyDate.get(position)));
        holder.list_Effectivedate_TextView.setText(String.valueOf(expiration.get(position)));
        holder.list_Tag_TextView.setText(String.valueOf(tag.get(position)));
    }

    @Override
    public int getItemCount() {
        return ID.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {

        TextView list_photo_TextView,list_Name_TextView,list_Quantity_TextView,
                list_ShopDate_TextView,list_Effectivedate_TextView,list_Tag_TextView ;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            list_photo_TextView = itemView.findViewById(R.id.list_photo_TextView);
            list_Name_TextView = itemView.findViewById(R.id.list_photo_TextView);
            list_Quantity_TextView = itemView.findViewById(R.id.list_Quantity_TextView);
            list_ShopDate_TextView = itemView.findViewById(R.id.list_ShopDate_TextView);
            list_Effectivedate_TextView = itemView.findViewById(R.id.list_Effectivedate_TextView);
            list_Tag_TextView = itemView.findViewById(R.id.list_Tag_TextView);
        }
    }
}
