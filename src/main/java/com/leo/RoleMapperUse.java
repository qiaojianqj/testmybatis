package com.leo;

import com.leo.dao.RoleMapper;
import com.leo.domain.Role;
import com.leo.domain.RoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
