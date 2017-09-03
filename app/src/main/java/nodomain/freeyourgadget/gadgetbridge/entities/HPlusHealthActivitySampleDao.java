package nodomain.freeyourgadget.gadgetbridge.entities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "HPLUS_HEALTH_ACTIVITY_SAMPLE".
*/
public class HPlusHealthActivitySampleDao extends AbstractDao<HPlusHealthActivitySample, Void> {

    public static final String TABLENAME = "HPLUS_HEALTH_ACTIVITY_SAMPLE";

    /**
     * Properties of entity HPlusHealthActivitySample.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Timestamp = new Property(0, int.class, "timestamp", true, "TIMESTAMP");
        public final static Property DeviceId = new Property(1, long.class, "deviceId", true, "DEVICE_ID");
        public final static Property UserId = new Property(2, long.class, "userId", false, "USER_ID");
        public final static Property RawHPlusHealthData = new Property(3, byte[].class, "rawHPlusHealthData", false, "RAW_HPLUS_HEALTH_DATA");
        public final static Property RawKind = new Property(4, int.class, "rawKind", true, "RAW_KIND");
        public final static Property RawIntensity = new Property(5, int.class, "rawIntensity", false, "RAW_INTENSITY");
        public final static Property Steps = new Property(6, int.class, "steps", false, "STEPS");
        public final static Property HeartRate = new Property(7, int.class, "heartRate", false, "HEART_RATE");
        public final static Property Distance = new Property(8, Integer.class, "distance", false, "DISTANCE");
        public final static Property Calories = new Property(9, Integer.class, "calories", false, "CALORIES");
    };

    private DaoSession daoSession;


    public HPlusHealthActivitySampleDao(DaoConfig config) {
        super(config);
    }
    
    public HPlusHealthActivitySampleDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"HPLUS_HEALTH_ACTIVITY_SAMPLE\" (" + //
                "\"TIMESTAMP\" INTEGER  NOT NULL ," + // 0: timestamp
                "\"DEVICE_ID\" INTEGER  NOT NULL ," + // 1: deviceId
                "\"USER_ID\" INTEGER NOT NULL ," + // 2: userId
                "\"RAW_HPLUS_HEALTH_DATA\" BLOB," + // 3: rawHPlusHealthData
                "\"RAW_KIND\" INTEGER  NOT NULL ," + // 4: rawKind
                "\"RAW_INTENSITY\" INTEGER NOT NULL ," + // 5: rawIntensity
                "\"STEPS\" INTEGER NOT NULL ," + // 6: steps
                "\"HEART_RATE\" INTEGER NOT NULL ," + // 7: heartRate
                "\"DISTANCE\" INTEGER," + // 8: distance
                "\"CALORIES\" INTEGER," + // 9: calories
                "PRIMARY KEY (" +
                "\"TIMESTAMP\" ," +
                "\"DEVICE_ID\" ," +
                "\"RAW_KIND\" ) ON CONFLICT REPLACE)" + ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) ? " WITHOUT ROWID;" : ";")
        );
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"HPLUS_HEALTH_ACTIVITY_SAMPLE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, HPlusHealthActivitySample entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getTimestamp());
        stmt.bindLong(2, entity.getDeviceId());
        stmt.bindLong(3, entity.getUserId());
 
        byte[] rawHPlusHealthData = entity.getRawHPlusHealthData();
        if (rawHPlusHealthData != null) {
            stmt.bindBlob(4, rawHPlusHealthData);
        }
        stmt.bindLong(5, entity.getRawKind());
        stmt.bindLong(6, entity.getRawIntensity());
        stmt.bindLong(7, entity.getSteps());
        stmt.bindLong(8, entity.getHeartRate());
 
        Integer distance = entity.getDistance();
        if (distance != null) {
            stmt.bindLong(9, distance);
        }
 
        Integer calories = entity.getCalories();
        if (calories != null) {
            stmt.bindLong(10, calories);
        }
    }

    @Override
    protected void attachEntity(HPlusHealthActivitySample entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public HPlusHealthActivitySample readEntity(Cursor cursor, int offset) {
        HPlusHealthActivitySample entity = new HPlusHealthActivitySample( //
            cursor.getInt(offset + 0), // timestamp
            cursor.getLong(offset + 1), // deviceId
            cursor.getLong(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getBlob(offset + 3), // rawHPlusHealthData
            cursor.getInt(offset + 4), // rawKind
            cursor.getInt(offset + 5), // rawIntensity
            cursor.getInt(offset + 6), // steps
            cursor.getInt(offset + 7), // heartRate
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // distance
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9) // calories
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, HPlusHealthActivitySample entity, int offset) {
        entity.setTimestamp(cursor.getInt(offset + 0));
        entity.setDeviceId(cursor.getLong(offset + 1));
        entity.setUserId(cursor.getLong(offset + 2));
        entity.setRawHPlusHealthData(cursor.isNull(offset + 3) ? null : cursor.getBlob(offset + 3));
        entity.setRawKind(cursor.getInt(offset + 4));
        entity.setRawIntensity(cursor.getInt(offset + 5));
        entity.setSteps(cursor.getInt(offset + 6));
        entity.setHeartRate(cursor.getInt(offset + 7));
        entity.setDistance(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setCalories(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(HPlusHealthActivitySample entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(HPlusHealthActivitySample entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDeviceDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getUserDao().getAllColumns());
            builder.append(" FROM HPLUS_HEALTH_ACTIVITY_SAMPLE T");
            builder.append(" LEFT JOIN DEVICE T0 ON T.\"DEVICE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN USER T1 ON T.\"USER_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected HPlusHealthActivitySample loadCurrentDeep(Cursor cursor, boolean lock) {
        HPlusHealthActivitySample entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Device device = loadCurrentOther(daoSession.getDeviceDao(), cursor, offset);
         if(device != null) {
            entity.setDevice(device);
        }
        offset += daoSession.getDeviceDao().getAllColumns().length;

        User user = loadCurrentOther(daoSession.getUserDao(), cursor, offset);
         if(user != null) {
            entity.setUser(user);
        }

        return entity;    
    }

    public HPlusHealthActivitySample loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<HPlusHealthActivitySample> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<HPlusHealthActivitySample> list = new ArrayList<HPlusHealthActivitySample>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<HPlusHealthActivitySample> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<HPlusHealthActivitySample> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
