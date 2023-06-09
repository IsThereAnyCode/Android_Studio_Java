package assignment.checkdo.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import assignment.checkdo.AddNewTask;
import assignment.checkdo.Model.ToDoModel;
import assignment.checkdo.R;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {
    private List<ToDoModel> todoList;
    private FragmentActivity activity;
    private FirebaseFirestore firestore;

    //
    // 문제 생길시 확인할 것!!!
    //
     public ToDoAdapter(FragmentActivity activity, List<ToDoModel> todoList) {
         this.todoList = todoList;
         this.activity  = activity;
     }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.each_task, parent, false);
        firestore = FirebaseFirestore.getInstance();


        return new MyViewHolder(view);
    }
    public void deleteTask(int position) {
         ToDoModel toDoModel = todoList.get(position);
         firestore.collection("task").document(toDoModel.TaskId).delete();
         todoList.remove(position);
         notifyItemRemoved(position);
    }
    public Context getContext(){
         return activity;
    }
    public void editTask(int position) {
         ToDoModel toDoModel = todoList.get(position);

        Bundle bundle = new Bundle();
        bundle.putString("task", toDoModel.getTask());
        bundle.putString("due", toDoModel.getDue());
        bundle.putString("id", toDoModel.TaskId);

        AddNewTask addNewTask = new AddNewTask();
        addNewTask.setArguments(bundle);
        addNewTask.show(activity.getSupportFragmentManager(), addNewTask.getTag());
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

         ToDoModel toDoModel = todoList.get(position);
         holder.mCheckBox.setText(toDoModel.getTask());
         holder.mDueDateTv.setText("Due on " + toDoModel.getDue());
         holder.mCheckBox.setChecked(toBoolean(toDoModel.getStatus()));
         holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                 if(isChecked) {
                     firestore.collection("task").document(toDoModel.TaskId).update("status", 1);
                 }else {
                     firestore.collection("task").document(toDoModel.TaskId).update("status", 0);
                 }
             }
         });

    }
    private boolean toBoolean(int status) {
         return status != 0;
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        TextView mDueDateTv;
        CheckBox mCheckBox;
         public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mDueDateTv = itemView.findViewById(R.id.due_date_tv);
            mCheckBox = itemView.findViewById(R.id.mcheckbox);
         }
    }
}
