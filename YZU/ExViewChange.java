package com.example.user.gina;

import android.content.res.Configuration;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll;
    private TextView tv;
    private ViewPager vp;
    private ArrayList<ImageView> ivs=new ArrayList<ImageView>();
    int [] ids={R.drawable.s1,R.drawable.s2,R.drawable.s3};
    String [] str={"Leo","Aries","Virgo"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll= (LinearLayout) findViewById(R.id.mainlayout);
        tv=new TextView(this);
        tv.setTextSize(40);
        ll.addView(tv);
        vp=new ViewPager(this);
        for(int i=0;i<ids.length;i++)
        {
            ImageView iv=new ImageView(this);
            iv.setBackgroundResource(ids[i]);
            ivs.add(iv);
        }
        vp.setAdapter(new vpViewPgerAdapter(ivs));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tv.setText(str[position]);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        ll.addView(vp);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation)
        {
            case Configuration.ORIENTATION_LANDSCAPE:
                Toast.makeText(MainActivity.this, "test1", Toast.LENGTH_SHORT).show();
                ll.setOrientation(LinearLayout.HORIZONTAL);
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                Toast.makeText(MainActivity.this,"test2",Toast.LENGTH_LONG).show();
                ll.setOrientation(LinearLayout.VERTICAL);
                break;
        }
    }
}



class vpViewPgerAdapter extends PagerAdapter{
        private List<ImageView> list;
    public vpViewPgerAdapter(ArrayList<ImageView> ivs){
        this.list=ivs;
    }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=list.get(position);
            container.addView(view);
            return view;
        }

        @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}