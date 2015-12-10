package com.example.cmx.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.support.v4.app.FragmentPagerAdapter ;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.wtu.school.activity.ImportActivity;
import com.wtu.school.activity.MyActivity;
import com.wtu.school.fragment.ForthFragment;
import com.wtu.school.fragment.OneFragment;
import com.wtu.school.fragment.ThirdFragment;
import com.wtu.school.fragment.TwoFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

//对navagiration 中的header中的空间
    private TextView tvAndroid ;
    private ImageView imageView_header ;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragments = new ArrayList<Fragment>(); // 使用v4包中fragement

    private ImageButton ibtn_baoxiu; // 按钮布局 报修
    private ImageButton ibtn_weixiu; // 按钮布局 维修
    private ImageButton ibtn_my; // 按钮布局 我的
    private ImageButton ibtn_more; // 按钮布局 更多

    private LayoutInflater inflater ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        System.out.print(width+"我的手机宽高是："+height);
        initComp();
        initData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
               this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    private void initComp(){
        //layout/nav_header_main.xml 中的imageview
        inflater = LayoutInflater.from(getApplicationContext()) ;
        View view = inflater.inflate(R.layout.nav_header_main,null) ;

        //
        tvAndroid = (TextView) view.findViewById(R.id.androidstudio);
        tvAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                overridePendingTransition(R.anim.in_right_left,R.anim.out_right_left);
            }
        });

        imageView_header = (ImageView) view.findViewById(R.id.imageView);
        imageView_header.setOnClickListener(this);
        imageView_header.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startActivity(new Intent(MainActivity.this, MyActivity.class));
                overridePendingTransition(R.anim.in_right_left,R.anim.out_right_left);
                return true;
            }
        });
        ibtn_baoxiu = (ImageButton) findViewById(R.id.main_btn_baoxiu);
        ibtn_weixiu = (ImageButton) findViewById(R.id.main_btn_weixiu);
        ibtn_my = (ImageButton) findViewById(R.id.main_btn_my);
        ibtn_more = (ImageButton) findViewById(R.id.main_btn_more);

        ibtn_baoxiu.setOnClickListener(this);
        ibtn_more.setOnClickListener(this);
        ibtn_my.setOnClickListener(this);
        ibtn_weixiu.setOnClickListener(this) ;
        //ViewPager 的实例化
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);


        //实例化Fragment
        OneFragment oneFrag = new OneFragment();
        TwoFragment twoFrag = new TwoFragment();
        ThirdFragment thirdFrag = new ThirdFragment();
        ForthFragment forthFrag = new ForthFragment();
        fragments.add(oneFrag );
        fragments.add(twoFrag);
        fragments.add(thirdFrag);
        fragments.add(forthFrag);

    }
    private void initData() {

        // mainActivity 中ViewPager 适配器的构造
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return  fragments.size();
            }
        };
        //viewpager  中时间监听器的实现
        viewPager.setAdapter(adapter   );
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                int currentIndex =0 ;
                resertBtn ();
                switch (position)
                {
                    case 0:
                        //ibtn_baoxiu.setBackgroundColor(Color.RED);
                        ibtn_baoxiu.setBackgroundResource(R.drawable.selected01);
                        //changeBackground(currentIndex	, position) ;
                        break;
                    case 1:
                        ibtn_weixiu.setBackgroundResource(R.drawable.selected02);
                        //changeBackground(currentIndex	, position) ;
                        break;
                    case 2:

                       /* RelativeLayout relativeLayout  = (RelativeLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main1,null);
                        relativeLayout.removeView();
                        relativeLayout.removeView();*/
                        ibtn_my.setBackgroundResource(R.drawable.selected03) ;
                        //changeBackground(currentIndex	, position) ;
                        break;
                    case 3:
                        ibtn_more.setBackgroundResource(R.drawable.selected04) ;
                        //changeBackground(currentIndex	, position) ;
                        break;
                }

                currentIndex = position;
                viewPager.setCurrentItem(currentIndex) ;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void resertBtn() {
        ibtn_baoxiu.setBackgroundResource(R.drawable.select01);
        ibtn_weixiu.setBackgroundResource(R.drawable.select02);
        ibtn_my.setBackgroundResource(R.drawable.select03);
        ibtn_more.setBackgroundResource(R.drawable.select04);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, ImportActivity.class);

            startActivity(intent);
           // finish();R.anim.in_right_left,R.anim.out_right_left
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            startActivity(new Intent(MainActivity.this, MyActivity.class));
            overridePendingTransition(R.anim.in_right_left,R.anim.out_right_left);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.main_btn_baoxiu:
                viewPager.setCurrentItem(0);

                break;
            case R.id.main_btn_weixiu:
                viewPager.setCurrentItem(1);
                break;
            case R.id.main_btn_my:
                viewPager.setCurrentItem(2);
                break;
            case R.id.main_btn_more:
                viewPager.setCurrentItem(3);
                break;
            case R.id.imageView:


                startActivity(new Intent(MainActivity.this, MyActivity.class));
                overridePendingTransition(R.anim.in_right_left,R.anim.out_right_left);

                break;
            default:
                break;
        }
    }
}
