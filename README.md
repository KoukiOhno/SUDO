# SUDO
Auxiliary tool for Docker Compose up or down command

## 概要
指定したディレクトリまたはJARの配置ディレクトリ以下の階層にある
環境ごとのYAMLファイルを一覧より選択してDocker Composeのupコマンドまたはdownコマンドを
実行できることを目的とした補助ツールです。

同一ディレクトリ内に開発やテスト環境別に複数のDocker Composeファイルを書いて
使用している場合などに役立つかもしれません。

## 動作環境
・Java 12以上がインストールされた環境

## 出来ること
- YAMLファイルを指定して`docker-compose up`または`docker-compose down`を実行できる。  
- すべてのコンテナ情報出力すること。

## 使い方
SUDO.jarを好きなディレクトリに配置し、以下のどちらかのコマンドを実行し起動する。

a. カレントディレクトをjarを配置したディレクトリにする場合

    java -jar SUDO.jar

b. カレントディレクトリを指定したディレクトリにする場合

    java -jar SUDO.jar [ディレクトリパス]

## 余談
ツール名のSUDOは「Start Up Docker-compose Optimization」の頭文字を取りました。
あまり最適化出来てるとは言い難いですが`須藤くん`などといった感じで読める名前を付けるとなんだか親しみが湧きます。