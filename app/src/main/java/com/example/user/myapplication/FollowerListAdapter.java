package com.example.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.data.ets.Follower;
import com.data.ets.HistEx;
import com.data.ets.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.utils.EtsUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by User on 28/10/2560.
 */


public class FollowerListAdapter extends ArrayAdapter<Follower> implements View.OnClickListener{

    private ArrayList<Follower> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {

        TextView txtMail;

    }

    public FollowerListAdapter(ArrayList<Follower> data, Context context) {
        super(context, R.layout.follow_row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Follower dataModel=(Follower)object;

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
       final Follower dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.follow_row_item, parent, false);
            viewHolder.txtMail = (TextView) convertView.findViewById(R.id.follow_row_item_friendmail);




            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

//START ปุ่มยืนยัน
        Button buttondelete = (Button) convertView.findViewById(R.id.deleteEmail);

        buttondelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference mRootRef = database.getReference();
                User user = EtsUtils.getSavedObjectFromPreference(getContext(),"user", User.class); //get User
                TextView email = (TextView) v.findViewById(R.id.follow_row_item_friendmail);
                DatabaseReference mfollowExRef = mRootRef.child("follow/"+user.getUserCode()+"/"+dataModel.getFollowKey());

                mfollowExRef.removeValue();
            }
        });
       viewHolder.txtMail.setText(dataModel.getEmail());

        // Return the completed view to render on screen
        return convertView;
    }
}

