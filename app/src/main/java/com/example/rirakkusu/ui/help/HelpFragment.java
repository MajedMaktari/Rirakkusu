package com.example.rirakkusu.ui.help;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rirakkusu.Feelings;
import com.example.rirakkusu.R;
import com.example.rirakkusu.FeelingsLab;

import org.w3c.dom.Text;

import java.util.List;

public class HelpFragment extends Fragment {

    private TextView historyTitle;
    private RecyclerView mHistoryRecyclerView;
    private HistoryAdapter mAdapter;
    private SharedPreferences savedValues;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(getActivity(), R.xml.root_preferemces, false);
        savedValues = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_help, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        historyTitle = root.findViewById(R.id.historyTitle);


        PreferenceManager.setDefaultValues(getActivity(), R.xml.root_preferemces, false);
        mHistoryRecyclerView = root.findViewById(R.id.history_recycler_view);
        mHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (!savedValues.getBoolean(getResources().getString(R.string.display_history), true)) {
            historyTitle.setText("Enable \"Display  History\" from the Preferences page to view previous session history.");
            mHistoryRecyclerView.setVisibility(View.GONE);

        }



        updateUI();
        return root;

    }

    @Override
    public void onResume() {
        updateUI();
        super.onResume();

    }

    private void updateUI() {
        FeelingsLab feelingsLab = FeelingsLab.get(getActivity());
        List<Feelings> feelings = feelingsLab.getFeelings();
        if (mAdapter == null) {
            mAdapter = new HistoryAdapter(feelings);
            mHistoryRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setFeelings(feelings);
            mAdapter.notifyDataSetChanged();
        }

    }


    private class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private TextView mFeelingTextView;
        private Feelings mFeelings;

        public HistoryHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_history, parent, false));
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.session_title);
            mDateTextView = itemView.findViewById(R.id.session_date);
            mFeelingTextView = itemView.findViewById(R.id.session_feelings);
        }

        public void bind(Feelings feelings) {
            mFeelings = feelings;
            mTitleTextView.setText(mFeelings.getName());
            mDateTextView.setText(mFeelings.getDate().toString());
            mFeelingTextView.setText(mFeelings.getFeeling());
        }

        @Override
        public void onClick(View v) {
            Intent intent = HelpActivity.newIntent(getActivity(), 0);
            startActivity(intent);
        }
    }
    private class HistoryAdapter extends RecyclerView.Adapter<HistoryHolder> {
        private List<Feelings> mFeelings;

        public HistoryAdapter(List<Feelings> feelings) {
            mFeelings = feelings;
        }

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HistoryHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
            Feelings feelings = mFeelings.get(position);
            holder.bind(feelings);
        }

        @Override
        public int getItemCount() {
            return mFeelings.size();
        }

        public void setFeelings(List<Feelings> feelings) {
            mFeelings = feelings;
        }
    }

}

