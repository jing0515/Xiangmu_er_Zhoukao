package com.bawei.lvwenjing.xiangmu_er_zhoukao;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by lenovo-pc on 2017/7/11.
 */

public class Fragment01 extends Fragment {
    private LinearLayoutManager linearLayoutManager;
    int num = 0;

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    recyclerView.setRefreshing(false);
                    break;
                case 2:
                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);

                    break;
            }
        }
    };
    private LoadMoreFooterView loadMoreFooterView;
    private IRecyclerView recyclerView;
    private Adapters adapter;
    private List<Beans> url;

    private GoogleApiClient client;
    private View view;
    private ResAdapter adapters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment01, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = (IRecyclerView) view.findViewById(R.id.recycleview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        recyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .color(Color.RED)
                        .build());
        url = getUrl("http://qhb.2dyt.com/Bwei/news?postkey=1503d&page=1&type=1");
        adapters = new ResAdapter(getActivity(), url);
        recyclerView.setIAdapter(adapters);
        adapters.setOnItemClickListener(new ResAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(int position, View view) {
                if (url.get(position) != null) {
                    startActivity(new Intent(getActivity(), Itmer_dome.class));
                }
            }

            @Override
            public void onItemLongClickListener(int position, View view) {

            }
        });

    }


    public List<Beans> getUrl(String path) {
        List<Beans> lists = new ArrayList<Beans>();
        try {
            String s = new Asytask().execute(path).get();
            Gson gson = new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            List<Bean.ListBean> list = bean.getList();
            for (Bean.ListBean listBean : list) {
                lists.add(new Beans(listBean.getDate(), listBean.getId(), listBean.getPic(), listBean.getTitle(), listBean.getType()));
            }

            return lists;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


}

