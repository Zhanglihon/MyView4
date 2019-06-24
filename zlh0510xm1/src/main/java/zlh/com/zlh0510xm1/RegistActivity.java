package zlh.com.zlh0510xm1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zlh.com.zlh0510xm1.bean.Result;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.RegistPresenter;

public class RegistActivity extends AppCompatActivity implements ShowCall<Result> {

    @BindView(R.id.regist_edit_phone)
    EditText registEditPhone;
    @BindView(R.id.regist_edit_yzm)
    EditText registEditYzm;
    @BindView(R.id.regist_text_yzm)
    TextView registTextYzm;
    @BindView(R.id.regist_edit_pwd)
    EditText registEditPwd;
    @BindView(R.id.login_image_eyes)
    ImageView loginImageEyes;
    @BindView(R.id.regist_btn_zc)
    Button registBtnZc;
    @BindView(R.id.regist_text_yyzh)
    TextView registTextYyzh;
    RegistPresenter registPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        ButterKnife.bind(this);
        final RegistPresenter registPresenter=new RegistPresenter(this);
        //点击注册获取输入框的值
        registBtnZc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pho=registEditPhone.getText().toString();
                String poss=registEditPwd.getText().toString();
                if (TextUtils.isEmpty(pho)&&(TextUtils.isEmpty(poss))){
                    Toast.makeText(RegistActivity.this,"手机号或密码不能为空",Toast.LENGTH_SHORT).show();
                }else if (poss.length()<6){
                    Toast.makeText(RegistActivity.this,"密码最少六位",Toast.LENGTH_SHORT).show();
                    finish();
                }
                registPresenter.requestData(pho,poss);
            }
        });
        //点击已有账号跳转
        registTextYyzh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void success(Result data) {
            Toast.makeText(RegistActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(RegistActivity.this,MainActivity.class);
            startActivity(intent);
    }
}
