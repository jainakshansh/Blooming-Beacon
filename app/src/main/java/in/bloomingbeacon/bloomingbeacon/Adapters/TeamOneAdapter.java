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

import in.bloomingbeacon.bloomingbeacon.Objects.TeamOne;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamOneAdapter extends RecyclerView.Adapter<TeamOneAdapter.TeamOneHolder> {

    private Context context;
    private List<TeamOne> teamOneList;
    private Typeface bold, regular;

    public class TeamOneHolder extends RecyclerView.ViewHolder {
        public ImageView team_image;
        public TextView team_name, team_post;

        public TeamOneHolder(View view) {
            super(view);
            team_image = view.findViewById(R.id.hierarchy_image);
            team_name = view.findViewById(R.id.hierarchy_name);
            team_post = view.findViewById(R.id.hierarchy_post);
        }
    }

    public TeamOneAdapter(Context context, List<TeamOne> teamOneList) {
        this.context = context;
        this.teamOneList = teamOneList;
        bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public TeamOneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hierarchy_item, parent, false);

        return new TeamOneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamOneHolder holder, int position) {
        TeamOne teamOne = teamOneList.get(position);

        holder.team_name.setText(teamOne.getOne_name());
        holder.team_name.setTypeface(bold);
        holder.team_name.setBackgroundResource(R.drawable.redgradient);
        holder.team_post.setText(teamOne.getOne_post());
        holder.team_post.setTypeface(regular);
        holder.team_post.setBackgroundResource(R.drawable.redgradient);
        Picasso.with(context)
                .load(teamOne.getOne_image())
                .into(holder.team_image);
    }

    @Override
    public int getItemCount() {
        return teamOneList.size();
    }
}