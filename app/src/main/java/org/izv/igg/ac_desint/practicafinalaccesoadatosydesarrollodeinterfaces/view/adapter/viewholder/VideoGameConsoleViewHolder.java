package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.adapter.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;

public class VideoGameConsoleViewHolder extends RecyclerView.ViewHolder {

    private TextView tvVideoGameConsole;

    public VideoGameConsoleViewHolder(@NonNull View itemView) {
        super(itemView);
        tvVideoGameConsole = itemView.findViewById(R.id.tvVideoGameConsole);
    }

    public TextView getVideoGameConsoleItemView() {
        return tvVideoGameConsole;
    }
}
