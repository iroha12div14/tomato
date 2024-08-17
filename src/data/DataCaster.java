package data;

import scene.SceneManager;
import scene.Scene;

import java.util.Map;

// 各シーンで運用されるMap型変数dataの出力時のキャスト用メソッド
public class DataCaster {
    // ゲーム内で運用されるデータの「要素」
    DataElements elements = new DataElements();

    // プリミティブなもの
    public boolean getBoolData (Map<Integer, Object> data, int key) {
        return (boolean) data.get(key);
    }
    public int getIntData (Map<Integer, Object> data, int key) {
        return (int) data.get(key);
    }
    public float getFloatData (Map<Integer, Object> data, int key) {
        return (float) data.get(key);
    }

    // 型に対する要素が一意に定まるもの
    public SceneManager getSceneManager(Map<Integer, Object> data) {
        int key = elements.SCENE_MANAGER;
        return (SceneManager) data.get(key);
    }
    public Scene getScene(Map<Integer, Object> data) {
        int key = elements.SCENE;
        return (Scene) data.get(key);
    }

    // 配列や参照型のもの
    public int[] getIntArrData (Map<Integer, Object> data, int key) {
        return (int[]) data.get(key);
    }
    public String getStrData (Map<Integer, Object> data, int key) {
        return (String) data.get(key);
    }
}
