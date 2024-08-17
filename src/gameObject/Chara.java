package gameObject;

import calc.CalcUtil;

public class Chara {
    CalcUtil calc = new CalcUtil();

    private int x;          // 座標
    private int jumpTimer;  // ジャンプ時間
    private int dir;        // 向いてる方向
    private int jumpDir;    // ジャンプ慣性方向

    private int movXLimit;  // 行動範囲

    private final int moveX = 2;
    private static final int JUMP_TIMER_SET = 40;
    private static final int DIR_RIGHT = 1;
    private static final int DIR_LEFT  = -1;

    private final int width  = 36;
    private final int height = 48;
    private final int jumpH  = 60;

    // コン
    public Chara(int movXLimit) {
        this.x = 0;
        this.jumpTimer = 0;
        this.dir = DIR_RIGHT;
        this.jumpDir = 0;

        this.movXLimit = movXLimit;
    }

    // 左右移動
    public void moveRight() {
        if(x < movXLimit) {
            x += moveX;
        }
        dir = DIR_RIGHT;
    }
    public void moveLeft() {
        if(x > -movXLimit) {
            x -= moveX;
        }
        dir = DIR_LEFT;
    }
    // ジャンプ
    public void jump() {
        jumpTimer = JUMP_TIMER_SET;
    }
    // 落下
    public void falling() {
        if(isJumping()) {
            jumpTimer--;
        }
    }
    // ジャンプしているか着地しているか
    public boolean isJumping() {
        return jumpTimer > 0;
    }
    public boolean isLanding() {
        return jumpTimer == 0;
    }

    // 下駄
    public int getX() {
        return x;
    }
    public int getDir() {
        return dir;
    }
    public int getJumpDir() {
        return jumpDir;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getJumpH() {
        return jumpH;
    }

    // ジャンプの高さ 0.0F → 1.0F → 0.0F
    public double getHeightUnit() {
        int jtsh = JUMP_TIMER_SET / 2;
        return 1.0F - calc.pow2(jumpTimer - jtsh) / calc.pow2(jtsh);
    }

    // 雪駄
    public void setJumpDir(int dir) {
        jumpDir = dir;
    }
}
