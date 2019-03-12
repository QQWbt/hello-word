package com.bwie.wu.week.bean;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.wu.week.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/10 11:18:44
 * @Description:
 */
public class ListJsonAdapter extends BaseAdapter {

    private Context context;

    private List<JsonBeanListview.DataBean> data;

    public ListJsonAdapter(Context context, List<JsonBeanListview.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size ();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         switch (getItemViewType (position)){
             case 0:
                 ViewHolder1 holder1=null;
                 if (convertView == null) {
                     convertView = View.inflate(context, R.layout.item_list1, null);
                     holder1 = new ViewHolder1();
                     holder1.imageView = convertView.findViewById(R.id.imageView);
                     holder1.textView = convertView.findViewById(R.id.textView);
                     convertView.setTag(holder1);
                 } else {
                     holder1 = (ViewHolder1) convertView.getTag();
                 }
                 JsonBeanListview.DataBean bean = data.get(position);
                 DisplayImageOptions  options=new  DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).build();

                 ImageLoader.getInstance().displayImage(bean.getThumbnail_pic_s(), holder1.imageView, options);

                 holder1.textView.setText(bean.getTitle());
                 break;
             case 1:
                 ViewHolder2 holder2;

                 if (convertView == null) {
                     convertView = View.inflate(context, R.layout.item_list2, null);
                     holder2 = new ViewHolder2();

                     holder2.textView = convertView.findViewById(R.id.textView);
                     convertView.setTag(holder2);
                 } else {
                     holder2 = (ViewHolder2) convertView.getTag();
                 }
                 JsonBeanListview.DataBean bean2 = data.get(position);
                 holder2.textView.setText(bean2.getTitle());
                 break;
             case 2:
                 ViewHolder3 holder3;

                 if (convertView == null) {
                     convertView = View.inflate(context, R.layout.item_list3, null);
                     holder3 = new ViewHolder3();
                     holder3.imageView = convertView.findViewById(R.id.imageView);
                     convertView.setTag(holder3);
                 } else {
                     holder3 = (ViewHolder3) convertView.getTag();
                 }
                 JsonBeanListview.DataBean bean3 = data.get(position);
                 ImageLoader.getInstance().displayImage(bean3.getThumbnail_pic_s(), holder3.imageView);
                 break;
         }
        return convertView;
    }
    class ViewHolder1 {
        ImageView imageView;
        TextView textView;
    }

    class ViewHolder2 {
        TextView textView;
    }

    class ViewHolder3 {
        ImageView imageView;
    }

}
