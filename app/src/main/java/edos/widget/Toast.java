package edos.widget;
import android.content.*;
import android.app.*;
import br.com.senac.projetointegrador.*;
import android.view.*;

public class Toast {
	private android.widget.Toast t;
	public Toast(Activity ctx, int xml, int tempo) {
		t = new android.widget.Toast(ctx);
		View layout = ctx.getLayoutInflater().inflate(xml,null,true);
		t.setDuration(tempo);
		t.setView(layout);
	}
	
	public void show() {
		t.show();
	}
	
	public void setGravity(int gravity, int x, int y) {
		t.setGravity(gravity,x,y);
	}
}
