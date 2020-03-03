package net.xerosoft;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=true)
public class NotFoundException extends RuntimeException {
}
