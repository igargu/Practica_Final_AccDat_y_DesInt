package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame_VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.repository.VideoGameRepository;

import java.util.List;

public class VideoGameViewModel extends AndroidViewModel {

    private VideoGameRepository repository;

    public VideoGameViewModel(@NonNull Application application) {
        super(application);
        repository = new VideoGameRepository(application);
    }

    public void insertVideoGame(VideoGame... videoGames) {
        repository.insertVideoGame(videoGames);
    }

    public void updateVideoGame(VideoGame... videoGames) {
        repository.updateVideoGame(videoGames);
    }

    public void updateVideoGameByName(String name, String genre, String developer, String date, String url, long console, String oldName) {
        repository.updateVideoGameByName(name, genre, developer, date, url, console, oldName);
    }

    public void deleteVideoGame(VideoGame... videoGames) {
        repository.deleteVideoGame(videoGames);
    }

    public void deleteVideoGameByName(String name) {
        repository.deleteVideoGameByName(name);
    }

    public LiveData<List<VideoGame>> getVideoGames() {
        return repository.getVideoGames();
    }

    public LiveData<VideoGame> getVideoGame(long id) {
        return repository.getVideoGame(id);
    }

    @Query("select name from VideoGameConsole where id = :id")
    public String getVideoGameConsoleName(long id) {
        return repository.getVideoGameConsoleName(id);
    }

    public void insertVideoGame(VideoGame videoGame, VideoGameConsole videoGameConsole) {
        repository.insertVideoGame(videoGame, videoGameConsole);
    }

    public LiveData<List<VideoGame_VideoGameConsole>> getAllVideoGames() {
        return repository.getAllVideoGames();
    }

    public MutableLiveData<Long> getInsertResult() {
        return repository.getInsertResult();
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return repository.getInsertResults();
    }
}
