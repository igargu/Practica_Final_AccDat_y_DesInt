package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "VideoGame",
        indices = {@Index(value = "name", unique = true)},
        foreignKeys = {@ForeignKey(entity = VideoGameConsole.class, parentColumns = "id", childColumns = "idVideoGameConsole", onDelete = ForeignKey.CASCADE)})

public class VideoGame {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;

    @NonNull
    @ColumnInfo(name = "genre")
    public String genre;

    @NonNull
    @ColumnInfo(name = "developer")
    public String developer;

    @NonNull
    @ColumnInfo(name = "releaseDate")
    public String releaseDate;

    @NonNull
    @ColumnInfo(name = "imageUrl")
    public String imageUrl;

    @NonNull
    @ColumnInfo(name = "idVideoGameConsole")
    public long idVideoGameConsole;

    public boolean isValid() {
        return !(name.isEmpty() || developer.isEmpty() || idVideoGameConsole <= 0 ||
                genre.isEmpty() || releaseDate.isEmpty() || Integer.parseInt(releaseDate) > LocalDate.now().getYear() ||
                Integer.parseInt(releaseDate) < 1977 || imageUrl.isEmpty());
    }
}
