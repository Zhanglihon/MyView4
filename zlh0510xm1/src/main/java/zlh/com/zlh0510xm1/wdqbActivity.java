package zlh.com.zlh0510xm1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.adapter.WdqbAdapter;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.WdqbBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.WdqbPresenter;

public class wdqbActivity extends AppCompatActivity implements ShowCall<WdqbBean> {
    LoginBean loginBean;
    @BindView(R.id.wdqb_ye)
    TextView wdqbYe;
    @BindView(R.id.wdzj_recycler_view)
    RecyclerView wdzjRecyclerView;
    private WdqbAdapter wdqbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        setContentView(R.layout.activity_wdqb);
        ButterKnife.bind(this);
        WdqbPresenter wdqbPresenter = new WdqbPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wdzjRecyclerView.setLayoutManager(layoutManager);
        //创建适配器
        wdqbAdapter = new WdqbAdapter(this);
        wdzjRecyclerView.setAdapter(wdqbAdapter);
        wdqbPresenter.requestData(loginBean.getUserId(), loginBean.getSessionId(), "1", "10");
    }

    @Override
    public void success(WdqbBean data) {
        wdqbYe.setText(data.balance+".00");
        wdqbAdapter.add(data);
        wdqbAdapter.notifyDataSetChanged();
    }
}
