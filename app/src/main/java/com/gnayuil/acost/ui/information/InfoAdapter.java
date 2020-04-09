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
import com.gnayuil.acost.databinding.AdapterInfoItemBinding;
import com.gnayuil.acost.utils.DisplayUtils;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {

    private Context mContext;
    private List<InfoItem> mList;

    public InfoAdapter(Context context) {
        this.mContext = context;
    }

    public List<InfoItem> getList() {
        return mList;
    }

    public void setList(List<InfoItem> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterInfoItemBinding binding = AdapterInfoItemBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new InfoViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        final InfoItem item = mList.get(holder.getAdapterPosition());
        switch (holder.getAdapterPosition()) {
            case 0:
                item.setTitle(mContext.getResources().getString(R.string.realCost));
                break;
            case 1:
                item.setTitle(mContext.getResources().getString(R.string.originalCost));
                break;
            default:
                item.setTitle(mContext.getResources().getString(R.string.packet, String.valueOf(holder.getAdapterPosition() - 1)));
                break;
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
                for (InfoItem one :
                        mList) {
                    one.setCheck(false);
                }
                item.setCheck(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    private InfoStyle getInfoStyle() {
        InfoStyle is = new InfoStyle();
        is.setRadius(DisplayUtils.dp2px(4));
        is.setNormalSolidColor(0x88ffffff);
        is.setSelectedSolidColor(0x88eeeeee);
        is.setStrokeColor(0x33666666);
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
