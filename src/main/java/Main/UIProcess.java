package Main;

import DockerComposeFiler.DockerComposeAdministrator;

import java.io.*;
import java.util.Scanner;

public class UIProcess {

    DockerComposeAdministrator _dca;
    String _searchDirectory;

    UIProcess(String directoryPath) {
        _searchDirectory = directoryPath;
        startMessage();
        while(true){
            System.out.println();
            System.out.println("===============================================");
            System.out.println("= 行う操作の番号を入力してください              =");
            System.out.println("= 1: DockerCompose upコマンドの実行            =");
            System.out.println("= 2: DockerCompose downコマンドの実行          =");
            System.out.println("= 3: Dockerコンテナのステータス確認             =");
            System.out.println("= 0: ツールを終了する                          =");
            System.out.println("===============================================");
            int num = getInputNumber();
            if( num == 1){
                up();
            }else if(num == 2) {
                down();
            }else if(num == 3){
                status();
            }else if( num == 0){
                System.exit(0);
            }
        }
    }

    private void startMessage(){
        System.out.println();
        System.out.println("##########################################");
        System.out.println("# S U D O                                #");
        System.out.println("#   Start Up Docker-compose Optimization #");
        System.out.println("#   version:1.0.0                        #");
        System.out.println("##########################################");
    }
    private void status(){
        System.out.println();
        System.out.println("=========================================");
        System.out.println("Dockerコンテナのステータス");
        int status = checkDockerPS();
        if(status == 0){
            System.out.println("");
        }else if(status == 1 || status == 2){
            System.out.println("例外が発生しました。");
        }
    }

    private void down(){
        System.out.println();
        System.out.println("=========================================");
        System.out.println("DockerComposeのDown");
        int status = downDockerCompose();
        if(status == 0) {
            System.out.println("正常にDownしました。");
        }else if(status == 1 || status == 2){
            System.out.println("例外が発生しました。対象のDockerComposeFileの確認をしてください");
        }else if(status == 999){
            System.out.println("存在しないNumberが選択されましたメインメニューに戻ります。");
        }
    }

    private void up(){
        System.out.println();
        System.out.println("=========================================");
        System.out.println("DockerComposeのUp");
        int status = upDockerCompose();
        if(status == 0){
            System.out.println("正常にUpしました。");
        }else if(status == 1 || status == 2){
            System.out.println("例外が発生しました。対象のDockerComposeFileの確認をしてください");
        }else if(status == 999){
            System.out.println("存在しないNumberが選択されましたメインメニューに戻ります。");
        }
    }

    private int getInputNumber(){
        Scanner sc = new Scanner(System.in);
        return Integer.parseInt(sc.nextLine());
    }

    private int upDockerCompose(){
        _dca = new DockerComposeAdministrator(_searchDirectory);
        _dca.viewDockerComposeFile();
        System.out.println("UpするDockerComposeファイルのNumberを入力してください。");
        System.out.println("※メニューに戻る場合は0を入力");
        int num = getInputNumber();
        if(num == 0){
            return 99;
        }
        File file = _dca.getDockerComposeFile(num);
        if(file != null) {
            try {
                Runtime runtime = Runtime.getRuntime();
                String command = "docker-compose -f " + file.getAbsolutePath() + " up -d";
                System.out.println("実行コマンド:" + command);
                Process consoleProcess = runtime.exec(command);
                InputStream is = consoleProcess.getInputStream();
                // コンソールのメッセージを出力
                viewConsoleMessage(is);

                // プロセスの正常終了まで待機
                return consoleProcess.waitFor();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return 999;
    }

    private int downDockerCompose(){
        _dca = new DockerComposeAdministrator(_searchDirectory);
        _dca.viewDockerComposeFile();
        System.out.println("DownするDockerComposeファイルのNumberを入力してください。");
        System.out.println("※メニューに戻る場合は0を入力");
        int num = getInputNumber();
        if(num == 0){
            return 99;
        }
        File file = _dca.getDockerComposeFile(num);
        if(file != null) {
            try {
                Runtime runtime = Runtime.getRuntime();
                String command = "docker-compose -f " + file.getAbsolutePath() + " down";
                System.out.println("実行コマンド:" + command);
                Process consoleProcess = runtime.exec(command);
                InputStream is = consoleProcess.getInputStream();
                // コンソールのメッセージを出力
                viewConsoleMessage(is);

                // プロセスの正常終了まで待機
                return consoleProcess.waitFor();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return 999;
    }

    public int checkDockerPS(){
        try {
            Runtime runtime = Runtime.getRuntime();
            String command = "docker ps -a";
            System.out.println("実行コマンド:" + command);
            Process consoleProcess = runtime.exec(command);
            InputStream is = consoleProcess.getInputStream();
            // コンソールのメッセージを出力
            viewConsoleMessage(is);

            // プロセスの終了まで待機
            return consoleProcess.waitFor();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return 999;
    }

    private void viewConsoleMessage(InputStream is){
        BufferedReader br  = new BufferedReader(new InputStreamReader(is));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
