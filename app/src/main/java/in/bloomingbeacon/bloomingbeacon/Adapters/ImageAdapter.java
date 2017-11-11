package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Objects.ImageItem;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 23-10-2017.
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private List<ImageItem> imageItemList;

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView eventImage;

        public ImageViewHolder(View view) {
            super(view);
            eventImage = view.findViewById(R.id.slider_image);
        }
    }

    public ImageAdapter(Context context, List<ImageItem> imageItemList) {
        this.context = context;
        this.imageItemList = imageItemList;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_recycler_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ImageItem imageItem = imageItemList.get(position);
        Picasso.with(context)
                .load(imageItem.getEvent_image())
                .into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        return imageItemList.size();
    }
}
