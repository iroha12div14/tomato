package calc;

// ちょっとした煩わしい計算処理するときに便利なやつ
public class CalcUtil {
    private int digit(int fraction){
        // (int)10^nにすると桁処理が上手く行かないのでこの処理にしている
        return (int) Math.pow(10, fraction);
    }

    // 余り
    public int mod(int val, int mod) {
        int m = (mod + val) % mod;
        return (m < 0) ? mod(m, mod) : m;
    }
    // 割って指定桁数の小数で出す
    public float div(int val, int div, int fraction) {
        int digit = digit(fraction);
        return (float) Math.round( (float) digit * val / div) / digit;
    }
    // 割って四捨五入
    public int div(int val, int div) {
        return Math.round( (float) val / div);
    }

    // 2乗
    public double pow2(int val) {
        return Math.pow(val, 2);
    };
}
