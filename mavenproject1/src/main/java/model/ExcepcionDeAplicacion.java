package es.unirioja.paw.model;

public class ExcepcionDeAplicacion extends Exception {

    public ExcepcionDeAplicacion() {
        super();
    }

    public ExcepcionDeAplicacion(String msg) {
        super(msg);
    }

    public ExcepcionDeAplicacion(Exception nested) {
        super(nested);
    }

    public ExcepcionDeAplicacion(String msg, Exception nested) {
        super(msg, nested);
    }

//
//  private Throwable nested;
//  /**
//   * Constructor
//   */
//  public ExcepcionDeAplicacion() {
//    super();
//  }
//
//  /**
//   * Constructor
//   * @param msg mensage de error
//   */
//  public ExcepcionDeAplicacion(String msg) {
//    super(msg);
//  }
//
//  /**
//   * Constructor
//   * @param nested excepción anidada sobre la que se construye la nueva excepcion
//   */
//  public ExcepcionDeAplicacion(Exception nested) {
//    this(null, nested);
//  }
//
//  /**
//   * Constructor
//   * @param msg mensage de error
//   * @param nested excepción anidada sobre la que se construye la nueva excepcion
//   */
//  public ExcepcionDeAplicacion(String msg, Exception nested) {
//    super(msg);
//    this.nested = nested;
//  }
//  /**
//   * Devuelve el mensaje de exception, añadiendo, entre "[...]", el mensaje
//   * asociado a la exception que motivó la creación de esta ExecutionException.
//   */
//  public String getMessage() {
//    String msg = super.getMessage();
//    if (this.nested != null)
//      msg = msg+"["+nested.getClass().getName()+":"+nested.getMessage()+"]";
//
//    return msg;
//  }
}
