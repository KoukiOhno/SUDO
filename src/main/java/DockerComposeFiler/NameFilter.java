package DockerComposeFiler;

import java.io.File;
import java.io.FilenameFilter;

public class NameFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        return name.matches(".+\\.yml$");
    }
}
