package zlh.com.zlh0510xm1.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zlh.com.zlh0510xm1.PirticActicity1;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.OneFragmentBean;
import zlh.com.zlh0510xm1.bean.Shop;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class OneFragmentAdapter3 extends RecyclerView.Adapter<OneFragmentAdapter3.holder>{
    Context context;
    List<Shop>list;
    OneFragment3Onclick oneFragment3Onclick;
    public OneFragmentAdapter3(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.onefragment_item3_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.text_title3.setText(list.get(i).commodityName);
        holder.text_price3.setText(list.get(i).price);
        Glide.with(context).load(list.get(i).masterPic).into(holder.image_view3);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=list.get(i).commodityId;
                Intent intent=new Intent(context,PirticActicity1.class);
                intent.putExtra("str",str);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(OneFragmentBean data) {
        List<Shop> pzsh= data.pzsh.commodityList;
        list.addAll(pzsh);
    }


    public class holder extends RecyclerView.ViewHolder{

        private final ImageView image_view3;
        private final TextView text_title3;
        private final TextView text_price3;

        public holder(@NonNull View itemView) {
            super(itemView);
            image_view3 = itemView.findViewById(R.id.image_view3);
            text_title3 = itemView.findViewById(R.id.text_title3);
            text_price3 = itemView.findViewById(R.id.text_price3);
        }
    }
    public interface OneFragment3Onclick{

    }

    public OneFragmentAdapter3(OneFragment3Onclick oneFragment3Onclick) {
        this.oneFragment3Onclick = oneFragment3Onclick;
    }
}
