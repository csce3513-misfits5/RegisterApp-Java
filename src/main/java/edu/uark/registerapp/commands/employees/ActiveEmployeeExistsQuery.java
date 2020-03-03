package edu.uark.registerapp.commands.employees;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uark.registerapp.commands.ResultCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class ActiveEmployeeExistsQuery implements ResultCommandInterface<Employee>
{
    @Override
    public Employee execute()
    {
        //Optional class used to determine if there is an activeUser
        final Optional<EmployeeEntity> employeeEntity =         
            this.employeeRepository.existsByIsActive(this.isActive); 
        if (employeeEntity.isPresent()) //activeUserfound
        {
            return new Employee(employeeEntity.get());
        }
        else
        {
            throw new NotFoundException("Employee");
        }
    }
    
    //execute uses this to Query using employee ID
    private boolean isActive;
	public boolean getIsActive() {
		return this.isActive;
	}
	public ActiveEmployeeExistsQuery setIsActive(final boolean isActive) {
		this.isActive = isActive;
		return this;
	}
    
    @Autowired
    private EmployeeRepository employeeRepository;
}