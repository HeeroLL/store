<%@ page language="java" contentType="text/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	out.println("{"+
			"total: 3,"+
			"data: ["+
				"{hzzsy:'001',xtymc:'002',mzkh:'003',zyh:'004',sfzh:'005',xm:'006',xb:'1',csrq:'007',lxdh:'008',jtdz:'009',ppd:'010',cz:''},"+
				"{hzzsy:'01',xtymc:'02',mzkh:'03',zyh:'04',sfzh:'05',xm:'06',xb:'2',csrq:'07',lxdh:'08',jtdz:'09',ppd:'10',cz:''},"+
				"{hzzsy:'0001',xtymc:'0002',mzkh:'0003',zyh:'0004',sfzh:'0005',xm:'0006',xb:'2',csrq:'0007',lxdh:'0008',jtdz:'0009',ppd:'10',cz:''}"+
			"]"+
		"}");
%>