package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame_VideoGameConsole;

import java.util.List;

@Dao
public interface VideoGameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertVideoGame(VideoGame... videoGames);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    List<Long> insertVideoGameConsole(VideoGameConsole... videoGameConsoles);

    @Update
    int updateVideoGame(VideoGame... videoGames);

    @Update
    int updateVideoGameConsole(VideoGameConsole... videoGameConsoles);

    @Query("update VideoGame " +
            "set name = :name, genre = :genre, developer = :developer, releaseDate = :date, imageUrl = :url, idVideoGameConsole = :console " +
            "where name = :oldName")
    int updateVideoGameByName(String name, String genre, String developer, String date, String url, long console, String oldName);

    @Delete
    int deleteVideoGame(VideoGame... videoGames);

    @Delete
    int deleteVideoGameConsole(VideoGameConsole... videoGameConsoles);

    @Query("delete from VideoGame where name = :name")
    int deleteVideoGameByName(String name);

    @Query("select * from VideoGame order by name asc")
    LiveData<List<VideoGame>> getVideoGames();

    @Query("select vg.*, vgc.id, vgc.name videoGameConsole_name from VideoGame vg join VideoGameConsole vgc on vg.idVideoGameConsole = vgc.id order by name asc")
    LiveData<List<VideoGame_VideoGameConsole>> getAllVideoGames();

    @Query("select * from VideoGameConsole order by name asc")
    LiveData<List<VideoGameConsole>> getVideoGameConsoles();

    @Query("select * from VideoGame where id = :id")
    LiveData<VideoGame> getVideoGame(long id);

    @Query("select name from VideoGame")
    String getVideoGameName();

    @Query("select * from VideoGameConsole where id = :id")
    LiveData<VideoGameConsole> getVideoGameConsole(long id);

    @Query("select id from VideoGameConsole where name = :name")
    long getIdVideoGameConsole(String name);

    @Query("select name from VideoGameConsole where id = :id")
    String getVideoGameConsoleName(long id);

}
