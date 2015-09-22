package com.zebone;


public class TIETLServerClient {
/*
	private XStream xstream = null;

	public TIETLServerClient() {
		NoNameCoder nameCoder = new NoNameCoder();
		xstream = new XStream(new DomDriver("UTF-8", nameCoder));
		xstream.processAnnotations(WebResult.class);
		xstream.processAnnotations(TransStatus.class);
		xstream.processAnnotations(StepStatus.class);
		xstream.processAnnotations(Result.class);
		xstream.processAnnotations(TransConfig.class);
		// xstream.processAnnotations(TransRef.class);
		xstream.processAnnotations(TransExecConfig.class);
		xstream.processAnnotations(Repository.class);
	}

	private TransStatus getTransStatus(String transName, String id) {
		TransStatus tranStatus = null;

		SimpleHTTPClient client = new SimpleHTTPClient("localhost", 8090);
		try {
			String serivce = "/tietl/transStatus" + "/" + "?xml=Y" + "&name="
					+ URLEncoder.encode(transName, "UTF-8") + "&id=" + id;
			String xml = client.executeHTTPGet(serivce);
			System.out.println("xml: " + xml);

			tranStatus = (TransStatus) xstream.fromXML(xml);

			String gzippedLogString = tranStatus.getLogging_string();
			String logString = TIETLServerUtil.ungzipLogString(gzippedLogString);
			tranStatus.setLogging_string(logString);
			System.out.println(logString);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tranStatus;
	}

	public static void main(String args[]) {
		TIETLServerClient client = new TIETLServerClient();

		// TransConfig transConfig = createTransConfig();
		// WebResult webResult = client.deployTrans(transConfig);
		// System.out.println("webResult: " + webResult);
        //多多
		client.getTransStatus("��ұ�ͬ��","40601ee1-08ac-43a2-9867-90ca52cb6b11");
	}


*/
}
