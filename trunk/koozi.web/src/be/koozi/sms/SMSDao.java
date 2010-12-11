package be.koozi.sms;

import java.util.List;

import be.koozi.entity.EntityDao;

public interface SMSDao extends EntityDao<SMS, Long>  {
	List<SMS> findByUserId(String userId);
}
