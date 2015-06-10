package com.zebone.dip.etl.vo;
import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;


@SuppressWarnings("serial")
@XStreamAlias("stepstatus")
public class StepStatus implements Serializable{
	String stepname;
	int copy;
	long linesRead;
	long linesWritten;
	long linesInput;
	long linesOutput;
	long linesUpdated;
	long linesRejected;
	long errors;
	String statusDescription;
	double seconds;
	String speed;
	String priority;
	boolean stopped;
	boolean paused;
	
	public StepStatus() {
		super();
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public int getCopy() {
		return copy;
	}

	public void setCopy(int copy) {
		this.copy = copy;
	}

	public long getLinesRead() {
		return linesRead;
	}

	public void setLinesRead(long linesRead) {
		this.linesRead = linesRead;
	}

	public long getLinesWritten() {
		return linesWritten;
	}

	public void setLinesWritten(long linesWritten) {
		this.linesWritten = linesWritten;
	}

	public long getLinesInput() {
		return linesInput;
	}

	public void setLinesInput(long linesInput) {
		this.linesInput = linesInput;
	}

	public long getLinesOutput() {
		return linesOutput;
	}

	public void setLinesOutput(long linesOutput) {
		this.linesOutput = linesOutput;
	}

	public long getLinesUpdated() {
		return linesUpdated;
	}

	public void setLinesUpdated(long linesUpdated) {
		this.linesUpdated = linesUpdated;
	}

	public long getLinesRejected() {
		return linesRejected;
	}

	public void setLinesRejected(long linesRejected) {
		this.linesRejected = linesRejected;
	}

	public long getErrors() {
		return errors;
	}

	public void setErrors(long errors) {
		this.errors = errors;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public double getSeconds() {
		return seconds;
	}

	public void setSeconds(double seconds) {
		this.seconds = seconds;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public String toString() {
		return "StepStatus\n [copy=" + copy + ", errors=" + errors
				+ ", linesInput=" + linesInput + ", linesOutput=" + linesOutput
				+ ", linesRead=" + linesRead + ", linesRejected="
				+ linesRejected + ", linesUpdated=" + linesUpdated
				+ ", linesWritten=" + linesWritten + ", paused=" + paused
				+ ", priority=" + priority + ", seconds=" + seconds
				+ ", speed=" + speed + ", statusDescription="
				+ statusDescription + ", stepname=" + stepname + ", stopped="
				+ stopped + "]\n";
	}

	
}
