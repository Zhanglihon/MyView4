package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.WdzjBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class WdzjAdapter extends RecyclerView.Adapter<WdzjAdapter.holder> {
    Context context;
    List<WdzjBean>list;

    public WdzjAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.wdzj_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.wdzj_content.setText(list.get(position).commodityName);
        holder.wdzj_liulan.setText("已浏览"+list.get(position).browseNum+"次");
        holder.wdzj_price.setText("￥"+list.get(position).price);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-DD HH:mm");
        String data=simpleDateFormat.format(new Date(list.get(position).browseTime));
        holder.wdzj_data.setText(data);
        Glide.with(context).load(list.get(position).masterPic).into(holder.wdzj_image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<WdzjBean> data) {
        list.addAll(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView wdzj_content;
        private final TextView wdzj_data;
        private final ImageView wdzj_image;
        private final TextView wdzj_liulan;
        private final TextView wdzj_price;

        public holder(View itemView) {
            super(itemView);
            wdzj_content = itemView.findViewById(R.id.wdzj_content);
            wdzj_data = itemView.findViewById(R.id.wdzj_data);
            wdzj_image = itemView.findViewById(R.id.wdzj_image);
            wdzj_liulan = itemView.findViewById(R.id.wdzj_liulan);
            wdzj_price = itemView.findViewById(R.id.wdzj_price);
        }
    }
}
