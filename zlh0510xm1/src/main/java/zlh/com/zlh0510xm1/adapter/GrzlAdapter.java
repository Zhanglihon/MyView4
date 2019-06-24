package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.GrzlBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class GrzlAdapter extends RecyclerView.Adapter<GrzlAdapter.holder> {
    Context context;
    List<GrzlBean>list;

    public GrzlAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.grzl_item,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.grzl_name.setText(list.get(position).nickName);
        Glide.with(context).load(list.get(position).headPic).into(holder.grzl_tx);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void add(GrzlBean data) {
        list.add(data);
    }

    public class holder extends RecyclerView.ViewHolder{

        private final TextView grzl_name;
        private final ImageView grzl_tx;

        public holder(View itemView) {
            super(itemView);
            grzl_name = itemView.findViewById(R.id.grzl_name);
            grzl_tx = itemView.findViewById(R.id.grzl_tx);
        }
    }
}
