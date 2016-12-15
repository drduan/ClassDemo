package neusoft.duanxudong.com.classdemo.util;


/**
 * Created by sunfusheng on 2015/6/25.
 */
//@SharedPreferences(name = "settings", mode = SharedPreferenceMode.PRIVATE)
public interface SettingsSharedPreferences {

    boolean isReceivePush();

    void isReceivePush(boolean isReceivePush);

    int themeValue();

    void themeValue(int themeValue);

    String newBingoUrl();

    void newBingoUrl(String newBingoUrl);

    String newBingoDes();

    void newBingoDes(String newBingoDes);
}
