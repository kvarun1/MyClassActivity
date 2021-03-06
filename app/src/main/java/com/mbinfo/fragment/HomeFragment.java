package com.mbinfo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mbinfo.myclassactivity.ClickToCall;
import com.mbinfo.myclassactivity.R;
import com.mbinfo.util.SendMessage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
SendMessage sendMessage;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText name;
    Button senddata,clicktoactivity;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        sendMessage = (SendMessage) getActivity();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        name = view.findViewById(R.id.txtedit);
        clicktoactivity = view.findViewById(R.id.button4);
        senddata = view.findViewById(R.id.click);
        senddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = name.getText().toString();
                sendMessage.sendData(data);

            }
        });
        clicktoactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = name.getText().toString();
                // using bundle transfer data
                Bundle bundle = new Bundle();
                bundle.putString("value",data);
              Intent i = new Intent(getActivity(), ClickToCall.class);
              i.putExtra("message",data);
              i.putExtra("number", 8);
              startActivity(i);

            }
        });
        return view;
    }
}