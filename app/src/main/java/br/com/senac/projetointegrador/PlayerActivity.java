package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import br.com.senac.projetointegrador.util.*;
import android.widget.*;
import android.net.*;
import android.graphics.drawable.*;
import com.sachinchandil.videodownloadandplay.*;

public class PlayerActivity extends Activity {
	private VideoDownloadAndPlayService server;
	
	VideoView video;
	ImageView play;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		AndroidUtils.requestPermission(this,new String[] {
			android.Manifest.permission.READ_EXTERNAL_STORAGE,
			android.Manifest.permission.WRITE_EXTERNAL_STORAGE
		});
		play = findViewById(R.id.PLAYER_PLAY);
		video = findViewById(R.id.PLAYER_VIDEO);
		
		play.setImageResource(R.drawable.loading);

		String url = "https://player.vimeo.com/external/399913636.hd.mp4?s=ec8c3bcf6597f7f5cfe3098d5edddb5ecd28c179&profile_id=174";
		video.setVideoURI(Uri.parse(url));
		video.start();
		
		//startServer("https://player.vimeo.com/external/399913636.hd.mp4?s=ec8c3bcf6597f7f5cfe3098d5edddb5ecd28c179&profile_id=174");
	}

	@Override protected void onResume() {
		AndroidUtils.setImmersiveMode(this,true);
		super.onResume();
	}
	
	public void startServer(String url) {
		server = VideoDownloadAndPlayService.startServer(this, url, Environment.getExternalStorageDirectory().getPath() + "/cache.mp4", "127.0.0.1", new VideoDownloadAndPlayService.VideoStreamInterface() {
			@Override public void onServerStart(String p1) {
				try {
					video.setVideoURI(Uri.parse(p1));
					video.start();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void stopServer() {
		 if (server != null) {
			 server.stop();
		 }
	}

	@Override protected void onStop() {
		super.onStop();
		stopServer();
	}
	
	
}
