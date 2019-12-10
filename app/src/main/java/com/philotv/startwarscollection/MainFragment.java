package com.philotv.startwarscollection;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainFragment extends Fragment implements View.OnClickListener {
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        view.findViewById(R.id.view_transactions_btn).setOnClickListener(this);
        view.findViewById(R.id.view_balance_btn).setOnClickListener(this);
        view.findViewById(R.id.send_money_btn).setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_transactions_btn:
                navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment);
                break;
            case R.id.send_money_btn:
                navController.navigate(R.id.action_mainFragment_to_chooseRecipientFragment);
                break;
            case R.id.view_balance_btn:
                navController.navigate(R.id.action_mainFragment_to_viewBalanceFragment);
                break;

        }

    }
}
