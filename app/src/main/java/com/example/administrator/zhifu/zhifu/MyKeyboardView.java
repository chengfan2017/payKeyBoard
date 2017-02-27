package com.example.administrator.zhifu.zhifu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.example.administrator.zhifu.R;

import java.util.List;

/**
 * 因为没有办法单设置按键的背景，所以重写她的绘制方法（针对一些特殊按键的背景）
 * Created by 程梵 on 2015/6/7
 */

public class MyKeyboardView extends KeyboardView {

    private Context context;

    public MyKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for (Keyboard.Key key : keys) {
            if (key.codes[0] == -3 || key.codes[0] == -5)
                resetOKBtn(key, canvas);
        }
    }

    /**
     * 绘制键盘底部左边和右侧背景色
     *
     * @param key
     * @param canvas
     * @author Song
     */
    private void resetOKBtn(Keyboard.Key key, Canvas canvas) {
        //将OK键重新绘制
        if (key.codes[0] == -3) {
            Drawable npd = ContextCompat.getDrawable(context, R.drawable.btn_keyboard_left);
            npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
            npd.draw(canvas);
        } else if (key.codes[0] == -5) {
            Drawable npd = ContextCompat.getDrawable(context, R.mipmap.key_delete);
            npd.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
            npd.draw(canvas);
        }

    }
}