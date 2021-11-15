package mb.ganesh.dailystaff;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    private static final String MY_PREF = "user_data";
    private static final String MASKEY = "0";
    private static final String COOKEY = "1";
    SharedPreferences.Editor editor;

    ImageView masAdd, masSub;
    ImageView cooAdd, cooSub;
    TextInputEditText masCount, cooCount;

    int COOCOUNT = 0;
    int MASCOUNT = 0;

    MyAdapter adapter;
    RecyclerView recyclerView;
    MaterialButton submitBtn;

//    String[] dates = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    List<MainData> dataList = new ArrayList<>();
    RoomDB database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        editor = preferences.edit();

        //        Variables
        masCount = findViewById(R.id.masCount);
        cooCount = findViewById(R.id.cooCount);

        masCount.setFocusable(false);
        masCount.setClickable(true);
        cooCount.setFocusable(false);
        cooCount.setClickable(true);
        submitBtn = findViewById(R.id.todayDataBtn);
        masAdd = findViewById(R.id.addMas);
        masSub = findViewById(R.id.subMas);
        cooAdd = findViewById(R.id.addCoo);
        cooSub = findViewById(R.id.subCoo);
        recyclerView = findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        init db
        database = RoomDB.getInstance(this);
//         store data
        dataList = database.mainDao().getAll();


        adapter = new MyAdapter(MainActivity.this , dataList , database);
        recyclerView.setAdapter(adapter);


        int mas = preferences.getInt(MASKEY, MASCOUNT);
        int coo = preferences.getInt(COOKEY, COOCOUNT);


        if (mas == 0 || coo == 0) {
            Log.e("CHECKKIE", "its null");
        } else {
            Log.e("CHECKKIE", "its not null");
            MASCOUNT = mas;
            COOCOUNT = coo;
        }
        updateMasCount(MASCOUNT);
        updateCooCount(COOCOUNT);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                Log.e("Date", date);


                for (int i = 0; i < dataList.size(); i++) {

                    if(date.equals(dataList.get(i).getDate())){
                        Log.e("isEql" , "yes its equal");
                        View v = findViewById(android.R.id.content);
                        Snackbar.make(v, "Already Report Submitted", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                        return;
                    }


                }


                MainData mainData = new MainData();
                mainData.setDate(date);
                mainData.setCigCount(MASCOUNT);
                mainData.setCooCount(COOCOUNT);

                database.mainDao().insert(mainData);
                dataList.clear();
                dataList.addAll(database.mainDao().getAll());
                adapter = new MyAdapter(MainActivity.this , dataList , database);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

                View v = findViewById(android.R.id.content);
                Snackbar.make(v, date + " report added successfully ", Snackbar.LENGTH_LONG).setBackgroundTint(Color.GREEN).show();

                return;
            }
        });

//        Mas
        masAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MASCOUNT = MASCOUNT + 1;
                masCount.setText(String.valueOf(MASCOUNT));
                saveData(MASCOUNT, COOCOUNT);
            }
        });

        masSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MASCOUNT == 0) {
                    View v = findViewById(android.R.id.content);
                    Snackbar.make(v, "Count cannot be negative", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                    return;
                } else {
                    MASCOUNT = MASCOUNT - 1;
                    masCount.setText(String.valueOf(MASCOUNT));
                    saveData(MASCOUNT, COOCOUNT);
                }

            }
        });

//        Coo
        cooAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                COOCOUNT = COOCOUNT + 1;
                cooCount.setText(String.valueOf(COOCOUNT));
                saveData(MASCOUNT, COOCOUNT);
            }
        });

        cooSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (COOCOUNT == 0) {
                    View v = findViewById(android.R.id.content);
                    Snackbar.make(v, "Count cannot be negative", Snackbar.LENGTH_LONG).setBackgroundTint(Color.RED).show();
                    return;
                } else {
                    COOCOUNT = COOCOUNT - 1;
                    cooCount.setText(String.valueOf(COOCOUNT));
                    saveData(MASCOUNT, COOCOUNT);
                }
            }
        });
    }

    private void updateCooCount(int coocount) {
        cooCount.setText(String.valueOf(coocount));
    }

    private void updateMasCount(int mascount) {
        masCount.setText(String.valueOf(mascount));
    }

    private void saveData(int mascount, int coocount) {
        editor.putInt(MASKEY, mascount);
        editor.putInt(COOKEY, coocount);
        editor.apply();
    }
}
