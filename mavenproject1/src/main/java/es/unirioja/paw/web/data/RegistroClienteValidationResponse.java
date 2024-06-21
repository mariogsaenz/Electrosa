package es.unirioja.paw.web.data;

import java.util.ArrayList;
import java.util.List;

public class RegistroClienteValidationResponse {

    private List<String> messageCollection = new ArrayList<>();

    private boolean success;

    public List<String> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(List<String> messageCollection) {
        this.messageCollection = messageCollection;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
