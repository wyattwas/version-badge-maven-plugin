package org.opencodespace;

import javax.inject.Named;
import javax.inject.Singleton;
import java.io.*;
import java.net.URL;

@Named
@Singleton
public class RuntimeExecImageProvider implements ImageProvider{

    /**
     * This method will download the image from the url and save it to the output path
     *
     * @param stringUrl The url of the image
     * @param outputPath The path to save the image
     */
    @Override
    public void getImage(String stringUrl, String outputPath) {
        try {
            URL url = new URL(stringUrl);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n;
            while (-1!=(n=in.read(buf)))
            {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            try {
                if (outputPath.contains(".png")) {
                    FileOutputStream fos = new FileOutputStream(outputPath);
                    fos.write(response);
                    fos.close();
                } else {
                    FileOutputStream fos = new FileOutputStream(outputPath + "\\version-batch.png");
                    fos.write(response);
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
