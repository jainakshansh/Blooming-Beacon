package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Objects.ContactUsItem;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 18-10-2017.
 */

public class ContactUsAdapter extends RecyclerView.Adapter<ContactUsAdapter.ContactUsViewHolder> {

    private Context context;
    private List<ContactUsItem> contactUsItemList;
    private Typeface poppins_bold;
    private Typeface poppins_regular;

    public class ContactUsViewHolder extends RecyclerView.ViewHolder {
        private TextView name, designation;
        private ImageView image;
        private Button call, email;

        public ContactUsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.cu_name);
            designation = view.findViewById(R.id.cu_designation);
            image = view.findViewById(R.id.cu_image);
            call = view.findViewById(R.id.cu_call);
            email = view.findViewById(R.id.cu_email);
        }
    }

    public ContactUsAdapter(Context context, List<ContactUsItem> contactUsItemList) {
        this.context = context;
        this.contactUsItemList = contactUsItemList;
        poppins_bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        poppins_regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public ContactUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_us_item, parent, false);
        return new ContactUsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactUsViewHolder holder, int position) {
        final ContactUsItem contactUsItem = contactUsItemList.get(position);
        holder.name.setText(contactUsItem.getCu_name());
        holder.name.setTypeface(poppins_bold);
        holder.designation.setText(contactUsItem.getCu_desc());
        holder.designation.setTypeface(poppins_regular);
        Picasso.get()
                .load(contactUsItem.getCu_image())
                .into(holder.image);
        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", contactUsItem.getCu_phone(), null)));
            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", contactUsItem.getCu_email(), null)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactUsItemList.size();
    }
}
