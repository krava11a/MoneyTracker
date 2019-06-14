package com.krava11.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> data = new ArrayList<>();

//    public ItemsAdapter() {
//        createData();
//    }

    public void setData(List<Item> data){
        this.data = data;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ItemsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record1, parent, false);

//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view1 = inflater.inflate(R.layout.item_record1, parent, false);

        return new ItemsAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder viewHolder, int i) {
        Item item = data.get(i);
        viewHolder.applyData(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Item item) {
        data.add(0,item);
        notifyItemInserted(0);
    }

//    private void createData() {
//        data.add(new Item("Молоко", 100));
//        data.add(new Item("Жизнь", 200));
//        data.add(new Item("Хлеб", 30));
//        data.add(new Item("Курсы", 30000));
//        data.add(new Item("Тот самый ужин который я оплатил за всех картой", 4600));
//        data.add(new Item("", 0));
//        data.add(new Item("Ужин с любимой женой", 987));
//        data.add(new Item("ракета", 90));
//        data.add(new Item("Тысячелетний сокол", 100000000));
//        data.add(new Item("Macbook Pro", 240000));
//        data.add(new Item("Машина", 620000));
//        data.add(new Item("Молоко", 100));
//        data.add(new Item("Жизнь", 200));
//        data.add(new Item("Хлеб", 30));
//        data.add(new Item("Курсы", 30000));
//        data.add(new Item("Тот самый ужин который я оплатил за всех картой", 4600));
//        data.add(new Item("", 0));
//        data.add(new Item("Ужин с любимой женой", 987));
//        data.add(new Item("ракета", 90));
//        data.add(new Item("Тысячелетний сокол", 100000000));
//        data.add(new Item("Macbook Pro", 240000));
//        data.add(new Item("Машина", 620000));
//    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }

        public void applyData(Item item) {
            title.setText(item.title);
            price.setText(String.format("%s\u20BD",item.price));
        }
    }

}
