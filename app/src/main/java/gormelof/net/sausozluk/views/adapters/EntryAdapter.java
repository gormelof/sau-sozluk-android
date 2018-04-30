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
import gormelof.net.sausozluk.entities.Entry;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private static final String TAG_ADAPTER = TopicAdapter.class.getSimpleName().toUpperCase();

    private List<Entry> mEntryList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public EntryAdapter(Context context, List<Entry> entryList) {
        mEntryList = entryList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public EntryAdapter.EntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_entry, parent, false);
        EntryAdapter.EntryViewHolder entryViewHolder = new EntryAdapter.EntryViewHolder(view);
        return entryViewHolder;
    }

    @Override
    public void onBindViewHolder(EntryAdapter.EntryViewHolder entryViewHolder, int position) {
        Entry entry = mEntryList.get(position);

        entryViewHolder.mTvEntryId.setText("#" + entry.getId().toString());
        entryViewHolder.mTvEntryText.setText(entry.getText());
        entryViewHolder.mTvUsername.setText(entry.getUser().getUsername());

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date date = format.parse(entry.getCreatedAt());
            String reFormattedDate = new SimpleDateFormat("dd.MM.yyyy").format(date);
            entryViewHolder.mTvEntryCreatedAt.setText(reFormattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mEntryList.size();
    }

    class EntryViewHolder extends RecyclerView.ViewHolder {

        TextView mTvEntryId, mTvEntryText, mTvEntryCreatedAt, mTvUsername;

        EntryViewHolder(View view) {
            super(view);

            mTvEntryId = view.findViewById(R.id.tv_item_entry_id);
            mTvEntryText = view.findViewById(R.id.tv_item_entry_text);
            mTvEntryCreatedAt = view.findViewById(R.id.tv_item_entry_created_at);
            mTvUsername = view.findViewById(R.id.tv_item_entry_username);
        }
    }

}
