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

import com.tanhuan.fanslation.BaseApp;
import com.tanhuan.fanslation.R;
import com.tanhuan.fanslation.bean.ParaBean;
import com.tanhuan.fanslation.entity.ParaEntity;
import com.tanhuan.fanslation.entity.ParaEntity_;

import java.util.ArrayList;
import java.util.List;

import io.objectbox.Box;

public class StandandFragment extends Fragment {
    private final static String TAG = "-----StandardFragment-----";

    ParaBean paraBean;

    ListView lvTrans;
    TextView tvExamTypes;
    ListView lvSentence;

    List<ParaBean.BlngSentsPartBean.SentencepairBean> sentencepairBeans;
    List<String> trans;

    Box<ParaEntity> paraBox;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        paraBean = (ParaBean) getArguments().getSerializable("para");

        paraBox = BaseApp.getBoxStore().boxFor(ParaEntity.class);

        if (paraBox.query().equal(ParaEntity_.input, paraBean.getInput()).build().find().size() > 0) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_standard, null);

        lvTrans = view.findViewById(R.id.lv_trans);
        tvExamTypes = view.findViewById(R.id.tv_exam_type);
        lvSentence = view.findViewById(R.id.lv_sentence);

        //show sentences
        if (paraBean.getBlng_sents_part() != null) {
            sentencepairBeans = paraBean.getBlng_sents_part().getSentencepair();
            lvSentence.setAdapter(new StcAdapter(getContext(), R.layout.item_sentences, sentencepairBeans));
        }

        //show exam types
        if (paraBean.getEc().getExam_type() != null) {
            for (int i = 0; i < paraBean.getEc().getExam_type().size(); i++) {
                tvExamTypes.append(paraBean.getEc().getExam_type().get(i) + "/");
            }
        }

        //show translations
        trans = new ArrayList<>();
        for (int i = 0; i < paraBean.getEc().getWord().get(0).getTrs().size(); i++) {
            trans.add(paraBean.getEc().getWord().get(0).getTrs().get(i).getTr().get(0).getL().getI().get(0));
        }
        lvTrans.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, trans));

        return view;
    }

    class StcAdapter extends ArrayAdapter {
        List<ParaBean.BlngSentsPartBean.SentencepairBean> sentencepairBeans;
        int resId;

        StcAdapter(Context context, int resId, List<ParaBean.BlngSentsPartBean.SentencepairBean> sentencepairBeans) {
            super(context, resId, sentencepairBeans);
            this.sentencepairBeans = sentencepairBeans;
            this.resId = resId;
        }

        @Override
        public int getCount() {
            return sentencepairBeans.size();
        }

        @Nullable
        @Override
        public ParaBean.BlngSentsPartBean.SentencepairBean getItem(int position) {
            return sentencepairBeans.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                View view = getLayoutInflater().inflate(resId, null);
                convertView = view;
                viewHolder = new ViewHolder();
                viewHolder.tvStcEn = view.findViewById(R.id.tv_stc_en);
                viewHolder.tvStcCn = view.findViewById(R.id.tv_stc_cn);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ParaBean.BlngSentsPartBean.SentencepairBean sentencepairBean = sentencepairBeans.get(position);
            viewHolder.tvStcEn.setText(sentencepairBean.getSentence());
            viewHolder.tvStcCn.setText(sentencepairBean.getSentencetranslation());
            return convertView;
        }

        class ViewHolder {
            TextView tvStcEn;
            TextView tvStcCn;
        }
    }
}
