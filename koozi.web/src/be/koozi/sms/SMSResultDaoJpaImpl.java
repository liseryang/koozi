package be.koozi.sms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;

import be.koozi.entity.EntityDaoJpaImpl;

@Repository
public class SMSResultDaoJpaImpl extends EntityDaoJpaImpl<SMSResult, Long> implements SMSResultDao {

	@Autowired
	public SMSResultDaoJpaImpl( JpaTemplate jpaTemplate) {
		super(SMSResult.class, jpaTemplate);
	}

	@Override
	public SMSResult findBySMS(final Long smsId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("smsId", smsId);
		return find(params).iterator().next();
	}

}
