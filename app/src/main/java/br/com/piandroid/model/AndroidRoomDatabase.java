package br.com.piandroid.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class/*adicionar as demais entidades aqui, separado por virgula*/}, version = 1, exportSchema = false)
public abstract class AndroidRoomDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
    private static volatile AndroidRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static AndroidRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AndroidRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AndroidRoomDatabase.class, "android_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
