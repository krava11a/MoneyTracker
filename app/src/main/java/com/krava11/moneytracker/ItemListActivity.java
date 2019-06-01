package com.krava11.moneytracker;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Record> data = new ArrayList<>();
    private ItemListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SpacesItemDecorator spacesItemDecorator = new SpacesItemDecorator(10,10,5,5);
        recyclerView.addItemDecoration(spacesItemDecorator);

        adapter = new ItemListAdapter();
        createData();
        recyclerView.setAdapter(adapter);
    }

    private void createData() {
        data.add(new Record("Молоко", 100));
        data.add(new Record("Жизнь", 200));
        data.add(new Record("Хлеб", 30));
        data.add(new Record("Курсы", 30000));
        data.add(new Record("Тот самый ужин который я оплатил за всех картой", 4600));
        data.add(new Record("", 0));
        data.add(new Record("Ужин с любимой женой", 987));
        data.add(new Record("ракета", 90));
        data.add(new Record("Тысячелетний сокол", 100000000));
        data.add(new Record("Macbook Pro", 240000));
        data.add(new Record("Машина", 620000));
        data.add(new Record("Молоко", 100));
        data.add(new Record("Жизнь", 200));
        data.add(new Record("Хлеб", 30));
        data.add(new Record("Курсы", 30000));
        data.add(new Record("Тот самый ужин который я оплатил за всех картой", 4600));
        data.add(new Record("", 0));
        data.add(new Record("Ужин с любимой женой", 987));
        data.add(new Record("ракета", 90));
        data.add(new Record("Тысячелетний сокол", 100000000));
        data.add(new Record("Macbook Pro", 240000));
        data.add(new Record("Машина", 620000));
    }

    private class ItemListAdapter extends RecyclerView.Adapter<RecordViewHolder> {
        @NonNull
        @Override
        public RecordViewHolder onCreateViewHolder(ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_record1, parent, false);
            return new RecordViewHolder(view);
        }

        @Override
        public void onBindViewHolder( RecordViewHolder viewHolder, int i) {
            Record record = data.get(i);
            viewHolder.applyData(record);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    private class RecordViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView price;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
        }

        public void applyData(Record record) {
            title.setText(record.getTitle());
            price.setText(String.format("%s\u20BD",String.valueOf(record.getPrice())));
        }
    }


    private class SpacesItemDecorator extends RecyclerView.ItemDecoration {
        private int left;
        private int right;
        private int top;
        private int bottom;

        public SpacesItemDecorator(int left, int right, int top, int bottom) {
            this.left = left;
            this.right = right;
            this.top = top;
            this.bottom = bottom;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public int getTop() {
            return top;
        }

        public int getBottom() {
            return bottom;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.bottom=bottom;
            outRect.top=top;
            outRect.right=right;
            outRect.left=left;
        }
    }
}
