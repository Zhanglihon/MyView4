package zlh.com.zlh0510xm1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.GrzlAdapter;
import zlh.com.zlh0510xm1.bean.GrzlBean;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.GrzlPresenter;

public class grzlActivity extends AppCompatActivity implements ShowCall<GrzlBean> {
    LoginBean loginBean;
    private GrzlAdapter grzlAdapter;
    private RecyclerView grzl_recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzl);
        grzl_recycler_view = findViewById(R.id.grzl_recycler_view);
        loginBean=DaoMaster.newDevSession(this,LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        grzl_recycler_view.setLayoutManager(layoutManager);
        //创建适配器
        grzlAdapter = new GrzlAdapter(this);
        grzl_recycler_view.setAdapter(grzlAdapter);
        GrzlPresenter grzlPresenter=new GrzlPresenter(this);
        grzlPresenter.requestData(loginBean.getUserId(),loginBean.getSessionId());
        ButterKnife.bind(this);
    }

    @Override
    public void success(GrzlBean data) {
        grzlAdapter.add(data);
        grzlAdapter.notifyDataSetChanged();
    }
}
