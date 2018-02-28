package gormelof.net.sausozluk.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.models.random.Random;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private static final String TAG_ADAPTER = HomeAdapter.class.getSimpleName().toUpperCase();

    private List<Random> mRandomList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public HomeAdapter(Context context, List<Random> entryList) {
        mRandomList = entryList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View homeRowView = mLayoutInflater.inflate(R.layout.row_home, parent, false);
        HomeViewHolder homeViewHolder = new HomeViewHolder(homeRowView);
        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        Random random = mRandomList.get(position);

        holder.mTvTopicTitle.setText(random.getTopic().getTitle());
        holder.mTvTopicId.setText("#" + random.getTopic().getId());
        holder.mTvEntryText.setText(random.getEntry().getText());
        holder.mTvUserUsername.setText(random.getEntry().getUser().getUsername());

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date date = format.parse(random.getEntry().getCreatedAt());
            String reFormattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
            holder.mTvEntryCreatedAt.setText(reFormattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return mRandomList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder{

        TextView mTvTopicTitle, mTvTopicId, mTvEntryText, mTvEntryCreatedAt, mTvUserUsername;

        HomeViewHolder(View homeRowView) {
            super(homeRowView);

            mTvTopicTitle = (TextView) homeRowView.findViewById(R.id.tv_row_home_topic_title);
            mTvTopicId = (TextView) homeRowView.findViewById(R.id.tv_row_home_topic_id);
            mTvEntryText = (TextView) homeRowView.findViewById(R.id.tv_row_home_entry_text);
            mTvEntryCreatedAt = (TextView) homeRowView.findViewById(R.id.tv_row_home_entry_created_at);
            mTvUserUsername = (TextView) homeRowView.findViewById(R.id.tv_row_home_user_username);
        }
    }
}
