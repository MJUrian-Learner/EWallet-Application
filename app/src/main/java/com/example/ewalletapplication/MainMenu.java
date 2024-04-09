package com.example.ewalletapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.img_slide_one, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slide_two, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slide_three, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slide_four, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.img_slide_five, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
    }
}