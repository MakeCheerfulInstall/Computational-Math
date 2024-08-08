package Computational.math;

import java.util.function.Function;

public class CalculatorUtils {
    /**
     * Достоверность аппроксимации (коэффициент детерминации)
     * Чем ближе значение 𝑅^2 к единице (𝑅^2 → 1), тем надежнее
     * функция 𝜑 аппроксимирует исследуемый процесс.
     * Если 𝑅^2 ≥ 0,95,то говорят о высокой точности аппроксимации(модель хорошо описывает явление).
     * Если 0,75 ≤ 𝑅^< 0,95, то говорят об удовлетворительной аппроксимации (модель в целом адекватно описывает явление).
     * Если 0,5 ≤ 𝑅^2 <0,75, то говорят о слабой аппроксимации (модель слабо описывает явление).
     * Если 𝑅^2 < 0,5, то говорят, что точность аппроксимации недостаточна и модель требует изменения
     * @return R^2 коэффициент
     */
    public static double coefficientOfDetermination(FunctionalTable data, Function<Double,Double> phiFunction){
        var table = data.getTable();
        double chislitel = 0;
        double znamenatel= 0;
        for (int i = 0; i < table[0].length; i++) {
            var currentPhi = phiFunction.apply(table[0][i]);
            chislitel+= Math.pow(table[1][i] -currentPhi,2);
            znamenatel+= Math.pow(table[1][i] - (1d / table[0].length) * currentPhi,2);
        }
        return 1-chislitel/znamenatel;
    }
}
