package Modules.Files.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Karol Golec on 12.08.2016.
 */
public interface ICompression {

    /**
     * Compression file to zip
     *
     * @param filePath path to file for compression
     * @param zipPath path with compression zip file
     * @return true if compression has success status, false if compression has'nt success status
     * @throws IOException
     */
    Boolean toZip(String filePath, String zipPath) throws IOException;
}
