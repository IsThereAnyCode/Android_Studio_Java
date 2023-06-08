package assignment.checkdo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckDoAdapter extends RecyclerView.Adapter<VH>{

    List<String> items;

    public CheckDoAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkdo_item, parent, false);
        return new VH(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

class VH extends RecyclerView.ViewHolder {

    TextView textView;
    private CheckDoAdapter adapter;

    public VH(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.setDate);
        itemView.findViewById(R.id.checkDo).setOnClickListener(view -> {
            adapter.items.remove(getAdapterPosition());
            adapter.notifyItemRemoved(getAdapterPosition());
        });
    }

    public VH linkAdapter(CheckDoAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}