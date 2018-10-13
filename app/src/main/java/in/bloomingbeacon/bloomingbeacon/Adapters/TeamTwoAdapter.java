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

import in.bloomingbeacon.bloomingbeacon.Objects.TeamTwo;
import in.bloomingbeacon.bloomingbeacon.R;

/**
 * Created by Akshansh on 22-10-2017.
 */

public class TeamTwoAdapter extends RecyclerView.Adapter<TeamTwoAdapter.TeamTwoHolder> {

    private Context context;
    private List<TeamTwo> teamTwoList;
    private Typeface bold, regular;

    public class TeamTwoHolder extends RecyclerView.ViewHolder {
        public ImageView team_image;
        public TextView team_name, team_post;

        public TeamTwoHolder(View view) {
            super(view);
            team_image = view.findViewById(R.id.hierarchy_image);
            team_name = view.findViewById(R.id.hierarchy_name);
            team_post = view.findViewById(R.id.hierarchy_post);
        }
    }

    public TeamTwoAdapter(Context context, List<TeamTwo> teamTwoList) {
        this.context = context;
        this.teamTwoList = teamTwoList;
        bold = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(context.getAssets(), "fonts/poppinsr.ttf");
    }

    @Override
    public TeamTwoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hierarchy_item, parent, false);

        return new TeamTwoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamTwoHolder holder, int position) {
        TeamTwo teamTwo = teamTwoList.get(position);

        holder.team_name.setText(teamTwo.getTwo_name());
        holder.team_name.setTypeface(bold);
        holder.team_name.setBackgroundResource(R.drawable.yellowgradient);
        holder.team_post.setText(teamTwo.getTwo_post());
        holder.team_post.setTypeface(regular);
        holder.team_post.setBackgroundResource(R.drawable.yellowgradient);
        Picasso.with(context)
                .load(teamTwo.getTwo_image())
                .into(holder.team_image);
    }

    @Override
    public int getItemCount() {
        return teamTwoList.size();
    }
}