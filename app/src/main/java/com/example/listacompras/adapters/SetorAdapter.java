package com.example.listacompras.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.listacompras.R;
import com.example.listacompras.entities.SetorEntity;

import java.util.List;

public class SetorAdapter extends ArrayAdapter<SetorEntity> {

    private final List<SetorEntity> setores;
    private final LayoutInflater inflater;

    public SetorAdapter(Context context, List<SetorEntity> setores) {
        super(context, 0, setores);
        this.setores = setores;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return setores.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fragment_setor, parent, false);
        }

        SetorEntity setor = getItem(position);
        TextView setorName = convertView.findViewById(R.id.setor_name_txt);
        setorName.setText(setor.getName());

        return convertView;
    }
}

