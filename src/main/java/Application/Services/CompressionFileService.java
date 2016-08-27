package Application.Services;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created by Karol Golec on 12.08.2016.
 */
public class CompressionFileService {

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
     * @param fileNameInsideZip
     * @return true if compression has success status, false if compression has'nt success status
     * @throws IOException
     */
    public Boolean toZip(String filePath, String zipPath, String fileNameInsideZip) throws IOException {

            FileOutputStream fos = new FileOutputStream(zipPath);
            ZipOutputStream zos = new ZipOutputStream(fos);
            ZipEntry ze= new ZipEntry(fileNameInsideZip);
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
