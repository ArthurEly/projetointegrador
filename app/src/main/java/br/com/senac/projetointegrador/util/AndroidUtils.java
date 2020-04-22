package br.com.senac.projetointegrador.util;

import android.view.*;
import android.app.*;
import android.transition.*;
import android.content.*;
import android.view.animation.*;

public class AndroidUtils {
    public static void setImmersiveMode(Activity act, boolean bool) {
        if (bool = true) {
            act.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        } else {
            act.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
	
	public static void animate(final Activity startact,int transition, final Class finalactiviy, int time) {
		Animation a = AnimationUtils.loadAnimation(startact,transition);
		//Transition t = a.ge;
		a.setDuration(time);
		a.setAnimationListener(new Animation.AnimationListener() {

				@Override
				public void onAnimationStart(Animation p1)
				{
				// TODO: Implement this method
				}

				@Override
				public void onAnimationEnd(Animation p1)
				{

					Intent i = new Intent(startact,finalactiviy);
					startact.startActivity(i);
				}                                    

				@Override
				public void onAnimationRepeat(Animation p1)
				{
				// TODO: Implement this method
				}
			});
		a.start();
	}
}
