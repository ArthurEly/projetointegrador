package edos.widget;
import android.widget.*;
import android.content.*;
import android.util.*;
import br.com.senac.projetointegrador.*;
import android.view.*;
import android.text.*;

public class EditText extends LinearLayout {
	
	private ImageView b;
	private android.widget.EditText e;
	private int ac = 0x00000000;
	
	private void init() {
		View v = inflate(getContext(),R.layout.edittext_view,null);
		addView(v);
		
		b = findViewById(R.id.EDIT_TEXT_BUTTON);
		e = findViewById(R.id.EDIT_TEXT_EDITABLE);
		e.setTextColor(0xFF000000);
		
		setOnHoverListener(new OnHoverListener() {
			@Override public boolean onHover(View p1, MotionEvent p2) {
				boolean bool = false;
				if (p2.getAction() == p2.ACTION_HOVER_ENTER) {
					ac = 0xFF000000;
					setBorderColor(ac);
					bool = true;
				} else if (p2.getAction() == p2.ACTION_HOVER_EXIT) {
					ac = 0x00000000;
					setBorderColor(ac);
					bool = false;
				}

				return bool;
			}
		});

		setOnTouchListener(new View.OnTouchListener() {
			@Override public boolean onTouch(View p1, MotionEvent p2) {
				boolean bool = false;
				if (p2.getAction() == p2.ACTION_DOWN) {
					ac = 0xFF000000;
					setBorderColor(ac);
					bool = true;
				} else if (p2.getAction() == p2.ACTION_UP) {
					ac = 0x00000000;
					setBorderColor(ac);
					bool = false;
				}
				
				return bool;
			}
		});
	}
	
	public EditText(Context context) {
		super(context);
		init();
	}

	public EditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public EditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public void setOnButtonClickListener(View.OnClickListener listener) {
		b.setOnClickListener(listener);
	}
	
	public void setButtonImageResource(int res) {
		b.setImageResource(res);
	}

	@Override public void setBackgroundColor(int color) {
		super.setBackgroundColor(ac);
		b.setBackgroundColor(color);
		e.setBackgroundColor(color);
	}
	
	public void setBorderColor(int color) {
		super.setBackgroundColor(color);
	}
	
	public Editable getText() {
		return e.getText();
	}
	
	public void setText(CharSequence text) {
		e.setText(text);
	}
	
	public void setText(int res) {
		e.setText(res);
	}
	
	public void setInputType(int type) {
		e.setInputType(type);
	}
}
