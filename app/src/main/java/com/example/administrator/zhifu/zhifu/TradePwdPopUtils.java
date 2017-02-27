package com.example.administrator.zhifu.zhifu;

import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.zhifu.R;

/**
 * 封装po类，回调在这里创建
 * Created by 程梵 on 2015/6/7
 */
public class TradePwdPopUtils {
    private PopupWindow popWindow = null;
    private CallBackTradePwd callBackTradePwd;

    public TradePwdPopUtils() {
        super();
    }

    public CallBackTradePwd getCallBackTradePwd() {
        return callBackTradePwd;
    }

    public void setCallBackTradePwd(CallBackTradePwd callBackTrade) {
        this.callBackTradePwd = callBackTrade;
    }

    public interface CallBackTradePwd {
        public void callBaceTradePwd(String pwd);
    }

    protected void showPopWindow(Context context, Activity ac, LinearLayout lin) {
        popWindow = null;
        if (popWindow == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.trade_key_layout, null);
            SecurityPasswordEditText myEdit = (SecurityPasswordEditText) view.findViewById(R.id.my_edit);
            TextView tvClose = (TextView) view.findViewById(R.id.tv_close);
            myEdit.setSecurityEditCompleListener(new SecurityPasswordEditText.SecurityEditCompleListener() {

                @Override
                public void onNumCompleted(String num) {
                    if (callBackTradePwd != null) {
                        callBackTradePwd.callBaceTradePwd(num);
                    }
                    popWindow.dismiss();
                }
            });
            tvClose.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    popWindow.dismiss();
                }
            });
            ac.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            Method setShowSoftInputOnFocus = null;
            try {
                setShowSoftInputOnFocus = myEdit.getClass().getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(myEdit, false);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            new KeyboardUtil(view, context, myEdit).showKeyboard();
            popWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            popWindow.setFocusable(true);
            popWindow.setOutsideTouchable(true);
            popWindow.setBackgroundDrawable(new BitmapDrawable());
            popWindow.showAtLocation(lin, Gravity.BOTTOM, 0, 0);
        }
    }
}
