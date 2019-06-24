package zlh.com.zlh0510xm1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.text_djs)
    TextView textDjs;
    int i=5;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i>0){
                i--;
                textDjs.setText("倒计时"+i+"s");
                handler.sendEmptyMessageDelayed(99,1000);
                if (i==0){
                    Intent intent=new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(99,1000);
    }
}
