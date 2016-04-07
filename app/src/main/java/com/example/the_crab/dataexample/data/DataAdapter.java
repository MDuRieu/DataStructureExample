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
 * The Adapter is the interface between the data layer and the UI. It's used in this case because
 * we have a list of items to display which is subject to change. So far it's just using a list of
 * dummy data but will be updated to use a Loader which will access the database on a background
 * thread.
 *
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
    //This method describes which view should be inflated to be used for each RecyclerView
    //Note that it returns a new MyViewHolder which is passed the view inflated in this method.
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    //This is the method called for each instance of the RecyclerView. It describes
    //which data should be set to the views in the RecyclerView layout.
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DummyData dummyData = dataList.get(position);
        holder.word.setText(dummyData.getWord());
        holder.meaning.setText(dummyData.getMeaning());
    }

    @Override
    public int getItemCount() {
        //This method determines how many times the recyclerview will be recycled
        //eg if you set this to two and give it a data set with 10 items, only 2 recyclerviews
        //will be made
        return dataList.size();
    }
}
