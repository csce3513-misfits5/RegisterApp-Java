package edu.uark.registerapp.commands.activeUsers;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.uark.registerapp.commands.VoidCommandInterface;
import edu.uark.registerapp.commands.exceptions.NotFoundException;
import edu.uark.registerapp.commands.exceptions.UnprocessableEntityException;
import edu.uark.registerapp.models.entities.ActiveUserEntity;
import edu.uark.registerapp.models.entities.EmployeeEntity;
import edu.uark.registerapp.models.repositories.ActiveUserRepository;

@Service
public class ActiveUserDeleteCommand implements VoidCommandInterface
{
    @Transactional
    @Override
    public void execute()
    {
        this.validateProperties(); //validates that first name and last name are not empty strings
        final Optional<ActiveUserEntity> activeUserEntity =
            this.activeUserRepository.findBySessionKey(this.sessionKey);
        if(!activeUserEntity.isPresent()) //No user w associated Session key found
        {
            throw new NotFoundException("Active User");
        }

        this.activeUserRepository.delete(activeUserEntity.get()); //Delete user that Optional.get() returns
    }

    //Properties
    private String sessionKey;
	public String getSessionKey() {
		return this.sessionKey;
	}

	public ActiveUserDeleteCommand setSessionKey(final String sessionKey) {
    	this.sessionKey = sessionKey;
    	return this;
    }

    public String lastName;
    public String getLastName() {
		return this.lastName;
	}

	public ActiveUserDeleteCommand setLastName(final String lastName) {
		this.lastName = lastName;
		return this;
    }
    
    private String firstName;
	public String getFirstName() {
		return this.firstName;
	}

	public ActiveUserDeleteCommand setFirstName(final String firstName) {
		this.firstName = firstName;
		return this;
	}

    //validate properties helper
    private void validateProperties() {
		if (StringUtils.isBlank(this.firstName) || StringUtils.isBlank(this.lastName)) {
			throw new UnprocessableEntityException("ActiveUser");
		}
	}

    @Autowired
    private ActiveUserRepository activeUserRepository;
}