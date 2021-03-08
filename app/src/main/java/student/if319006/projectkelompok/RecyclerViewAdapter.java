package student.if319006.projectkelompok;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private Context context;
    private List<Result> results;

    public RecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = results.get(position);
        holder.textViewId.setText(result.getId());
        holder.textViewNama.setText(result.getNama());
        holder.textViewHarga.setText(result.getHarga());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.textNama) TextView textViewNama;
        @BindView(R.id.textHarga) TextView textViewHarga;
        @BindView(R.id.textId) TextView textViewId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            String id = textViewId.getText().toString();
            String nama = textViewNama.getText().toString();
            String harga = textViewHarga.getText().toString();

            Intent i = new Intent(context, UpdateActivity.class);
            i.putExtra("id", id);
            i.putExtra("nama", nama);
            i.putExtra("harga", harga);
            context.startActivity(i);
        }
    }
}
