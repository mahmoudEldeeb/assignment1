package uk.ac.shef.oak.com4510.model.datebase;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class MyDatabase_Impl extends MyDatabase {
  private volatile VisitDao _visitDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `visit` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT, `desscription` TEXT, `date` TEXT, `longitude` REAL NOT NULL, `latitude` REAL NOT NULL, `temp` REAL NOT NULL, `pressure` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `visit_images` (`visitId` INTEGER, `id` INTEGER PRIMARY KEY AUTOINCREMENT, `image` BLOB, `longitude` REAL NOT NULL, `latitude` REAL NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"4dcd1d016e3493156782aa0aa5ed8341\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `visit`");
        _db.execSQL("DROP TABLE IF EXISTS `visit_images`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsVisit = new HashMap<String, TableInfo.Column>(8);
        _columnsVisit.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsVisit.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsVisit.put("desscription", new TableInfo.Column("desscription", "TEXT", false, 0));
        _columnsVisit.put("date", new TableInfo.Column("date", "TEXT", false, 0));
        _columnsVisit.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
        _columnsVisit.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
        _columnsVisit.put("temp", new TableInfo.Column("temp", "REAL", true, 0));
        _columnsVisit.put("pressure", new TableInfo.Column("pressure", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVisit = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVisit = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVisit = new TableInfo("visit", _columnsVisit, _foreignKeysVisit, _indicesVisit);
        final TableInfo _existingVisit = TableInfo.read(_db, "visit");
        if (! _infoVisit.equals(_existingVisit)) {
          throw new IllegalStateException("Migration didn't properly handle visit(uk.ac.shef.oak.com4510.model.models.Visit).\n"
                  + " Expected:\n" + _infoVisit + "\n"
                  + " Found:\n" + _existingVisit);
        }
        final HashMap<String, TableInfo.Column> _columnsVisitImages = new HashMap<String, TableInfo.Column>(5);
        _columnsVisitImages.put("visitId", new TableInfo.Column("visitId", "INTEGER", false, 0));
        _columnsVisitImages.put("id", new TableInfo.Column("id", "INTEGER", false, 1));
        _columnsVisitImages.put("image", new TableInfo.Column("image", "BLOB", false, 0));
        _columnsVisitImages.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0));
        _columnsVisitImages.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysVisitImages = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesVisitImages = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoVisitImages = new TableInfo("visit_images", _columnsVisitImages, _foreignKeysVisitImages, _indicesVisitImages);
        final TableInfo _existingVisitImages = TableInfo.read(_db, "visit_images");
        if (! _infoVisitImages.equals(_existingVisitImages)) {
          throw new IllegalStateException("Migration didn't properly handle visit_images(uk.ac.shef.oak.com4510.model.models.VisitImages).\n"
                  + " Expected:\n" + _infoVisitImages + "\n"
                  + " Found:\n" + _existingVisitImages);
        }
      }
    }, "4dcd1d016e3493156782aa0aa5ed8341", "3c9c0ae1d1a7f026e1dc0acbf3c7aa30");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "visit","visit_images");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `visit`");
      _db.execSQL("DELETE FROM `visit_images`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public VisitDao dao() {
    if (_visitDao != null) {
      return _visitDao;
    } else {
      synchronized(this) {
        if(_visitDao == null) {
          _visitDao = new VisitDao_Impl(this);
        }
        return _visitDao;
      }
    }
  }
}
