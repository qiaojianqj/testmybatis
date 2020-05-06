package com.leo;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TranController {
    @Autowired
    RoleMapperUse roleMapperUse;
    @Autowired
    TranService tranService;

    @RequestMapping("/test/transactional")
    public String doSqlInTransactional() {
        roleMapperUse.throwInTransactional (1);
        return "hehe";
    }

    @RequestMapping("/test/thread")
    public String testThreadConcurrent() {
        String thread = Thread.currentThread ().toString ();
        System.out.println ( thread + "in test thread" );
        return thread;
    }

    @RequestMapping("/test/mobileLogin")
    public String testMobileLogin(@RequestParam String name) throws Exception {
        return tranService.mobileLoginSubCmd (name);
    }

    @RequestMapping("/test/visitorLogin")
    public String testVisitorLogin(@RequestParam String name) throws Exception {
        return tranService.visitorLoginSubCmd (name);
    }


    @RequestMapping("/test/ab")
    public String apacheBench(@RequestParam String name) throws Exception {
        return "HELLO-" + name;
    }


}
