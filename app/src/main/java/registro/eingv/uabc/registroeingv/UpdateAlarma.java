package registro.eingv.uabc.registroeingv;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import registro.eingv.uabc.registroeingv.dbA.Alarma;
import registro.eingv.uabc.registroeingv.dbA.DaoMaster;
import registro.eingv.uabc.registroeingv.lista.ListActivity;



public class UpdateAlarma extends ActionBarActivity {


    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker alarmTimePicker;
    private static UpdateAlarma inst;
    private TextView alarmTextView;
    private EditText tituloAlarma;
    private ArrayList<String> listaAlarma;
    private String strFecha;
    private TextView aqui;
    private  int recuperar;
    private long recuperarLong;

    public static UpdateAlarma instance(){
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        inst=this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_alarma);

        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker4);
        alarmTimePicker.setIs24HourView(true);

        alarmTextView = (TextView) findViewById(R.id.alarmText4);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle4);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        tituloAlarma= (EditText) findViewById(R.id.tituloAlarma4);
        aqui= (TextView) findViewById(R.id.aquiId4);



        //inicia base de datos
        initDataBase();
        List<Alarma> reg=SinglentonDB.getInstance().getDaoSession().getAlarmaDao().loadAll();
        for(Alarma registro:reg){

            //   System.out.println(registro.getTituloAlarma());//obtiene titulos
            //  System.out.println(registro.getHoraDeAlarma());//obtiene horas guardadas
        }

    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
             recuperar=getIntent().getIntExtra("reg.get_id()",1);
recuperarLong=getIntent().getLongExtra("reg.get_id()",1);

            updateDB();
            Log.d("MyActivity", "Alarm On");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            Intent myIntent = new Intent(UpdateAlarma.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(UpdateAlarma.this, 0, myIntent, 0);
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
            miNotificacion("prendido", Toast.LENGTH_SHORT);

            aqui.setText("Sonará a las:"+alarmTimePicker.getCurrentHour()+
                    ":"+alarmTimePicker.getCurrentMinute());

        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
            Log.d("MyActivity", "Alarm Off");
            miNotificacion("apagado",Toast.LENGTH_SHORT);

        }
    }
    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }


    public void updateDB(){

        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm");

        strFecha=alarmTimePicker.getCurrentHour()+":"+alarmTimePicker.getCurrentMinute();
        Date fecha;
        fecha=null;
        try {
            fecha=dateFormat.parse(strFecha);
        }catch (ParseException e){e.printStackTrace();  System.err.println("errorrr!");}

        Alarma alarma=new Alarma();

        //insertar datos a alarma
        if (tituloAlarma.getText().length()>=5){//si titulo esta vacio
            //agregamos titulo a la alarma

            alarma.set_id(recuperar);

                alarma.setTituloAlarma(tituloAlarma.getText().toString());    //inserar nombre a mi alarma
                alarma.setHoraDeAlarma(fecha);
                SinglentonDB.getInstance().getDaoSession().getAlarmaDao().update(alarma);
                tituloAlarma.setText("");

        }else{
            miNotificacion("escribe nombre de alarma ", Toast.LENGTH_LONG);   }
    }//fin del metodo insertDB

    public void miNotificacion(String texto, int duracion){
        //se crea una notificacion
        Toast toast=Toast.makeText(this.getApplicationContext(),texto,duracion);
        //mostrar toast
        toast.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent=new Intent(this, ListActivity.class);
            startActivity(intent);
            return true;
        }
       /* if (id==R.id.modificar){
            Intent inten=new Intent(this, ListActivity.class);
            startActivity(inten);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    //Instancia de base de datos
    private static SQLiteDatabase db;

    //Clase ayudante para abrir y crear la base de datos
    SQLiteOpenHelper helper;
    private void initDataBase() {
        helper = new DaoMaster.DevOpenHelper(this, "alarma", null);
        db = helper.getWritableDatabase();
        setDb(helper.getWritableDatabase());
    }

    public static SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

}
