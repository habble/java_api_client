package it.habble.api.agent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

/**
 * It represents a staging.flows record
 * @author ccastelli 
 */
public class Flow {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(Flow.class);
	public final static String TO_BE_PROCESSED = "TO_BE_PROCESSED";
	public final static String DONE = "DONE"; 
	public final static String JSON_ERROR = "JSON_ERROR";
	
	/** id_communication integer NOT NULL */
	private Integer idCommunication;
	
	/** driver character varying, it could be "JSON" or "SMS" */
	private String driver;
	
	/** driver_version character varying, es "2.2.1B" must be in versions.code column */
	private String driverVersion;
	
	/** flow_type character varying, "CONNECTIONS", "MESSAGES", "CALLS", "ACTIVATION" */
	private FlowType flowType; 
	
	/** serial_device character varying, "222017403084401" or iPhone UUID */
	private String serialDevice;
	
	/** start_detection character varying, "2015-09-24 11:00:36" */
	private String startDetection;
	
	/** end_detection character varying, "2015-09-24 11:30:48" */
	private String endDetection;
	
	/** connection_id character varying */
	private String connectionId;
	
	/** message character varying, used in the past */
	@Deprecated private String message;
	
	/** original_message character varying, JSON string */
	private String originalMessage;
	
	/** errors character varying, JSON array of strings, es "[]" */
	private String errors;
	
	/** received timestamp without time zone */
	private Timestamp received;
	
	/** processing timestamp without time zone */
	private Timestamp processing;
	
	/** processing_state character varying */
	private String processingState;
	
	/** job_id integer, used in the past */
//	@Deprecated private Integer jobId;

	public Integer getIdCommunication() {
		return this.idCommunication;
	}

	public String getDriver() {
		return this.driver;
	}

	public String getDriverVersion() {
		return this.driverVersion;
	}

	public FlowType getFlowType() {
		return this.flowType;
	}

	public String getSerialDevice() {
		return this.serialDevice;
	}

	public String getStartDetection() {
		return this.startDetection;
	}

	public String getEndDetection() {
		return this.endDetection;
	}

	public String getConnectionId() {
		return this.connectionId;
	}

	public String getMessage() {
		return this.message;
	}

	public String getOriginalMessage() {
		return this.originalMessage;
	}

	public String getErrors() {
		return this.errors;
	}

	public Timestamp getReceived() {
		return this.received;
	}

	public Timestamp getProcessing() {
		return this.processing;
	}

	public String getProcessingState() {
		return this.processingState;
	}

	public void setIdCommunication(Integer idCommunication) {
		this.idCommunication = idCommunication;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}

	public void setFlowType(String flowType) {
		this.flowType = FlowType.getFlow(flowType);
	}

	public void setSerialDevice(String serialDevice) {
		this.serialDevice = serialDevice;
	}

	public void setStartDetection(String startDetection) {
		this.startDetection = startDetection;
	}

	public void setEndDetection(String endDetection) {
		this.endDetection = endDetection;
	}

	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public void setReceived(Timestamp received) {
		this.received = received;
	}

	public void setProcessing(Timestamp processing) {
		this.processing = processing;
	}

	public void setProcessingState(String processingState) {
		this.processingState = processingState;
	}
	
	public Boolean isProcessable() {
		return this.processingState != null && 
				this.processingState.equals(Flow.TO_BE_PROCESSED);
	}
	
	public void done() {
		this.processingState = Flow.DONE;
	}
	
	public void done(String finalState) {
		this.processingState = finalState;
	}
	
	/**
	 * Gets a row from a {@link ResultSet} and creates a new Flow instance
	 * @param res 
	 * @return
	 * @throws SQLException 
	 */
	public Flow setFlow(ResultSet res) throws SQLException {
		Flow flow = new Flow();
		flow.setIdCommunication(res.getInt("id_communication"));
		flow.setDriver(res.getString("driver"));
		flow.setDriverVersion(res.getString("driver_version"));
		flow.setFlowType(res.getString("flow_type"));
		flow.setSerialDevice(res.getString("serial_device"));
		flow.setStartDetection(res.getString("start_detection"));
		flow.setEndDetection(res.getString("end_detection"));
		flow.setConnectionId(res.getString("connection_id"));
		flow.setMessage(res.getString("message"));
		flow.setOriginalMessage(res.getString("original_message"));
		flow.setErrors(res.getString("errors"));
		flow.setReceived(res.getTimestamp("received"));
		flow.setProcessing(res.getTimestamp("processing"));
		flow.setProcessingState(res.getString("processing_state"));
		
		return flow;
	}

}

