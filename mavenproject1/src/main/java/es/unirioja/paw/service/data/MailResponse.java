package es.unirioja.paw.service.data;

/**
 * Resultado del envío de email
 */
public class MailResponse {

    private final boolean success;

    public MailResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

}
