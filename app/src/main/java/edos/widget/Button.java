package edos.widget;
import android.content.*;
import android.util.*;
import android.view.*;

public class Button extends android.widget.Button implements View.OnClickListener, View.OnTouchListener
{

	@Override
	public void onClick(View p1)
	{
		// TODO: Implement this method
	}

	@Override
	public boolean onTouch(View p1, MotionEvent p2)
	{
		// TODO: Implement this method
		return false;
	}

	private void init() {
		setBackgroundColor(0xFFCC0000);
		setPadding(20,20,20,20);
		
		setOnHoverListener(new OnHoverListener() {
				@Override public boolean onHover(View p1, MotionEvent p2) {
					boolean bool = false;
					if (p2.getAction() == p2.ACTION_HOVER_ENTER) {
						int ac = 0xFFFFFFFF;
						setBackgroundColor(ac);
						bool = true;
					} else if (p2.getAction() == p2.ACTION_HOVER_EXIT) {
						int ac = 0xFFCC0000;
						setBackgroundColor(ac);
						bool = false;
					}

					return bool;
				}
			});

		setOnTouchListener(new View.OnTouchListener() {
				@Override public boolean onTouch(View p1, MotionEvent p2) {
					boolean bool = false;
					if (p2.getAction() == p2.ACTION_DOWN) {
						int ac = 0xFFFFFFFF;
						setBackgroundColor(ac);
						bool = true;
					} else if (p2.getAction() == p2.ACTION_UP) {
						int ac = 0xFFCC0000;
						setBackgroundColor(ac);
						bool = false;
					}

					return bool;
				}
			});
	}
	
	public Button(Context context) {
		super(context);
		init();
	}

	public Button(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Button(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
}
