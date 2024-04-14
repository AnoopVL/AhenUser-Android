package com.avl.ahenuser;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class selectedDS extends AppCompatActivity {

    private TextView dsNameInfo, dsAddressInfo, dsPhoneInfo, dsEmailInfo;
    private TextInputEditText userName, userPhone;
    private MaterialButton bookNowBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_ds);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            // Set the status bar color to transparent
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        Spinner spinner = findViewById(R.id.timeSlotSpnr);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(selectedDS.this, "You've selected Time Slot of: "+item, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String>arrayList = new ArrayList<>();
        arrayList.add("6-7");
        arrayList.add("7-8");
        arrayList.add("8-9");
        arrayList.add("9-10");
        arrayList.add("13-14");
        arrayList.add("14-15");
        arrayList.add("15-16");
        ArrayAdapter<String>adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        String dsNameInfoStr = getIntent().getStringExtra("dsName");
        String dsAddressInfoStr = getIntent().getStringExtra("dsAddress");
        String dsPhoneInfoStr = getIntent().getStringExtra("dsPhone");
        String dsEmailInfoStr = getIntent().getStringExtra("dsEmail");

        dsNameInfo = findViewById(R.id.dsNameInfo);
        dsAddressInfo = findViewById(R.id.dsAddressInfo);
        dsPhoneInfo = findViewById(R.id.dsPhoneInfo);
        dsEmailInfo = findViewById(R.id.dsEmailInfo);

        dsNameInfo.setText(dsNameInfoStr);
        dsAddressInfo.setText(dsAddressInfoStr);
        dsPhoneInfo.setText(dsPhoneInfoStr);
        dsEmailInfo.setText(dsEmailInfoStr);
    }

}