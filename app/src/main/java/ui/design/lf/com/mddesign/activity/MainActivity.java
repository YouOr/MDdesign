package ui.design.lf.com.mddesign.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ui.design.lf.com.mddesign.R;
import ui.design.lf.com.mddesign.fragment.FirstFragment;
import ui.design.lf.com.mddesign.fragment.MyFragmentAdapter;
import ui.design.lf.com.mddesign.fragment.SecondFragment;
import ui.design.lf.com.mddesign.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public static final String[] sTitle = new String[]{"精品展示", "分类展示", "试衣秀"};
    private TabLayout mTabLayout;
    private List<Fragment> fragmentList;
    private ViewPager mViewPager;
    private NavigationView navigationView;
    private DrawerLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initToolbar();
        initViewPager();
    }

    private void init() {
        //初始化控件
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        navigationView = (NavigationView) findViewById(R.id.nav_View);
        for (String text : sTitle) {
            mTabLayout.addTab(mTabLayout.newTab().setText(text));
        }
        // 设置导航菜单宽度
        ViewGroup.LayoutParams params = navigationView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels * 2 / 3;
        navigationView.setLayoutParams(params);
        int[][] states = new int[][]{

                new int[]{android.R.attr.state_activated}, // enabled
                new int[]{android.R.attr.state_active}, // disabled
                new int[]{android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_focused}, // pressed
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };
        int[] colors = new int[]{
                Color.BLACK,
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimary),
                Color.BLACK,
                Color.BLACK,
                getResources().getColor(R.color.colorPrimary),
                Color.BLACK
        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        navigationView.setItemTextColor(colorStateList);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(MainActivity.this, "点击了第1个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_myInfo:
                        Toast.makeText(MainActivity.this, "点击了第2个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_closet:
                        Toast.makeText(MainActivity.this, "点击了第3个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_collect:
                        Toast.makeText(MainActivity.this, "点击了第4个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_history:
                        Toast.makeText(MainActivity.this, "点击了第5个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_clothesShow:
                        Toast.makeText(MainActivity.this, "点击了第6个菜单", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(MainActivity.this, "点击了第7个菜单", Toast.LENGTH_SHORT).show();
                        break;
                }
                item.setChecked(true);
                drawer_layout.closeDrawers();
                return true;
            }
        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) this.findViewById(R.id.toolBar);
        //      将toolbar设置为兼容的actionBar
        toolbar.setTitle("Mr.Liu");
        setSupportActionBar(toolbar);
//        toolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.roundhead);
        }

    }

    //    public int getStatusBarHeight() {
//        int result = 0;
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            result = getResources().getDimensionPixelSize(resourceId);
//        }
//        return result;
//    }
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        mTabLayout.setupWithViewPager(mViewPager);
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(sTitle));
        mViewPager.setAdapter(adapter);
    }

    //加载菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //配置ActionBar的home键点击监听
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打開左側的抽屜
                if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START);
                } else {
                    drawer_layout.openDrawer(GravityCompat.START);
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
