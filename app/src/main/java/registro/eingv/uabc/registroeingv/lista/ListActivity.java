package registro.eingv.uabc.registroeingv.lista;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import registro.eingv.uabc.registroeingv.R;
import registro.eingv.uabc.registroeingv.SinglentonDB;
import registro.eingv.uabc.registroeingv.UpdateAlarma;
import registro.eingv.uabc.registroeingv.dbA.Alarma;


public class ListActivity extends Activity implements ListView.OnItemClickListener, ListView.OnItemLongClickListener {
    private ListView listView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.lista);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        context=this.getApplicationContext();

        runOnUiThread(new Runnable() {
            public void run() {
                //Crea una lista vacia de Registro

                List<Alarma> lista;
                //Obtener la lista de Registros en la BD
                lista= SinglentonDB.getInstance().getDaoSession().getAlarmaDao().loadAll();
                if(lista!=null) {
                    //Agrega el contexto y lista a la vista ListView
                    ListaAlarmaAdapter listaAdpter = new ListaAlarmaAdapter(context, lista);
                    listView.setAdapter(listaAdpter);
                }else{
                    lista=new ArrayList<Alarma>();
                    ListaAlarmaAdapter listaAdpter = new ListaAlarmaAdapter(context, lista);
                    listView.setAdapter(listaAdpter);
                }}});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        ListaAlarmaAdapter listaClavesAdapter_ =
                (ListaAlarmaAdapter) adapterView.getAdapter();
        Alarma reg = (Alarma) listaClavesAdapter_.getItem(i);


    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ListaAlarmaAdapter listaClavesAdapter_ =
                (ListaAlarmaAdapter) parent.getAdapter();
        Alarma reg = (Alarma) listaClavesAdapter_.getItem(position);

        buildAlertMessageConfiguration(reg);

        return false;


    }

    private void buildAlertMessageConfiguration(final Alarma reg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Que decea hacer?")
                .setCancelable(false)

                .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    public void onClick( final DialogInterface dialog, final int id) {

                        Intent laEdicion=new Intent(ListActivity.this, UpdateAlarma.class);
                        laEdicion.putExtra("reg.get_id()", reg.get_id());
                        startActivity(laEdicion);


                    }
                })
                .setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {

                        SinglentonDB.getInstance().getDaoSession().getAlarmaDao().deleteByKey(reg.get_id());

                        dialog.cancel();

                    }
                })
                .setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                })

        ;

        final AlertDialog alert = builder.create();
        alert.show();

    }



}
