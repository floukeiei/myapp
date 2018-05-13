package com.example.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.data.ets.History;
import com.data.ets.Plan;
import com.data.ets.User;
import com.utils.EtsUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.heading_title_profile));
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        History  history =  EtsUtils.getSavedObjectFromPreference(getActivity().getApplicationContext(),"history", History.class);
        User user = EtsUtils.getSavedObjectFromPreference(getActivity().getApplicationContext(),"user", User.class);
        Plan plan = EtsUtils.getSavedObjectFromPreference(getActivity().getApplicationContext(),"plan", Plan.class);

        TextView txtName = (TextView) view.findViewById(R.id.profile_name);
        TextView txtGender = (TextView) view.findViewById(R.id.profile_gender);
        TextView txtAge = (TextView) view.findViewById(R.id.profile_age);
        TextView txtRisk = (TextView) view.findViewById(R.id.profile_risk);
        TextView txtPlan = (TextView) view.findViewById(R.id.profile_plan);

        txtName.setText(user.getUserName() +" "+user.getUserSurname());
        String riskLabel = "";
        try{
            txtAge.setText( String.valueOf(EtsUtils.getAge(user.getUserBirthday())));
            String risk = EtsUtils.calRisk(user,history);
            if("1".equals(risk)){
                riskLabel = "ต่ำ";
            }else if("2".equals(risk)){
                riskLabel = "ปานกลาง";
            }else if("3".equals(risk)){
                riskLabel = "สูง";
            }else if("4".equals(risk)){
                riskLabel = "สูงมาก";
            }else if("5".equals(risk)){
                riskLabel = "สูงอันตราย";
            }
        }catch (Exception e){

        }
        txtGender.setText( "M".equals( user.getUserGender())?"ชาย":"หญิง");
        txtRisk.setText(riskLabel);
        String planLevel = "";
        if("L".equals(plan.getPlanMaxLevel())){
            planLevel = "ต่ำ";
        }else if("M".equals(plan.getPlanMaxLevel())){
            planLevel = "ปานกลาง";
        }else{
            planLevel = "สูง";
        }
        txtPlan.setText(planLevel);

        Button buttonEdit = (Button) view.findViewById(R.id.profile_edit);

        buttonEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(), NameActivity.class);
            i.putExtra("fromPage","Menu");
            startActivity(i);
            //return true;
            }
        });

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
