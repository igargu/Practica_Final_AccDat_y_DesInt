package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.entity;

import androidx.room.Embedded;

public class VideoGame_VideoGameConsole {

    @Embedded
    public VideoGame videoGame;

    @Embedded(prefix="VideoGameConsole_")
    public VideoGameConsole videoGameConsole;
}
