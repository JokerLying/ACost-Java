package com.gnayuil.acost.ui.information;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gnayuil.acost.R;
import com.gnayuil.acost.data.bean.InfoItem;
import com.gnayuil.acost.data.style.InfoStyle;
import com.gnayuil.acost.databinding.AdapterInfoAddBinding;
import com.gnayuil.acost.databinding.AdapterInfoItemBinding;
import com.gnayuil.acost.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context mContext;
    private List<InfoItem> mList;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_ADD = 2;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddClick();
    }

    public InfoAdapter(Context mContext, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(List<InfoItem> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.addAll(list);
        InfoItem add = new InfoItem();
        add.setType(InfoItem.ItemType.ADD);
        add.setStatus(InfoItem.ItemStatus.NONE);
        mList.add(add);
        for (int i = 0; i < list.size(); i++) {
            InfoItem one = list.get(i);
            if (one.getStatus() == InfoItem.ItemStatus.MODIFY) {
                one.setStatus(InfoItem.ItemStatus.NONE);
                notifyItemChanged(i);
            } else if (one.getStatus() == InfoItem.ItemStatus.ADD) {
                one.setStatus(InfoItem.ItemStatus.NONE);
                notifyItemInserted(i);
            }
        }
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(1, mList.size() - 1);
    }

    public boolean checkCanBeRemoved(int position) {
        if (position == 0 || position == mList.size() - 1 || (position == 1 && mList.size() == 3)) {
            notifyItemChanged(position);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int getItemViewType(int position) {
        InfoItem item = mList.get(position);
        if (item.getType() == InfoItem.ItemType.NORMAL) {
            return TYPE_NORMAL;
        } else {
            return TYPE_ADD;
        }
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            AdapterInfoItemBinding binding = AdapterInfoItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
            return new InfoViewHolder(binding.getRoot());
        } else {
            AdapterInfoAddBinding binding = AdapterInfoAddBinding.inflate(LayoutInflater.from(mContext), parent, false);
            return new InfoViewHolder(binding.getRoot());
        }
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TYPE_NORMAL:
                initNormalItem(holder);
                break;
            case TYPE_ADD:
                initAddItem(holder);
                break;
        }
    }

    private void initNormalItem(InfoViewHolder holder) {
        final InfoItem item = mList.get(holder.getAdapterPosition());
        final int position = holder.getAdapterPosition();
        if (holder.getAdapterPosition() == 0) {
            item.setTitle(mContext.getResources().getString(R.string.realCost));
        } else {
            item.setTitle(mContext.getResources().getString(R.string.packet, String.valueOf(holder.getAdapterPosition())));
        }
        AdapterInfoItemBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (binding == null) {
            return;
        }
        binding.setItem(item);
        binding.setIs(getInfoStyle());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
        binding.executePendingBindings();
    }

    private void initAddItem(InfoViewHolder holder) {
        AdapterInfoAddBinding binding = DataBindingUtil.getBinding(holder.itemView);
        if (binding == null) {
            return;
        }
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onAddClick();
            }
        });
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private InfoStyle getInfoStyle() {
        InfoStyle is = new InfoStyle();
        is.setRadius(DisplayUtils.dp2px(4));
        is.setNormalSolidColor(mContext.getColor(R.color.solidColor));
        is.setSelectedSolidColor(mContext.getColor(R.color.selectedSolidColor));
        is.setStrokeColor(mContext.getColor(R.color.strokeColor));
        is.setStrokeWidth(1);
        is.setSpacing(DisplayUtils.dp2px(5));
        return is;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
