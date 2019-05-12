package a2u.tn.utils.computer;

/**
 * Error when value is overflowed
 */
public class ValueOverflowException extends RuntimeException {
  public ValueOverflowException(String message) {
    super(message);
  }
}
