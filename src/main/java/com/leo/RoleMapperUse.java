package com.leo;

import com.leo.dao.RoleMapper;
import com.leo.domain.Role;
import com.leo.domain.RoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @description: auto generator mapper use in springboot
 * @create: 2018-12-20 09:54
 */
@Component
public class RoleMapperUse {
    @Autowired
    private RoleMapper roleMapper;

    @PostConstruct
    public void useRoleMapper() {
        Role role = new Role ();
        role.setSeqid ( 1L );
        role.setName ( "经理" );
        role.setNo ( "manager" );
        role.setType ( 1 );
        role.setRemark ( "xxoo" );
        RoleExample roleExample = new RoleExample ();
        roleExample.createCriteria ().andNoEqualTo ( "manager" ).andSeqidEqualTo ( 1L );
        int count = roleMapper.updateByExampleSelective ( role,  roleExample);
        System.out.println ( "count: " + count );
    }

    @Transactional
    public void  throwInTransactional() {
        Role role = new Role ();
        role.setSeqid ( 3L );
        role.setName ( "谯剑" );
        role.setNo ( "xiaoluoluo" );
        role.setType ( 2 );
        role.setRemark ( "小螺螺" );
        int count = roleMapper.insert ( role );
        System.out.println ( "count: " + count );
        //加了Transactional注解，碰到异常会回滚数据库事务
        //没加注解，碰到异常也插入成功
        if (true) {
            throw  new RuntimeException ( "故意抛出异常" );
        }
    }
}
