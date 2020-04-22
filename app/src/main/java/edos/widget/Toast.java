package edos.widget;
import android.content.*;
import android.app.*;
import br.com.senac.projetointegrador.*;
import android.view.*;
import android.widget.*;

public class Toast extends android.widget.Toast {
	public Toast(Activity ctx, int xml, int tempo) {
		super(ctx);
		setDuration(tempo);
		View layout = ctx.getLayoutInflater().inflate(R.layout.toast_view,null,true);
		LinearLayout l = layout.findViewById(R.id.TOASTVIEW_CONTENT);
		setView(layout);
		View xxml = ctx.getLayoutInflater().inflate(xml,null,true);
		l.addView(xxml);
	}
}
