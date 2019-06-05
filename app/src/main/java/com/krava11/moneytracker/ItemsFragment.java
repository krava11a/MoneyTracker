package com.krava11.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemsFragment extends Fragment {

    private static final int TYPE_UNKNOWN = -1;
    public static final int TYPE_INCOMES = 1;
    public static final int TYPE_EXPENSES = 1;
    public static final String TYPE_KEY = "key";

    public static ItemsFragment createItemsFragment(int type){
        ItemsFragment fragment = new ItemsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ItemsFragment.TYPE_KEY, ItemsFragment.TYPE_INCOMES);
        fragment.setArguments(bundle);
        return fragment;
    }



    private int type;
    private RecyclerView recyclerView;
    private ItemsAdapter adapter;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ItemsAdapter();

        Bundle bundle = getArguments();
        bundle.getInt(TYPE_KEY,TYPE_UNKNOWN);

        if (type==TYPE_UNKNOWN){
            throw new IllegalArgumentException("UNKNOWN type");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new SpacesItemDecorator(10,10,5,5));
        recyclerView.setAdapter(adapter);
    }
}
