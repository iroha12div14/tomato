package drawer;

import calc.CalcUtil;
import draw.*;
import font.FontUtil;
import gameObject.Chara;
import gameObject.Earth;
import gameObject.Tomato;

import java.awt.*;
import java.util.List;

public class GameDrawer {
    private final CalcUtil calc = new CalcUtil();

    // コン
    public GameDrawer(int displayWidth, int displayHeight, Earth earth) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        displayCenterX = this.displayWidth / 2;
        displayCenterY = this.displayHeight / 2;

        frameWidth  = earth.getFrameWidth();
        frameHeight = earth.getFrameHeight();
        earthH = earth.getEarthH();

        earthY = displayCenterY + frameHeight / 2 - earthH;
    }

    // 枠と地面と背景
    public void drawFrame(Graphics2D g2d, boolean isGameOver) {
        Color cFrame = !isGameOver ? colorFrame : colorGameOver;
        rect.fill(g2d, cFrame,
                rect.makeParam(displayCenterX, displayCenterY, frameWidth, frameHeight),
                rect.makeSide(rect.CENTER, rect.CENTER)
        );
        rect.fill(g2d, Color.BLACK,
                rect.makeParam(displayCenterX, displayCenterY,
                        frameWidth - frameBold * 2, frameHeight - frameBold * 2),
                rect.makeSide(rect.CENTER, rect.CENTER)
        );

        Color cEarth = !isGameOver ? colorEarth : colorGameOver;
        int earthX = displayCenterX - frameWidth  / 2 + frameBold;
        rect.fill(g2d, cEarth,
                rect.makeParam(earthX, earthY, frameWidth - frameBold * 2, earthH - frameBold)
        );

        Color cStarAndMoon = !isGameOver ? colorStarAndMoon : colorGameOver;
        font.setStr(g2d, font.Meiryo(20), cStarAndMoon);
        font.drawStr(g2d, "★", 200, 100);
        font.drawStr(g2d, "★", 300, 160);
        font.drawStr(g2d, "★", 400, 130);

        circle.fillRegular(g2d, cStarAndMoon,
                circle.makeParamRegular(550, 150, 25),
                circle.makeSide(circle.CENTER, circle.CENTER)
        );
        circle.fillRegular(g2d, Color.BLACK,
                circle.makeParamRegular(550 - 10, 150, 20),
                circle.makeSide(circle.CENTER, circle.CENTER)
        );
    }

    // 操作説明とReady?のやつ
    public void drawExplain(Graphics2D g2d) {
        drawER(g2d, controlExplainStr);
    }
    public void drawReady(Graphics2D g2d) {
        drawER(g2d, readyStr);
    }
    public void drawER(Graphics2D g2d, String str) {
        int w = font.strWidth(g2d, fontExplain, str);
        int x = displayCenterX - w / 2;
        int y = displayCenterY + frameHeight - earthH;
        font.setStr(g2d, fontExplain, Color.WHITE);
        font.drawStr(g2d, str, x, y);
    }

    // キャラクタ
    public void drawCharacter(Graphics2D g2d, Chara chara) {
        int x = chara.getX();
        int h = (int) (chara.getJumpH() * chara.getHeightUnit());
        int posX = displayCenterX + x;
        int posY = earthY - h + 3;

        /*
        rect.fill(g2d, new Color(0, 0, 255, 50),
                rect.makeParam(posX, posY, 36, 48),
                rect.makeSide(rect.CENTER, rect.BOTTOM)
        );
         */

        int dir = chara.getDir();
        dtz.fill(g2d, Color.RED,
                dtz.makeParam(posX, posY, 0, charaWidth, charaHeight),
                dtz.makeSide(dtz.CENTER, dtz.BOTTOM, dtz.HORIZONTAL)
        );
        dtz.draw(g2d, Color.BLACK,
                dtz.makeParam(posX, posY, 0, charaWidth, charaHeight),
                dtz.makeSide(dtz.CENTER, dtz.BOTTOM, dtz.HORIZONTAL)
        );
        circle.fillRegular(g2d, Color.RED,
                circle.makeParamRegular(posX + dir * 6, posY - charaHeight + charaWidth / 2, charaWidth / 2),
                circle.makeSide(circle.CENTER, circle.BOTTOM)
        );
        circle.drawRegular(g2d, Color.BLACK,
                circle.makeParamRegular(posX + dir * 6, posY - charaHeight + charaWidth / 2, charaWidth / 2),
                circle.makeSide(circle.CENTER, circle.BOTTOM)
        );
    }

    // トマト
    public void drawTomatoes(Graphics2D g2d, List<Tomato> tomatoes) {
        for(Tomato tomato : tomatoes) {
            if(!tomato.isGotten()) {
                // 落下中のトマト
                if(!tomato.isLanding()) {
                    int x = tomato.getX();
                    int y = tomato.getY() - 4;
                    int w = tomato.getWidth();
                    int h = tomato.getHeight();
                    circle.fill(g2d, colorTomato,
                            circle.makeParam(displayCenterX + x, earthY - y, w, h),
                            circle.makeSide(circle.CENTER, circle.BOTTOM)
                    );
                    int padX = w / 4;
                    int padY = h * 3 / 4;
                    font.setStr(g2d, fontTomatoStem, colorTomatoStem);
                    font.drawStr(g2d, "★", displayCenterX + x - padX, earthY - y - padY);
                }
                // 落ちたトマト
                else {
                    int x = tomato.getX();
                    int y = tomato.getY();
                    int w = tomato.getWidth() + 5;
                    int h = tomato.getHeight() - 5;

                    circle.fill(g2d, colorTomatoFallen,
                            circle.makeParam(displayCenterX + x, earthY - y + h, w, h),
                            circle.makeSide(circle.CENTER, circle.BOTTOM)
                    );
                }
            }
        }
    }

    // スコア
    public void drawScore(Graphics2D g2d, int score, int hiScore) {
        int x = displayCenterX - frameWidth / 2 + 20;
        int y = displayCenterY - frameHeight / 2 + 30;
        String paddedScore = calc.paddingZero(score, 5);
        String paddedHiScore = calc.paddingZero(hiScore, 5);
        String scoreStr = "SCORE: " + paddedScore + "  HI-SCORE: " + paddedHiScore;

        font.setStr(g2d, fontScore, Color.WHITE);
        font.drawStr(g2d, scoreStr, x, y);
    }

    // ゲームオーバー
    public void drawGameOver(Graphics2D g2d) {
        int goW = font.strWidth(g2d, fontGameOver, gameOverStr);
        int goX = displayCenterX - goW / 2;
        int goY = displayCenterY + 50;
        font.setStr(g2d, fontGameOver, Color.WHITE);
        font.drawStr(g2d, gameOverStr, goX, goY);

        int pekW = font.strWidth(g2d, fontPressEnterKey, pressEnterKeyStr);
        int pekX = displayCenterX - pekW / 2;
        int pekY = displayCenterY + 100;
        font.setStr(g2d, fontPressEnterKey, Color.WHITE);
        font.drawStr(g2d, pressEnterKeyStr, pekX, pekY);
    }

    // ------------------------------------------------------ //

    private final DrawPolygon rect = new DrawRect();
    private final DrawPolygon circle = new DrawOval();
    private final DrawTrapezium dtz = new DrawTrapezium();
    private final FontUtil font = new FontUtil();

    private final int displayWidth;
    private final int displayHeight;
    private final int displayCenterX;
    private final int displayCenterY;

    private final int frameWidth;
    private final int frameHeight;
    private final int frameBold = 8;
    private final int earthH;
    private final int earthY;

    private final int charaHeight = 36;
    private final int charaWidth = 24;

    private final Font fontExplain = font.MSGothic(20);
    private final Font fontTomatoStem = font.MSGothic(8);
    private final Font fontScore = font.MSGothic(16);
    private final Font fontGameOver = font.MSGothic(84, font.BOLD);
    private final Font fontPressEnterKey = font.MSGothic(20);

    private final Color colorFrame = new Color(60, 120, 60);
    private final Color colorEarth = new Color(120, 60, 120);
    private final Color colorStarAndMoon = new Color(250, 170, 0);
    private final Color colorTomato = new Color(160, 50,  60);
    private final Color colorTomatoFallen = new Color(60, 20,  30);
    private final Color colorTomatoStem = new Color(60, 120, 60);
    private final Color colorGameOver = new Color(255, 0, 0);

    private final String controlExplainStr = "←→で移動　↑でジャンプ";
    private final String readyStr = "Ready?";
    private final String gameOverStr = "GAME OVER";
    private final String pressEnterKeyStr = "PRESS ENTER KEY";
}
