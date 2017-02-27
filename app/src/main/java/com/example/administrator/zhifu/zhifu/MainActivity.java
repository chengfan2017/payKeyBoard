package com.example.administrator.zhifu.zhifu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.zhifu.R;


/**
 * 主活动
 *
 * @author AHF
 */
public class MainActivity extends Activity implements TradePwdPopUtils.CallBackTradePwd {
    Button btn;
    LinearLayout lin;
    private TradePwdPopUtils pop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initView();
    }

    public void initView() {
        btn = (Button) findViewById(R.id.btn);
        pop = new TradePwdPopUtils();
        lin = (LinearLayout) findViewById(R.id.lin);
        pop = new TradePwdPopUtils();
        pop.setCallBackTradePwd(new TradePwdPopUtils.CallBackTradePwd() {
            @Override
            public void callBaceTradePwd(String pwd) {
                Toast.makeText(MainActivity.this, "回调密码" + pwd, Toast.LENGTH_LONG).show();
            }
        });
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pop.showPopWindow(MainActivity.this, MainActivity.this, lin);
            }
        });
    }


    @Override
    public void callBaceTradePwd(String pwd) {
        Toast.makeText(this, "回调密码" + pwd, Toast.LENGTH_LONG).show();
    }
}
