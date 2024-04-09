package com.example.ewalletapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Objects;

public class Register extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.ewalletapplication.MESSAGE";
    String email, fname, lname, pass, cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button submitBtn = findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> details = new ArrayList<>();

                EditText inputEmail = findViewById(R.id.emailText);
                String email = inputEmail.getText().toString();

                EditText inputFname = findViewById(R.id.fnameText);
                String fname = inputFname.getText().toString();

                EditText inputLname = findViewById(R.id.lnameText);
                String lname = inputLname.getText().toString();

                EditText inputPass = findViewById(R.id.passText);
                String pass = inputPass.getText().toString();

                EditText inputCpass = findViewById(R.id.pass2Text);
                String cpass = inputCpass.getText().toString();

                LinearLayout layout = findViewById(R.id.layout);

                checkPassword(pass, cpass, layout);

                validateEditText((EditText) findViewById(R.id.emailText), layout);
                validateEditText((EditText) findViewById(R.id.fnameText), layout);
                validateEditText((EditText) findViewById(R.id.lnameText), layout);
                validateEditText((EditText) findViewById(R.id.passText), layout);
                validateEditText((EditText) findViewById(R.id.pass2Text), layout);

                details.add(0,email);
                details.add(1,fname);
                details.add(2,lname);
                details.add(3,pass);

                if(!details.contains("") && pass.equals(cpass)) {
                    Intent intent = new Intent(Register.this, Details.class);
                    intent.putStringArrayListExtra(EXTRA_MESSAGE, details);
                    startActivity(intent);
                }
            }
        });
    }

    private void checkPassword(String pass, String cpass, LinearLayout layout) {
        EditText editText = (EditText) findViewById(R.id.pass2Text);;

        int index = layout.indexOfChild(editText);

        if(!pass.equals(cpass)) {
            TextView requiredTextView = (TextView) editText.getTag(R.id.required_pass_text_view);
            if (requiredTextView == null) {
                requiredTextView = createRequiredTextView("* Passwords do not match");
                layout.addView(requiredTextView, index + 1);
                editText.setTag(R.id.required_pass_text_view, requiredTextView);
            }
        } else {
            TextView requiredTextView = (TextView) editText.getTag(R.id.required_pass_text_view);
            if (requiredTextView != null) {
                layout.removeView(requiredTextView);
                editText.setTag(R.id.required_pass_text_view, null);
            }
        }
    }

    private void validateEditText(EditText editText, LinearLayout layout) {
        int index = layout.indexOfChild(editText);
        TextView requiredPassTextView = (TextView) editText.getTag(R.id.required_pass_text_view);

        // Check if the passwords do not match message is not visible
        if (requiredPassTextView == null && editText.getText().toString().isEmpty()) {
            // Add the "Required" TextView below this EditText if it's not already added
            TextView requiredTextView = (TextView) editText.getTag(R.id.required_text_view);
            if (requiredTextView == null) {
                requiredTextView = createRequiredTextView();
                layout.addView(requiredTextView, index + 1);
                editText.setTag(R.id.required_text_view, requiredTextView);
            }
        } else {
            // If there is a value, remove the "Required" TextView
            TextView requiredTextView = (TextView) editText.getTag(R.id.required_text_view);
            if (requiredTextView != null) {
                layout.removeView(requiredTextView);
                editText.setTag(R.id.required_text_view, null);
            }
        }
    }


    private TextView createRequiredTextView() {
        TextView requiredTextView = new TextView(Register.this);
        requiredTextView.setText("* Required");
        requiredTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(35, 2, 0, 2); // Match your XML layout
        requiredTextView.setLayoutParams(layoutParams);
        return requiredTextView;
    }

    private TextView createRequiredTextView(String text) {
        TextView requiredTextView = new TextView(Register.this);
        requiredTextView.setText(text);
        requiredTextView.setTextColor(ContextCompat.getColor(this, R.color.red));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(35, 2, 0, 2); // Match your XML layout
        requiredTextView.setLayoutParams(layoutParams);
        return requiredTextView;
    }
}