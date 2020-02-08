package aws.lambda.factory.handlers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

import aws.lambda.factory.constants.LambdaConstants;
import aws.lambda.factory.types.EventKeyTypes.ILambdaType;
import aws.lambda.factory.types.EventKeyTypes.SqsKeyType;
import aws.lambda.factory.types.LambdaType;
/**
 * 
 * @author asaf
 * @last_updated: 26/1/2020
 * 
 * @param <O>
 * 
 * SQS lambda abstract trigger handler
 */
public abstract class LambdaSqsHandler<O> extends LambdaAbstractHandler<Map<String, Object>, List<O>>{
	
	protected LambdaSqsHandler(List<ILambdaType> keyTypes) {
		super(LambdaType.SQS,keyTypes);
	}
	
	protected LambdaSqsHandler() {
		super(LambdaType.SQS,Arrays.asList(SqsKeyType.values()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected List<O> triggerFunction(Map<String, Object> input, LambdaLogger LOG) throws Exception {
		List<Map<String, Object>> records = (List<Map<String, Object>>) input.get(LambdaConstants.RECORDS);
		LOG.log("#triggerFunction# sqs records: "+records);
		List<O> results = new ArrayList<>();
		for(Map<String, Object> record : records) {
			LOG.log("#triggerFunction# sqs record: "+record);
			Map<String, Object> recordDetails = getRequestDetails(record, LOG);
			O result = handleRequest(recordDetails, LOG);
			LOG.log("#triggerFunction# result: "+result);
			results.add(result);
		}
		LOG.log("#triggerFunction# all results: "+results);
		return results;
	}
	
	protected abstract O handleRequest(Map<String, Object> requestDetails, LambdaLogger logger);

	@Override
	public List<O> onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger){
		logger.log("#onNewRequest# ERR: Not implemented");
		return null;
	}


}
