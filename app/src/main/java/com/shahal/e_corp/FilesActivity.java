package com.shahal.e_corp;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FilesActivity extends AppCompatActivity {

    private List<ECorpFile> filesList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;


//                filesList.add(new ECorpFile("Unassigned", "Microsoft Office 2016 AIO Final.iso", "Microsoft Office 2016 AIO Final", "iso", 54523123, 5200000000L));
//                filesList.add(new ECorpFile("Unassigned", "Java_Notebook.docx", "Java_Notebook", "docx", 54523123, 600000L));
//                filesList.add(new ECorpFile("Unassigned", "Apps Setups", "Apps Setups", "", 54523123, 924288000L));
//                filesList.add(new ECorpFile("Unassigned", "Algorithms & Data Structures.pdf", "Algorithms & Data Structures", "pdf", 54523123, 2288000L));
//                filesList.add(new ECorpFile("Unassigned", "Hello.txt", "Hello", "txt", 54523123, 60000L));
//                filesList.add(new ECorpFile("Unassigned", "AndroidStudio Projects", "AndroidStudio Projects", "", 54523123, 2924288000L));

//    int[] filesImages = {R.drawable.docx, R.drawable.exe, R.drawable.folder, R.drawable.iso, R.drawable.java, R.drawable.folder, R.drawable.jpeg, R.drawable.pdf,
//            R.drawable.txt, R.drawable.zip, R.drawable.folder, R.drawable.iso, R.drawable.jpeg};
//
//    String[] filesNames = {"Python_Notebook.docx", "TaskbarX.exe", "Apps Setups", "Microsoft Office 2016 AIO Final.iso",
//            "FilesActivity.java", "AndroidStudio Projects", "The Gang.jpeg", "Algorithms & Data Structures.pdf", "Hello.txt", "KMS_VL_ALL-32-beta.zip", "Idea Projects", "Ubuntu Version 2.4", "Me"};
//
//    String[] filesDates = {"3/5/2020", "5/2/2019", "1/1/2020", "3/3/2020",
//            "6/12/2019", "19/3/2020", "13/8/2020", "5/10/2020", "29/3/2020", "3/9/2020", "5/5/2020", "23/12/2018", "14/4/2017"};
//
//    String[] filesSizes = {"3.0 MB", "0.5 MB", "8.0 GB", "4.5 GB",
//            "519 KB", "3.3 GB", "6.0 MB", "45 MB", "2.0 B", "450 KB", "2.0 GB", "2.8 GB", "1.8 MB"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);
        getSupportActionBar().setElevation(0);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.button_color));
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                makeACall();
            }
        }, 1000);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeACall();
                    }
                }, 500);
            }
        });

    }

    public void makeACall() {
        GetDataAPI service = RetrofitCreation.getRetrofitInstance().create(GetDataAPI.class);
        Call<List<ECorpFile>> call = service.getAllFiles();
        call.enqueue(new Callback<List<ECorpFile>>() {
            @Override
            public void onResponse(Call<List<ECorpFile>> call, Response<List<ECorpFile>> response) {
                filesList = response.body();
                ListView listView = findViewById(R.id.listView);
                swipeRefreshLayout.setRefreshing(false);
                listView.setAdapter(new MyAdapter(FilesActivity.this, filesList));
            }

            @Override
            public void onFailure(Call<List<ECorpFile>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                System.out.println("=====================================\n");
                t.printStackTrace();
                System.out.println("\n=======================================");
                Toast.makeText(FilesActivity.this, "Unable to load files", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends ArrayAdapter<ECorpFile> {

        Context context;
        List<ECorpFile> files;

        MyAdapter(Context c, List<ECorpFile> list) {
            super(c, R.layout.list_row, list);
            this.context = c;
            this.files = list;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.list_row, parent, false);
            ImageView image = row.findViewById(R.id.file_pic);
            TextView name = row.findViewById(R.id.file_name);
            TextView date = row.findViewById(R.id.file_date);
            TextView size = row.findViewById(R.id.file_size);

            image.setImageResource(files.get(position).getImage());
            name.setText(files.get(position).nameFormatted());
            size.setText(files.get(position).sizeFormatted());
            date.setText(files.get(position).dateFormatted());

            return row;
        }
    }
}
