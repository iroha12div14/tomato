package scene;

import drawer.GameDrawer;
import gameObject.Chara;
import gameObject.Earth;
import gameObject.Tomato;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameScene extends SceneBase {

    //コンストラクタ
    public GameScene(Map<Integer, Object> data) {
        // 画面サイズ、FPS、キーアサインの初期化
        init(keyAssign, data);

        hiScore = cast.getIntData(this.data, elem.HI_SCORE);

        drawer = new GameDrawer(displayWidth, displayHeight, earth);
        chara = new Chara(movXLimit);

        isGameOver = false;
    }

    // 描画したい内容はここ
    @Override
    protected void paintField(Graphics2D g2d) {
        drawer.drawFrame(g2d, isGameOver); // 枠

        if(startTimer > readyTimerLap) {
            drawer.drawExplain(g2d); // 説明
        } else if(startTimer > 0) {
            drawer.drawReady(g2d); // Ready?
        }

        if(gameOverSlipTimer > 0) {
            drawer.drawCharacter(g2d, chara); // キャラクタ
            drawer.drawTomatoes(g2d, tomatoes); // トマト
        } else {
            drawer.drawGameOver(g2d); // ゲームオーバー表示
        }

        drawer.drawScore(g2d, calcScore(), hiScore); // スコア表示
    }

    // 毎フレーム処理したい内容はここ
    @Override
    protected void actionField() {

        if(!isGameOver) {
            int pastTime = fru.getPastFrame();

            boolean isJump = key.getKeyPress(KeyEvent.VK_UP);
            boolean isMoveRight = key.getKeyHold(KeyEvent.VK_RIGHT);
            boolean isMoveLeft = key.getKeyHold(KeyEvent.VK_LEFT);

            charaBehavior(isJump, isMoveLeft, isMoveRight); // キャラクタの動き
            tomatoesBehavior(); // トマトの動き

            if(pastTime >= 150 && pastTime % 150 == 0) {
                tomatoes.add(new Tomato(movXLimit - 20)); // トマトを生成
            }

            // 強制終了
            if (key.getKeyPress(KeyEvent.VK_ESCAPE)) {
                sceneTransition(Scene.TitleScene);
            }

            // 説明表示とReady?のやつのタイマー
            if (startTimer > 0) {
                startTimer--;
            }

            if(fallenTomatoes() == 3) {
                isGameOver = true;
            }
        } else {
            tomatoesBehavior(); // トマトの動き

            // ちょっと遅延させてからゲームオーバーにする
            if(gameOverSlipTimer > 0) {
                gameOverSlipTimer--;
            }
            else if(gameOverSlipTimer == 0) {
                if (key.getKeyPress(KeyEvent.VK_ENTER)) {
                    if (calcScore() > hiScore) {
                        data.put(elem.HI_SCORE, calcScore());
                    }
                    sceneTransition(Scene.TitleScene);
                }
            }
        }
    }

    // キャラクタの動き
    private void charaBehavior(boolean isJump, boolean isMoveLeft, boolean isMoveRight) {
        // ジャンプ慣性方向
        int jumpDir = chara.getJumpDir();

        // 非ジャンプ時に左右の移動が可能
        if(chara.isLanding()) {
            chara.setJumpDir(0);
            if (isMoveRight) {
                chara.moveRight();
            } else if (isMoveLeft) {
                chara.moveLeft();
            }
        }
        // 降下中もしくは着地時の処理
        else if (chara.isJumping()) {
            chara.falling();
        }

        // ジャンプ
        if (isJump) {
            if (chara.isLanding()) {
                chara.jump();
            }
            // ジャンプ時に←→キーが押されてると慣性が働く
            if (isMoveRight) {
                chara.setJumpDir(DIR_RIGHT);
            } else if (isMoveLeft) {
                chara.setJumpDir(DIR_LEFT);
            } else {
                chara.setJumpDir(0);
            }
        }

        // 慣性が働いている時(ジャンプ中)の移動
        if (jumpDir == DIR_RIGHT) {
            chara.moveRight();
        } else if (jumpDir == DIR_LEFT) {
            chara.moveLeft();
        }
    }

    // トマトの落下
    private void tomatoesBehavior() {
        for(Tomato tomato : tomatoes) {
            if(tomato.isFalling()) {
                tomato.fall();
                tomato.checkCollision(chara);
            }
        }
    }

    // スコア計算
    private int calcScore() {
        int score = 0;
        for(Tomato tomato : tomatoes) {
            if(tomato.isGotten()) {
                score += 10;
            }
        }
        return score;
    }

    // 落下したトマトの数
    private int fallenTomatoes() {
        int fallenCount = 0;
        for(Tomato tomato : tomatoes) {
            if(tomato.isLanding()) {
                fallenCount++;
            }
        }
        return fallenCount;
    }

    // ------------------------------------------------------ //
    // ここにインスタンスやフィールドを書く

    // 描画コンストラクタ
    private final GameDrawer drawer;
    private final Chara chara;
    private final List<Tomato> tomatoes = new ArrayList<>();
    private final Earth earth = new Earth();

    private boolean isGameOver;

    private final int hiScore;

    private final int frameWidth = earth.getFrameWidth();

    private final int movXLimit = frameWidth / 2 - 30;

    private final int DIR_RIGHT = 1;
    private final int DIR_LEFT = -1;

    private int startTimer = 180;
    private int readyTimerLap = 60;
    private int gameOverSlipTimer = 40;

    // ------------------------------------------------------ //

    // キーアサインの初期化
    private static final List<Integer> keyAssign = Arrays.asList(
            KeyEvent.VK_RIGHT,
            KeyEvent.VK_LEFT,
            KeyEvent.VK_UP,
            KeyEvent.VK_ENTER,
            KeyEvent.VK_ESCAPE
    );
}
