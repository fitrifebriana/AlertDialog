package id.or.codelabs.alertdialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by FitriFebriana on 6/20/2016.
 */
public class MainActivity extends AppCompatActivity {
    private Button btnPage1;
    private Button btnPage2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPage1 = (Button)findViewById(R.id.btn_page_1);
        btnPage2 = (Button)findViewById(R.id.btn_page_2);

        btnPage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Page1Activity.class);
                startActivity(intent);
            }
        });

        btnPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Page2Activity.class);
                startActivity(intent);
            }
        });
    }
}
