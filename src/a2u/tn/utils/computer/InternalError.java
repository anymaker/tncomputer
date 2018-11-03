package a2u.tn.utils.computer;

/**
 * Throw when error in internal code
 */
public class InternalError extends RuntimeException {
  public InternalError(String message) {
    super(message);
  }
}
