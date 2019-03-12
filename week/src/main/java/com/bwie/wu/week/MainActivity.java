package com.bwie.wu.week;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bwie.wu.week.frag.FragmentOne;
import com.bwie.wu.week.frag.FragmentTwo;

public class MainActivity extends FragmentActivity {

    private RadioGroup radioGroup;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        radioGroup = findViewById (R.id.radiogroup);
        manager = getSupportFragmentManager ();

        final FragmentOne one = new FragmentOne();
        final FragmentTwo two = new FragmentTwo();

        manager.beginTransaction ().add (R.id.frag,one).add (R.id.frag,two).hide (two).show (one).commit ();

        radioGroup.check (radioGroup.getChildAt (0).getId ());

        radioGroup.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        manager.beginTransaction ().hide (two).show (one).commit ();
                        break;
                    case R.id.radioButton2:
                        manager.beginTransaction ().hide (one).show (two).commit ();
                        break;
                }

            }
        });
    }
}
