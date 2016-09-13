package com.sp.lib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferenceUser spUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spUser= SharedPreferenceUser.create(this);


    }

    public void onSave(View mView){
       spUser.edit().name().put("gavin")
               .age().put(29)
               .score().put(100f)
               .birth().put(100l)
               .apply();
    }

    public void onLoad(View mView){
        StringBuffer buffer=new StringBuffer("info:").append("name:").append(spUser.name().get())
                .append("age:").append(spUser.age().get())
                .append("score:").append(spUser.score().get())
                .append("birth:").append(spUser.birth().get());
        Toast.makeText(this,buffer.toString(),Toast.LENGTH_LONG).show();
    }
}
