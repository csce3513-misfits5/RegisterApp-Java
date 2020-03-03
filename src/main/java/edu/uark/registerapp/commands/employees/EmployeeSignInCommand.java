package edu.uark.registerapp.commands.employees;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.api.Employee;
import edu.uark.registerapp.models.api.EmployeeSignIn;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;
import edu.uark.registerapp.models.repositories.EmployeeRepository;

@Service
public class EmployeeSignInCommand implements VoidCommandInterface
{
    @Transactional
    @Override
    public void execute() {
        this.validateProperties();
        int id = Integer.parseInt(this.employee.getEmployeeId()); //uses Str empId as an Int
        byte[] password = this.employee.getPassword().getBytes(); //convert str password to byte array
        final Optional<EmployeeEntity> employeeEntity =
            this.employeeRepository.findByEmployeeId(id); //Query
            if (employeeEntity.isPresent()) { //Employee Found
                if (!Arrays.equals(this.getPassword(), password)); //not sure how to do this
                {
                    throw new NotFoundException("EmployeeSignIn"); //Passwords don't match?
                }
            }

        final Optional<ActiveUserEntity> userEntity =
            this.activeUserRepository.findByEmployeeId(this.getEmployeeId()); //Query active user table
        if(userEntity.isPresent()) //active user with supplied id found
        {
            this.activeUserRepository.save(userEntity.get()); //update session key
        }
        else //add new ActiveUser to table
        {
            //*THIS COULD CAUSE ERRORS
            this.setSessionKey(this.getSessionKey()); //this will be set in employee sign-in route controller
            this.activeUserRepository.save(new ActiveUserEntity(this.employeeId, this.classification, this.name, this.employeeId, this.sessionKey));
        }
        
    }
    

    //Properties for EmployeeSignIn
    private EmployeeSignIn employee;
    public EmployeeSignIn getEmployeeSignIn() {
		return this.employee;
	}
	public EmployeeSignInCommand setEmployeeSignIn(final EmployeeSignIn employee) {
		this.employee = employee;
		return this;
    }
    
    private byte[] password;
	public byte[] getPassword() {
		return this.password;
	}

	public EmployeeSignInCommand setPassword(final byte[] password) {
		this.password = password;
		return this;
    }

    private String lastName;
	public String getLastName() {
		return this.lastName;
	}

	public EmployeeSignInCommand setLastName(final String lastName) {
		this.lastName = lastName;
		return this;
    }
    private String firstName;
	public String getFirstName() {
		return this.firstName;
	}

	public EmployeeSignInCommand setFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
    }

    //Properties for ActiveUserEntity
    private UUID employeeId;
	public UUID getEmployeeId() {
		return this.employeeId;
	}

	public EmployeeSignInCommand setEmployeeId(final UUID employeeId) {
		this.employeeId = employeeId;
		return this;
    }

	private String name;
	public String getName() {
		return this.name;
	}

	public EmployeeSignInCommand setName(final String name) {
		this.name = name;
		return this;
    }
    
	private int classification;
	public int getClassification() {
		return this.classification;
	}

	public EmployeeSignInCommand setClassification(final int classification) {
		this.classification = classification;
		return this;
	}

    private String sessionKey;
	public String getSessionKey() {
		return this.sessionKey;
	}

	public EmployeeSignInCommand setSessionKey(final String sessionKey) {
    	this.sessionKey = sessionKey;
    	return this;
    }

    private void validateProperties() {
		if (StringUtils.isBlank(employee.getEmployeeId()) || StringUtils.isBlank(employee.getPassword())) {
			throw new UnprocessableEntityException("EmployeeSignIn");
		}
    }

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ActiveUserRepository activeUserRepository;
}