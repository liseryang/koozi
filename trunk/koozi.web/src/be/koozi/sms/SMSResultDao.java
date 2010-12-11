package be.koozi.sms;

import be.koozi.entity.EntityDao;

public interface SMSResultDao extends EntityDao<SMSResult, Long> {

	SMSResult findBySMS(Long smsId);

}
