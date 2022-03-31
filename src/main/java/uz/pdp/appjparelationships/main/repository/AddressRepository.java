package uz.pdp.appjparelationships.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjparelationships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
