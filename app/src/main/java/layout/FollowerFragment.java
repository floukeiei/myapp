package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.data.ets.Follower;
import com.data.ets.HistEx;
import com.data.ets.User;
import com.example.user.myapplication.FollowerListAdapter;
import com.example.user.myapplication.HistExListAdapter;
import com.example.user.myapplication.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.utils.EtsUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FollowerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FollowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    static ArrayList<Follower> dataModels;
    ListView listView;
    private static FollowerListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public FollowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowerFragment newInstance(String param1, String param2) {
        FollowerFragment fragment = new FollowerFragment();
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


        dataModels= new ArrayList<>();

        adapter= new FollowerListAdapter(dataModels,getActivity().getApplicationContext());
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRootRef = database.getReference();

        Log.i("Test","INIT");
        User user = EtsUtils.getSavedObjectFromPreference(getContext(),"user", User.class); //get User
        user.getUserName(); //เวลาใช้
        DatabaseReference mHistExRef = mRootRef.child("follow/"+user.getUserCode());
        mHistExRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Test","Add");
                List<Follower> exList = new ArrayList<>();
                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    Follower follower = messageSnapshot.getValue(Follower.class);
                    follower.setFollowKey(messageSnapshot.getKey());
                    exList.add(0,follower);
                }
                adapter.clear();
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
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.heading_title_follower));

        View view = inflater.inflate(R.layout.fragment_follower, container, false);
        listView = (ListView) view.findViewById(R.id.FollowerList);
        listView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
