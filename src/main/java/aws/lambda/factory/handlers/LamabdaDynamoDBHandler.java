package aws.lambda.factory.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import aws.lambda.factory.constants.LambdaConstants;
import aws.lambda.factory.types.EventKeyTypes.DynamoDBEventTypes;
import aws.lambda.factory.types.EventKeyTypes.ILambdaType;
import aws.lambda.factory.types.LambdaType;

public abstract class LamabdaDynamoDBHandler<O> extends LambdaAbstractHandler<Map<String, Object>, List<O>>{
	
	public LamabdaDynamoDBHandler(List<ILambdaType> types) {
		super(LambdaType.DYNAMO_DB,types);
	}
	
	public LamabdaDynamoDBHandler() {
		super(LambdaType.DYNAMO_DB,Arrays.asList(DynamoDBEventTypes.values()));
	}

	@Override
	protected List<O> triggerFunction(Map<String, Object> input, LambdaLogger lOG) throws Exception {
		List<Map<String,Object>> records = (List<Map<String,Object>>) input.get(LambdaConstants.RECORDS);
		lOG.log("#triggerFunction# dynamodb records: "+records);
		List<O> results = new ArrayList<>();
		for(Map<String, Object> record : records) {
			lOG.log("#triggerFunction# dynamodb record: "+record);
			Map<String, Object> recordDetails = getRequestDetails(input, lOG);
			O result = handleRequest(recordDetails, lOG);
			lOG.log("#triggerFunction# result: "+result);
			results.add(result);
		}
		lOG.log("#triggerFunction# all results: "+results);
		return results;
	}

	protected abstract O handleRequest(Map<String, Object> recordDetails, LambdaLogger lOG);

	@Override
	public List<O> onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger) {
		logger.log("#onNewRequest# ERR not implemented");
		return null;
	}

}
