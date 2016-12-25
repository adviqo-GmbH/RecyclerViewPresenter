package net.kibotu.android.recyclerviewpresenter.app;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import net.kibotu.android.recyclerviewpresenter.BaseViewHolder;
import net.kibotu.android.recyclerviewpresenter.Presenter;
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;

/**
 * Created by <a href="https://about.me/janrabe">Jan Rabe</a>.
 */

public class PhotoPresenter extends Presenter<String, PhotoPresenter.ViewHolder> {

    public PhotoPresenter(@NonNull PresenterAdapter<String> presenterAdapter) {
        super(presenterAdapter);
    }

    @LayoutRes
    @Override
    protected int getLayout() {
        return R.layout.photo_presenter_item; // your layout
    }

    /**
     * Hint: Required method; if java had a way of allocating a
     * generic I could move this method into the adapter. :(
     */
    @NonNull
    @Override
    protected ViewHolder createViewHolder(@LayoutRes int layout, @NonNull ViewGroup parent) {
        return new ViewHolder(layout, parent);
    }

    /**
     * Binding your model against your ViewHolder.
     */
    public void bindViewHolder(@NonNull ViewHolder viewHolder, @NonNull String item, int position) {

        Glide.with(viewHolder.itemView.getContext())
                .load(item)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(viewHolder.photo);

        viewHolder.itemView.setOnClickListener(v -> {

            // accessing the click listener from the adapter:
            if (getPresenterAdapter().getOnItemClickListener() != null)
                getPresenterAdapter().getOnItemClickListener().onItemClick(item, v, position);
        });
    }

    @Override
    public <VH extends RecyclerView.ViewHolder> void bindViewHolder(@NotNull VH viewHolder, String item, int position) {

    }

    public static class ViewHolder extends BaseViewHolder {

        @NonNull
        @BindView(R.id.photo)
        ImageView photo;

        ViewHolder(@LayoutRes int layout, @NonNull ViewGroup parent) {
            super(layout, parent); // does butterknife binding
        }
    }
}