package ui.design.lf.com.mddesign.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ui.design.lf.com.mddesign.R;
import ui.design.lf.com.mddesign.adapter.MyAdapter;
import ui.design.lf.com.mddesign.util.StatusBarUtils;

/**
 * Created by Administrator on 2017/8/11.
 */
public class ClothesInfo extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private Toolbar toolbar;
    private ArrayList<ImageView> mViewArrayList;
    private boolean isRunning = false;
    private ViewPager mviewPager;
    private TextView tv_desc;
    private LinearLayout ll_point_container;
    private int[] imageResIds;
    private String[] contentDescs;
    private int previousSelectedPosition = 0;
    private MyAdapter mMyAdapter;
    private Integer mInteger;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_clothes_info);
        intView();
        // Model数据
        initData();
        // Controller 控制器
        initAdapter();
        //开启一个线程图片切换
        initThread();
    }

    private void intView() {
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        mviewPager = (ViewPager) findViewById(R.id.viewpager);
        ll_point_container = (LinearLayout) findViewById(R.id.ll_point_container);
//        tv_desc = (TextView) findViewById(R.id.tv_desc);
        mviewPager.setOnPageChangeListener(ClothesInfo.this);// 设置页面更新监听
        StatusBarUtils.setTranslucentImageHeader(this, 0, toolbar);
        toolbar.setTitle("Maje");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    //开启一个线程
    private void initThread() {
        new Thread() {
            @Override
            public void run() {
                isRunning = true;
                while (isRunning) {
                    try {
                        Thread.sleep(6000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 往下跳一位
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mviewPager.setCurrentItem(mviewPager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }.start();

    }
    // Model数据
    private void initData() {
        imageResIds = new int[]{R.mipmap.maje61, R.mipmap.maje62, R.mipmap.maje63, R.mipmap.maje64, R.mipmap.maje65};
    // 文本描述
        contentDescs = new String[]{
                "No.1",
                "No.2",
                "No.3",
                "No.4",
                "No.5"};
        mViewArrayList = new ArrayList<ImageView>();
        ImageView imageview;
        View pointView;
        LinearLayout.LayoutParams layoutParams;
        for (int i = 0; i < imageResIds.length; i++) {
            // 初始化要显示的图片对象
            imageview = new ImageView(this);
            imageview.setBackgroundResource(imageResIds[i]);
            imageview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            mViewArrayList.add(imageview);
            // 加小白点, 指示器
            pointView = new View(this);
            pointView.setBackgroundResource(R.drawable.selector_bg_point);
            layoutParams = new LinearLayout.LayoutParams(10, 10);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            }
            // 设置默认所有都不可用
            pointView.setEnabled(false);
            ll_point_container.addView(pointView, layoutParams);
        }
    }
    // Controller 控制器
    private void initAdapter() {
        ll_point_container.getChildAt(0).setEnabled(true);
//        tv_desc.setText(contentDescs[0]);
        previousSelectedPosition = 0;
        // 设置适配器
        mMyAdapter = new MyAdapter(mInteger, mViewArrayList);
        mviewPager.setAdapter(mMyAdapter);
        // 默认设置到中间的某个位置
        int pos = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2 % mViewArrayList.size());
        mviewPager.setCurrentItem(pos);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 滚动时调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    // 新的条目被选中时调用
    @Override
    public void onPageSelected(int position) {
        int newposition = position % mViewArrayList.size();
        //设置文本
//        tv_desc.setText(contentDescs[newposition]);
        // 把之前的禁用, 把最新的启用, 更新指示器
        ll_point_container.getChildAt(previousSelectedPosition).setEnabled(false);
        ll_point_container.getChildAt(newposition).setEnabled(true);
        // 记录之前的位置
        previousSelectedPosition = newposition;
    }
    // 滚动状态变化时调用
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
