package com.smartjob.cl.service;

import java.util.List;

/**
 * This interface generics with the operations CRUD of a class. <br/>
 * <b>Class</b>: {@link CrudService}<br/>
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
public interface CrudService<T, ID> {

    T saveService(T obj);

    void saveAllService(List<T> listObj);

    T updateService(T obj);

    void deleteService(T obj);

    T findByIdService(ID id);

    List<T> findAllService();

}
