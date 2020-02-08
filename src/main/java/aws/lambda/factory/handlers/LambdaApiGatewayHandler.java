package aws.lambda.factory.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import aws.lambda.factory.types.EventKeyTypes.ApiGatewayKeyTpes;
import aws.lambda.factory.types.EventKeyTypes.ILambdaType;
import aws.lambda.factory.types.LambdaType;

public abstract class LambdaApiGatewayHandler<O> extends LambdaAbstractHandler<Map<String, Object>, O>{
	
	protected LambdaApiGatewayHandler(List<ILambdaType> keys) {
		super(LambdaType.API_GATEWAY,keys);
	}
	
	protected LambdaApiGatewayHandler() {
		super(LambdaType.API_GATEWAY,Arrays.asList(ApiGatewayKeyTpes.values()));
	}

	@Override
	protected O triggerFunction(Map<String, Object> input, LambdaLogger lOG) throws Exception {
		Map<String, Object> requestDetails = getRequestDetails(input, lOG);
		O result = onNewRequest(requestDetails,lOG);
		return result;
	}

	@Override
	public abstract O onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger);
	

}
