package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.FragmentMainBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame_VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.adapter.VideoGameAdapter;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameConsoleViewModel;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameViewModel;

import java.util.List;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void initialize() {

        RecyclerView recyclerView = binding.rvPokemon;
        recyclerView.setLayoutManager(new LinearLayoutManager(getParentFragment().getContext()));

        VideoGameViewModel videoGameVM = new ViewModelProvider(this).get(VideoGameViewModel.class);
        VideoGameAdapter videoGameAdapter = new VideoGameAdapter(getContext());

        recyclerView.setAdapter(videoGameAdapter);

        LiveData<List<VideoGame_VideoGameConsole>> videoGameList = videoGameVM.getAllVideoGames();
        videoGameList.observe(getViewLifecycleOwner(), videoGames -> {
            videoGameAdapter.setVideoGameList(videoGames);
        });

        binding.fabVideoGameAdd.setOnClickListener(view -> NavHostFragment.findNavController(MainFragment.this)
                .navigate(R.id.action_mainFragment_to_addFragment));

        videoGameAdapter.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("id_VideoGameName",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.name);
            bundle.putSerializable("id_VideoGameDeveloper",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.developer);
            bundle.putSerializable("id_VideoGameConsole",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.idVideoGameConsole);
            bundle.putSerializable("id_VideoGameGenre",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.genre);
            bundle.putSerializable("id_VideoGameReleaseDate",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.releaseDate);
            bundle.putSerializable("id_VideoGameImageUrl",videoGameAdapter.getItem(recyclerView.getChildAdapterPosition(view)).videoGame.imageUrl);
            NavHostFragment.findNavController(MainFragment.this).navigate(R.id.action_mainFragment_to_editFragment,bundle);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}