package com.shj.y18shj;

import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @ViewInject(R.id.navigation)
    //侧滑菜单
    private NavigationView navigation;//作用

    @ViewInject(R.id.drawer)
    //用于侧滑
    private DrawerLayout drawer;//作用
    @ViewInject(R.id.pager)
    private ViewPager pager;
    @ViewInject(R.id.tab)
    private TabLayout tab;//作用
    private ActionBarDrawerToggle toggle;//作用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
        DbHelper.init(this);//跳转
        BitmapHelper.init(this);//跳转，执行完毕回来，数据库中已经有数据
        //菜单监听
        navigation.setNavigationItemSelectedListener(this);

        //显示ActionBar 的Home菜单
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //三横线变箭头的控件
        toggle = new ActionBarDrawerToggle(this, drawer, 0, 0);
        //toggle和drawer关联
        drawer.setDrawerListener(toggle);
        //同步当前状态
        toggle.syncState();

        try {
            List<EntryType> types = DbHelper.getUtils().findAll(EntryType.class);
            pager.setAdapter(new MainAdapter(getSupportFragmentManager(), types));//跳转
        } catch (DbException e) {
            e.printStackTrace();
        }

        tab.setupWithViewPager(pager);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.coll:
                break;
            case R.id.settings:
                break;
            case R.id.exit:
                finish();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
