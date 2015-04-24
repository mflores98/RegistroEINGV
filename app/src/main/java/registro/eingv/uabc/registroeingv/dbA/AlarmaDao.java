package registro.eingv.uabc.registroeingv.dbA;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table ALARMA.
*/
public class AlarmaDao extends AbstractDao<Alarma, Integer> {

    public static final String TABLENAME = "ALARMA";

    /**
     * Properties of entity alarma.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property _id = new Property(0, Integer.class, "_id", true, "_ID");
        public final static Property TituloAlarma = new Property(1, String.class, "tituloAlarma", false, "TITULO_ALARMA");
        public final static Property HoraDeAlarma = new Property(2, java.util.Date.class, "horaDeAlarma", false, "HORA_DE_ALARMA");
    };


    public AlarmaDao(DaoConfig config) {
        super(config);
    }
    
    public AlarmaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'ALARMA' (" + //
                "'_ID' INTEGER PRIMARY KEY ," + // 0: _id
                "'TITULO_ALARMA' TEXT," + // 1: tituloAlarma
                "'HORA_DE_ALARMA' INTEGER);"); // 2: horaDeAlarma
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'ALARMA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Alarma entity) {
        stmt.clearBindings();
 
        Integer _id = entity.get_id();
        if (_id != null) {
            stmt.bindLong(1, _id);
        }
 
        String tituloAlarma = entity.getTituloAlarma();
        if (tituloAlarma != null) {
            stmt.bindString(2, tituloAlarma);
        }
 
        java.util.Date horaDeAlarma = entity.getHoraDeAlarma();
        if (horaDeAlarma != null) {
            stmt.bindLong(3, horaDeAlarma.getTime());
        }
    }

    /** @inheritdoc */
    @Override
    public Integer readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Alarma readEntity(Cursor cursor, int offset) {
        Alarma entity = new Alarma( //
            cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0), // _id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // tituloAlarma
            cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)) // horaDeAlarma
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Alarma entity, int offset) {
        entity.set_id(cursor.isNull(offset + 0) ? null : cursor.getInt(offset + 0));
        entity.setTituloAlarma(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setHoraDeAlarma(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
     }
    
    /** @inheritdoc */
    @Override
    protected Integer updateKeyAfterInsert(Alarma entity, long rowId) {
        return entity.get_id();
    }
    
    /** @inheritdoc */
    @Override
    public Integer getKey(Alarma entity) {
        if(entity != null) {
            return entity.get_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }

}
