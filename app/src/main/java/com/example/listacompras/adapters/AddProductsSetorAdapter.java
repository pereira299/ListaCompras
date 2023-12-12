package com.example.listacompras.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.listacompras.R;
import com.example.listacompras.entities.ShoppingListEntity;

import java.util.List;

public class AddProductsSetorAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShoppingListEntity> lists;

    public AddProductsSetorAdapter(Context context, List<ShoppingListEntity> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.fragment_card_select,
                    parent,
                    false
            );
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        //get item and set holderview properties
        return convertView;
    }

    private static class HolderView {
        private final TextView title;
        private final TextView price;
        private final CheckBox check;

        public HolderView(View view) {
            title = view.findViewById(R.id.fragTitleSelect);
            price = view.findViewById(R.id.fragPriceSelect);
            check = view.findViewById(R.id.fragCheckboxSelect);
        }
    }
}
