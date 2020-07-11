package pe.com.exirium.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnBoardingViewHolder> {
    private List<OnboardingItem> onboardingItemList;

    public OnboardingAdapter(List<OnboardingItem> onboardingItems) {
        this.onboardingItemList = onboardingItems;
    }

    @NonNull
    @Override
    public OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingViewHolder holder, int position) {
        holder.setOnboardingData(onboardingItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onboardingItemList.size();
    }

    class OnBoardingViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView descriptionTextView;
        private ImageView onboardingImage;

        OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            onboardingImage = itemView.findViewById(R.id.onboardingImageView);
        }

        void setOnboardingData(OnboardingItem onboardingItem) {
            titleTextView.setText(onboardingItem.getTitle());
            descriptionTextView.setText(onboardingItem.getDescription());
            onboardingImage.setImageResource(onboardingItem.getImage());
        }
    }
}
