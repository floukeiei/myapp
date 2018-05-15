package com.example.user.myapplication;

import android.support.design.widget.Snackbar;
import android.widget.ArrayAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

import com.data.ets.Plan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 28/10/2560.
 */


public class PlanListAdapter extends ArrayAdapter<Plan> implements View.OnClickListener{

    private ArrayList<Plan> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView txtDate;
        TextView txtPlanLevel;
        TextView txtPlanTime;

    }

    public PlanListAdapter(ArrayList<Plan> data, Context context) {
        super(context, R.layout.plan_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Plan dataModel=(Plan)object;

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
        Plan dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.plan_row_item, parent, false);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.plan_row_item_date);
            viewHolder.txtPlanLevel = (TextView) convertView.findViewById(R.id.plan_row_item_level);
            viewHolder.txtPlanTime = (TextView) convertView.findViewById(R.id.plan_row_item_time);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        viewHolder.txtDate.setText(dateFormatter.format(dataModel.getPlanDate()));
        String planLevel;
        if("H".equals(dataModel.getPlanMaxLevel())){
            planLevel = "ออกกำลังกายระดับสูง";
        }else if("M".equals(dataModel.getPlanMaxLevel())){
            planLevel = "ออกกำลังกายระดับกลาง";
        }else{
            planLevel = "ออกกำลังกายระดับต่ำ";
        }
        viewHolder.txtPlanLevel.setText(planLevel);

        viewHolder.txtPlanTime.setText(String.valueOf(dataModel.getPlanTime()));

        // Return the completed view to render on screen
        return convertView;
    }
}

