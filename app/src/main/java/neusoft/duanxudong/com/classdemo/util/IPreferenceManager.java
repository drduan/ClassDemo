/*-
 * Authors      : harry 
 *
 * Created Date : Jun 14, 2013
 *  
 * Mojob Limited Inc.  All rights reserved.
 *
 */

package neusoft.duanxudong.com.classdemo.util;

import java.util.Set;

public interface IPreferenceManager {

    //
    // Save methods //
    //
    boolean save(String key, String value);

    boolean save(String key, int value);

    boolean save(String key, boolean value);

    boolean save(String key, long value);

    boolean save(String key, Set<String> value);

    //
    // Read methods //
    //
    String read(String key, String defaultVal);

    boolean read(String key, boolean defaultVal);

    int read(String key, int defaultVal);

    long read(String key, long defaultVal);

    Set<String> read(String key, Set<String> defaultVal);

    //
    // Check key's availability
    //
    boolean contains(String key);

    //
    // Edit
    //
    boolean remove(String key);

    void removeAll();

}
