package in.bloomingbeacon.bloomingbeacon.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Objects.TeamThree;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamThreeAdapter extends RecyclerView.Adapter<TeamThreeAdapter.TeamThreeHolder> {

    private Context context;
    private List<TeamThree> teamThreeList;
    private Typeface bold, regular;

    public class TeamThreeHolder extends RecyclerView.ViewHolder {
        public ImageView team_image;
        public TextView team_name, team_post;

        public TeamThreeHolder(View view) {
            super(view);
            team_image = view.findViewById(R.id.hierarchy_image);
            team_name = view.findViewById(R.id.hierarchy_name);
            team_post = view.findViewById(R.id.hierarchy_post);
        }
    }

    public TeamThreeAdapter(Context context, List<TeamThree> teamThreeList) {
        this.context = context;
        this.teamThreeList = teamThreeList;
        bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public TeamThreeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hierarchy_item, parent, false);

        return new TeamThreeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamThreeHolder holder, int position) {
        TeamThree teamThree = teamThreeList.get(position);

        holder.team_name.setText(teamThree.getThree_name());
        holder.team_name.setTypeface(bold);
        holder.team_name.setBackgroundResource(R.drawable.greengradient);
        holder.team_post.setText(teamThree.getThree_post());
        holder.team_post.setTypeface(regular);
        holder.team_post.setBackgroundResource(R.drawable.greengradient);
        Picasso.with(context)
                .load(teamThree.getThree_image())
                .into(holder.team_image);
    }

    @Override
    public int getItemCount() {
        return teamThreeList.size();
    }
}