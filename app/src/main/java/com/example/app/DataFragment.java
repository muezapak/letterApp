package com.example.app;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class DataFragment extends Fragment {

    RecyclerView recview;
    ArrayList<Model> dataholder;
    DBHandler dbHandler;
    MyAdapter adapter;

    public DataFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_data, container, false);
        recview = view.findViewById(R.id.recView);
        dataholder = new ArrayList<>();
        dbHandler = new DBHandler(getContext());
        adapter = new MyAdapter(dataholder);

        recview.setAdapter(adapter);
        recview.setLayoutManager(new LinearLayoutManager(getContext()));

        loadData();

        return view;
    }

    private void loadData() {
        try {
            Cursor cursor = dbHandler.getAllData();
            if (cursor.getCount() == 0) {
                Toast.makeText(getContext(), "No Data yet", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range")String id=cursor.getString(cursor.getColumnIndex("id"));
                    @SuppressLint("Range") String Canswer = cursor.getString(cursor.getColumnIndex("CAnswer"));
                    @SuppressLint("Range") String Uanswer = cursor.getString(cursor.getColumnIndex("UAnswer"));
                    @SuppressLint("Range") String alphabet = cursor.getString(cursor.getColumnIndex("Alphabet"));

                    Model model = new Model(Canswer, Uanswer, alphabet,id);
                    dataholder.add(model);
                }
                adapter.notifyDataSetChanged();
            }
            cursor.close();
        }catch(Exception e)
        {
            Toast.makeText(getContext(), "load wala"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage().toString());

        }
    }

}
