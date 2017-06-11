package com.bawei.lvwenjing.xiangmu_er_zhoukao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.irecyclerview.IRecyclerView;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by lenovo-pc on 2017/7/11.
 */

public class Fragment02 extends Fragment {
    private LoadMoreFooterView loadMoreFooterView;
    private IRecyclerView recyclerView;
    private Adapters adapter;
    private List<Beans> url;

    private GoogleApiClient client;
    private View view;
    private ResLiuAdapter adapters;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment02, container, false);
        init();
        return view;
    }

    private void init() {


        recyclerView = (IRecyclerView) view.findViewById(R.id.recycleview_ids);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置layoutManager
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        url = getUrl("http://qhb.2dyt.com/Bwei/news?postkey=1503d&page=1&type=1");
        adapters = new ResLiuAdapter(getActivity(), url);
        recyclerView.setIAdapter(adapters);
        //设置item之间的间隔


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

