package Main;

import java.io.File;

public class StartUpDocker_composeOptimization {
    public static void main(String[] args){

        UIProcess uip;
        String directoryPath = ".";
        if(args.length == 1){
            // パラメータ指定でパスが渡された場合
            if(new File(args[0]).isDirectory()){
                directoryPath = args[0];
            }else{
                System.out.println("正しいディレクトリのパスを指定してください。");
                return;
            }
        }else if(args.length > 1){
            System.out.println("Hint:");
            System.out.println("Command: java -jar SUDO.jar");
            System.out.println("Command: java -jar SUDO.jar [path]");
            return;
        }
        uip = new UIProcess(directoryPath);
    }
}
