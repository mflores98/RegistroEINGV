package registro.eingv.uabc.registroeingv.lista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import registro.eingv.uabc.registroeingv.R;
import registro.eingv.uabc.registroeingv.dbA.Alarma;


/**
 * Created by Mifc on 3/20/2015.
 */
public class ListaAlarmaAdapter extends BaseAdapter{
   private Context context;
    private List<Alarma> listaAlarma;

    public ListaAlarmaAdapter(Context context, List<Alarma> listaAlarma){
        super();
        this.context=context;
        this.listaAlarma=listaAlarma;
    }
    @Override
    public int getCount() {
        return listaAlarma.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAlarma.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.list_vew_each_item, null);
    if (!this.listaAlarma.isEmpty()) {

    TextView textViewClave = (TextView) convertView.findViewById(R.id.textViewClave);
    TextView textViewHorass = (TextView) convertView.findViewById(R.id.textViewTitulos);

    //textViewClave.setText(this.listaAlarma.get(position).getTituloAlarma());

    textViewClave.setText(this.listaAlarma.get(position).get_id() + ". " +
            " " + this.listaAlarma.get(position).getTituloAlarma());
    textViewHorass.setText(this.listaAlarma.get(position).getHoraDeAlarma().toString());

    }
    return convertView;
    }
}
