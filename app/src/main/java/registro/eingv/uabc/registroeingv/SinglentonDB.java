package registro.eingv.uabc.registroeingv;

import android.database.sqlite.SQLiteDatabase;

import registro.eingv.uabc.registroeingv.dbA.DaoMaster;
import registro.eingv.uabc.registroeingv.dbA.DaoSession;

/**
 * Created by Mifc on 3/27/2015.
 */
public class SinglentonDB {
    private static SinglentonDB ourInstance = new SinglentonDB();

    public static SinglentonDB getInstance() {
        return ourInstance;
    }

    private SinglentonDB() {
        SQLiteDatabase db=  MainActivity.getDb();

        openDatabase(db);
    }

    //Instancia de base de datos
    private SQLiteDatabase db;
    //Instancia de DaoMaster
    private DaoMaster daoMaster;
    //Instancia de Sesion
    private DaoSession daoSession;

    public void openDatabase(SQLiteDatabase db){
        this.db=db;
        //Agrega la base de datos al Master
        daoMaster = new DaoMaster(db);
        setDaoSession(daoMaster.newSession());
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public void setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

}
