package com.krava11.moneytracker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsFragment extends Fragment {

    private static final String TAG = "ItemsFragment";

    public static final int ADD_ITEM_REQUEST_CODE=777;

    public static final String TYPE_KEY = "key";
    private Api api;

    public static ItemsFragment createItemsFragment(String type) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ItemsFragment.TYPE_KEY, type);
        fragment.setArguments(bundle);
        return fragment;
    }


    private String type;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        type = bundle.getString(TYPE_KEY, Item.TYPE_UNKNOWN);

        if (type.equals(Item.TYPE_UNKNOWN)) {
            throw new IllegalArgumentException("UNKNOWN type");
        }

        api = ((App) getActivity().getApplication()).getApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpacesItemDecorator(10, 10, 5, 5));
        recyclerView.setAdapter(adapter);



        swipeRefreshLayout = view.findViewById(R.id.refresh);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.CYAN,Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadItems();
                swipeRefreshLayout.setRefreshing(false);
            }


        });

        loadItems();
    }

    private void loadItems() {
        Call<List<Item>> call = api.getItems(type);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
                Log.d(TAG, "onResponse: thread name = " + Thread.currentThread().getName());
                adapter.setData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ITEM_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Item item = data.getParcelableExtra("item");
            if (item.type.equals(type)){
                adapter.addItem(item);
                recyclerView.scrollToPosition(0);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
