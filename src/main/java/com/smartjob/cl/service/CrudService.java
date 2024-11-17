package com.smartjob.cl.service;

import java.util.List;

public interface CrudService<T, ID> {

    T saveService(T obj);

    void saveAllService(List<T> listObj);

    T updateService(T obj);

    void deleteService(T obj);

    T findByIdService(ID id);

    List<T> findAllService();

}
