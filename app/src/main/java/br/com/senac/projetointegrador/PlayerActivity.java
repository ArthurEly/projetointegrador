package br.com.senac.projetointegrador;
import android.app.*;
import android.os.*;
import br.com.senac.projetointegrador.util.*;

import android.view.View;
import android.widget.*;
import android.net.*;
import android.graphics.drawable.*;
import com.sachinchandil.videodownloadandplay.*;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerActivity extends Activity {
	private VideoDownloadAndPlayService server;
	boolean pause = false;
	int porcentagem = 0;

	int tempoSecs = 00;
	int tempoMins = 00;
	int tempoHoras = 0;
	int tempoInativo = 0;
	int esconderTelaToque = 0;
	//em ultimo caso (filmes) teremos horas
	
	VideoView video;
	ImageView play, voltar;
	SeekBar slider;
	LinearLayout linearComandos, linearSeekBar, linearSettings;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		AndroidUtils.requestPermission(this,new String[] {
			android.Manifest.permission.READ_EXTERNAL_STORAGE,
			android.Manifest.permission.WRITE_EXTERNAL_STORAGE
		});
		play = findViewById(R.id.PLAYER_PLAY);
		video = findViewById(R.id.PLAYER_VIDEO);
		slider = findViewById(R.id.PLAYER_TRACK);
		voltar = findViewById(R.id.PLAYER_RETURN);
		linearComandos = findViewById(R.id.linearComandos);
		linearSeekBar = findViewById(R.id.linearSeekBar);
		linearSettings = findViewById(R.id.linearSettings);

		
		play.setImageResource(R.drawable.ic_pause_white);

		//for (int naoUsado; tempoSecs < 60; tempoSecs++)


		play.setOnClickListener(new ImageView.OnClickListener() {
			@Override public void onClick(View image) {
				if (!pause) {
					((ImageView) image).setImageResource(R.drawable.ic_play_white);
					video.pause();
					pause = true;
				} else {
					((ImageView) image).setImageResource(R.drawable.ic_pause_white);
					video.start();
					pause = false;
				}
			}
		});

        final Handler temporizador = new Handler();

        temporizador.postDelayed(new TimerTask() {
            @Override public void run() {
                tempoInativo++;
                if (pause == false) {
                    tempoSecs++;
                    if (tempoSecs == 60) {
                        tempoMins += 1;
                        tempoSecs = 00;
                    }
                    if (tempoMins == 60) {
                        tempoHoras += 1;
                        tempoMins = 00;
                    }
                    if (tempoInativo > 5) {
                        escondeTudo();
                    }

                    //só de teste
                    slider.setLeft(tempoSecs);
                    System.out.println("Tempo:" + tempoHoras + " : " + tempoMins + " :" + tempoSecs);
                }
                else {
                }

                temporizador.postDelayed(this,1000);
                this.cancel();
            }
        },1000);

		slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				porcentagem = seekBar.getLeft() / 100; // porcentagem
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				//antes de setarmos isso devemos criar um contador de tempo

			}
		});

		String url = getIntent().getStringExtra("url");
		video.setVideoURI(Uri.parse(url));
		video.start();
		
		//startServer("https://player.vimeo.com/external/399913636.hd.mp4?s=ec8c3bcf6597f7f5cfe3098d5edddb5ecd28c179&profile_id=174");
	}

	@Override protected void onResume() {
		AndroidUtils.setImmersiveMode(this,true);
		super.onResume();
	}

	public void escondeTudo() {
        play.setVisibility(View.INVISIBLE);
        voltar.setVisibility(View.INVISIBLE);
        linearSettings.setVisibility(View.INVISIBLE);
        linearComandos.setVisibility(View.INVISIBLE);
        linearSeekBar.setVisibility(View.INVISIBLE);
    }

    public void mostraTudo() {
	    play.setVisibility(View.VISIBLE);
	    voltar.setVisibility(View.VISIBLE);
	    linearSettings.setVisibility(View.VISIBLE);
	    linearComandos.setVisibility(View.VISIBLE);
	    linearSeekBar.setVisibility(View.VISIBLE);
	    tempoInativo = 0;
    }
	
	public void startServer(String url) {
		server = VideoDownloadAndPlayService.startServer(this, url, Environment.getExternalStorageDirectory().getPath() + "/cache.mp4", "127.0.0.1", new VideoDownloadAndPlayService.VideoStreamInterface() {
			@Override public void onServerStart(String p1) {
				try {
					video.setVideoURI(Uri.parse(p1));
					video.start();
					play.setImageResource(R.drawable.ic_pause_white);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        video.pause();
    }

    public void esconderTelaToque(View view){
        mostraTudo();
    }
}
