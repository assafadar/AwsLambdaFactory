package aws.lambda.factory.handlers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import aws.lambda.factory.types.EventKeyTypes.ILambdaType;
import aws.lambda.factory.types.LambdaType;
/**
 * 
 * @author asaf
 * @last_update: 26/1/2020
 *
 * @param <I>
 * @param <O>
 */
public abstract class LambdaAbstractHandler<I,O> implements RequestHandler<I, O>,ILambdaHandler<O>{
	/**
	 * The type of the event triggered the lambda function
	 */
	private LambdaType lambdaType;
	/**
	 * List of requested keys from a request.
	 */
	private List<ILambdaType> requestedKeys;
	
	protected LambdaAbstractHandler(LambdaType lambdaType,List<ILambdaType> keys) {
		this.lambdaType = lambdaType;
		this.requestedKeys = keys;
	}
	/**
	 * The initial method being called by AWS lambda API after an event or trigger has fired.
	 */
	@Override
	public O handleRequest(I input, Context context) {
		LambdaLogger LOG = context.getLogger();
		LOG.log("#handleRequest# function: "+context.getFunctionName()+" version: "+context.getFunctionVersion()
					+"called, request id: "+context.getAwsRequestId());
		LOG.log("#handleRequest# input: "+input);
		O o = null;
		try {
			o = triggerFunction(input,LOG);
			LOG.log("#handleRequest# result: "+o);
		}catch (Exception e) {
			LOG.log("#handleRequest# ERROR: request id: "+context.getAwsRequestId()+" event type: "+this.lambdaType+" "
					+ "	function: "+context.getFunctionName()+" version: "+context.getFunctionVersion()+" failed due to: "+e.getMessage());
		}
		return o;
	}
	
	protected Map<String, Object> getRequestDetails(Map<String, Object> input, LambdaLogger lOG){
		Map<String, Object> requestDetails = new HashMap<String, Object>();
		for(ILambdaType type : this.requestedKeys) {
			Object typeValue = input.get(type.toString());
			lOG.log("#getRequestDetails# key: "+type+" value: "+typeValue);
			requestDetails.put(type.toString(), typeValue);
		}
		lOG.log("#getRequestDetails# requested keys from request: "+requestDetails);
		return requestDetails;
	}
	
	protected abstract O triggerFunction(I input, LambdaLogger lOG) throws Exception;
	
	@Override
	public abstract O onNewRequest(Map<String, Object> requestDetails, LambdaLogger logger);
	
	public LambdaType geLambdaType() {
		return this.lambdaType;
	}
}
