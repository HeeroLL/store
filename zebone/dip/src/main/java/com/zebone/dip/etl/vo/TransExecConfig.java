package com.zebone.dip.etl.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TransExecConfig  implements Serializable{
	private List<Parameter> parameters = new ArrayList();

	private List<Variable> variables = new ArrayList();
	private List<Argument> arguments;
	private String log_level;
	private Repository repository;

	public TransExecConfig() {
		if (this.arguments == null) {
			this.arguments = new ArrayList();
			addArgument("01");
			addArgument("02");
			addArgument("03");
			addArgument("04");
			addArgument("05");
			addArgument("06");
			addArgument("07");
			addArgument("08");
			addArgument("09");
			addArgument("10");
		}
	}

	public List<Parameter> getParameters() {
		return this.parameters;
	}

	public void setParameter(String name, String value) {
		Parameter parameter = findParameterByName(name);
		if (parameter == null) {
			Parameter param = new Parameter();
			param.setName(name);
			param.setValue(value);
			this.parameters.add(param);
		} else {
			parameter.setValue(value);
		}
	}

	private Parameter findParameterByName(String name) {
		for (Parameter parameter : this.parameters) {
			if (parameter.getName().equals(name)) {
				return parameter;
			}
		}
		return null;
	}

	public List<Variable> getVariables() {
		return this.variables;
	}

	public void setVariable(String name, String value) {
		Variable variable = findVariableByName(name);
		if (variable == null) {
			Variable v = new Variable();
			v.setName(name);
			v.setValue(value);
			this.variables.add(v);
		} else {
			variable.setValue(value);
		}
	}

	private Variable findVariableByName(String name) {
		for (Variable variable : this.variables) {
			if (variable.getName().equalsIgnoreCase(name)) {
				return variable;
			}
		}
		return null;
	}

	public List<Argument> getArguments() {
		return this.arguments;
	}

	public void setArgument(int i, String value) {
		if ((i < 1) || (i > 10)) {
			throw new IllegalArgumentException("参数名i只能取1到10十个整数");
		}
		Argument argument = findArgumentByName(new DecimalFormat("00")
				.format(i));

		argument.setValue(value);
	}

	private Argument findArgumentByName(String name) {
		for (Argument argument : this.arguments) {
			if (argument.getName().equals(name)) {
				return argument;
			}
		}
		return null;
	}

	private Argument addArgument(String name) {
		Argument argument = new Argument();
		argument.setName(name);
		argument.setValue("");
		this.arguments.add(argument);
		return argument;
	}

	public String getLog_level() {
		return this.log_level;
	}

	public void setLog_level(String logLevel) {
		this.log_level = logLevel;
	}

	public Repository getRepository() {
		return this.repository;
	}

	public void setRepository(Repository repository) {
		this.repository = repository;
	}
}