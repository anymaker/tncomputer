package a2u.tn.utils.computer.calculator;

/**
 * Calculation exception
 */
public class CalculatingException extends RuntimeException {
  public CalculatingException(String msg) {
    super(msg);
  }
  public CalculatingException(String message, Throwable cause) {
    super(message, cause);
  }
}
