package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.EventDetailActivity;
import in.bloomingbeacon.bloomingbeacon.Objects.EventsListItem;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.EventHolder> {

    private Context context;
    private List<EventsListItem> eventsListItemList;
    private Typeface bold, regular;

    public class EventHolder extends RecyclerView.ViewHolder {
        public TextView date, date_more, eventName, description, location;
        public CardView event_item;
        public LinearLayout dateLayout;

        public EventHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.event_date);
            date_more = (TextView) view.findViewById(R.id.event_date_extra);
            eventName = (TextView) view.findViewById(R.id.event_name);
            description = (TextView) view.findViewById(R.id.event_description);
            location = (TextView) view.findViewById(R.id.event_location);
            event_item = (CardView) view.findViewById(R.id.event_item_card);
            dateLayout = (LinearLayout) view.findViewById(R.id.parent_date_event);
        }
    }

    public EventsListAdapter(Context context, List<EventsListItem> eventsItemList) {
        this.context = context;
        this.eventsListItemList = eventsItemList;
        bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item, parent, false);
        return new EventHolder(view);
    }

    @Override
    public void onBindViewHolder(EventHolder holder, int position) {
        final EventsListItem eventsListItem = eventsListItemList.get(position);

        holder.date.setText(eventsListItem.getEvent_date());
        holder.date.setTypeface(bold);
        holder.date_more.setText(eventsListItem.getEvent_date_more());
        holder.date_more.setTypeface(regular);

        holder.dateLayout.setBackgroundResource(R.drawable.greengradient);
        if (position % 2 == 0) {
            holder.dateLayout.setBackgroundResource(R.drawable.yellowgradient);
        }
        if (position % 3 == 0) {
            holder.dateLayout.setBackgroundResource(R.drawable.redgradient);
        }
        if (position % 5 == 0) {
            holder.dateLayout.setBackgroundResource(R.drawable.bluegradient);
        }

        holder.eventName.setText(eventsListItem.getEvent_name());
        holder.eventName.setTypeface(bold);
        holder.description.setText(eventsListItem.getEvent_description());
        holder.description.setTypeface(regular);
        holder.location.setText(eventsListItem.getEvent_location());
        holder.location.setTypeface(bold);

        holder.event_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), EventDetailActivity.class);
                intent.putExtra("IMAGE", eventsListItem.getEvent_image());
                intent.putExtra("DATETIME", eventsListItem.getEvent_date_time());
                intent.putExtra("TITLE", eventsListItem.getEvent_name());
                intent.putExtra("DESC", eventsListItem.getEvent_desc_detailed());
                intent.putExtra("LOCATION", eventsListItem.getEvent_location_detailed());
                intent.putExtra("LAT", eventsListItem.getEvent_lat());
                intent.putExtra("LONG", eventsListItem.getEvent_long());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsListItemList.size();
    }
}
