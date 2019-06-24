package zlh.com.zlh0510xm1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import butterknife.BindView;
import butterknife.ButterKnife;
import zlh.com.zlh0510xm1.bean.LoginBean;
import zlh.com.zlh0510xm1.core.ShowCall;
import zlh.com.zlh0510xm1.p.LoginPresenter;

public class MainActivity extends AppCompatActivity implements ShowCall<LoginBean>{


    @BindView(R.id.login_edit_phone)
    EditText loginEditPhone;
    @BindView(R.id.login_edit_pwd)
    EditText loginEditPwd;
    @BindView(R.id.login_image_eyes)
    ImageView loginImageEyes;
    @BindView(R.id.login_check_box)
    CheckBox loginCheckBox;
    @BindView(R.id.login_text_regist)
    TextView loginTextRegist;
    @BindView(R.id.login_btn_dl)
    Button loginBtnDl;
    LoginPresenter loginPresenter;
    LoginBeanDao loginBeanDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loginBeanDao = DaoMaster.newDevSession(MainActivity.this,LoginBeanDao.TABLENAME).getLoginBeanDao();
        //获取sp对象
        SharedPreferences sharedPreferences=getSharedPreferences("config",MODE_PRIVATE);
        boolean flag=sharedPreferences.getBoolean("flag",false);
        loginCheckBox.setChecked(flag);
        if (flag){
            String name=sharedPreferences.getString("name","");
            String pwd=sharedPreferences.getString("pwd","");
            loginEditPhone.setText(name);
            loginEditPwd.setText(pwd);
        }
        loginPresenter =new LoginPresenter(this);
        //点击快速注册跳转到注册页面
        loginTextRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistActivity.class);
                startActivity(intent);
            }
        });
        //点击登录获取输入框的值
        loginBtnDl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=loginEditPhone.getText().toString();
                String poss=loginEditPwd.getText().toString();
                SharedPreferences.Editor edit=sharedPreferences.edit();
                if (loginCheckBox.isChecked()){
                    edit.putString("name",name);
                    edit.putString("pwd",poss);
                    edit.putBoolean("flag",true);
                }else {
                    edit.clear();
                }
                edit.commit();
                if (TextUtils.isEmpty(name)&&TextUtils.isEmpty(poss)){
                    Toast.makeText(MainActivity.this,"手机号或密码输入不能为空",Toast.LENGTH_SHORT).show();
                }
                loginPresenter.requestData(name,poss);
            }
        });
    }
    @Override
    public void success(LoginBean data) {
        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(MainActivity.this,ShowActivity.class);
        startActivity(intent);
        loginBeanDao.insertOrReplaceInTx(data);
        Toast.makeText(this,loginBeanDao.toString(),Toast.LENGTH_SHORT).show();
        finish();
    }
}
