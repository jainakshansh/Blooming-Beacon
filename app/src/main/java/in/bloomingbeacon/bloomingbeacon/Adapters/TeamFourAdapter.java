package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Objects.TeamFour;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamFourAdapter extends RecyclerView.Adapter<TeamFourAdapter.TeamFourHolder> {

    private Context context;
    private List<TeamFour> teamFourList;
    private Typeface bold, regular;

    public class TeamFourHolder extends RecyclerView.ViewHolder {
        public ImageView team_image;
        public TextView team_name, team_post;

        public TeamFourHolder(View view) {
            super(view);
            team_image = view.findViewById(R.id.hierarchy_image);
            team_name = view.findViewById(R.id.hierarchy_name);
            team_post = view.findViewById(R.id.hierarchy_post);
        }
    }

    public TeamFourAdapter(Context context, List<TeamFour> teamFourList) {
        this.context = context;
        this.teamFourList = teamFourList;
        bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public TeamFourHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hierarchy_item, parent, false);

        return new TeamFourHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamFourHolder holder, int position) {
        TeamFour teamFour = teamFourList.get(position);

        holder.team_name.setText(teamFour.getFour_name());
        holder.team_name.setTypeface(bold);
        holder.team_name.setBackgroundResource(R.drawable.bluegradient);
        holder.team_post.setText(teamFour.getFour_post());
        holder.team_post.setTypeface(regular);
        holder.team_post.setBackgroundResource(R.drawable.bluegradient);
        Picasso.with(context)
                .load(teamFour.getFour_image())
                .into(holder.team_image);
    }

    @Override
    public int getItemCount() {
        return teamFourList.size();
    }
}