package org.example;

public class math {
    private float result1;
    private float result2;
    private float result3;

    public static float getResult1(float x) {
        return exponent(x);
    }

    public static float getResult2(float x) {
        return linear(x);
    }

    public static float getResult3(float x) {
        return cosinus(x);
    }

    private static float exponent(float x){
        return (float) Math.exp(-0.5*x);
    }
    private static float linear(float x){
        return (float) 0.5*x+2;
    }
    private static float cosinus(float x){
        return (float) Math.exp(-0.5*x);
    }

    public float MathSum(float x){
        return exponent(x)+linear(x)+cosinus(x);
    }

}
