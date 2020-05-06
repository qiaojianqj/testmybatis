package com.leo;

import com.leo.dao.AccountMapper;
import com.leo.domain.Account;
import com.leo.domain.AccountParam;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 */
@Service
public class TranService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    RoleMapperUse roleMapperUse;
    @Autowired
    TranAnnoTakeEffectService tranAnnoTakeEffectService;

    //@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public String mobileLoginSubCmd(String name) throws Exception {

        Account selfAccount = this.accountMapper.selfDefinedSelect(4);
        System.out.println ( selfAccount.getAccid () );

        Account account = this.accountMapper.getByUserName(name);
        if (account == null) {
            System.out.println ( "account null" );
        }
        //Thread.sleep ( 2000 );
        // 更新登录信息
        AccountParam param = new AccountParam();
        param.createCriteria().andUsernameEqualTo(name);
        if (!tranAnnoTakeEffectService.updateLoginInfo(name, "DWJ_qj_test1", "9001", param)) {
            return "update error";
        }
        Account newAccount = this.accountMapper.selectByPrimaryKey(account.getId());
        if (newAccount != null) {
            System.out.println ( "set login token" );
        }
        //Thread.sleep ( 2000 );
        return "update ok";
    }

    //@Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public String visitorLoginSubCmd(String name) throws Exception {
        AccountParam param = new AccountParam();
        param.createCriteria().andUsernameEqualTo(name).andCounttypeEqualTo(1);

        Account account = this.accountMapper.selectByExample(param).get(0);
        if (account != null) {
            //Thread.sleep ( 2000 );
            // 更新用户登录信息。
            if (!tranAnnoTakeEffectService.updateLoginInfo(name, "DWJ_qj_test2","9002", param)) {
                System.out.println ( "visitor update error" );
            }
        } else { // 老的未绑定的账号
            account = new Account();
            account.setUsername(name);
            account.setDevstring(name);
            account.setDevicetype(3);
            account.setLogindevstring(name);
            account.setCounttype(1);
            account.setPackageflag("DWJ_qj_test2");
            account.setPackagetype(0);
            account.setLoginpackageflag("DWJ_qj_test2");
            account.setLoginpackagetype(302);

            account.setRegiestip("1.2.3.4");
            account.setSpreadid(0);
            account.setCreatetime(LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            account.setRegisttime(System.currentTimeMillis ());
            account.setRoletime(0l);
            account.setPversion("9002");
            account.setRegversion("9002");

            int count = this.accountMapper.insertSelective(account);
            if (count == 0 || account.getId() <= 0) {
               System.out.println ( "reg new account error" );
            }

            Account update = new Account();
            update.setLastlogintime(LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
            count = this.accountMapper.updateByExampleSelective(update, param);
            if (count == 0) {
               System.out.println ( "visitor login error" );
            }
            //Thread.sleep ( 2000 );
        }
        return "update ok";
    }

   }