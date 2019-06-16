package com.krava11.moneytracker;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder> {

    private List<Item> data = new ArrayList<>();
    private ItemsAdapterListener listener = null;

//    public ItemsAdapter() {
//        createData();
//    }

    public void setListener(ItemsAdapterListener listener){
        this.listener = listener;
    }

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
    public void onBindViewHolder(@NonNull ItemsAdapter.ItemViewHolder viewHolder, int position) {
        Item item = data.get(position);
        viewHolder.applyData(item, position, listener, selections.get(position,false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Item item) {
        data.add(0,item);
        notifyItemInserted(0);
    }

    private SparseBooleanArray selections = new SparseBooleanArray();

    public void toggleSelection(int position) {
        if (selections.get(position,false)){
            selections.delete(position);
        }else{
            selections.put(position,true);
        }

        notifyItemChanged(position);
    }

    public void clearSelection(){
        selections.clear();
        notifyDataSetChanged();
    }

    public int getSelectionItemsCount(){
        return selections.size();
    }

    public List<Integer> getSelectedItems(){
        List<Integer> items = new ArrayList<>(selections.size());
        for (int i = 0; i < selections.size(); i++) {
            items.add(selections.keyAt(i));
        }
        return items;

    }

    public Item removeItem(int position){
        Item item = data.remove(position);
        notifyItemRemoved(position);
        return item;
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }

        public void applyData(final Item item, final int position, final ItemsAdapterListener listener, boolean selected) {
            title.setText(item.title);
            price.setText(String.format("%s\u20BD",item.price));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onItemClick(item,position);
                    }

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null){
                        listener.onItemLongClick(item,position);
                    }
                    return true;
                }
            });

            itemView.setActivated(selected);
        }
    }

}
