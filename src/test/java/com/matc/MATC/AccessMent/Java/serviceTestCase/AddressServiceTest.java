package com.matc.MATC.AccessMent.Java.serviceTestCase;

import com.matc.MATC.AccessMent.Java.model.Address;
import com.matc.MATC.AccessMent.Java.model.Employee;
import com.matc.MATC.AccessMent.Java.model.Person;
import com.matc.MATC.AccessMent.Java.repository.AddressRepository;
import com.matc.MATC.AccessMent.Java.repository.EmployeeRepository;
import com.matc.MATC.AccessMent.Java.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Slf4j
public class AddressServiceTest {
    @InjectMocks
    AddressService addressService;

    @Mock
   private AddressRepository addressrepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    public void getAddressByIdTest(){
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        Address address = new Address(1l, "chennai", "porur", "900090",employee);
        when(addressrepository.findById(1l)).thenReturn(Optional.of(address));

        address = addressService.getAddressById(address.getAddId());
        assertEquals("chennai", address.getLocation());
        assertEquals("porur", address.getLandMark());
    }

    @Test
    public void getAllAddress(){
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        Address address = new Address(1l, "chennai", "porur", "900090",employee);
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        when(addressrepository.findAll()).thenReturn(addressList);
       addressList= addressService.getAllAddress();
        assertEquals(1,addressList.size());
        verify(addressrepository,times(1)).findAll();
    }

    @Test
    public void deleteAddress(){
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        Address address = new Address(1l, "chennai", "porur", "900090",employee);
        when(addressrepository.findById(address.getAddId())).thenReturn(Optional.of(address));
      addressService.deleteByAddress(address.getAddId());
        verify(addressrepository,times(1)).deleteById(address.getAddId());
    }

    @Test
    public void updateAddress() throws Exception {
        Person person = new Person(1l, "siva", "siva@gmail.com", "234567890");
        Employee employee = new Employee(1l, "one", "java", 12l, person);
        Address address = new Address(1l, "chennai", "porur", "900090",employee);
        given(addressrepository.save(address)).willReturn(address);
        when(addressrepository.findById(address.getAddId())).thenReturn(Optional.of(address));
        address.setAddId(1l);
        address.setLocation("chennai2");
        address.setLandMark("chennai");
        address.setPinCode("90-9000");
        address = addressService.updateAddress(address.getAddId(),address);
        assertThat(address.getLandMark()).isEqualTo("chennai");
        assertThat(address.getPinCode()).isEqualTo("90-9000");
    }

    @Test
    public void createAddress(){

    }

}
