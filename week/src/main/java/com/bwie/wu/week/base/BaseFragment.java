package com.bwie.wu.week.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/9 20:10:32
 * @Description:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (bindLayout (),container,false);
    }

    protected  abstract int bindLayout();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated (savedInstanceState);
        initView();
        initData();
        bindEvent();
    }

    //绑定组件
    protected abstract void initView();
    //操作数据
    protected abstract void initData();
    //设置监听
    protected abstract void bindEvent();

    protected <T extends View> T bindView(int resId){
        return (T) getView ().findViewById (resId);
    }
}
