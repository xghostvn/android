package com.example.theripper.a2048;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by The Ripper on 11/16/2017.
 */

public class SquareAdapter extends BaseAdapter{

   private Context context;
   private int layout;
   private ArrayList<Integer> arrayList;
   Datagame datagame=new Datagame();

    public SquareAdapter(Context context, int layout, ArrayList<Integer> arrayList) {
        this.context = context;
        this.layout = layout;
        this.arrayList = arrayList;
    }
    @Override
    public void notifyDataSetChanged() {
        arrayList=datagame.getArrayList();
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(layout,null);
        Square square= view.findViewById(R.id.square);
        int nubmer=arrayList.get(i);
        square.settext(nubmer);
        return view;
    }


}
