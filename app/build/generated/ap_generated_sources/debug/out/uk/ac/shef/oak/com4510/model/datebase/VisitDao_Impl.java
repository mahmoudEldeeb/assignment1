package uk.ac.shef.oak.com4510.model.datebase;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.lifecycle.ComputableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.InvalidationTracker.Observer;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import uk.ac.shef.oak.com4510.model.models.Visit;
import uk.ac.shef.oak.com4510.model.models.VisitImages;

@SuppressWarnings("unchecked")
public final class VisitDao_Impl implements VisitDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfVisit;

  private final EntityInsertionAdapter __insertionAdapterOfVisitImages;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfVisit;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfVisit;

  public VisitDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfVisit = new EntityInsertionAdapter<Visit>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `visit`(`id`,`title`,`desscription`,`date`,`longitude`,`latitude`,`temp`,`pressure`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Visit value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDesscription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDesscription());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindDouble(5, value.getLongitude());
        stmt.bindDouble(6, value.getLatitude());
        stmt.bindDouble(7, value.getTemp());
        stmt.bindDouble(8, value.getPressure());
      }
    };
    this.__insertionAdapterOfVisitImages = new EntityInsertionAdapter<VisitImages>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `visit_images`(`visitId`,`id`,`image`,`longitude`,`latitude`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, VisitImages value) {
        if (value.visitId == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.visitId);
        }
        if (value.getId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindLong(2, value.getId());
        }
        if (value.getImage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindBlob(3, value.getImage());
        }
        stmt.bindDouble(4, value.getLongitude());
        stmt.bindDouble(5, value.getLatitude());
      }
    };
    this.__deletionAdapterOfVisit = new EntityDeletionOrUpdateAdapter<Visit>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `visit` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Visit value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
      }
    };
    this.__updateAdapterOfVisit = new EntityDeletionOrUpdateAdapter<Visit>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `visit` SET `id` = ?,`title` = ?,`desscription` = ?,`date` = ?,`longitude` = ?,`latitude` = ?,`temp` = ?,`pressure` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Visit value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindLong(1, value.getId());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDesscription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDesscription());
        }
        if (value.getDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDate());
        }
        stmt.bindDouble(5, value.getLongitude());
        stmt.bindDouble(6, value.getLatitude());
        stmt.bindDouble(7, value.getTemp());
        stmt.bindDouble(8, value.getPressure());
        if (value.getId() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.getId());
        }
      }
    };
  }

  @Override
  public Long insertVist(Visit vist) {
    __db.beginTransaction();
    try {
      long _result = __insertionAdapterOfVisit.insertAndReturnId(vist);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Long[] insertVisitImage(List<VisitImages> image) {
    __db.beginTransaction();
    try {
      Long[] _result = __insertionAdapterOfVisitImages.insertAndReturnIdsArrayBox(image);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteVist(Visit vist) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfVisit.handle(vist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateVist(Visit vist) {
    __db.beginTransaction();
    try {
      __updateAdapterOfVisit.handle(vist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<Visit>> search(String search) {
    final String _sql = "select * from visit where title like ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (search == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, search);
    }
    return new ComputableLiveData<List<Visit>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Visit> compute() {
        if (_observer == null) {
          _observer = new Observer("visit") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDesscription = _cursor.getColumnIndexOrThrow("desscription");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final int _cursorIndexOfTemp = _cursor.getColumnIndexOrThrow("temp");
          final int _cursorIndexOfPressure = _cursor.getColumnIndexOrThrow("pressure");
          final List<Visit> _result = new ArrayList<Visit>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Visit _item;
            _item = new Visit();
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDesscription;
            _tmpDesscription = _cursor.getString(_cursorIndexOfDesscription);
            _item.setDesscription(_tmpDesscription);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item.setDate(_tmpDate);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _item.setLongitude(_tmpLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _item.setLatitude(_tmpLatitude);
            final double _tmpTemp;
            _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
            _item.setTemp(_tmpTemp);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            _item.setPressure(_tmpPressure);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<Visit>> getAllVisits() {
    final String _sql = "select * from visit";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<Visit>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<Visit> compute() {
        if (_observer == null) {
          _observer = new Observer("visit") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDesscription = _cursor.getColumnIndexOrThrow("desscription");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final int _cursorIndexOfTemp = _cursor.getColumnIndexOrThrow("temp");
          final int _cursorIndexOfPressure = _cursor.getColumnIndexOrThrow("pressure");
          final List<Visit> _result = new ArrayList<Visit>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Visit _item;
            _item = new Visit();
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _item.setTitle(_tmpTitle);
            final String _tmpDesscription;
            _tmpDesscription = _cursor.getString(_cursorIndexOfDesscription);
            _item.setDesscription(_tmpDesscription);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _item.setDate(_tmpDate);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _item.setLongitude(_tmpLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _item.setLatitude(_tmpLatitude);
            final double _tmpTemp;
            _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
            _item.setTemp(_tmpTemp);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            _item.setPressure(_tmpPressure);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<Visit> getVisitByID(long iD) {
    final String _sql = "select * from visit where id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, iD);
    return new ComputableLiveData<Visit>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected Visit compute() {
        if (_observer == null) {
          _observer = new Observer("visit") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
          final int _cursorIndexOfDesscription = _cursor.getColumnIndexOrThrow("desscription");
          final int _cursorIndexOfDate = _cursor.getColumnIndexOrThrow("date");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final int _cursorIndexOfTemp = _cursor.getColumnIndexOrThrow("temp");
          final int _cursorIndexOfPressure = _cursor.getColumnIndexOrThrow("pressure");
          final Visit _result;
          if(_cursor.moveToFirst()) {
            _result = new Visit();
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            _result.setTitle(_tmpTitle);
            final String _tmpDesscription;
            _tmpDesscription = _cursor.getString(_cursorIndexOfDesscription);
            _result.setDesscription(_tmpDesscription);
            final String _tmpDate;
            _tmpDate = _cursor.getString(_cursorIndexOfDate);
            _result.setDate(_tmpDate);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            _result.setLongitude(_tmpLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _result.setLatitude(_tmpLatitude);
            final double _tmpTemp;
            _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
            _result.setTemp(_tmpTemp);
            final double _tmpPressure;
            _tmpPressure = _cursor.getDouble(_cursorIndexOfPressure);
            _result.setPressure(_tmpPressure);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<VisitImages>> getVisitImagesByVisitID(long visitID) {
    final String _sql = "select * from visit_images where visitId=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, visitID);
    return new ComputableLiveData<List<VisitImages>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<VisitImages> compute() {
        if (_observer == null) {
          _observer = new Observer("visit_images") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final List<VisitImages> _result = new ArrayList<VisitImages>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final VisitImages _item;
            final Long _tmpVisitId;
            if (_cursor.isNull(_cursorIndexOfVisitId)) {
              _tmpVisitId = null;
            } else {
              _tmpVisitId = _cursor.getLong(_cursorIndexOfVisitId);
            }
            final byte[] _tmpImage;
            _tmpImage = _cursor.getBlob(_cursorIndexOfImage);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _item = new VisitImages(_tmpVisitId,_tmpImage,_tmpLongitude,_tmpLatitude);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<VisitImages> getVisitImageByID(long ID) {
    final String _sql = "select * from visit_images where Id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ID);
    return new ComputableLiveData<VisitImages>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected VisitImages compute() {
        if (_observer == null) {
          _observer = new Observer("visit_images") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final VisitImages _result;
          if(_cursor.moveToFirst()) {
            final Long _tmpVisitId;
            if (_cursor.isNull(_cursorIndexOfVisitId)) {
              _tmpVisitId = null;
            } else {
              _tmpVisitId = _cursor.getLong(_cursorIndexOfVisitId);
            }
            final byte[] _tmpImage;
            _tmpImage = _cursor.getBlob(_cursorIndexOfImage);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _result = new VisitImages(_tmpVisitId,_tmpImage,_tmpLongitude,_tmpLatitude);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _result.setId(_tmpId);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<VisitImages>> getVisitImagesByID(long ID) {
    final String _sql = "select * from visit_images where Id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ID);
    return new ComputableLiveData<List<VisitImages>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<VisitImages> compute() {
        if (_observer == null) {
          _observer = new Observer("visit_images") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final List<VisitImages> _result = new ArrayList<VisitImages>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final VisitImages _item;
            final Long _tmpVisitId;
            if (_cursor.isNull(_cursorIndexOfVisitId)) {
              _tmpVisitId = null;
            } else {
              _tmpVisitId = _cursor.getLong(_cursorIndexOfVisitId);
            }
            final byte[] _tmpImage;
            _tmpImage = _cursor.getBlob(_cursorIndexOfImage);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _item = new VisitImages(_tmpVisitId,_tmpImage,_tmpLongitude,_tmpLatitude);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }

  @Override
  public LiveData<List<VisitImages>> getAllVisitImagesSorted() {
    final String _sql = "select * from visit_images ORDER BY id ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return new ComputableLiveData<List<VisitImages>>(__db.getQueryExecutor()) {
      private Observer _observer;

      @Override
      protected List<VisitImages> compute() {
        if (_observer == null) {
          _observer = new Observer("visit_images") {
            @Override
            public void onInvalidated(@NonNull Set<String> tables) {
              invalidate();
            }
          };
          __db.getInvalidationTracker().addWeakObserver(_observer);
        }
        final Cursor _cursor = __db.query(_statement);
        try {
          final int _cursorIndexOfVisitId = _cursor.getColumnIndexOrThrow("visitId");
          final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
          final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
          final int _cursorIndexOfLongitude = _cursor.getColumnIndexOrThrow("longitude");
          final int _cursorIndexOfLatitude = _cursor.getColumnIndexOrThrow("latitude");
          final List<VisitImages> _result = new ArrayList<VisitImages>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final VisitImages _item;
            final Long _tmpVisitId;
            if (_cursor.isNull(_cursorIndexOfVisitId)) {
              _tmpVisitId = null;
            } else {
              _tmpVisitId = _cursor.getLong(_cursorIndexOfVisitId);
            }
            final byte[] _tmpImage;
            _tmpImage = _cursor.getBlob(_cursorIndexOfImage);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            _item = new VisitImages(_tmpVisitId,_tmpImage,_tmpLongitude,_tmpLatitude);
            final Long _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getLong(_cursorIndexOfId);
            }
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    }.getLiveData();
  }
}
