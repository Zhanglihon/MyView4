package zlh.com.zlh0510xm1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.bean.GwcBean;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class ThreeFragmentAdapter extends XRecyclerView.Adapter<ThreeFragmentAdapter.holder> {
    Context context;
    List<GwcBean>list;

    public ThreeFragmentAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.threefragment_item_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.three_text_view.setText(list.get(position).commodityName);
        holder.three_text_price.setText(list.get(position).price);
        holder.three_text_num.setText(list.get(position).count+"");
        Glide.with(context).load(list.get(position).pic).into(holder.three_image_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<GwcBean> data) {
        if (data!=null){
            list.addAll(data);
        }
    }

    public class holder extends XRecyclerView.ViewHolder{

        private final CheckBox three_check_box;
        private final ImageView three_image_view;
        private final TextView three_text_view;
        private final TextView three_text_price;
        private final TextView three_text_jian;
        private final TextView three_text_num;
        private final TextView three_text_jia;

        public holder(View itemView) {
            super(itemView);
            three_check_box = itemView.findViewById(R.id.three_check_box);
            three_image_view = itemView.findViewById(R.id.three_image_view);
            three_text_view = itemView.findViewById(R.id.three_text_view);
            three_text_price = itemView.findViewById(R.id.three_text_price);
            three_text_jian = itemView.findViewById(R.id.three_text_jian);
            three_text_num = itemView.findViewById(R.id.three_text_num);
            three_text_jia = itemView.findViewById(R.id.three_text_jia);
        }
    }
}
