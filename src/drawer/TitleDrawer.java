package drawer;

import calc.CalcUtil;
import draw.DrawPolygon;
import draw.DrawRect;
import font.FontUtil;

import java.awt.*;

public class TitleDrawer {
    // コン
    public TitleDrawer(int displayWidth, int displayHeight) {
        this.displayWidth = displayWidth;
        this.displayHeight = displayHeight;

        displayCenterX = this.displayWidth / 2;
        displayCenterY = this.displayHeight / 2;
    }

    // 枠
    public void drawFrame(Graphics2D g2d) {
        rect.fill(g2d, colorFrame,
                rect.makeParam(displayCenterX, displayCenterY, frameWidth, frameHeight),
                rect.makeSide(rect.CENTER, rect.CENTER)
        );
        rect.fill(g2d, Color.BLACK,
                rect.makeParam(displayCenterX, displayCenterY,
                        frameWidth - frameBold * 2, frameHeight - frameBold * 2),
                rect.makeSide(rect.CENTER, rect.CENTER)
        );
    }

    // タイトル
    public void drawTitle(Graphics2D g2d) {
        int titleStrWidth = font.strWidth(g2d, fontTitle, titleStr);
        int x = displayCenterX - titleStrWidth / 2;
        int y = displayCenterY - 40;
        font.setStr(g2d, fontTitle, colorTitleShadow);
        font.drawStr(g2d, titleStr, x + 3, y + 3);
        font.setStr(g2d, fontTitle, colorTitle);
        font.drawStr(g2d, titleStr, x, y);
    }

    // メニュー
    public void drawMenu(Graphics2D g2d, int hiScore) {
        int peW = font.strWidth(g2d, fontPressEnter, pressEnterStr);
        int peX = displayCenterX - peW / 2;
        int peY = displayCenterY + 30;
        font.setStr(g2d, fontPressEnter, Color.WHITE);
        font.drawStr(g2d, pressEnterStr, peX, peY);

        String paddedHiScore = calc.paddingZero(hiScore,5);
        String hiScoreStr = this.hiScoreStr + paddedHiScore;
        int scoreW = font.strWidth(g2d, fontScore, hiScoreStr);
        int scoreX = displayCenterX - scoreW / 2;
        int scoreY = displayCenterY + 100;
        font.setStr(g2d, fontScore, Color.WHITE);
        font.drawStr(g2d, hiScoreStr, scoreX, scoreY);
    }

    // ------------------------------------------------------ //

    private final DrawPolygon rect = new DrawRect();
    private final FontUtil font = new FontUtil();
    private final CalcUtil calc = new CalcUtil();

    private final int displayWidth;
    private final int displayHeight;
    private final int displayCenterX;
    private final int displayCenterY;

    private final int frameWidth  = 640;
    private final int frameHeight = 360;
    private final int frameBold = 8;

    private final Color colorFrame = new Color(60, 120, 60);
    private final Color colorTitle = new Color(160, 50,  60);
    private final Color colorTitleShadow = new Color(250, 80, 80);

    private final Font fontTitle = font.MSGothic(108, font.BOLD);
    private final Font fontPressEnter = font.MSGothic(24);
    private final Font fontScore = font.MSGothic(32);

    private final String titleStr = "TOMATO";
    private final String pressEnterStr = "PRESS ENTER KEY";
    private final String hiScoreStr = "HI-SCORE: ";
}
