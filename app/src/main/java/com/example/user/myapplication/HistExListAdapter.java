package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.data.ets.HistEx;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by User on 28/10/2560.
 */


public class HistExListAdapter extends ArrayAdapter<HistEx> implements View.OnClickListener{

    private ArrayList<HistEx> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView txtDate;
        TextView txtDistance;
        TextView txtInZoneTime;

    }

    public HistExListAdapter(ArrayList<HistEx> data, Context context) {
        super(context, R.layout.histex_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        HistEx dataModel=(HistEx)object;

//        switch (v.getId())
//        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
//        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HistEx dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.histex_row_item, parent, false);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.histex_row_item_date);
            viewHolder.txtDistance = (TextView) convertView.findViewById(R.id.histList_distance);
            viewHolder.txtInZoneTime = (TextView) convertView.findViewById(R.id.histlist_inzone);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        viewHolder.txtDate.setText(dateFormatter.format(dataModel.getHistexDate()));
        viewHolder.txtDistance.setText(String.valueOf(dataModel.getHistexDistance()));

        BigDecimal inZone = new BigDecimal(dataModel.getHistexInZoneTime());
        BigDecimal var60 = new BigDecimal("60");

        BigDecimal minutes = inZone.divide(var60, BigDecimal.ROUND_FLOOR);
        BigDecimal seconds = inZone.remainder(var60);
        viewHolder.txtInZoneTime.setText(minutes.toString()+":"+seconds.toString() );

        // Return the completed view to render on screen
        return convertView;
    }
}

