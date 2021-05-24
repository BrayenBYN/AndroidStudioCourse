package umn.ac.id.week09_29117;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class MahasiswaRepository {
    private MahasiswaDAO daoMahasiwa;
    private LiveData<List<Mahasiswa>> daftarMahasiswa;

    MahasiswaRepository(Application app){
        MahasiswaRoomDatabase db = MahasiswaRoomDatabase.getDatabase(app);
        daoMahasiwa = db.daoMahasiswa();
        daftarMahasiswa = daoMahasiwa.getAllMahasiswa();
    }

    LiveData<List<Mahasiswa>> getDaftarMahasiswa(){
        return this.daftarMahasiswa;
    }

    public void insert(Mahasiswa mhs){
        new insertAsyncTask(daoMahasiwa).execute(mhs);
    }

    public void deleteAll(){
        new deleteAllAsyncTask(daoMahasiwa).execute();
    }

    public void delete(Mahasiswa mhs){
        new deleteAsyncTask(daoMahasiwa).execute(mhs);
    }

    public void update(Mahasiswa mhs){
        new updateAsyncTask(daoMahasiwa).execute(mhs);
    }

    private static class insertAsyncTask extends AsyncTask<Mahasiswa, Void, Void>{
        private MahasiswaDAO asyncDaoMahasiswa;
        insertAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }
        @Override
        protected Void doInBackground(final Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.insert(mahasiswas[0]);
            return null;
        }
    }

    private static class deleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAllAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDaoMahasiswa.deleteAll();
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Mahasiswa, Void, Void>{
        private MahasiswaDAO asyncDaoMahasiswa;
        deleteAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.delete(mahasiswas[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Mahasiswa, Void, Void>{
        private MahasiswaDAO asyncDaoMahasiswa;
        updateAsyncTask(MahasiswaDAO dao){
            this.asyncDaoMahasiswa = dao;
        }

        @Override
        protected Void doInBackground(Mahasiswa... mahasiswas) {
            asyncDaoMahasiswa.update(mahasiswas[0]);
            return null;
        }
    }
}