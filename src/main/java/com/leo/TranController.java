package com.leo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TranController {
    @Autowired
    RoleMapperUse roleMapperUse;

    @RequestMapping("/test/transactional")
    public String doSqlInTransactional() {
        roleMapperUse.throwInTransactional ();
        return "hehe";
    }
}
