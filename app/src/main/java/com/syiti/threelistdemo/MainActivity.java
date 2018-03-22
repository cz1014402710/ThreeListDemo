package com.syiti.threelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ThreeListView mThreeListView;

    private List<String> datas;
    StringBuffer sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThreeListView = findViewById(R.id.threeListView);
        sb = new StringBuffer();
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add(i + "");
        }


        mThreeListView.setList1Adapter(new ComAdapter<String>(this, datas, R.layout.item) {
            @Override
            public void convert(BaseViewHolder holder, String data) {
                holder.setTextView(R.id.tv, data);
            }
        });

        mThreeListView.setListView1OnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sb.append(datas.get(position));
                mThreeListView.setList2Adapter(new ComAdapter<String>(MainActivity.this, datas, R
                        .layout.item) {
                    @Override
                    public void convert(BaseViewHolder holder, String data) {
                        holder.setTextView(R.id.tv, data);
                    }
                });
            }
        });

        mThreeListView.setListView2OnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sb.append("-" + datas.get(position));
                mThreeListView.setList3Adapter(new ComAdapter<String>(MainActivity.this, datas, R
                        .layout.item) {
                    @Override
                    public void convert(BaseViewHolder holder, String data) {
                        holder.setTextView(R.id.tv, data);
                    }
                });
            }
        });

        mThreeListView.setListView3OnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sb.append("-" + datas.get(position));
                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                sb.setLength(0);
            }
        });
    }
}
