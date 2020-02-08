package aws.lambda.factory.types;

public class EventKeyTypes {
	
	public interface ILambdaType{
		String toString();
	}
	
	public enum ApiGatewayKeyTpes implements ILambdaType{
		resource("resource"),
		path("path"),
		http_method("httpMethod"),
		headers("headers"),
		multi_value_headers("multiValueHeaders"),
		query_string_parameters("queryStringParameters"),
		multi_value_query_string_parameters("multiValueQueryStringParameters"),
		path_parameters("pathParameters"),
		stage_variables("stageVariables"),
		request_context("requestContext"),
		body("body"),
		is_base_64_encoded("isBase64Encoded")
		;
		private String type;
		ApiGatewayKeyTpes(String type) {
			this.type = type;
		}
		public String toString() {
			return this.type;
		}
	}
	
	public enum SqsKeyType implements ILambdaType{
		MESSAGE_ID("messageId"),
		RECEIPT_HANDLER("receiptHandle"),
		BODY("body"),
		ATTRIBUTES("attributes"),
		MESSAGE_ATTRIBUTES("messageAttributes"),
		MD5_OF_BODY("md5OfBody"),
		EVENT_SOURCE("eventSource"),
		EVENT_SOURCE_ARN("eventSourceARN"),
		REGION("awsRegion")
		;
		private String type;
		SqsKeyType(String type){
			this.type = type;
		}
		public String toString() {
			return this.type;
		}
		
	}
	
	public enum CloudwatchEventTypes implements ILambdaType{
		VERSION("version"),
		ID("id"),
		DETAIL_TYPE("detail-type"),
		SOURCE("source"),
		ACCOUNT("account"),
		TIME("time"),
		REGION("region"),
		RESOURCES("resources"),
		DETAIL("detail")
		;
		private String type;
		CloudwatchEventTypes(String type){
			this.type = type;
		}
		public String toString() {
			return this.type;
		}
	}
	
	public enum DynamoDBEventTypes implements ILambdaType{
		EVENT_ID("eventID"),
		EVENT_NAME("eventName"),
		EVENT_VERSION("eventVersion"),
		EVENT_SOURCE("eventSource"),
		REGION("awsRegion"),
		DYNAMO_DB("dynamodb"),
		EVENT_SOURCE_ARN("eventSourceARN");		;
		private String type;
		DynamoDBEventTypes(String type){
			this.type = type;
		}
		public String toString() {
			return this.type;
		}
	}
	
}
