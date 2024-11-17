package com.smartjob.cl.service.impl;

import com.smartjob.cl.entity.Phone;
import com.smartjob.cl.repository.PhoneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class test for PhoneServiceImpl. <br/>
 * <b>Class</b>: {@link PhoneServiceImplTest}<br/>
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
 *     <li>Nov 17, 2024 (JAR) Creation class.</li>
 *     </ul>
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class PhoneServiceImplTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneServiceImpl phoneService;

    private Phone phone;

    @BeforeEach
    void setUp() {
        phone = new Phone();
        phone.setId(1L);
        phone.setNumber("123456789");
    }

    @Test
    void testSaveService() {
        when(phoneRepository.save(phone)).thenReturn(phone);

        Phone savedPhone = phoneService.saveService(phone);

        assertNotNull(savedPhone);
        assertEquals("123456789", savedPhone.getNumber());

        verify(phoneRepository, times(1)).save(phone);
    }

    @Test
    void testSaveAllService() {
        Phone phone2 = new Phone();
        phone2.setId(2L);
        phone2.setNumber("987654321");

        List<Phone> phones = Arrays.asList(phone, phone2);

        when(phoneRepository.saveAll(phones)).thenReturn(phones);

        phoneService.saveAllService(phones);

        verify(phoneRepository, times(1)).saveAll(phones);
    }

    @Test
    void testUpdateService() {
        when(phoneRepository.save(phone)).thenReturn(phone);

        Phone updatedPhone = phoneService.updateService(phone);

        assertNotNull(updatedPhone);
        assertEquals("123456789", updatedPhone.getNumber());

        verify(phoneRepository, times(1)).save(phone);
    }

    @Test
    void testDeleteService() {
        phoneService.deleteService(phone);

        verify(phoneRepository, times(1)).delete(phone);
    }

    @Test
    void testFindByIdServiceFound() {
        when(phoneRepository.findById(1L)).thenReturn(Optional.of(phone));

        Phone foundPhone = phoneService.findByIdService(1L);

        assertNotNull(foundPhone);
        assertEquals("123456789", foundPhone.getNumber());
        verify(phoneRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdServiceNotFound() {
        when(phoneRepository.findById(1L)).thenReturn(Optional.empty());

        Phone foundPhone = phoneService.findByIdService(1L);

        assertNotNull(foundPhone);
        assertNull(foundPhone.getNumber());
        verify(phoneRepository, times(1)).findById(1L);
    }

    @Test
    void testFindAllService() {
        List<Phone> phones = Arrays.asList(phone, new Phone());
        when(phoneRepository.findAll()).thenReturn(phones);

        List<Phone> allPhones = phoneService.findAllService();

        assertNotNull(allPhones);
        assertEquals(2, allPhones.size());
        verify(phoneRepository, times(1)).findAll();
    }

}