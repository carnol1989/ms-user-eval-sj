package com.smartjob.cl.service.impl;

import com.smartjob.cl.entity.Phone;
import com.smartjob.cl.repository.PhoneRepository;
import com.smartjob.cl.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This class implements the operations of the PhoneService. <br/>
 * <b>Class</b>: {@link PhoneServiceImpl}<br/>
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
@Service
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository repository;

    @Override
    public Phone saveService(Phone obj) {
        return repository.save(obj);
    }

    @Override
    public void saveAllService(List<Phone> listObj) {
        repository.saveAll(listObj);
    }

    @Override
    public Phone updateService(Phone obj) {
        return repository.save(obj);
    }

    @Override
    public void deleteService(Phone obj) {
        repository.delete(obj);
    }

    @Override
    public Phone findByIdService(Long id) {
        Optional<Phone> phoneOptional = repository.findById(id);
        return phoneOptional.orElseGet(Phone::new);
    }

    @Override
    public List<Phone> findAllService() {
        return repository.findAll();
    }
}
