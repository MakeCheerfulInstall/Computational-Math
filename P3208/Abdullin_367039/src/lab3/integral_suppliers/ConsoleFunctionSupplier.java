package lab3.integral_suppliers;

import lab2.utils.ConsoleWorker;
import lab3.domains.Equation;
import lab3.domains.Integral;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ConsoleFunctionSupplier implements FunctionSupplier {
  private final Map<String, Equation> functions = new HashMap<>();

  {
    functions.put(
        "1",
        new Equation(
            (x) ->
                x.pow(3)
                    .negate()
                    .subtract(x.pow(2))
                    .subtract(BigDecimal.valueOf(2).multiply(x))
                    .add(BigDecimal.ONE),
            "-x^3 - x^2 - 2x + 1"));
  }

  @Override
  public Integral get() {
    Integral i = new Integral();
    i.setFunction(chooseFunction());
    i.setLeft(chooseLeft());
    i.setRight(chooseRight());
    i.setEps(chooseEpsilon());
    i.setN(chooseStartN());
    return i;
  }

  private UnaryOperator<BigDecimal> chooseFunction() {
    ConsoleWorker.printMap(functions, "> Выберите уравнение:");
    return ConsoleWorker.getObjectsFromConsole(1, functions::get, Objects::nonNull)
        .iterator()
        .next()
        .getFun();
  }

  private BigDecimal chooseRight() {
    System.out.println("> Выберите b ({double} / {inf} / {-inf}):");
    return parseDouble();
  }

  private BigDecimal chooseLeft() {
    System.out.println("> Выберите a ({double} / {inf} / {-inf}):");
    return parseDouble();
  }

  private BigDecimal chooseEpsilon() {
    System.out.println("> Выберите точность:");
    return ConsoleWorker.getObjectsFromConsole(
            1, line -> BigDecimal.valueOf(Double.parseDouble(line)), Objects::nonNull)
        .iterator()
        .next();
  }

  private int chooseStartN() {
    System.out.println("> Выберите начальное разбиение:");
    return ConsoleWorker.getObjectsFromConsole(1, Integer::parseInt, Objects::nonNull)
        .iterator()
        .next();
  }

  private BigDecimal parseDouble() {
    String input =
        ConsoleWorker.getObjectsFromConsole(
                1,
                String::trim,
                line ->
                    line.equals("inf")
                        || line.equals("-inf")
                        || line.matches("^([+-]?\\d*\\.?\\d*)$"))
            .iterator()
            .next();

    double result;

    if (input.equals("inf")) {
      result = Double.POSITIVE_INFINITY;
    } else if (input.equals("-inf")) {
      result = Double.NEGATIVE_INFINITY;
    } else {
      result = Double.parseDouble(input);
    }

    return BigDecimal.valueOf(result);
  }

  @Override
  public String toString() {
    return "Console";
  }
}
