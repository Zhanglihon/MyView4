package zlh.com.zlh0510xm1.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zlh.com.zlh0510xm1.R;
import zlh.com.zlh0510xm1.grzlActivity;
import zlh.com.zlh0510xm1.shdzActivity;
import zlh.com.zlh0510xm1.wdqbActivity;
import zlh.com.zlh0510xm1.wdzjActivity;

/**
 * Time:${Data}
 * <p>
 * Author:Lenovo
 * <p>
 * Description:写这个类的作用
 */
public class FiveFragment extends Fragment {
    @BindView(R.id.image_tx)
    ImageView imageTx;
    @BindView(R.id.fv_text_name)
    TextView fvTextName;
    Unbinder unbinder;
    @BindView(R.id.five_text_grzl)
    TextView fiveTextGrzl;
    @BindView(R.id.five_text_wdqz)
    TextView fiveTextWdqz;
    @BindView(R.id.five_text_wdzj)
    TextView fiveTextWdzj;
    @BindView(R.id.five_text_wdqb)
    TextView fiveTextWdqb;
    @BindView(R.id.five_text_shdz)
    TextView fiveTextShdz;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fivefragment_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
        Glide.with(this).load(imageTx).apply(RequestOptions.circleCropTransform()).into(imageTx);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //个人资料
        fiveTextGrzl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),grzlActivity.class);
                startActivity(intent);
            }
        });
        //我的足迹
        fiveTextWdzj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),wdzjActivity.class);
                startActivity(intent);
            }
        });
        //查询用户钱包
        fiveTextWdqb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),wdqbActivity.class);
                startActivity(intent);
            }
        });
        //收货地址列表
        fiveTextShdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),shdzActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
