//package br.com.senac.projetointegrador.util;
//import android.app.Service;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//import android.util.Log;
//
//import br.com.senac.projetointegrador.R;
//
//public class SoundService extends Service {
//    MediaPlayer player;
//    String arroz = "a";
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.i("musica","eeeeeeeeeeeeeeee musica boa");
//        return null;
//    }
//
//    public void onCreate() {
//        player = MediaPlayer.create(this, R.raw.netflixintro); //select music file
//        player.setLooping(false); //set looping
//        player.setVolume(100,100);
//    }
//
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        player.start();
//        Log.i("musica","eeeeeeeeeeeeeeee musica boa");
//        return Service.START_NOT_STICKY;
//
//    }
//
//    public void onDestroy() {
//        player.stop();
//        player.release();
//        stopSelf();
//        super.onDestroy();
//    }
//
//}