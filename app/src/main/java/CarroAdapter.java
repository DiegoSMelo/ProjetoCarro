import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diegomelo.projetocarro.R;
import com.example.diegomelo.projetocarro.model.Carro;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Diego Melo on 02/11/2015.
 */
public class CarroAdapter extends ArrayAdapter<Carro> {

    public CarroAdapter(Context context, List<Carro> carros) {
        super(context, 0, carros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carro carro = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_carro, null);

            viewHolder = new ViewHolder();
            viewHolder.imgCarro = (ImageView)convertView.findViewById(R.id.imgCarro);
            viewHolder.txtModelo = (TextView)convertView.findViewById(R.id.txtModelo);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Picasso.with(getContext()).load(carro.imagem).into(viewHolder.imgCarro);
        viewHolder.txtModelo.setText(carro.modelo);

        return convertView;
    }

    class ViewHolder{
        ImageView imgCarro;
        TextView txtModelo;

    }
}
