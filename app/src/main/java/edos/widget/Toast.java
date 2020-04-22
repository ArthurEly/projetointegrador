package edos.widget;
import android.content.*;
import android.app.*;
import br.com.senac.projetointegrador.*;
import android.view.*;

public class Toast extends android.widget.Toast {
	public Toast(Activity ctx, int xml, int tempo) {
		super((Context) ctx);
		View layout = ctx.getLayoutInflater().inflate(xml,null,true);
		setDuration(tempo);
		setView(layout);
	}
}
