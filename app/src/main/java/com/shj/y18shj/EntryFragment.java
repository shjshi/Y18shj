package com.shj.y18shj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
 //�ܽ᣺�����Ϊ����ʾ����ҳ���fragment��
public class EntryFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ID = "arg_id";//

    // TODO: Rename and change types of parameters
    private int id;//fragmentadapter�д�������id
    private EntryType type;//
    private EntryAdapter adapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EntryFragment.
     */
    // TODO: Rename and change types and number of parameters
	//�����ȡfragment����ķ��������id������ز�ͬ��ҳ�棬���id��EntryType���е�id
    public static EntryFragment newInstance(int id) {//id��EntryType�л�ȡ
        EntryFragment fragment = new EntryFragment();//�Լ����Լ��������ǵ�
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public EntryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {//�����Լ����Լ����Ĳ���
            id = getArguments().getInt(ARG_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry, container, false);
    }
    @ViewInject(R.id.recycler)
    private RecyclerView recycler;//���ã��൱��listview
    @ViewInject(R.id.refresh)
    private SwipeRefreshLayout refresh;//���ã�ˢ��
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ViewUtils.inject(this, view);
        try {
            type = DbHelper.getUtils().findById(EntryType.class, id);
        } catch (DbException e) {
            e.printStackTrace();
        }
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));//������
        adapter = new EntryAdapter(getActivity(), new ArrayList<Entry>());//������ҳ�����ݴ���������
        recycler.setAdapter(adapter);
        refresh.setOnRefreshListener(this);
        try {
            List<Entry> e = type.getFinder().getAllFromDb();//entry��entrytypeͨ��id��������ô��type��ͨ��id�Ϳ����ҵ�����entry��ص�id�Ķ���
            if (e != null && e.size() > 0){
                adapter.addAll(e);
            }
        } catch (DbException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onRefresh() {
        HttpUtils utils = new HttpUtils(10000);
        utils.send(HttpRequest.HttpMethod.GET,
                String.format("http://api.yi18.net/lore/list?id=%d", type.getId()),
                new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        JSONObject object = JSON.parseObject(responseInfo.result);
                        List<Entry> yi18 = JSON.parseArray(object.getString("yi18"), Entry.class);
                        adapter.addAll(yi18);
                        for (Entry e : yi18) {
                            e.setType(type);
                        }
                        try {
                            DbHelper.getUtils().saveOrUpdateAll(yi18);
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                        refresh.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(HttpException e, String s) {
                        refresh.setRefreshing(false);
                    }
                });
    }
}
