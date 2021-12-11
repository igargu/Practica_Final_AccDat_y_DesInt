package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.view.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.R;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.FragmentAddBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.databinding.FragmentEditBinding;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameConsoleViewModel;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel.VideoGameViewModel;

import java.time.LocalDate;

public class EditFragment extends Fragment {

    private FragmentEditBinding binding;

    private EditText etVideoGameName, etVideoGameDeveloper, etVideoGameGenre,
            etVideoGameReleaseDate, etVideoGameImageUrl;
    private Spinner spVideoGameConsole;
    private ImageView ivVideoGameCover;
    private Button btDeleteVideoGame, btEditVideoGame, btCancelEditVideoGame, btSaveEditVideoGame;
    private VideoGame videoGame;
    private VideoGameViewModel videoGameVM;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentEditBinding.inflate(inflater, container, false);
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

    private void initialize() {

        etVideoGameName = binding.etVideoGameName;
        etVideoGameName.setText(String.valueOf(getArguments().getSerializable("id_VideoGameName")));
        etVideoGameDeveloper = binding.etVideoGameDeveloper;
        etVideoGameDeveloper.setText(String.valueOf(getArguments().getSerializable("id_VideoGameDeveloper")));
        spVideoGameConsole = binding.spVideoGameConsole;
        spVideoGameConsole.setSelection(Integer.parseInt(String.valueOf(getArguments().getSerializable("id_VideoGameConsole"))));
        etVideoGameGenre = binding.etVideoGameGenre;
        etVideoGameGenre.setText(String.valueOf(getArguments().getSerializable("id_VideoGameGenre")));
        etVideoGameReleaseDate = binding.etVideoGameDate;
        etVideoGameReleaseDate.setText(String.valueOf(getArguments().getSerializable("id_VideoGameReleaseDate")));
        etVideoGameImageUrl = binding.etVideoGameImageUrl;
        etVideoGameImageUrl.setText(String.valueOf(getArguments().getSerializable("id_VideoGameImageUrl")));

        ivVideoGameCover = binding.ivUploadImageVideoGame;
        Picasso.get().load(etVideoGameImageUrl.getText().toString()).into(ivVideoGameCover);

        btDeleteVideoGame = binding.btDeleteVideoGame;
        btEditVideoGame = binding.btEditVideoGame;
        btSaveEditVideoGame = binding.btSaveEditVideoGame;
        btCancelEditVideoGame = binding.btCancelEditVideoGame;

        getViewModel();

        etVideoGameName.setEnabled(false);
        etVideoGameDeveloper.setEnabled(false);
        spVideoGameConsole.setEnabled(false);
        etVideoGameGenre.setEnabled(false);
        etVideoGameReleaseDate.setEnabled(false);
        etVideoGameImageUrl.setEnabled(false);

        defineDeleteListener();
        defineEditListener();
    }

    private void defineDeleteListener() {
        binding.btDeleteVideoGame.setOnClickListener(view -> {
            VideoGame videoGame = getVideoGame();
            deleteVideoGame(videoGame);
        });
    }

    private void defineEditListener() {
        binding.btEditVideoGame.setOnClickListener(view -> editVideoGame());
    }

    private void defineSaveCancelEditListener() {
        binding.btCancelEditVideoGame.setOnClickListener(view -> {

            etVideoGameName.setEnabled(false);
            etVideoGameDeveloper.setEnabled(false);
            spVideoGameConsole.setEnabled(false);
            etVideoGameGenre.setEnabled(false);
            etVideoGameReleaseDate.setEnabled(false);
            etVideoGameImageUrl.setEnabled(false);

            btDeleteVideoGame.setVisibility(View.VISIBLE);
            btEditVideoGame.setVisibility(View.VISIBLE);
            btSaveEditVideoGame.setVisibility(View.INVISIBLE);
            btCancelEditVideoGame.setVisibility(View.INVISIBLE);
        });
    }

    private void defineSaveEditListener() {
        binding.btSaveEditVideoGame.setOnClickListener(view -> {
            VideoGame videoGame = getVideoGame();
            if (videoGame.isValid()) {
                saveEditVideoGame(videoGame);
            } else {
                Toast.makeText(getParentFragment().getContext(), R.string.toast_invalidInputData, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteVideoGame(VideoGame videoGame) {

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialogDelete_title)
                .setMessage(R.string.alertDialogDelete_message)
                .setPositiveButton(R.string.alertDialogDelete_confirm, (dialog, which) -> {
                    // Borrar juego de la base de datos
                    videoGameVM.deleteVideoGameByName(videoGame.name);
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_deleteVideoGame, Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                })
                .setNegativeButton(R.string.alertDialogDelete_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();
    }

    private void editVideoGame() {
        btDeleteVideoGame.setVisibility(View.GONE);
        btEditVideoGame.setVisibility(View.GONE);

        btSaveEditVideoGame.setVisibility(View.VISIBLE);
        defineSaveEditListener();

        btCancelEditVideoGame.setVisibility(View.VISIBLE);
        defineSaveCancelEditListener();

        etVideoGameName.setEnabled(true);
        etVideoGameDeveloper.setEnabled(true);
        spVideoGameConsole.setEnabled(true);
        etVideoGameGenre.setEnabled(true);
        etVideoGameReleaseDate.setEnabled(true);
        etVideoGameImageUrl.setEnabled(true);
    }


    private void saveEditVideoGame(VideoGame videoGame) {

        new AlertDialog.Builder(getParentFragment().getContext())
                .setTitle(R.string.alertDialogEdit_title)
                .setMessage(R.string.alertDialogEdit_message)
                .setPositiveButton(R.string.alertDialogEdit_confirm, (dialog, which) -> {
                    // Guardo cambios del video juego
                    videoGameVM.updateVideoGameByName(videoGame.name, videoGame.genre, videoGame.developer, videoGame.releaseDate, videoGame.imageUrl, videoGame.idVideoGameConsole, String.valueOf(getArguments().getSerializable("id_VideoGameName")));
                    Toast.makeText(getParentFragment().getContext(), R.string.toast_editVideoGame, Toast.LENGTH_LONG).show();
                    getActivity().onBackPressed();
                })
                .setNegativeButton(R.string.alertDialogEdit_cancel, (dialog, which) -> {
                    dialog.cancel();
                })
                .show();

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
        System.out.println(getArguments().getSerializable("id_VideoGameId"));
        return videoGame;
    }

    private void getViewModel() {
        videoGameVM = new ViewModelProvider(this).get(VideoGameViewModel.class);

        videoGameVM.getInsertResults().observe(getViewLifecycleOwner(), list -> {

        });

        VideoGameConsoleViewModel videoGameConsoleVM = new ViewModelProvider(this).get(VideoGameConsoleViewModel.class);
        videoGameConsoleVM.getVideoGameConsoles().observe(getViewLifecycleOwner(), videoGameConsoles -> {
            // videoGameConsoleVM.getVideoGameConsoles().observe(this, videoGameConsoles -> {
            VideoGameConsole videoGameConsole = new VideoGameConsole();
            //videoGameConsole.id = 0;
            videoGameConsole.name = getString(R.string.default_videoGameConsole);
            videoGameConsoles.add(0, videoGameConsole);
            ArrayAdapter<VideoGameConsole> adapter =
                    new ArrayAdapter<VideoGameConsole>(getParentFragment().getContext(), android.R.layout.simple_spinner_dropdown_item, videoGameConsoles);
            spVideoGameConsole.setAdapter(adapter);

            spVideoGameConsole = binding.spVideoGameConsole;
            System.out.println(getArguments().getSerializable("id_VideoGameConsole"));
            spVideoGameConsole.setSelection(Integer.parseInt(String.valueOf(getArguments().getSerializable("id_VideoGameConsole"))));

        });
    }

}