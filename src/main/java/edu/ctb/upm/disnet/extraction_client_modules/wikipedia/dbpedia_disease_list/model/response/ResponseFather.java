package edu.ctb.upm.disnet.extraction_client_modules.wikipedia.dbpedia_disease_list.model.response;
/**
 * Created by gerardo on 02/11/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className ResponseFather
 * @see
 */
public class ResponseFather {

    private String token;
    private boolean authorized;
    private String authorizationMessage;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getAuthorizationMessage() {
        return authorizationMessage;
    }

    public void setAuthorizationMessage(String authorizationMessage) {
        this.authorizationMessage = authorizationMessage;
    }

    @Override
    public String toString() {
        return "ResponseFather{" +
                "token='" + token + '\'' +
                ", authorized=" + authorized +
                ", authorizationMessage='" + authorizationMessage + '\'' +
                '}';
    }
}
