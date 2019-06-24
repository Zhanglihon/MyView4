package zlh.com.zlh0510xm1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.ShdzAdapter;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.ShdzBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.ShdzPresenter;

public class shdzActivity extends AppCompatActivity implements ShowCall<List<ShdzBean>> {

    @BindView(R.id.shdz_recycler_view)
    RecyclerView shdzRecyclerView;
    LoginBean loginBean;
    @BindView(R.id.shdz_xzshdz)
    Button shdzXzshdz;
    private ShdzAdapter shdzAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shdz);
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        ButterKnife.bind(this);
        ShdzPresenter shdzPresenter = new ShdzPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        shdzRecyclerView.setLayoutManager(layoutManager);
        //创建适配器
        shdzAdapter = new ShdzAdapter(this);
        shdzRecyclerView.setAdapter(shdzAdapter);
        shdzPresenter.requestData(loginBean.getUserId(), loginBean.getSessionId());
        //点击新增收货地址
        shdzXzshdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(shdzActivity.this,XzshdzActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(List<ShdzBean> data) {
        shdzAdapter.addAll(data);
        shdzAdapter.notifyDataSetChanged();
    }
}
