package com.tanhuan.fanslation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ParaBean;

import java.util.List;

public class CollinsFragment extends Fragment {
    ListView lvCollins;

    ParaBean paraBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paraBean = (ParaBean) getArguments().getSerializable("para");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collins, null);
        lvCollins = view.findViewById(R.id.lv_collins);

        lvCollins.setAdapter(new ClsAdapter(getContext(), R.layout.item_collins, paraBean.getCollins().getCollins_entries().get(0).getEntries().getEntry()));

        return view;
    }

    class ClsAdapter extends ArrayAdapter {
        int resId;
        List<ParaBean.CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean> entryBeanList;

        ClsAdapter(Context context, int resId, List<ParaBean.CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean> entryBeanList) {
            //todo: super(context, resId) 也可以？
            super(context, resId, entryBeanList);
            this.resId = resId;
            this.entryBeanList = entryBeanList;
        }

        @Override
        public int getCount() {
            return entryBeanList.size();
        }

        @Nullable
        @Override
        public ParaBean.CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean getItem(int position) {
            return entryBeanList.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                View view = getLayoutInflater().inflate(resId, null);
                convertView = view;
                viewHolder = new ViewHolder();
                viewHolder.tvPos = view.findViewById(R.id.tv_collins_pos);
                viewHolder.tvTrans = view.findViewById(R.id.tv_collins_trans);
                viewHolder.tvSentence = view.findViewById(R.id.tv_collins_sentence);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ParaBean.CollinsBean.CollinsEntriesBean.EntriesBean.EntryBean entryBean = entryBeanList.get(position);
            if (entryBean.getTran_entry().get(0).getPos_entry() != null) {
                viewHolder.tvPos.setText(entryBean.getTran_entry().get(0).getPos_entry().getPos());
            }

            if (entryBean.getTran_entry().get(0).getTran() != null) {
                viewHolder.tvTrans.setText(entryBean.getTran_entry().get(0).getTran());
            }

            if (entryBean.getTran_entry().get(0).getExam_sents() != null) {
                for (int i = 0; i < entryBean.getTran_entry().get(0).getExam_sents().getSent().size(); i++) {
                    viewHolder.tvSentence.setText(entryBean.getTran_entry().get(0).getExam_sents().getSent().get(i).getEng_sent() + "\n");
                    viewHolder.tvSentence.append(entryBean.getTran_entry().get(0).getExam_sents().getSent().get(i).getChn_sent() + "\n");
                }
            }

            return convertView;
        }

        class ViewHolder{
            TextView tvPos;
            TextView tvTrans;
            TextView tvSentence;
        }
    }
}
