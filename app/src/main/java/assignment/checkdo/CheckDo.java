package assignment.checkdo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.LinkedList;
import java.util.List;

public class CheckDo extends Fragment{

    RecyclerView recyclerView;
    String []data = {"Hello", "Hii", "Welcome"};
    int counter = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<String> items = new LinkedList<>();
        items.add("Code it");

        View view = inflater.inflate(R.layout.fragment_check_do, container, false);


        recyclerView = view.findViewById(R.id.recyclerView);
        CheckDoAdapter checkDoAdapter = new CheckDoAdapter(items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(checkDoAdapter);

        view.findViewById(R.id.add).setOnClickListener(V -> {
            items.add(data[counter%3]);
            counter++;
            checkDoAdapter.notifyItemInserted(items.size()-1);
        });
        return view;
    }
}