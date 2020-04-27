package br.com.senac.projetointegrador.view;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;
import br.com.senac.projetointegrador.*;

public class AdaptadorUSB extends ArrayAdapter<USB> {
	ArrayList<USB> usb;

    public AdaptadorUSB(Context context, int textViewResourceId, ArrayList<USB> items) {
        super(context, textViewResourceId, items);
        usb = items;
    }

	@Override public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.view_episodio, null);
		}
		
		
		USB  i = usb.get(position);

		if (i != null) {
			TextView nome = v.findViewById(R.id.episodio_nome);
			TextView sino = v.findViewById(R.id.episodio_sinopse);
			ImageView img = v.findViewById(R.id.episodio_thumb);
			
			if (nome != null) {
				nome.setText(i.getNome());
			}
			
			if (sino != null) {
				sino.setText(i.getSinopse());
			}
			
			if (img != null) {
				img.setImageResource(i.getImage());
			}
		}
		
		return v;
	}
	
}
