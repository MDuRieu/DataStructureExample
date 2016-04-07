package com.example.the_crab.dataexample.data;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.the_crab.dataexample.R;

import java.util.List;

/**
 * Created by the_crab on 6/04/16.
 */
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private List<DummyData> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView word, meaning;

        public MyViewHolder(View view) {
            super(view);
            word = (TextView) view.findViewById(R.id.word_textView);
            meaning = (TextView) view.findViewById(R.id.meaning_textView);
        }
    }

    public DataAdapter(List<DummyData> dataList) {
        this.dataList = dataList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DummyData dummyData = dataList.get(position);
        holder.word.setText(dummyData.getWord());
        holder.meaning.setText(dummyData.getMeaning());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
