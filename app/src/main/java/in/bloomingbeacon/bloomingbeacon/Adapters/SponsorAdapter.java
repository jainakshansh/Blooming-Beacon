package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Objects.SponsorItem;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 21-10-2017.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {

    private Context context;
    private List<SponsorItem> sponsorItemList;
    private Typeface medium;

    public class SponsorViewHolder extends RecyclerView.ViewHolder {
        private ImageView sponsorImage;
        private TextView sponsorName;

        public SponsorViewHolder(View view) {
            super(view);
            sponsorImage = view.findViewById(R.id.sponsor_image);
            sponsorName = view.findViewById(R.id.sponsor_name);
        }
    }

    public SponsorAdapter(Context context, List<SponsorItem> sponsorItemList) {
        this.context = context;
        this.sponsorItemList = sponsorItemList;
        medium = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsm.ttf");
    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_item, parent, false);
        return new SponsorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SponsorViewHolder holder, int position) {
        final SponsorItem sponsorItem = sponsorItemList.get(position);
        holder.sponsorName.setTypeface(medium);
        holder.sponsorName.setText(sponsorItem.getName());
        if (position % 3 == 0) {
            holder.sponsorName.setBackgroundResource(R.drawable.yellowgradient);
        } else if (position % 5 == 0) {
            holder.sponsorName.setBackgroundResource(R.drawable.greengradient);
        } else {
            holder.sponsorName.setBackgroundResource(R.drawable.bluegradient);
        }

        Picasso.with(context)
                .load(sponsorItem.getImage())
                .into(holder.sponsorImage);
        if (sponsorItem.getUrl() != null) {
            holder.sponsorImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sponsorItem.getUrl())));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return sponsorItemList.size();
    }
}
