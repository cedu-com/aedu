package net.chinaedu.aedu.utils;

import android.os.Handler;
import android.os.Message;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xujianbao on 2015/11/13.
 * <p/>
 * 下载文件工具类
 */
public class AeduHttpDownloadUtil {

    private String mHttpUrl;
    private String mSaveBasePath;
    public final static int HTTP_DOWNLOAD_VAR = 0x99999;

    public AeduHttpDownloadUtil(String httpUrl, String saveBasePath) {
        this.mHttpUrl = httpUrl;
        this.mSaveBasePath = saveBasePath;
    }

    /**
     * 根据指定的文件名下载并保存文件
     *
     * @param saveFileName
     * @return 0:下载成功,-1:下载失败,1:已经存在
     */
    public void downAndSavefile(final String saveFileName, final Handler handler) {
        Message m = new Message();
        m.arg1 = HTTP_DOWNLOAD_VAR;
        InputStream input = null;

        try {
            if (isFileExist(saveFileName)) {
                m.arg2 = 1;
            } else {
                input = getInputStream(mHttpUrl);
                File resultFile = write2SDFromInput(mSaveBasePath, saveFileName, input);
                if (resultFile == null) {
                    m.arg2 = -1;
                } else {
                    m.arg2 = 0;
                }
            }
        } catch (Exception e) {
            m.arg2 = -1;
            e.printStackTrace();
        } finally {
            m.obj = saveFileName;
            handler.sendMessage(m);
            try {
                if(input != null){
                    input.close();
                }
            } catch (IOException e) {
            }
        }
    }


    /**
     * 根据url获得文件的InputStream
     *
     * @param urlStr
     * @return
     * @throws IOException
     */
    private InputStream getInputStream(String urlStr) throws IOException {
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setReadTimeout(11000);
            urlConn.setRequestMethod("GET");
            urlConn.setDoInput(true);
            if (urlConn.getResponseCode() == 200) {
                is = urlConn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 将InputStream里面的数据写入到SD卡中
     */
    private File write2SDFromInput(String path, String fileName, InputStream is) {
        File file = null;
        FileOutputStream fos = null;
        try {
            creatSDDir(path);
            file = creatSDFile(path + fileName);
            fos = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    private boolean isFileExist(String fileName) {
        File file = new File(mSaveBasePath + fileName);
        return file.exists();
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    private File creatSDFile(String fileName) throws IOException {
        File file = new File(fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    private File creatSDDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdirs();
        return dir;
    }
}
