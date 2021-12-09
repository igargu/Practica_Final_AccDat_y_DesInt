package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGame;
import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity.VideoGameConsole;

@Database(entities = {VideoGame.class, VideoGameConsole.class}, version = 1, exportSchema = false)

public abstract class VideoGameDatabase extends RoomDatabase {

    public abstract VideoGameDao getDao();

    private static volatile VideoGameDatabase INSTANCE;

    public static VideoGameDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), VideoGameDatabase.class, "VideoGame").build();
        }
        return INSTANCE;
    }
}
