package com.aviral.androidme.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.aviral.androidme.R;
import com.aviral.androidme.data.AndroidImageAssets;

import org.jetbrains.annotations.NotNull;

public class MasterListFragment extends Fragment {

    OnImageClickListener mCallBack;

    public interface OnImageClickListener {
        void OnImageSelected(int position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            mCallBack = (OnImageClickListener) context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement onClickListener");
        }
    }

    public MasterListFragment() {
    }

    // Inflates the GridView of all AndroidMe images
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        // Get a reference to the GridView in the fragment_master_list xml layout file
        GridView gridView =  rootView.findViewById(R.id.images_grid_view);

        // Create the adapter
        // This adapter takes in the context and an ArrayList of ALL the image resources to display
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        // Set the adapter on the GridView
        gridView.setAdapter(mAdapter);

        gridView.setOnItemClickListener((adapterView, view, i, l) -> mCallBack.OnImageSelected(i));

        // Return the root view
        return rootView;
    }

}
