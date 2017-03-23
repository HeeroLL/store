package com.ihidea.charge.mq;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import com.ihidea.charge.domain.bo.DistributionBoxDataBo;
import com.ihidea.charge.domain.bo.OrderInfoBo;
import com.ihidea.charge.utils.CommonUtils;
import com.ihidea.component.cache.redis.RedisClient;
import com.ihidea.core.util.JSONUtilsEx;
import com.ihidea.generator.dao.model.Stub;
import com.ihidea.generator.dao.model.StubGroup;

@Component
public class MQDataSender {

	private final static Logger logger = LoggerFactory.getLogger(MQDataSender.class);

	@Resource(name = "kafkaProducerChannel")
	private MessageChannel channel;

	public void sendOrderInfo(OrderInfoBo orderInfo) {

		Map<String, Object> params = new HashMap<String, Object>();

		params.put("info", orderInfo);

		// 设置订单中间数据
		{
			int hisSize = RedisClient.getRedisTemplate().opsForList().size("platform:orderId:" + orderInfo.getId() + ":status:his")
					.intValue();

			if (hisSize > 0) {
				params.put("his", RedisClient.getRedisTemplate(String.class).opsForList()
						.range("platform:orderId:" + orderInfo.getId() + ":status:his", 0, hisSize));
			}
		}

		send("open.orderInfo", params);
	}

	/**
	 * 如果桩是属于德和的桩,则发送数据消息
	 */
	public void sendStubStatus(String stubId, String status, String info) {

		if (CommonUtils.isStarchargeStub(stubId)) {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("stubId", stubId);
			params.put("status", status);

			if (StringUtils.isNotBlank(info)) {
				params.put("info", info);
			}

			params.put("time", new Date().getTime());
			send("open.stubStatus", params);
		}
	}

	public void sendChargeInfo(String stubId, String orderId, BigDecimal current, BigDecimal voltage, BigDecimal power, Integer soc,
			BigDecimal feeElectric, BigDecimal feeService) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("stubId", stubId);
		params.put("orderId", orderId);
		params.put("current", current);
		params.put("voltage", voltage);
		params.put("soc", soc);

		params.put("power", power);
		params.put("feeElectric", feeElectric);
		params.put("feeService", feeService);

		send("open.chargeInfo", params);
	}

	public void sendStubGroupInfo(StubGroup stubGroupInfo) {

		// 互联互通桩群信息不上报到数据通道
		if (CommonUtils.isStarchargeStubGroup(stubGroupInfo.getId())) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", "stubGroup");
			params.put("data", stubGroupInfo);

			send("open.dataInfo", params);
		}

		// 刷新集群中数据
		ONSIotSender.sendRefresh("stubGroupInfo", stubGroupInfo);
	}

	public void sendStubInfo(Stub stubInfo) {

		// 互联互通桩信息不上报到数据通道
		if (CommonUtils.isStarchargeStub(stubInfo.getId())) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type", "stub");
			params.put("data", stubInfo);

			send("open.dataInfo", params);
		}

		// 刷新集群中数据
		ONSIotSender.sendRefresh("stubInfo", stubInfo);
	}

	public void sendGisQueryStubGroupList(Double lat, Double lng) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lat", lat);
		params.put("lng", lng);
		params.put("time", new Date().getTime());

		send("open.gis.queryStubGroupList", params);
	}

	public void sendDistributionBoxInfo(DistributionBoxDataBo dbInfo) {
		send("open.distributionBox.info", dbInfo);
	}

	private void send(String topicId, Object value) {
		Message<String> mess = MessageBuilder.withPayload(JSONUtilsEx.serialize(value)).setHeader(KafkaHeaders.TOPIC, topicId).build();
		channel.send(mess);
	}

	/**
	 * 发送审计告警信息
	 */
	public void sendWarn(String stubId, String info) {

		logger.info("警报-桩:{},{}", new Object[] { stubId, info });

		sendStubStatus(stubId, "02090", info);

		sendStubStatus("000000", "02090", info);
	}

}
