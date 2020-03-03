package edu.uark.registerapp.models.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import edu.uark.registerapp.models.entities.EmployeeEntity;

public interface EmployeeRepository extends CrudRepository<EmployeeEntity, UUID> {
	Optional<EmployeeEntity> existsByIsActive(boolean isActive); //I changed the return type, was previously boolean, could cause errors JC
	boolean existsByEmployeeId(int employeeId);
	Optional<EmployeeEntity> findById(UUID id);
	Optional<EmployeeEntity> findByEmployeeId(int employeeId);
}
