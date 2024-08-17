package gameObject;

import java.util.Random;

public class Tomato {
    int x;
    int y = 280;
    int width  = 20;
    int height = 16;
    boolean isGotten = false;

    int moveY = 2;

    Random rand = new Random();

    // コン
    public Tomato(int xRange) {
        this.x = rand.nextInt(xRange * 2) - xRange;
    }

    // 下駄
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    // 落下
    public void fall() {
        if(isFalling() && !isGotten) {
            y -= moveY;
        }
    }

    // 落下中か落としたか
    public boolean isFalling() {
        return y > 0;
    }
    public boolean isLanding() {
        return y == 0;
    }
    public boolean isGotten() {
        return isGotten;
    }

    // 衝突チェック
    public void checkCollision(Chara chara) {
        if(!isGotten) {
            int charaX = chara.getX();
            int charaY = (int) (chara.getJumpH() * chara.getHeightUnit());
            int charaWidth = chara.getWidth();
            int charaHeight = chara.getHeight();

            boolean colX = (charaX + charaWidth / 2 > x - width) && (charaX - charaWidth < x + width);
            boolean colY = (charaY + charaHeight / 2 > y - height) && (charaY - charaHeight < y + height);

            // キャラクタがジャンプしながら衝突しないと消失しない仕組み
            isGotten = colX && colY && chara.isJumping();
        }
    }
}
