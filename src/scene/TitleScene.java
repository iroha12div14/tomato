package scene;

import drawer.TitleDrawer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TitleScene extends SceneBase {

    //コンストラクタ
    public TitleScene(Map<Integer, Object> data) {
        // 画面サイズ、FPS、キーアサインの初期化
        init(keyAssign, data);

        drawer = new TitleDrawer(displayWidth, displayHeight);

        hiScore = cast.getIntData(this.data, elem.HI_SCORE);
    }

    // 描画したい内容はここ
    @Override
    protected void paintField(Graphics2D g2d) {
        drawer.drawFrame(g2d);
        drawer.drawTitle(g2d);
        drawer.drawMenu(g2d, hiScore);
    }

    // 毎フレーム処理したい内容はここ
    @Override
    protected void actionField() {
        // Enterキーでシーンの転換
        if(key.getKeyPress(KeyEvent.VK_ENTER)) {
            sceneTransition(Scene.GameScene);
        }
    }

    // ------------------------------------------------------ //
    // ここにインスタンスやフィールドを書く

    // 描画はここでおまかせ
    private final TitleDrawer drawer;

    private final int hiScore;

    // ------------------------------------------------------ //

    // キーアサインの初期化
    private static final List<Integer> keyAssign = Arrays.asList(
            KeyEvent.VK_UP,
            KeyEvent.VK_DOWN,
            KeyEvent.VK_ENTER
    );
}
