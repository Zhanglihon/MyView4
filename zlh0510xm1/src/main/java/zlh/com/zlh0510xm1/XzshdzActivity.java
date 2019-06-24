package zlh.com.zlh0510xm1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.citypickerview.widget.CityPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.bean.XzshdzBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.XzshdzPresenter;

public class XzshdzActivity extends AppCompatActivity implements ShowCall<XzshdzBean> {
    LoginBean loginBean;
    @BindView(R.id.Xzshdz_name)
    EditText XzshdzName;
    @BindView(R.id.Xzshdz_phone)
    EditText XzshdzPhone;
    @BindView(R.id.Xzshdz_address)
    EditText XzshdzAddress;
    @BindView(R.id.Xzshdz_xxdz)
    EditText XzshdzXxdz;
    @BindView(R.id.Xzshdz_email)
    EditText XzshdzEmail;
    @BindView(R.id.Xzshdz_btn_bc)
    Button XzshdzBtnBc;
    @BindView(R.id.Xzshdz_dz)
    TextView XzshdzDz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xzshdz);
        ButterKnife.bind(this);
        loginBean = DaoMaster.newDevSession(this, LoginBeanDao.TABLENAME).getLoginBeanDao().loadAll().get(0);
        XzshdzPresenter xzshdzPresenter = new XzshdzPresenter(this);
        XzshdzDz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityPicker cityPicker = new CityPicker.Builder(XzshdzActivity.this).textSize(20)//滚轮文字的大小
                        .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                        .provinceCyclic(true)//省份滚轮是否循环显示
                        .cityCyclic(false)//城市滚轮是否循环显示
                        .districtCyclic(false)//地区（县）滚轮是否循环显示
                        .visibleItemsCount(7)//滚轮显示的item个数
                        .itemPadding(10)//滚轮item间距
                        .build();

                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                        //省份
                        String province = citySelected[0];
                        //城市
                        String city = citySelected[1];
                        //区县
                        String district = citySelected[2];
                        //邮编
                        String code = citySelected[3];
                        XzshdzAddress.setText(province+" "+city+" "+ district);
                        XzshdzEmail.setText(code);
                    }
                });
            }
        });
        XzshdzBtnBc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = XzshdzName.getText().toString();
                String phone = XzshdzPhone.getText().toString();
                String address = XzshdzXxdz.getText().toString();
                String email = XzshdzEmail.getText().toString();
                String dz=XzshdzAddress.getText().toString();
                xzshdzPresenter.requestData(loginBean.getUserId(), loginBean.getSessionId(), name, phone, dz, email);
                finish();
            }
        });
    }

    @Override
    public void success(XzshdzBean data) {
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
    }
}
