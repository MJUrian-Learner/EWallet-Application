package com.example.ewalletapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        /*

        dtls_lst[0] = email
        dtls_lst[1] = fname
        dtls_lst[2] = lname
        dtls_lst[3] = pass

         */
        Intent intent = getIntent();
        ArrayList<String> dtls_lst = getIntent().getStringArrayListExtra(Register.EXTRA_MESSAGE);

        TextView welcomeTxt = findViewById(R.id.welcomeTxt);
        welcomeTxt.setText("Welcome to MJU Co, " + dtls_lst.get(1));

        TextView email_dtls = findViewById(R.id.email_dtls);
        email_dtls.setText(dtls_lst.get(0));

        TextView name_dtls = findViewById(R.id.name_dtls);
        name_dtls.setText(dtls_lst.get(1) + " " + dtls_lst.get(2));

        TextView pass_dtls = findViewById(R.id.pass_dtls);
        pass_dtls.setText(dtls_lst.get(3));

        Button submitBtn = findViewById(R.id.gbButton);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}