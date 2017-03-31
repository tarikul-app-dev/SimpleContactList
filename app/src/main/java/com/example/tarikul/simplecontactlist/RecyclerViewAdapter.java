package com.example.tarikul.simplecontactlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Alamgir Hossain on 3/31/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ContactListModel> contactList;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private OnLoadMoreListener onLoadMoreListener;

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView userName, userPhoneNumber;

        public ItemViewHolder(View view) {
            super(view);
            userName = (TextView) view.findViewById(R.id.txtv_user_name);
            userPhoneNumber = (TextView) view.findViewById(R.id.txtv_user_number);

        }
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder {
        Button footerText;

        public FooterViewHolder(View view) {
            super(view);
            footerText = (Button) view.findViewById(R.id.footer_text);
        }
    }

    public RecyclerViewAdapter(List<ContactListModel> contactList) {
        this.contactList = contactList;
    }

    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            //Inflating recycle view item layout
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.contactlist_row, parent, false);
            return new ItemViewHolder(itemView);
        }else if (viewType == TYPE_FOOTER) {
            //Inflating footer view
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_footer, parent, false);
            return new FooterViewHolder(itemView);
        } else return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;

            ContactListModel contact = contactList.get(position);
            itemViewHolder .userName.setText(contact.getUserName());
            itemViewHolder .userPhoneNumber.setText(contact.getUserPhoneNumber());
        }else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.footerText.setText("Load More");
            footerHolder.footerText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                }
            });


        }
    }
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return contactList.get(position) != null ? TYPE_ITEM : TYPE_FOOTER;
    }
    @Override
    public int getItemCount() {
        return contactList.size();
    }
}
