package com.krava11.moneytracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

        loadItems();
    }

    private void loadItems() {
        Call<List<Item>> call = api.getItems(type);
        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(@NonNull Call<List<Item>> call, @NonNull Response<List<Item>> response) {
                Log.d(TAG, "onResponse: thread name = "+Thread.currentThread().getName());
                adapter.setData(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Item>> call, @NonNull Throwable t) {

            }
        });
    }

    //asyncTask and thread

//    private void loadItems() {
//        AsyncTask<Void, Void, List<Item>> task = new AsyncTask<Void, Void, List<Item>>() {
//            @Override
//            protected void onPreExecute() {
//                Log.d(TAG, "onPreExecute: thread Name = " + Thread.currentThread().getName());
//            }
//
//            @Override
//            protected List<Item> doInBackground(Void... voids) {
//                Log.d(TAG, "onPreExecute: thread Name = " + Thread.currentThread().getName());
//
//                Call<List<Item>> call = api.getItems(type);
//                try {
//                    List<Item> items = call.execute().body();
//                    return items;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(List<Item> items) {
//                if (items!= null) adapter.setData(items);
//            }
//        };
//
//        task.execute();
//    }

    //handler and thread

//    private void loadItems() {
//        Log.d(TAG, "loadItems: current thread "+Thread.currentThread().getName());
//
//        new LoadItemTask(new Handler(callback)).start();
//
//
//
//
//    }
//
//    private Handler.Callback callback = new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (msg.what ==DATA_LOADED){
//                List<Item> items=(List<Item>) msg.obj;
//                adapter.setData(items);
//            }
//            return true;
//        }
//    };
//
//    private final static int DATA_LOADED=123;
//
//    private class LoadItemTask implements Runnable{
//
//        private Thread thread;
//        private Handler handler;
//
//        public LoadItemTask(Handler handler) {
//            thread = new Thread(this);
//            this.handler = handler;
//        }
//
//        private void start(){
//            thread.start();
//        }
//
//        @Override
//        public void run() {
//
//            Log.d(TAG, "run: current thread "+Thread.currentThread().getName());
//
//            Call<List<Item>> call = api.getItems(type);
//            try {
//                List<Item> items = call.execute().body();
//                handler.obtainMessage(DATA_LOADED,items).sendToTarget();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}
