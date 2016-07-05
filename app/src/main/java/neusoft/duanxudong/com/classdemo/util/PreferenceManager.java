/*-
 * Authors      : harry 
 *
 * Created Date : Jun 13, 2013
 *  
 * Mojob Limited Inc.  All rights reserved.
 *
 */

package neusoft.duanxudong.com.classdemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class PreferenceManager implements IPreferenceManager {

    private static final String PREF_NAME = PreferenceManager.class.getSimpleName();
    private static final String EMPTY = "";
    private static PreferenceManager sPrefMgr = null;

    private Context mContext;

    private SharedPreferences prefs;

    private PreferenceManager(Context context, String prefFileName) {

        this.mContext = context;
        this.prefs = this.mContext.getSharedPreferences(prefFileName,
                Context.MODE_PRIVATE);
    }

    public static PreferenceManager getInstance(Context context) {

        return getInstance(context, PREF_NAME);
    }

    public static PreferenceManager getInstance(Context context,
                                                String prefFileName) {

        if (sPrefMgr == null) {
            sPrefMgr = new PreferenceManager(context, prefFileName);

        }

        return sPrefMgr;

    }

    //
    // API methods
    //
    @Override
    public boolean save(String key, String value) {

        return this.prefs.edit().putString(key, value).commit();
    }

    @Override
    public boolean save(String key, int value) {

        return this.prefs.edit().putInt(key, value).commit();
    }

    @Override
    public boolean save(String key, boolean value) {

        return this.prefs.edit().putBoolean(key, value).commit();
    }

    @Override
    public String read(String key, String defaultVal) {

        return this.prefs.getString(key, EMPTY);
    }

    @Override
    public boolean read(String key, boolean defaultVal) {

        return this.prefs.getBoolean(key, defaultVal);
    }

    @Override
    public int read(String key, int defaultVal) {

        return this.prefs.getInt(key, defaultVal);
    }

    @Override
    public boolean save(String key, long value) {

        return this.prefs.edit().putLong(key, value).commit();
    }

    @Override
    public boolean save(String key, Set<String> value) {
        return this.prefs.edit().putStringSet(key, value).commit();
    }

    @Override
    public long read(String key, long defaultVal) {

        return this.prefs.getLong(key, defaultVal);
    }

    @Override
    public Set<String> read(String key, Set<String> defaultVal) {
        return this.prefs.getStringSet(key, defaultVal);
    }


    @Override
    public boolean contains(String key) {

        return this.prefs.contains(key);
    }

    @Override
    public boolean remove(String key) {

        return this.prefs.edit().remove(key).commit();
    }

    @Override
    public void removeAll() {
        this.prefs.edit().clear().commit();
    }


}
