package com.leo;

import com.leo.dao.AccountMapper;
import com.leo.domain.Account;
import com.leo.domain.AccountParam;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 *
 */
@Service
public class TranAnnoTakeEffectService {
    private static RedissonClient redissonClient = Redisson.create ();
    static int seqId = 0;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    RoleMapperUse roleMapperUse;

    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public boolean updateLoginInfo(String deviceString, String packageFlag,String version, AccountParam param) {
        Account update = new Account();
        update.setLoginpackageflag(packageFlag);
        update.setPackagetype(0);
        update.setLastlogintime( LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        update.setLogindevstring(deviceString);
        update.setPversion(version);
        //Lock redisLock = redissonClient.getLock ( "test_mybatis_lock" );
        synchronized (this) {
        //try {
        //    if (redisLock.tryLock ( 10, TimeUnit.SECONDS )) {
                Account oldAccount = this.accountMapper.getByUserName ( deviceString );
                int oldAcid = oldAccount.getAccid () == null ? 0 : oldAccount.getAccid ();
                update.setAccid ( oldAcid + 1 );
                int count = this.accountMapper.updateByExampleSelective ( update, param );
                if (count == 0) { //return -3
                    return false;
                }
                //synchronized (this) {
                seqId++;
                if (seqId > 10) {
                    seqId = 0;
                    seqId++;
                }
                //}
                roleMapperUse.throwInTransactional ( seqId );
            //}
        //} catch (InterruptedException e) {
        //    e.printStackTrace ();
        //} finally {
            //throw exception 也会执行finally
            //redisLock.unlock ();
        //}
        }
        return true;
    }

}
