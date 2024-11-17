package com.smartjob.cl.service.impl;

import com.smartjob.cl.entity.Phone;
import com.smartjob.cl.repository.PhoneRepository;
import com.smartjob.cl.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void updateService(Phone obj) {
        repository.save(obj);
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
