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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    Context context;
    List<MainData> dataList;
    RoomDB database;

    public MyAdapter(Context context, List<MainData> dataList, RoomDB database) {
        this.context = context;
        this.dataList = dataList;
        this.database = database;
//        notifyDataSetChanged();
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

//        initialize main data
        MainData data = dataList.get(position);

//        initialize database
        database = RoomDB.getInstance(context);

        holder.date.setText(data.getDate());
        holder.cig.setText(String.valueOf(data.getCigCount()));
        holder.coo.setText(String.valueOf(data.getCooCount()));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
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
