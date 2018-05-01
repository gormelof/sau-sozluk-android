package gormelof.net.sausozluk.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gormelof.net.sausozluk.R;
import gormelof.net.sausozluk.entities.Topic;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private static final String TAG_ADAPTER = TopicAdapter.class.getSimpleName().toUpperCase();

    private List<Topic> mTopicList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public TopicAdapter(Context context, List<Topic> topicList) {
        mTopicList = topicList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_topic, parent, false);
        TopicViewHolder topicViewHolder = new TopicViewHolder(view);
        return topicViewHolder;
    }

    @Override
    public void onBindViewHolder(TopicViewHolder topicViewHolder, int position) {
        Topic topic = mTopicList.get(position);

        topicViewHolder.mTvTopicTitle.setText(topic.getTitle());

        if (topic.getCount().equals(0)) {
            topicViewHolder.mTvTopicCount.setText("");
        } else {
            topicViewHolder.mTvTopicCount.setText(topic.getCount().toString());
        }
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder{

        TextView mTvTopicTitle, mTvTopicCount;

        TopicViewHolder(View view) {
            super(view);

            mTvTopicTitle = view.findViewById(R.id.tv_item_topic_title);
            mTvTopicCount = view.findViewById(R.id.tv_item_topic_count);
        }
    }

    public void clear() {
        mTopicList.clear();
        notifyDataSetChanged();
    }
}
