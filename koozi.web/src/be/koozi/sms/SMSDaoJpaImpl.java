package be.koozi.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class SMSDaoJpaImpl extends EntityDaoJpaImpl<SMS, Long> implements SMSDao {

	@Autowired
	public SMSDaoJpaImpl(JpaTemplate jpaTemplate) {
		super(SMS.class, jpaTemplate);
	}

	@Override
	public List<SMS> findByUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		return find(params, "id DESC", 10, 0);
	}

}
