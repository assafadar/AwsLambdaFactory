package aws.lambda.factory.handlers;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public interface ILambdaHandler<O> {
	 O onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger);
}
