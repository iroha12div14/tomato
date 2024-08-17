
import scene.*;
import data.*;

import java.util.HashMap;
import java.util.Map;

class Main {
    public static void main(String[] args){
        // dataの初期化
        printMessage("初期化中");
        Map<Integer, Object> data = dataInit();

        // ウインドウを立ち上げ、最初の場面を表示する
        SceneManager sceneManager = new DataCaster().getSceneManager(data);
        sceneManager.activateDisplay(data);

        printMessage("初期化完了");
    }

    // dataの初期化
    private static Map<Integer, Object> dataInit() {
        Map<Integer, Object> data = new HashMap<>();
        DataElements elem = new DataElements();     // 受け渡しをするデータの要素
        SceneManager sceneManager = new SceneManager();  // 後に立ち上げるウインドウのマネージャ

        data.put(elem.WORK_DIRECTORY,"_240816_Tomato");
        data.put(elem.IS_ROOT_MAIN, false); // 直接Main.classを呼んだりJARから起動する場合はtrue

        // シーン切り替え時のシーン転換インスタンスの場所を埋め込んでおく
        data.put(elem.SCENE_MANAGER, sceneManager);

        data.put(elem.WINDOW_NAME, "上から読んでも　下から読んでも");
        data.put(elem.DISPLAY_WIDTH, 800);
        data.put(elem.DISPLAY_HEIGHT, 450);
        data.put(elem.DISPLAY_X, 100);
        data.put(elem.DISPLAY_Y, 100);
        data.put(elem.FRAME_RATE, 60);

        data.put(elem.SCENE, Scene.TitleScene);
        data.put(elem.SCENE_ID, 1);

        data.put(elem.HI_SCORE, 0);

        return data;
    }

    // ログ出力用
    private static void printMessage(String msg) {
        System.out.println(msg + "\t@Main");
    }
}
