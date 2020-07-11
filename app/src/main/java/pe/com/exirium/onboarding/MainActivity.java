package pe.com.exirium.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OnboardingAdapter onboardingAdapter;
    private LinearLayout layoutInidicators;
    private MaterialButton nextButton;
    private TextView skipTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutInidicators = findViewById(R.id.layoutIndicator);
        nextButton = findViewById(R.id.nextButton);
        skipTextView = findViewById(R.id.skipTextView);
        init();

        final ViewPager2 onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingViewPager.setAdapter(onboardingAdapter);

        setLayoutInidicators();
        setCurrentOnboardingIndicator(0);

        onboardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnboardingIndicator(position);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onboardingViewPager.getCurrentItem() + 1 < onboardingAdapter.getItemCount()) {
                    onboardingViewPager.setCurrentItem(onboardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), HelloActivity.class));
                    finish();
                }
            }
        });

        skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HelloActivity.class));
                finish();
            }
        });
    }

    private void init() {
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem onboardingItem = new OnboardingItem();
        onboardingItem.setTitle(getString(R.string.title1));
        onboardingItem.setDescription(getString(R.string.description1));
        onboardingItem.setImage(R.drawable.undraw_online_learning_ao11);
        onboardingItems.add(onboardingItem);

        OnboardingItem onboardingItem2 = new OnboardingItem();
        onboardingItem2.setTitle(getString(R.string.title2));
        onboardingItem2.setDescription(getString(R.string.description2));
        onboardingItem2.setImage(R.drawable.undraw_certification_aif8);
        onboardingItems.add(onboardingItem2);

        OnboardingItem onboardingItem3 = new OnboardingItem();
        onboardingItem3.setTitle(getString(R.string.title3));
        onboardingItem3.setDescription(getString(R.string.description3));
        onboardingItem3.setImage(R.drawable.undraw_credit_card_payment_12va);
        onboardingItems.add(onboardingItem3);

        onboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setLayoutInidicators() {
        ImageView[] indicators = new ImageView[onboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onboarding_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutInidicators.addView(indicators[i]);
        }
    }

    private void setCurrentOnboardingIndicator(int index) {
        int childCount = layoutInidicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutInidicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive)
                );
            }
        }
        if (index == onboardingAdapter.getItemCount() - 1) {
            nextButton.setText(getString(R.string.start));
        } else {
            nextButton.setText(getString(R.string.next));
        }
    }
}