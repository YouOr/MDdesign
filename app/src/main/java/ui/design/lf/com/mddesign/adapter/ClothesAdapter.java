package ui.design.lf.com.mddesign.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import ui.design.lf.com.mddesign.R;
import ui.design.lf.com.mddesign.model.Clothes;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    private Context mContext;
    private List<Clothes> mClothesList;
    private OnItemClickListener onItemClickListener;

    public ClothesAdapter(List<Clothes> mClothesList) {
        this.mClothesList = mClothesList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_clothes_item, parent, false);
        return new ViewHolder(view);
    }


    public interface OnItemClickListener {
        void onItemClick(View View, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Clothes clothes = mClothesList.get(position);
        holder.fruitName.setText(clothes.getName());
        Glide.with(mContext).load(clothes.getImageid()).into(holder.fruitImage);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mClothesList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.clothes_image);
            fruitName = (TextView) itemView.findViewById(R.id.clothes_name);
        }
    }
}
