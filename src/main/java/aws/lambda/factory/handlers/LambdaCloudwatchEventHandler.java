package aws.lambda.factory.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import aws.lambda.factory.types.EventKeyTypes.CloudwatchEventTypes;
import aws.lambda.factory.types.EventKeyTypes.ILambdaType;
import aws.lambda.factory.types.LambdaType;

public abstract class LambdaCloudwatchEventHandler<O> extends LambdaAbstractHandler<Map<String, Object>, O> {

	protected LambdaCloudwatchEventHandler(List<ILambdaType> types) {
		super(LambdaType.CLOUD_WATCH_EVENTS,types);
	}
	
	protected LambdaCloudwatchEventHandler() {
		super(LambdaType.CLOUD_WATCH_EVENTS,Arrays.asList(CloudwatchEventTypes.values()));
	}

	@Override
	protected O triggerFunction(Map<String, Object> input, LambdaLogger lOG) throws Exception {
		lOG.log("#triggerFunction# new cloudwatch event: "+input);
		Map<String, Object> eventDetails = 
				getRequestDetails(input, lOG);
		O result = onNewRequest(eventDetails, lOG);
		return result;
	}

	@Override
	public abstract O onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger);


}
