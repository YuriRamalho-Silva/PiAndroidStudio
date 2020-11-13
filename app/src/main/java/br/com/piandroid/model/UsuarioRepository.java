package br.com.piandroid.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

public class UsuarioRepository {
    private UsuarioDao usuarioDao;
    private LiveData<Usuario> usuario;
    UsuarioRepository(Application application) {
        AndroidRoomDatabase db = AndroidRoomDatabase.getDatabase(application);
        usuarioDao = db.usuarioDao();
        usuario = usuarioDao.getUsuario();
    }
    LiveData<Usuario> getUsuario() {
        return usuario;
    }
    void insert(Usuario usuario) {
        AndroidRoomDatabase.databaseWriteExecutor.execute(() -> {
            usuarioDao.insert(usuario);
        });
    }
}
