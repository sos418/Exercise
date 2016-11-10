package com.example.kuanyu.df;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NumberPicker npmonth;
    private NumberPicker npdate;
    private Button btnGo;
    private WebView wv;
    int month=1,date=1;

    ArrayList horoscope = new ArrayList();//宣告ArrayList
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        npmonth = (NumberPicker) findViewById(R.id.numberPicker);
        npdate = (NumberPicker) findViewById(R.id.numberPicker2);
        wv = (WebView) findViewById(R.id.wv);
        btnGo = (Button)findViewById(R.id.button);

        WebSettings webSettings = wv.getSettings();
        //使用內置縮放(手勢)
        webSettings.setSupportZoom(true);
        //使用內置縮放(+/-)
        webSettings.setBuiltInZoomControls(true);

        npmonth.setMaxValue(12);
        npmonth.setMinValue(1);
        npmonth.setValue(1);
        npmonth.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);//設定NumberPicker不會跳出視窗
        npdate.setMaxValue(31);
        npdate.setMinValue(1);
        npdate.setValue(1);
        npdate.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        //設定NumberPicke數字變動事件
        npmonth.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                month = i1;
            }
        });
        npdate.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                date = i1;
            }
        });

        //Arraylist加入12星座
        horoscope.add("http://astrodoor.cc/keyword/sign/capricorn.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/aquarius.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/pisces.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/aries.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/taurus.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/gemini.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/cancer.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/leo.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/virgo.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/libra.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/scorpio.jsp");
        horoscope.add("http://astrodoor.cc/keyword/sign/sagittarius.jsp");



        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (month)
                {
                    case 1:
                        if (date <= 20)
                        {
                            wv.loadUrl(horoscope.get(0).toString());

                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(1).toString());
                        }
                        break;
                    case 2:
                        if (date <= 21)
                        {
                            wv.loadUrl(horoscope.get(1).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(2).toString());
                        }
                        break;
                    case 3:
                        if (date <= 20)
                        {
                            wv.loadUrl(horoscope.get(2).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(3).toString());
                        }
                        break;
                    case 4:
                        if (date <= 20)
                        {
                            wv.loadUrl(horoscope.get(3).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(4).toString());
                        }
                        break;
                    case 5:
                        if (date <= 20)
                        {
                            wv.loadUrl(horoscope.get(4).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(5).toString());
                        }
                        break;
                    case 6:
                        if (date <= 21)
                        {
                            wv.loadUrl(horoscope.get(5).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(6).toString());
                        }
                        break;
                    case 7:
                        if (date <= 22)
                        {
                            wv.loadUrl(horoscope.get(6).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(7).toString());
                        }
                        break;
                    case 8:
                        if (date <= 22)
                        {
                            wv.loadUrl(horoscope.get(7).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(8).toString());
                        }
                        break;
                    case 9:
                        if (date <= 22)
                        {
                            wv.loadUrl(horoscope.get(8).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(9).toString());
                        }
                        break;
                    case 10:
                        if (date <= 23)
                        {
                            wv.loadUrl(horoscope.get(9).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(10).toString());
                        }
                        break;
                    case 11:
                        if (date <= 21)
                        {
                            wv.loadUrl(horoscope.get(10).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(11).toString());
                        }
                        break;
                    case 12:
                        if (date <= 20)
                        {
                            wv.loadUrl(horoscope.get(11).toString());
                        }
                        else
                        {
                            wv.loadUrl(horoscope.get(0).toString());
                        }
                        break;
                }
            }
        });



    }
}

