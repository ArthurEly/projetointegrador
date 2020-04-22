package edos.app;
import android.app.*;
import android.view.*;
import android.os.*;
import br.com.senac.projetointegrador.*;
import android.widget.*;

public class Dialog extends android.app.Dialog {
	private int d;
	private LinearLayout content;
	private edos.widget.Button c,o;
	public Dialog(Activity a, int layout) {
		super(a);
		d = layout;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_view);

		content = findViewById(R.id.DIALOGVIEW_CONTENT);
		c = findViewById(R.id.DIALOGVIEW_CANCEL);
		o = findViewById(R.id.DIALOGVIEW_OK);
		View rc = getLayoutInflater().inflate(d,null,true);
		
		content.addView(rc);
	}

	public edos.widget.Button getCancelButton() {
		return c;
	}
	
	public edos.widget.Button getOkButton() {
		return o;
	}
}
