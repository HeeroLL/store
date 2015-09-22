package com.zebone.dip.etl.vo;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@SuppressWarnings("serial")
public class Result implements Serializable{
    /*
	private long lines_input;
	private long lines_output;
	private long lines_read;
	private long lines_written;
	private long lines_updated;
	private long lines_rejected;
	private long lines_deleted;
	private long nr_errors;
	private long nr_files_retrieved;
	private long entry_nr;

	@XStreamConverter(BooleanConverter.class)
	private boolean result;
	private int exit_status;

	@XStreamConverter(BooleanConverter.class)
	private boolean is_stopped;
	private String log_channel_id;
	private String log_text;

	@XStreamAlias("result-files")
	private List<ResultFile> result_files;

	@XStreamAlias("result-rows")
	private String result_rows;

	public long getLines_input() {
		return this.lines_input;
	}

	public long getLines_output() {
		return this.lines_output;
	}

	public long getLines_read() {
		return this.lines_read;
	}

	public long getLines_written() {
		return this.lines_written;
	}

	public long getLines_updated() {
		return this.lines_updated;
	}

	public long getLines_rejected() {
		return this.lines_rejected;
	}

	public long getLines_deleted() {
		return this.lines_deleted;
	}

	public long getNr_errors() {
		return this.nr_errors;
	}

	public long getNr_files_retrieved() {
		return this.nr_files_retrieved;
	}

	public long getEntry_nr() {
		return this.entry_nr;
	}

	public boolean isResult() {
		return this.result;
	}

	public int getExit_status() {
		return this.exit_status;
	}

	public boolean isIs_stopped() {
		return this.is_stopped;
	}

	public String getLog_channel_id() {
		return this.log_channel_id;
	}

	public String getLog_text() {
		return this.log_text;
	}

	public List<ResultFile> getResult_files() {
		return this.result_files;
	}

	public String getResult_rows() {
		return this.result_rows;
	}

	public String toString() {
		return "Result \n[entry_nr=" + this.entry_nr + ", exit_status="
				+ this.exit_status + ", is_stopped=" + this.is_stopped
				+ ", lines_deleted=" + this.lines_deleted + ", lines_input="
				+ this.lines_input + ", lines_output=" + this.lines_output
				+ ", lines_read=" + this.lines_read + ", lines_rejected="
				+ this.lines_rejected + ", lines_updated=" + this.lines_updated
				+ ", lines_written=" + this.lines_written + ", log_channel_id="
				+ this.log_channel_id + ", log_text=" + this.log_text
				+ ", nr_errors=" + this.nr_errors + ", nr_files_retrieved="
				+ this.nr_files_retrieved + ", result=" + this.result
				+ ", result-files=" + this.result_files + ", result-rows="
				+ this.result_rows + "]\n";
	}
	*/
}