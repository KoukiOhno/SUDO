package DockerComposeFiler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DockerComposeAdministrator {

    private Map<Integer,File> _dockerComposeFIleMap;

    public DockerComposeAdministrator(String directoryPath){
        _dockerComposeFIleMap = new HashMap<>();
        dockerComposeFileSearch(directoryPath);
    }

    private void dockerComposeFileSearch(String directory){
        File rootDir = new File(directory);
        File[] files = rootDir.listFiles(new NameFilter());

        int number = 0;
        for (File file : files) {
            _dockerComposeFIleMap.put(++number,file);
        }
    }

    public File getDockerComposeFile(int num){

        if(_dockerComposeFIleMap.containsKey(num) == false){
               return null;
        }
        return _dockerComposeFIleMap.get(num);
    }

    public void viewDockerComposeFile(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Number | FileName | Directory");
        _dockerComposeFIleMap.forEach((key,file)
                -> System.out.println(" @ "+key + ": "
                + file.getName() + " [ " + file.getAbsolutePath() +" ] "));
        System.out.println("-----------------------------------------------------");
    }
}
