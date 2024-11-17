package com.smartjob.cl.service;

import com.smartjob.cl.entity.User;

/**
 * This interface with the operations of the User class. <br/>
 * <b>Class</b>: {@link UserService}<br/>
 * <b>Copyright</b>: &Copy; 2024 SmartJob. <br/>
 * <b>Company</b>: SmartJob. <br/>
 *
 * @author SmartJob <br/>
 *     <u>Service Provider</u>: Consultor TI <br/>
 *     <u>Developed by</u>: <br/>
 *     <ul>
 *     <li>Carlos Augusto Nole Machaca</li>
 *     </ul>
 *     <u>Changes</u>:<br/>
 *     <ul>
 *     <li>Nov 16, 2024 (JAR) Creation class.</li>
 *     </ul>
 * @version 1.0
 */
public interface UserService extends CrudService<User, Long> {

    User findByEmailService(String email);

}
