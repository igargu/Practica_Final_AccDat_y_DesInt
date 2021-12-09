package org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import org.izv.igg.ac_desint.practicafinalaccesoadatosydesarrollodeinterfaces.model.repository.VideoGameRepository;

public class CommonViewModel extends ViewModel {

    private Context context;
    private VideoGameRepository repository;

    public CommonViewModel() {
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
