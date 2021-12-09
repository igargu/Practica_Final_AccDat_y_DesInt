package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.repository.VideoGameRepository;

import java.util.List;

public class VideoGameConsoleViewModel extends AndroidViewModel {

    private VideoGameRepository repository;

    public VideoGameConsoleViewModel(@NonNull Application application) {
        super(application);
        repository = new VideoGameRepository(application);
    }

    public void insertVideoGameConsole(VideoGameConsole... videoGameConsoles) {
        repository.insertVideoGameConsole(videoGameConsoles);
    }

    public void updateVideoGameConsole(VideoGameConsole... videoGameConsoles) {
        repository.updateVideoGameConsole(videoGameConsoles);
    }

    public void deleteVideoGameConsole(VideoGameConsole... videoGameConsoles) {
        repository.deleteVideoGameConsole(videoGameConsoles);
    }

    public LiveData<List<VideoGameConsole>> getVideoGameConsoles() {
        return repository.getVideoGameConsoles();
    }

    public LiveData<VideoGameConsole> getVideoGameConsole(long id) {
        return repository.getVideoGameConsole(id);
    }
}
