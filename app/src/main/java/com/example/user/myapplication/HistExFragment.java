package com.example.user.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.data.ets.HistEx;
import com.data.ets.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utils.EtsUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistExFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistExFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistExFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

   static ArrayList<HistEx> dataModels;
    ListView listView;
    private static HistExListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public HistExFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistExFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistExFragment newInstance(String param1, String param2) {
        HistExFragment fragment = new HistExFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


       // listView=(ListView)getActivity().findViewById(R.id.HistExList);

        dataModels= new ArrayList<>();

        adapter= new HistExListAdapter(dataModels,getActivity().getApplicationContext());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRootRef = database.getReference();

        Log.i("Test","INIT");
        User user = EtsUtils.getSavedObjectFromPreference(getContext(),"user", User.class); //get User
        user.getUserName(); //เวลาใช้
        DatabaseReference mHistExRef = mRootRef.child("histEx/"+user.getUserCode());
        mHistExRef.orderByChild("histexDate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Test","Add");
                List<HistEx> exList = new ArrayList<>();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    HistEx HistEx = messageSnapshot.getValue(HistEx.class);
                    exList.add(0,HistEx);
                }
                adapter.addAll(exList);

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Test","Add");
            }


            // ...
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historyex, container, false);

        // Set the adapter
        listView = (ListView) view.findViewById(R.id.HistExList);
      listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                HistEx dataModel= dataModels.get(position);

                Fragment newFragment = new HistExDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("selectedHistEx", Parcels.wrap(dataModel));
                newFragment.setArguments(bundle);

                FragmentTransaction transaction =  getActivity().getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.frame, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
                Snackbar.make(view, "test", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
