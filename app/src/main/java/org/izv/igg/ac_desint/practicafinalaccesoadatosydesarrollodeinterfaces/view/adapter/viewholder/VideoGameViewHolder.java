package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.adapter.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;

public class VideoGameViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivVideoGame;
    public TextView tvVideoGameName, tvVideoGameDeveloper, tvVideoGameConsole,
            tvVideoGameGenre, tvVideoGameReleaseDate, tvVideoGameImageUrl;

    public VideoGameViewHolder(@NonNull View itemView) {
        super(itemView);
        ivVideoGame = itemView.findViewById(R.id.ivVideoGame);
        tvVideoGameName = itemView.findViewById(R.id.tvVideoGameName);
        tvVideoGameDeveloper = itemView.findViewById(R.id.tvVideoGameDeveloper);
        tvVideoGameConsole = itemView.findViewById(R.id.tvVideoGameConsole);
        tvVideoGameGenre = itemView.findViewById(R.id.tvVideoGameGenre);
        tvVideoGameReleaseDate = itemView.findViewById(R.id.tvVideoGameReleaseDate);
        tvVideoGameImageUrl = itemView.findViewById(R.id.tvVideoGameImageUrl);
    }
}
