package net.dirox.auluxa.utils.room_image;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import net.dirox.auluxa.utils.Prefs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class FileUtils {

    public static void copyFile(Context context, String filename) {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            String newFileName = "/data/data/" + context.getPackageName() + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            Log.d("copyFile", newFileName);
        } catch (Exception e) {
            Log.e("copyFile", e.getMessage());
        }

    }

    public static void copyFileOrDir(Context context, String path) {
        AssetManager assetManager = context.getAssets();
        String assets[] = null;
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(context, path);
            } else {
                String fullPath = "/data/data/" + context.getPackageName() + "/" + path;
                File dir = new File(fullPath);
                if (!dir.exists())
                    dir.mkdir();
                for (int i = 0; i < assets.length; ++i) {
                    copyFileOrDir(context, path + "/" + assets[i]);
                }

                Log.d("copyFileOrDir", fullPath);
            }
        } catch (IOException ex) {
            Log.e("copyFileOrDir", "I/O Exception", ex);
        }
    }

    public static void loadImageFromStorage(Context context, ImageView imageView) {

        String filePath = Prefs.getStringPrefs(context, "room_image_path");
        imageView.setImageDrawable(Drawable.createFromPath(filePath));

        Log.d("Load image", filePath);
    }

    public static String saveToInternalStorage(Context context, Bitmap bitmapImage) {

        String fullPath = "/data/data/" + context.getPackageName() + "/Images";

        // Create imageDir
        String fileName = "room_image_" + Calendar.getInstance().getTimeInMillis() + ".png";
        File filePath = new File(fullPath, fileName);
        Prefs.setStringPrefs(context, "room_image_path", filePath.getPath());
        Log.d("Save image", filePath.getPath());

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return filePath.getPath();

    }
}
