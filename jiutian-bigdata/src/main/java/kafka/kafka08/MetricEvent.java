package kafka.kafka08;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * blog：http://www.54tianzhisheng.cn/
 * 微信公众号：zhisheng
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricEvent implements Serializable {

	/**
	 * Metric name
	 */
	private String name;

	/**
	 * Metric timestamp
	 */
	private Long timestamp;

	/**
	 * Metric fields
	 */
//	private Map<String, Object> fields;

	/**
	 * Metric tags
	 */
	private Map<String, String> tags;

	public void setName(String name) {
		this.name = name;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

//	public void setFields(Map<String, Object> fields) {
//		this.fields = fields;
//	}

	public void setTags(Map<String, String> tags) {
		this.tags = tags;
	}
}
