package com.example.sean.cityrecyclertest;

import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.sean.cityrecyclertest.bean.Areainfo;
import com.example.sean.cityrecyclertest.dao.AreaInfoDao;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Areainfo> areainfo1;    //省的内容
    private List<Areainfo> areainfo2;   //市的内容
    private List<Areainfo> areainfo3;   //市的内容

    private String s1,s2,s3;


    private RecyclerView mRecyclerView1, mRecyclerView2,mRecyclerView3;

    TextView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (TextView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAreaInfo(1, 1);//获取省的内容
            }
        });
    }


    /**
     * 获取籍贯网络数据
     *
     * @param index
     */
    public void getAreaInfo(int flag, final int index) {
        switch (flag) {
            case 1:
                areainfo1 = AreaInfoDao.getAreaInfo(index);
                updateUI(1);
                break;
            case 2:
                areainfo2 = AreaInfoDao.getAreaInfo(index);
                break;
            case 3:
                areainfo3 = AreaInfoDao.getAreaInfo(index);
                break;
        }
    }

    private void showPopupWindows(){
        View contentView = LayoutInflater.from(MainActivity.this).inflate(
                R.layout.windows_popupwindow, null);
        mRecyclerView1 = (RecyclerView) contentView.findViewById(R.id.lv1);
        mRecyclerView2 = (RecyclerView) contentView.findViewById(R.id.lv2);
        mRecyclerView3 = (RecyclerView) contentView.findViewById(R.id.lv3);
        PopupWindow mPopupWindow =new PopupWindow(contentView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setContentView(contentView);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new PaintDrawable());
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.setAdapter(new MyAdapter(areainfo1,1));
        mPopupWindow.showAsDropDown(button);
    }

    private void updateUI(int is) {
        if (is == 1){
            showPopupWindows();
        }else if (is == 2){
            mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView2.setAdapter(new MyAdapter(areainfo2,2));


        }else if (is==3){
            mRecyclerView3.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView3.setAdapter(new MyAdapter(areainfo3,3));
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        List<Areainfo> data;
        int index;
        public MyAdapter(List<Areainfo> data,int index){
            this.data = data;
            this.index = index;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.item,parent,false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bindData(data.get(position),index);
        }   

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mTextView;
        Areainfo areainfo;
        int state;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.name);
            itemView.setOnClickListener(this);
        }

        public void bindData(Areainfo areainfo, int index) {
            this.areainfo = areainfo;
            this.state = index;
            mTextView.setText(areainfo.getAreaName());
        }

        @Override
        public void onClick(View view) {
            switch (state){
                case 1:
                    //设置市级
                    getAreaInfo(2, areainfo.getAreaId());
                    updateUI(2);
                    //设置默认的区级
                    if (areainfo2 != null) {
                        getAreaInfo(3, areainfo2.get(0).getAreaId());
                        s1 = areainfo.getAreaName();    //得到省
                        s2 = areainfo2.get(0).getAreaName();    //设置得到市
                        s3 = areainfo3.get(0).getAreaName();    //设置得到区
                        updateUI(3);
                    } else {
                        return;
                    }
                    break;
                case 2:
                    getAreaInfo(3, areainfo.getAreaId());
                    updateUI(3);
                    s2 = areainfo.getAreaName();    //设置得到市
                    s3 = areainfo3.get(0).getAreaName();    //设置得到区
                    break;
                case 3:
                    ///设置最终地址
                    s3 = areainfo.getAreaName();
                    button.setText(s1 +s2 +s3);
                    break;
            }

        }
    }

}
