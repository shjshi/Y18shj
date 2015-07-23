package com.shj.y18shj;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午4:32
 */
 //很不一样的写法，往常我是在主方法中newfragment，然后传参数给fragment，最后将fragment装进集合，将集合传递到FragmentPagerAdapter
 //通过重写的两个方法，将每个fragment适配到viewpager上，并返回fragment的个数，这里是在list中装了一个对象，通过一个对象（表），根据他的id传过去给fragment
 //获取fragment，同时对象的大小就是fragment的大小
public class MainAdapter extends FragmentPagerAdapter {
    private List<EntryType> types;//fragmentPagerAdapter可以适配非fragment的东西吗，应该不可以吧，

    public MainAdapter(FragmentManager fm, List<EntryType> types) {
        super(fm);
        this.types = types;
    }

    @Override
    public Fragment getItem(int position) {//获取对应位置上的Fragment，也就是在adapter中直接newfragment适配到viewpager上
        return EntryFragment.newInstance(types.get(position).getId());//传过去id给Fragment，有多少个就new多少个fragment，跳转
    }

    @Override
    public int getCount() {
        return types.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return types.get(position).getName();//每个pager上的标题数据
    }
}
