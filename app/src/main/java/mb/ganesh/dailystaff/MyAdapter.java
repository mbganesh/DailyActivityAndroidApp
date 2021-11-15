package mb.ganesh.dailystaff;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    ArrayList<String> dateList;
    ArrayList<String> cigList;
    ArrayList<String> coolList;

    public MyAdapter(Context context, ArrayList<String> dateList, ArrayList<String> cigList, ArrayList<String> coolList) {
        this.context = context;
        this.dateList = dateList;
        this.cigList = cigList;
        this.coolList = coolList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.row_layout, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
//        Do something

        holder.date.setText(dateList.get(position));
        holder.cig.setText(cigList.get(position));
        holder.coo.setText(coolList.get(position));

    }

    @Override
    public int getItemCount() {
        return cigList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView date , cig , coo;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cig = itemView.findViewById(R.id.cigCountId);
            coo = itemView.findViewById(R.id.cooCountId);
            date = itemView.findViewById(R.id.datesId);
        }
    }
}
