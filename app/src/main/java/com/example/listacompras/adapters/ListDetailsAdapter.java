package com.example.listacompras.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listacompras.R;
import com.example.listacompras.entities.ListaWithItems;
import com.example.listacompras.entities.ProductEntity;
import com.example.listacompras.entities.ShoppingListEntity;
import com.example.listacompras.entities.ShoppingListItemEntity;

import java.util.List;

public class ListDetailsAdapter extends BaseAdapter {
    private final Context context;
    private final List<ShoppingListItemEntity> lists;

    public ListDetailsAdapter(Context context, List<ShoppingListItemEntity> lists) {
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
                    R.layout.fragment_item_qtd,
                    parent,
                    false
            );
            holderView = new HolderView(convertView);
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }

        ShoppingListItemEntity item = lists.get(position);
        ProductEntity prod = item.getProduct();
        holderView.price.setText(String.format("R$ %,.2f",prod.getPrice())+ " " +prod.getUnity());
        holderView.qtd.setText(item.getQuantity());
        holderView.title.setText(prod.getName());
        return convertView;
    }

    private static class HolderView {
        private final TextView title;
        private final TextView price;
        private final TextView qtd;

        public HolderView(View view) {
            title = view.findViewById(R.id.fragTitleQtd);
            price = view.findViewById(R.id.fragPriceQtd);
            qtd = view.findViewById(R.id.fragNumQtd);
        }
    }
}
