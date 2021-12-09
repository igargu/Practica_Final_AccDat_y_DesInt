package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.DropdownMenuPopupItemBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.FragmentAddBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.ItemVideoGameBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameViewModel;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameConsoleViewModel;

import java.time.LocalDate;
import java.util.Date;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;

    private EditText etVideoGameName, etVideoGameDeveloper, etVideoGameGenre,
            etVideoGameReleaseDate, etVideoGameImageUrl;
    private Spinner spVideoGameConsole;
    private ImageView ivVideoGameCover;
    private VideoGame videoGame;
    private VideoGameViewModel videoGameVM;
    private VideoGameConsole auxVGC;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialize();
    }

    private void addVideoGame(VideoGame videoGame) {
        //videoGameVM.insertVideoGame(videoGame);

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialogAdd_title)
                .setMessage(R.string.alertDialogAdd_message)
                .setPositiveButton(R.string.alertDialogAdd_confirm, (dialog, which) -> {
                    // Añado video juego
                    videoGameVM.insertVideoGame(videoGame);
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_addVideoGame, Toast.LENGTH_LONG).show();
                })
                .setNegativeButton(R.string.alertDialogAdd_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();
    }

    private void cleanFields() {
        etVideoGameName.setText("");
        etVideoGameDeveloper.setText("");
        spVideoGameConsole.setSelection(0);
        etVideoGameGenre.setText("");
        etVideoGameReleaseDate.setText("");
        etVideoGameImageUrl.setText("");
    }

    private void defineAddListener() {
        binding.btAddVideoGame.setOnClickListener(view -> {
            VideoGame videoGame = getVideoGame();
            if (videoGame.isValid()) {
                addVideoGame(videoGame);
            } else {
                Toast.makeText(getParentFragment().getContext(), R.string.toast_invalidInputData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private VideoGame getVideoGame() {
        String name = etVideoGameName.getText().toString();
        String developer = etVideoGameDeveloper.getText().toString();
        VideoGameConsole console = (VideoGameConsole) spVideoGameConsole.getSelectedItem();
        String genre = etVideoGameGenre.getText().toString();
        String date = etVideoGameReleaseDate.getText().toString();
        String url = etVideoGameImageUrl.getText().toString();

        videoGame = new VideoGame();
        videoGame.name = name;
        videoGame.idVideoGameConsole = console.id;
        videoGame.developer = developer;
        videoGame.genre = genre;
        videoGame.releaseDate = date;
        videoGame.imageUrl = url;

        return videoGame;
    }

    private void getViewModel() {
        videoGameVM = new ViewModelProvider(this).get(VideoGameViewModel.class);

        videoGameVM.getInsertResults().observe(getViewLifecycleOwner(), list -> {
            cleanFields();
        });

        VideoGameConsoleViewModel videoGameConsoleVM = new ViewModelProvider(this).get(VideoGameConsoleViewModel.class);
        videoGameConsoleVM.getVideoGameConsoles().observe(getViewLifecycleOwner(), videoGameConsoles -> {
            // videoGameConsoleVM.getVideoGameConsoles().observe(this, videoGameConsoles -> {
            VideoGameConsole videoGameConsole = new VideoGameConsole();
            videoGameConsole.id = 0;
            videoGameConsole.name = getString(R.string.default_videoGameConsole);
            videoGameConsoles.add(0, videoGameConsole);
            ArrayAdapter<VideoGameConsole> adapter =
                    new ArrayAdapter<VideoGameConsole>(getParentFragment().getContext(), android.R.layout.simple_spinner_dropdown_item, videoGameConsoles);
            spVideoGameConsole.setAdapter(adapter);
        });
    }

    private void initialize() {
        etVideoGameName = binding.etVideoGameName;
        etVideoGameDeveloper = binding.etVideoGameDeveloper;
        spVideoGameConsole = binding.spVideoGameConsole;
        etVideoGameGenre = binding.etVideoGameGenre;
        etVideoGameReleaseDate = binding.etVideoGameDate;
        etVideoGameImageUrl = binding.etVideoGameImageUrl;

        ivVideoGameCover = binding.ivUploadImageVideoGame;

        getViewModel();

        defineAddListener();
    }
}