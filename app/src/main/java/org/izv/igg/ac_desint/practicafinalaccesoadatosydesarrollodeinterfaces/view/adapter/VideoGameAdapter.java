package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame_VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.adapter.viewholder.VideoGameViewHolder;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameViewModel;

import java.util.List;

public class VideoGameAdapter extends RecyclerView.Adapter<VideoGameViewHolder> implements View.OnClickListener {

    private List<VideoGame_VideoGameConsole> videoGameList;
    private Context context;

    private String[] videoGameConsoleNames = new String[] {"Atari 2600", "Atari Jaguar", "Atari Lynx",
                                                           "Bandai Apple Pippin", "Bandai WonderSwan", "Bandai WonderSwan Color",
                                                           "Microsoft Xbox", "Microsoft Xbox 360", "Microsoft Xbox One", "Microsoft Xbox Series X",
                                                           "NEC PC-FX", "NEC TurboExpress", "NEC TurboGrafx-16",
                                                           "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Game Boy", "Nintendo Game Boy Advance", "Nintendo Game Boy Color", "Nintendo GameCube", "Nintendo NES", "Nintendo Super NES", "Nintendo Switch", "Nintendo Wii", "Nintendo Wii U",
                                                           "SNK Neo Geo AES", "SNK Neo Geo CD", "SNK Neo Geo Pocket",
                                                           "Sega Dreamcast", "Sega Game Gear", "Sega Genesis", "Sega Master System", "Sega Nomad", "Sega Saturn",
                                                           "Sony PlayStation", "Sony PlayStation 2", "Sony PlayStation 3", "Sony PlayStation 4", "Sony PlayStation 5", "Sony PlayStation Portable", "Sony PlayStation Vita"};

    private View.OnClickListener listener;

    public VideoGameAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public VideoGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video_game, parent, false);

        view.setOnClickListener(this);

        return new VideoGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoGameViewHolder holder, int position) {

        VideoGame_VideoGameConsole videoGame_videoGameConsole = videoGameList.get(position);
        VideoGame videoGame = videoGame_videoGameConsole.videoGame;
        VideoGameConsole videoGameConsole = videoGame_videoGameConsole.videoGameConsole;
        holder.tvVideoGameName.setText(videoGame.name);
        holder.tvVideoGameDeveloper.setText(videoGame.developer);

        holder.tvVideoGameConsole.setText(videoGameConsoleNames[(int) videoGame.idVideoGameConsole - 1]);

        holder.tvVideoGameGenre.setText(videoGame.genre);
        holder.tvVideoGameReleaseDate.setText(videoGame.releaseDate);
        holder.tvVideoGameImageUrl.setText(videoGame.imageUrl);
        Picasso.get().load(videoGame.imageUrl).into(holder.ivVideoGame);
    }

    @Override
    public int getItemCount() {
        if (videoGameList == null) {
            return 0;
        }
        return videoGameList.size();
    }

    public void setVideoGameList(List<VideoGame_VideoGameConsole> videoGameList) {
        this.videoGameList = videoGameList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void update(List<VideoGame_VideoGameConsole> videoGames) {
        videoGameList = videoGames;
        notifyDataSetChanged();
    }

    public VideoGame_VideoGameConsole getItem(int position) {
        return videoGameList.get(position);
    }
}
