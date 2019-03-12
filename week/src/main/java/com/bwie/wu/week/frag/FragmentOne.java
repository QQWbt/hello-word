package com.bwie.wu.week.frag;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.bwie.wu.week.R;
import com.bwie.wu.week.base.BaseFragment;
import com.bwie.wu.week.bean.HttpUtils;
import com.bwie.wu.week.bean.JsonBeanListview;
import com.bwie.wu.week.bean.ListJsonAdapter;
import com.bwie.wu.week.dao.Dao;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/9 19:31:50
 * @Description:
 */
public class FragmentOne extends BaseFragment {

    private String str="http://www.xieast.com/api/news/news.php?page=1";
    private Context context;
    private PullToRefreshListView listView;
    private Dao dao;
    private JsonBeanListview json;
    private List<JsonBeanListview.DataBean> data;
    private ListJsonAdapter adapter;

    @Override
    protected int bindLayout() {
        return R.layout.fragmentone;
    }

    @Override
    protected void initView() {
        listView = bindView (R.id.pull_to_refresh_list);
        listView.setMode (PullToRefreshBase.Mode.BOTH)
        ;
        dao = new Dao (getActivity ());
    }

    @Override
    protected void initData() {
        //判断是否有网
        if(HttpUtils.isNetworkConnected (getActivity ())){
            HttpUtils.httpAsynTask (str, new HttpUtils.CallBackString () {
                @Override
                public void getData(String s) {
                        gsonJX(s);
                }
            });
        }else{
            Cursor cursor = dao.query ("jsonlist", null, null, null, null, null, null);

            data = new ArrayList<JsonBeanListview.DataBean> ();
            if(cursor.moveToFirst ()){
                do {
                    String title= cursor.getString(cursor.getColumnIndex("title"));
                    String url= cursor.getString(cursor.getColumnIndex("url"));
                    String thumbnail_pic_s= cursor.getString(cursor.getColumnIndex("thumbnail_pic_s"));
                    data.add(new JsonBeanListview.DataBean(title,url,thumbnail_pic_s));
                }while (cursor.moveToNext ());
                Toast .makeText(getActivity(),"哎呀 没网了",Toast.LENGTH_LONG).show();
                cursor.close();
                //设置适配器
                adapter = new ListJsonAdapter(getActivity(), data);
                listView.setAdapter(adapter);
            }
        }
    }
    private void gsonJX(String s) {
       Gson gson = new Gson ();
       json = gson.fromJson (s, JsonBeanListview.class);
       data = json.getData ();

       if (dao.query ("jsonlist",null,null,null,null,null,null).moveToFirst ()){

       }else{
           for (JsonBeanListview.DataBean bean : data){
               ContentValues values = new ContentValues ();
               values.put ("title",bean.getTitle ());
               values.put ("url",bean.getUrl());
               values.put ("thumbnail_pic_s",bean.getThumbnail_pic_s ());
               dao.insert ("jsonlist",null,values);
           }
       }
        adapter = new ListJsonAdapter (getActivity (),data);
        listView.setAdapter (adapter);
    }
    @Override
    protected void bindEvent() {
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HttpUtils.isNetworkConnected(getActivity())) {
                    //如果 有网就请求网络
                    HttpUtils.httpAsynTask(str, new HttpUtils.CallBackString() {
                        @Override
                        public void getData(String s) {
                            gsonJX(s);
                        }
                    });
                }else{
                    Toast .makeText(getActivity(),"哎呀 没网了",Toast.LENGTH_LONG).show();
                }
                listView.onRefreshComplete();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (HttpUtils.isNetworkConnected(getActivity())) {
                    //如果 有网就请求网络
                    HttpUtils.httpAsynTask(str, new HttpUtils.CallBackString() {
                        @Override
                        public void getData(String s) {
                            Gson gson = new Gson();
                            JsonBeanListview json = gson.fromJson(s, JsonBeanListview.class);
                            List<JsonBeanListview.DataBean> data2= json.getData();
                            data.addAll(data2);
                            adapter.notifyDataSetChanged();
                        }
                    });
                }else{
                    Toast .makeText(getActivity(),"哎呀 没网了",Toast.LENGTH_LONG).show();

                }
                listView.onRefreshComplete();
            }
        });
    }
}
