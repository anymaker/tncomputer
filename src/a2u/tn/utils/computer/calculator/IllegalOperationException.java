package a2u.tn.utils.computer.calculator;

/**
 * Exception when operation can not implemented for values
 */
public class IllegalOperationException extends RuntimeException {
  public IllegalOperationException(String message) {
    super(message);
  }
}
