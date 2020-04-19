package com.gnayuil.acost.ui.information;

import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.databinding.FragmentInformationBinding;
import com.gnayuil.acost.ui.base.BaseFragment;

import java.util.List;
import java.util.Locale;

import static android.view.HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING;

public class InformationFragment extends BaseFragment {

    private FragmentInformationBinding mBinding;
    private InformationViewModel mViewModel;

    private InfoAdapter adapter;

    public static InformationFragment newInstance() {
        return new InformationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_information, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = getFragmentViewModelProvider(this).get(InformationViewModel.class);

        adapter = new InfoAdapter(mActivity, new InfoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mSharedViewModel.checkItem(position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                mSharedViewModel.copyItem(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, FLAG_IGNORE_GLOBAL_SETTING);
            }

            @Override
            public void onAddClick() {
                mSharedViewModel.addItem();
            }
        });
        mBinding.rvInfo.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.START) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (adapter.checkCanBeRemoved(position)) {
                    mSharedViewModel.removeItem(position);
                    adapter.removeItem(position);
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(mBinding.rvInfo);

        mSharedViewModel.getInfoList().observe(mActivity, new Observer<List<InfoItem>>() {
            @Override
            public void onChanged(List<InfoItem> infoItems) {
                adapter.setList(infoItems);
            }
        });

        mSharedViewModel.getLanguage().observe(mActivity, new Observer<Locale>() {
            @Override
            public void onChanged(Locale locale) {
                adapter.updateLanguage();
            }
        });
    }

}
