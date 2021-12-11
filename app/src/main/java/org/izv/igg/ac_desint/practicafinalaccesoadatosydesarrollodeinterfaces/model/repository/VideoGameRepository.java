package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Query;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame_VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.room.VideoGameDao;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.room.VideoGameDatabase;

import java.util.List;

public class VideoGameRepository {

    private static final String INIT = "init";

    private VideoGameDao dao;

    LiveData<List<VideoGame_VideoGameConsole>> allVideoGames;
    LiveData<List<VideoGame>> liveVideoGames;
    LiveData<List<VideoGameConsole>> liveVideoGamesConsoles;

    LiveData<VideoGame> liveVideoGame;
    LiveData<VideoGameConsole> liveVideoGameConsole;

    MutableLiveData<Long> liveInsertResult;
    MutableLiveData<List<Long>> liveInsertResults;

    SharedPreferences preferences;

    public VideoGameRepository(Context context) {
        VideoGameDatabase db = VideoGameDatabase.getDatabase(context);
        dao = db.getDao();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        liveInsertResult = new MutableLiveData<>();
        liveInsertResults = new MutableLiveData<>();

        if(!getInit()) {
            videoGameConsolesPreload();
            setInit();
        }
    }

    public void insertVideoGame(VideoGame videoGame, VideoGameConsole videoGameConsole) {
        Runnable r = () -> {
            videoGame.idVideoGameConsole = insertVideoGameConsoleGetId(videoGameConsole);
            if (videoGame.idVideoGameConsole > 0) {
                dao.insertVideoGame(videoGame);
            }
        };
        new Thread(r).start();
    }

    public MutableLiveData<Long> getInsertResult() {
        return liveInsertResult;
    }

    public MutableLiveData<List<Long>> getInsertResults() {
        return liveInsertResults;
    }

    private long insertVideoGameConsoleGetId(VideoGameConsole videoGameConsole) {
        List<Long> ids = dao.insertVideoGameConsole(videoGameConsole);
        if (ids.get(0) < 1) {
            return dao.getIdVideoGameConsole(videoGameConsole.name);
        } else {
            return ids.get(0);
        }
    }

    public void insertVideoGame(VideoGame... videoGames) {
        Runnable r = () -> {
            List<Long> resultList = dao.insertVideoGame(videoGames);
            liveInsertResult.postValue(resultList.get(0));
            liveInsertResults.postValue(resultList);
        };
        new Thread(r).start();
    }

    public void insertVideoGameConsole(VideoGameConsole... videoGameConsoles) {
        Runnable r = () -> {
            dao.insertVideoGameConsole(videoGameConsoles);
        };
        new Thread(r).start();
    }

    public void updateVideoGame(VideoGame... videoGames) {
        Runnable r = () -> {
            dao.updateVideoGame(videoGames);
        };
        new Thread(r).start();
    }

    public void updateVideoGameConsole(VideoGameConsole... videoGameConsoles) {
        Runnable r = () -> {
            dao.updateVideoGameConsole(videoGameConsoles);
        };
        new Thread(r).start();
    }

    public void updateVideoGameByName(String name, String genre, String developer, String date, String url, long console, String oldName) {
        Runnable r = () -> {
            dao.updateVideoGameByName(name, genre, developer, date, url, console, oldName);
        };
        new Thread(r).start();
    }

    public void deleteVideoGame(VideoGame... videoGames) {
        Runnable r = () -> {
            dao.deleteVideoGame(videoGames);
        };
        new Thread(r).start();
    }

    public void deleteVideoGameByName(String name) {
        Runnable r = () -> {
            dao.deleteVideoGameByName(name);
        };
        new Thread(r).start();
    }

    public void deleteVideoGameConsole(VideoGameConsole... videoGameConsole) {
        Runnable r = () -> {
            dao.deleteVideoGameConsole(videoGameConsole);
        };
        new Thread(r).start();
    }

    public LiveData<List<VideoGame>> getVideoGames() {
        if(liveVideoGames == null) {
            liveVideoGames = dao.getVideoGames();
        }
        return liveVideoGames;
    }

    public LiveData<List<VideoGameConsole>> getVideoGameConsoles() {
        if(liveVideoGamesConsoles == null) {
            liveVideoGamesConsoles = dao.getVideoGameConsoles();
        }
        return liveVideoGamesConsoles;
    }

    public LiveData<VideoGame> getVideoGame(long id) {
        if(liveVideoGame == null) {
            liveVideoGame = dao.getVideoGame(id);
        }
        return liveVideoGame;
    }

    @Query("select name from VideoGame")
    public String getVideoGameName() {
        return dao.getVideoGameName();
    }

    public LiveData<VideoGameConsole> getVideoGameConsole(long id) {
        if(liveVideoGameConsole == null) {
            liveVideoGameConsole = dao.getVideoGameConsole(id);
        }
        return liveVideoGameConsole;
    }

    @Query("select name from VideoGameConsole where id = :id")
    public String getVideoGameConsoleName(long id) {
        return dao.getVideoGameConsoleName(id);
    }

    public LiveData<List<VideoGame_VideoGameConsole>> getAllVideoGames() {
        if(allVideoGames == null) {
            allVideoGames = dao.getAllVideoGames();
        }
        return allVideoGames;
    }

    public void videoGameConsolesPreload() {
        String[] videoGameConsoleNames = new String[] {"Atari 2600", "Atari Jaguar", "Atari Lynx",
                                                       "Bandai Apple Pippin", "Bandai WonderSwan", "Bandai WonderSwan Color",
                                                       "Microsoft Xbox", "Microsoft Xbox 360", "Microsoft Xbox One", "Microsoft Xbox Series X",
                                                       "NEC PC-FX", "NEC TurboExpress", "NEC TurboGrafx-16",
                                                       "Nintendo 3DS", "Nintendo 64", "Nintendo DS", "Nintendo Game Boy", "Nintendo Game Boy Advance", "Nintendo Game Boy Color", "Nintendo GameCube", "Nintendo NES", "Nintendo Super NES", "Nintendo Switch", "Nintendo Wii", "Nintendo Wii U",
                                                       "SNK Neo Geo AES", "SNK Neo Geo CD", "SNK Neo Geo Pocket",
                                                       "Sega Dreamcast", "Sega Game Gear", "Sega Genesis", "Sega Master System", "Sega Nomad", "Sega Saturn",
                                                       "Sony PlayStation", "Sony PlayStation 2", "Sony PlayStation 3", "Sony PlayStation 4", "Sony PlayStation 5", "Sony PlayStation Portable", "Sony PlayStation Vita"};

        VideoGameConsole[] videoGameConsoles = new VideoGameConsole[videoGameConsoleNames.length];
        VideoGameConsole videoGameConsole;
        int cont = 0;
        for (String s : videoGameConsoleNames) {
            videoGameConsole = new VideoGameConsole();
            videoGameConsole.name = s;
            videoGameConsoles[cont] = videoGameConsole;
            cont++;
        }
        insertVideoGameConsole(videoGameConsoles);
    }

    public void setInit() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(INIT, true);
        editor.commit();
    }

    public boolean getInit() {
        return preferences.getBoolean(INIT, false);
    }
}
