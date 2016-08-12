package Modules.Files.Models;

import Modules.Files.Interfaces.ICompression;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Karol Golec on 12.08.2016.
 */
public class Compression implements ICompression {

    /**
     * logger for this class
     */
    private static Logger logger = LogManager.getLogger();

    /** Buffer for compression */
    byte[] buffer = new byte[1024];

    /**
     * Compression file to zip
     *
     * @param filePath path to file for compression
     * @param zipPath path with compression zip file
     * @return true if compression has success status, false if compression has'nt success status
     * @throws IOException
     */
    public Boolean toZip(String filePath, String zipPath) throws IOException {

            FileOutputStream fos = new FileOutputStream(zipPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze= new ZipEntry("spy.log");
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(filePath);

            int len;
            while ((len = in.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            in.close();
            zos.closeEntry();
            zos.close();
            return true;
    }
}
