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

import javax.microedition.khronos.opengles.GL;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.QuanBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class QuanAdapter extends RecyclerView.Adapter<QuanAdapter.holder> {
    Context context;
    List<QuanBean>list;

    public QuanAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.quan_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {
        holder.tf_text_name.setText(list.get(i).nickName);
        holder.tf_text_dzcs.setText(list.get(i).userId);
        holder.tf_text_time.setText(list.get(i).createTime);
        holder.tf_text_view.setText(list.get(i).content);
        Glide.with(context).load(list.get(i).image).into(holder.tf_image_view);
        Glide.with(context).load(list.get(i).headPic).into(holder.tf_image_tx);
        //Glide.with(context).load(list.get(i).whetherGreat).into(holder.tf_image_dz);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<QuanBean> data) {
        if (data!=null){
            list.addAll(data);
        }
    }


    public class holder extends RecyclerView.ViewHolder{

        private final ImageView tf_image_tx;
        private final TextView tf_text_name;
        private final TextView tf_text_time;
        private final TextView tf_text_view;
        private final ImageView tf_image_view;
        private final ImageView tf_image_dz;
        private final TextView tf_text_dzcs;

        public holder(@NonNull View itemView) {
            super(itemView);
            tf_image_tx = itemView.findViewById(R.id.tf_image_tx);
            tf_text_name = itemView.findViewById(R.id.tf_text_name);
            tf_text_time = itemView.findViewById(R.id.tf_text_time);
            tf_text_view = itemView.findViewById(R.id.tf_text_view);
            tf_image_view = itemView.findViewById(R.id.tf_image_view);
            tf_image_dz = itemView.findViewById(R.id.tf_image_dz);
            tf_text_dzcs = itemView.findViewById(R.id.tf_text_dzcs);
        }
    }
}
