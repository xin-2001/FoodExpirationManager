package com.example.foodexpirationmanager;

//import static androidx.core.graphics.drawable.IconCompat.getResources;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    FEMDatabaseHelper DB_helper;
    private final Context context;
    private ArrayList ID,objType,name,tag,buyDate,expiration,num,ps,archived;
    int number=0,num_c=0;
    //public String id,OTYPE,NAME,TAG,BD,EXPD,NUM,PS;
    //listener for archive
    //private OnItemClickListener aListener;

    //interface
    /*
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    */

    /*
    public void setOnItemClickListener(OnItemClickListener clickListener){
        aListener = clickListener;
    }
    */

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
        //id,OTYPE,NAME,TAG,BD,EXPD,NUM,PS;
        //ID,objType,name,tag,buyDate,expiration,num,ps,archived;
        //position

        /*
        id = String.valueOf(ID.get(position));
        OTYPE = String.valueOf(objType.get(position));
        NAME = String.valueOf(name.get(position));
        TAG = String.valueOf(tag.get(position));
        BD = String.valueOf(buyDate.get(position));
        EXPD = String.valueOf(expiration.get(position));
        NUM = String.valueOf(num.get(position));
        PS = String.valueOf(ps.get(position));
        */

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
        holder.list_Quantity_TextView.setText(String.valueOf(num.get(position)));
        holder.list_ShopDate_TextView.setText(String.valueOf(buyDate.get(position)));
        holder.list_Effectivedate_TextView.setText(String.valueOf(expiration.get(position)));
        holder.list_Tag_TextView.setText(String.valueOf(tag.get(position)));
        holder.list_ps_TextView.setText(String.valueOf(ps.get(position)));


        //為什麼這個new onclick不會亮阿
        //item的點擊事件 跨頁傳資料
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
        private Button sub1Button, archiveButton;

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
            archiveButton=itemView.findViewById(R.id.archive_Button);

            //將delete改成archive

            //減1
            sub1Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //這東西怎麼get都是舊的num
                    if (num_c == 0) {
                        number=Integer.parseInt(String.valueOf(num.get(getAdapterPosition())));
                        num_c=1;
                    }
                    number=number-1;
                    //↓這行指令為何不動


                    //↓的確能更新資料
                    updateCVMaker(2,number);
                    //↑的確能更新資料

                    //toast報錯
                    Toast toast=Toast.makeText(context,String.valueOf(number),Toast.LENGTH_SHORT);
                    toast.show();
                    //這裡要同步刷新資料庫中的資料(同步過去了)

                    selectData(1);
                    //如何刷新頁面

                }
            });
            //封存
            archiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    updateCVMaker(1,0);
                }
            });

        }

        //FEM裡頭的東西搬過來用 selectData
        private void selectData(int i ) {
            //清空arraylist
            //ArrayList ID,objType,name,tag,buyDate,expiration,num,ps,archived;
            ID.clear();
            objType.clear();
            name.clear();
            tag.clear();
            buyDate.clear();
            expiration.clear();
            num.clear();
            ps.clear();
            archived.clear();
            //清空arraylist
            String SQL1 = "SELECT * FROM food"
                    //+ " WHERE archived =  0"
                    + " ORDER BY archived ASC ,"
                    + " expiration" + " ASC";
            DB_helper = new FEMDatabaseHelper(context);
            SQLiteDatabase db = DB_helper.getReadableDatabase();
            Cursor cursor = null;
            if(db!=null){
                if (i == 1){
                    cursor = db.rawQuery(SQL1, null);
                }
            }
            if (cursor.getCount() == 0){
                Toast toast=Toast.makeText(context,"ohnyo!",Toast.LENGTH_SHORT);
                toast.show();
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

                Toast toast=Toast.makeText(context,"well done!",Toast.LENGTH_SHORT);
                toast.show();
            }

            }




        //FEM裡頭的東西搬過來用 changeData
        @SuppressLint("NotifyDataSetChanged")
        private void updateCVMaker(int i , int number) {
            //aListener.onItemClick(getAdapterPosition());
            //ID,objType,name,tag,buyDate,expiration,num,ps,archived

            //取得item的內容
            String id,OTYPE,NAME,TAG,BD,EXPD,NUM,PS,ACIV;
            id = String.valueOf(ID.get(getAdapterPosition()));
            OTYPE = String.valueOf(objType.get(getAdapterPosition()));
            NAME = String.valueOf(name.get(getAdapterPosition()));
            TAG = String.valueOf(tag.get(getAdapterPosition()));
            BD = String.valueOf(buyDate.get(getAdapterPosition()));
            EXPD = String.valueOf(expiration.get(getAdapterPosition()));
            NUM = String.valueOf(num.get(getAdapterPosition()));
            PS = String.valueOf(ps.get(getAdapterPosition()));
            ACIV = String.valueOf(archived.get(getAdapterPosition()));


            //測試看看直接寫在這裡能不能動
            DB_helper = new FEMDatabaseHelper(context);
            SQLiteDatabase db = DB_helper.getWritableDatabase();
            //ID,objType,name,tag,buyDate,expiration,num,ps,archived
            //id,OTYPE,NAME,TAG,BD,EXPD,NUM,PS;

            ContentValues cv = new ContentValues();
            cv.put("_id", Integer.valueOf(id.trim()));
            cv.put("objType", OTYPE.trim());
            cv.put("name", NAME.trim());
            cv.put("tag", TAG.trim());
            cv.put("buyDate", BD.trim());
            cv.put("expiration", EXPD.trim());
            if(i == 1){
                cv.put("num",Integer.valueOf(NUM.trim()));
            }else{
                cv.put("num",number);

            }
            cv.put("ps", PS.trim());
            if (i == 1){
                cv.put("archived",1);
            }
            else{
                cv.put("archived",Integer.valueOf(ACIV.trim()));
            }

            db.update("food",cv,"_id = " + Integer.valueOf(id.trim()) ,null);
            //oh YES :(

            //刷新
            notifyDataSetChanged();

        }
    }
}
