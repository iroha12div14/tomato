package draw;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ParamDraw implements Draw{

    public Map<Param, Integer> makeParam(int x, int y, int w, int h) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.WIDTH, w);
        param.put(Param.HEIGHT, h);
        return param;
    }
    public Map<Param, Integer> makeParamRegular(int x, int y, int r) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.RADIUS, r);
        return param;
    }
    public Map<Param, Integer> makeParamArc(int x, int y, int w, int h, int a1, int a2) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.WIDTH, w);
        param.put(Param.HEIGHT, h);
        param.put(Param.ANGLE, a1);
        param.put(Param.ANGLE2, a2);
        return param;
    }
    public Map<Param, Integer> makeParamTrapezium(int x, int y, int wt, int wb, int h) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.WIDTH_TOP, wt);
        param.put(Param.WIDTH_BOTTOM, wb);
        param.put(Param.HEIGHT, h);
        return param;
    }
    public Map<Param, Integer> makeParamLine(int x, int y, int x2, int y2) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.X2, x2);
        param.put(Param.Y2, y2);
        return param;
    }
    public Map<Param, Integer> makeParamLineWithAngle(int x, int y, int l, int a) {
        Map<Param, Integer> param = new HashMap<>();
        param.put(Param.X, x);
        param.put(Param.Y, y);
        param.put(Param.LENGTH, l);
        param.put(Param.ANGLE, a);
        return param;
    }

    public Map<Side, Integer> makeSide(int sideX, int sideY) {
        Map<Side, Integer> side = new HashMap<>();
        side.put(Side.X, sideX);
        side.put(Side.Y, sideY);
        return side;
    }
    public Map<Side, Integer> makeSideTrapezium(int sideX, int sideY, int sideDir) {
        Map<Side, Integer> side = new HashMap<>();
        side.put(Side.X, sideX);
        side.put(Side.Y, sideY);
        side.put(Side.DIR, sideDir);
        return side;
    }

    @Override
    public void draw(Graphics2D g2d, Color c, Map<Param, Integer> param) { }
    @Override
    public void fill(Graphics2D g2d, Color c, Map<Param, Integer> param) { }
}
