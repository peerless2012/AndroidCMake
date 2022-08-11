package com.peerless2012.cmake;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.peerless2012.cmake.contrib.Contrib;
import com.peerless2012.cmake.makefile.Makefile;
import com.peerless2012.cmake.project.Project;

/**
 * @Author peerless2012
 * @Email peerless2012@126.com
 * @DateTime 2022/8/1 20:37
 * @Version V1.0
 * @Description:
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainText = findViewById(R.id.main_tv);
        findViewById(R.id.main_project).setOnClickListener(this);
        findViewById(R.id.main_contrib).setOnClickListener(this);
        findViewById(R.id.main_makefile).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_project) {
            mainText.setText("Project is : " + Project.getName());
        } else if (v.getId() == R.id.main_contrib) {
            mainText.setText("Contrib is : " + Contrib.getName());
        } else if (v.getId() == R.id.main_makefile) {
            mainText.setText("Makefile is : " + Makefile.getName());
        }
    }
}