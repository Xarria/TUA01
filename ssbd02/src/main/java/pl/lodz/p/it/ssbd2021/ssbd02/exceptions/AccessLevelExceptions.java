package pl.lodz.p.it.ssbd2021.ssbd02.exceptions;

import javax.ejb.ApplicationException;
import javax.ws.rs.core.Response;

@ApplicationException(rollback = true)
public class AccessLevelExceptions extends GeneralException {
    public static String ERROR_ACCOUNT_LEVEL_UNIQUE = "ERROR.ACCOUNT_LEVEL_UNIQUE";

    public AccessLevelExceptions(Response.Status status, String key) {
        super(status, key);
    }

    public static AccessLevelExceptions createExceptionConflict(String key) {
        return new AccessLevelExceptions(Response.Status.CONFLICT, key);
    }
}
