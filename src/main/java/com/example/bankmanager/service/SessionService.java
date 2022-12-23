package com.example.bankmanager.service;

import com.example.bankmanager.entity.Session;
import com.example.bankmanager.repository.SessionRepo;
import com.example.bankmanager.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	private SessionRepo sessionRepo;

	public void save(Session session){
		sessionRepo.save(session);
	}

	public void remove(Session session){
		if(session == null || session.getId() == null){
			return;
		}
		session.setStatus(Constants.IN_ACTIVE);
		sessionRepo.save(session);
	}

}
