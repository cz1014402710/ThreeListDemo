package com.syiti.threelistdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * description: <一句话功能简述>
 *
 * @author Chenz
 * @date 2018/3/22 0022
 */
public class ThreeListView extends LinearLayout {

    View layout_second, layout_third;

    ListView listView1;
    ListView listView2;
    ListView listView3;

    private Context mContext;

    private int screenWidth;
    private int screenHeight;

    public ThreeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setContentView(R.layout.three_listview);
        this.mContext = context;
        initView();
    }

    public ThreeListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ThreeListView(Context context) {
        this(context, null);

    }

    private void setContentView(int rId) {
        View v = LayoutInflater.from(getContext()).inflate(rId, null);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        v.setLayoutParams(lp);
        this.addView(v);
    }

    public void setList1Adapter(ComAdapter comAdapter) {
        listView1.setAdapter(comAdapter);
    }

    public void setList2Adapter(ComAdapter comAdapter) {
        listView2.setAdapter(comAdapter);
        toogleView(layout_second);
        if (layout_third.getVisibility() == View.VISIBLE) {
            hideView(layout_third);
        }
    }

    public void setList3Adapter(ComAdapter comAdapter) {
        listView3.setAdapter(comAdapter);
        toogleView(layout_third);
    }

    public void setAllAdapter(ComAdapter comAdapter1, ComAdapter comAdapter2, ComAdapter
            comAdapter3) {
        setList1Adapter(comAdapter1);
        setList2Adapter(comAdapter2);
        setList3Adapter(comAdapter3);
    }

    private void initView() {

        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        screenWidth = wm.getDefaultDisplay().getWidth();
        screenHeight = wm.getDefaultDisplay().getHeight();

        listView1 = findViewById(R.id.listView1);
        listView1.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    if (layout_second.getVisibility() == View.VISIBLE) {
                        if (layout_third.getVisibility() == View.VISIBLE) {
                            hideView(layout_third);
                        }
                        hideView(layout_second);
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
        listView2 = (ListView) findViewById(R.id.listView2);
        listView2.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                        if (layout_third.getVisibility() == View.VISIBLE) {
                            hideView(layout_third);
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
        listView3 = (ListView) findViewById(R.id.listView3);
        layout_second = findViewById(R.id.layout_second);
        layout_third = findViewById(R.id.layout_third);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins((int) (screenWidth * 0.3), 0, 0, 0);
        layout_second.setLayoutParams(lp);
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        lp2.setMargins((int) (screenWidth * 0.6), 0, 0, 0);
        layout_third.setLayoutParams(lp2);

    }

    public void setListView3OnItemClickListener(AdapterView.OnItemClickListener l) {
        listView3.setOnItemClickListener(l);
    }

    public void setListView1OnItemClickListener(AdapterView.OnItemClickListener l) {
        listView1.setOnItemClickListener(l);
    }

    public void setListView2OnItemClickListener(AdapterView.OnItemClickListener l) {
        listView2.setOnItemClickListener(l);
    }

    public void toogleView(final View view) {

        showView(view);
    }

    public void showView(View view) {
        Animation showanim = AnimationUtils.loadAnimation(mContext,
                R.anim.push_left_in);
        view.setVisibility(View.VISIBLE);
        view.startAnimation(showanim);
    }

    public void hideView(View view) {
        Animation hideanim = AnimationUtils.loadAnimation(mContext,
                R.anim.push_right_out);
        view.startAnimation(hideanim);
        view.setVisibility(View.GONE);
    }
}
