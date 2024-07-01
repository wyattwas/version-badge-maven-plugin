package org.opencodespace;

public class Utils {
    public static String makeOSSpecificSlashesInPath(String path) {
        if (System.getProperty("os.name").contains("Windows")) {
            path = path.replace("/", "\\");
            path += "\\";
        } else {
            path = path.replace("\\", "/");
            path += "/";
        }

        return path;
    }
}
