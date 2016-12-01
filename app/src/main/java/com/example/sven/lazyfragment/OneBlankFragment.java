package com.example.sven.lazyfragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.example.sven.lazyfragment.base.LazyFragment;
import com.example.sven.lazyfragment.view.adapter.CommonAdapter;
import com.example.sven.lazyfragment.view.adapter.CommonViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneBlankFragment extends LazyFragment implements OnRefreshListener, OnLoadMoreListener {

    private static final String TAG = "OneBlankFragment";
    @Bind(R.id.swipe_target)
    RecyclerView swipeTarget;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    private ArrayList<String> mList = new ArrayList<>();
    private CommonAdapter<String> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        swipeToLoadLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_blank, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        swipeTarget.setLayoutManager(layoutManager);
        mAdapter = new CommonAdapter<String>(getActivity(), mList, R.layout.item_recyclerview) {
            @Override
            public void init(CommonViewHolder holder, String bean, int position) {
                holder.setText(R.id.tv, bean);
            }
        };
        swipeTarget.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        if (!isPrepared || !isVisble|| mList.size() > 0) {
            return;
        }
        swipeToLoadLayout.setRefreshing(true);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeToLoadLayout.setRefreshing(false);
                mList.clear();
                for (int i = 0; i < 10; i++) {
                    mList.add(i + "");
                }
            }
        }, 1000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeToLoadLayout.setLoadingMore(false);
                for (int i = 0; i < 10; i++) {
                    mList.add(i + "");
                }
            }
        }, 1000);
    }
}
