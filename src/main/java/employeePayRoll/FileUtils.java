package employeePayRoll;

import java.io.File;

public class FileUtils {
    public static boolean deleteFiles(File contentToDelete){
        File[] allCounts = contentToDelete.listFiles();
        if (allCounts != null){
            for (File file : allCounts) {
                deleteFiles(file);
            }
        }
        return contentToDelete.delete();
    }
}
