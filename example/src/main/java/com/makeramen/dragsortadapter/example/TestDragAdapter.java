package com.makeramen.dragsortadapter.example;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makeramen.dragsortadapter.DragSortAdapter;
import com.makeramen.dragsortadapter.NoForegroundShadowBuilder;
import com.makeramen.dragsortadapter.example.util.EnglishNumberToWords;

import java.util.List;

/**
 * Created by zhang . DATA: 2017/4/5 . Description : 可拖动 recyclerview 的 adapter
 */

public class TestDragAdapter extends DragSortAdapter<TestDragAdapter.MainViewHolder> {

    private static final String TAG = "TestDragAdapter";

    private final List<Integer> data;

    public TestDragAdapter(RecyclerView recyclerView, List<Integer> data) {
        super(recyclerView);
        this.data = data;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_example, parent, false);
        MainViewHolder holder = new MainViewHolder(this, view);
        view.setOnClickListener(holder);
        view.setOnLongClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder,final int position) {
        int itemId = data.get(position);
        holder.text.setText(EnglishNumberToWords.convert(itemId));
        // NOTE: check for getDraggingId() match to set an "invisible space"
        // while dragging
        holder.container.setVisibility(getDraggingId() == itemId ? View.INVISIBLE : View.VISIBLE);
        holder.container.postInvalidate();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getPositionForId(long id) {
        return data.indexOf((int) id);
    }

    @Override
    public boolean move(int fromPosition, int toPosition) {
        data.add(toPosition, data.remove(fromPosition));
        return true;
    }



    static class MainViewHolder extends DragSortAdapter.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        public ViewGroup container;

        public TextView text;

        public MainViewHolder(DragSortAdapter dragSortAdapter, View itemView) {
            super(dragSortAdapter, itemView);
            container = (ViewGroup) itemView.findViewById(R.id.container);
            text = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onClick(@NonNull View v) {
            Log.d(TAG, text.getText() + " clicked!");
        }

        @Override
        public boolean onLongClick(@NonNull View v) {
            startDrag();
            return true;
        }

        @Override
        public View.DragShadowBuilder getShadowBuilder(View itemView, Point touchPoint) {
            return new NoForegroundShadowBuilder(itemView, touchPoint);
        }
    }

}
