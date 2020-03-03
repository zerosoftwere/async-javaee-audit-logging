package net.xerosoft.common;

import lombok.Data;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@Data
public class Page {
    private @QueryParam("offset") @DefaultValue("0") Integer offset;
    private @QueryParam("limit") @DefaultValue("10") Integer limit;
}
