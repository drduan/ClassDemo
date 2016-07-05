package neusoft.duanxudong.com.classdemo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by harry on 12/8/14.
 */
public class FileUtils {
    private FileUtils() {

    }

    /**
     * Save byte data to File (with the correct file name)
     *
     * @param file
     * @param data
     * @return count of bytes saved when successful, 0 when not.
     */
    public static long saveOnDisk(File file, byte[] data) {

        if (file == null || data == null || data.length == 0) {
            return 0;
        }

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.close();

            return file.length();

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return 0;
    }
}
