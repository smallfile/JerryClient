package com.jerry.sample.widget.banner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jerry.sample.R;
import com.jerry.uilib.widget.banner.Animations.DescriptionAnimation;
import com.jerry.uilib.widget.banner.SliderLayout;
import com.jerry.uilib.widget.banner.SliderTypes.BaseSliderView;
import com.jerry.uilib.widget.banner.SliderTypes.TextSliderView;

import java.util.HashMap;


public class BannerActivity extends Activity {

    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> file_maps = new HashMap<String, String>();
        file_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        file_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        file_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        file_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

//        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
//        file_maps.put("Hannibal",R.drawable.image_silder_3);
//        file_maps.put("Big Bang Theory",R.drawable.image_silder_1);
//        file_maps.put("House of Cards",R.drawable.image_silder_4);
//        file_maps.put("Game of Thrones", R.drawable.image_silder_2);

        for(String name : file_maps.keySet()){
//        	//DefaultSliderView
//        	DefaultSliderView sliderView = new DefaultSliderView(this);
//        	// initialize a SliderLayout
//        	sliderView
//                  .image(file_maps.get(name))
//                  .setScaleType(BaseSliderView.ScaleType.Fit)
//                  .setOnSliderClickListener(sliderClickListener);
//
//            //add your extra information
//        	sliderView.getBundle()
//                  .putString("extra",name);
//
//            mDemoSlider.addSlider(sliderView);
            
            //TextSliderView
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(sliderClickListener);

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);

           mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        
        ListView list = (ListView)findViewById(R.id.transformers);
        list.setAdapter(new BannerAdapter(this));
        list.setOnItemClickListener(sliderItemClickListener);
    }
    
    OnItemClickListener sliderItemClickListener = new OnItemClickListener() {
    	 @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
             Toast.makeText(BannerActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
         }
    };
    
    BaseSliderView.OnSliderClickListener sliderClickListener = new BaseSliderView.OnSliderClickListener() {
    	@Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(BannerActivity.this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
        }
    };
}
